package com.ssafy.mgmgproject.api.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.ssafy.mgmgproject.api.request.DiaryRequest;
import com.ssafy.mgmgproject.api.request.DiaryUpdateRequest;
import com.ssafy.mgmgproject.api.response.DiaryListMapping;
import com.ssafy.mgmgproject.db.entity.*;
import com.ssafy.mgmgproject.db.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.ion.IonException;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.*;

@Service
public class DiaryServiceImpl implements DiaryService {

    @Autowired
    DiaryRepository diaryRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    MusicRepository musicRepository;

    @Autowired
    GiftRepository giftRepository;

    @Autowired
    InterestMusicRepository interestMusicRepository;

    @Autowired
    InterestGiftRepository interestGiftRepository;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Autowired
    AmazonS3 amazonS3;

    @Override
    public Diary writeDiary(String userId, MultipartFile multipartFile, DiaryRequest diaryRequest) {
        User user = userRepository.findByUserId(userId).orElse(null);
        Music music = musicRepository.findByMusicNo(diaryRequest.getMusicNo()).orElse(null);
        Gift gift = giftRepository.findByGiftNo(diaryRequest.getGiftNo()).orElse(null);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        DayOfWeek dayOfWeek = LocalDate.parse(formatter.format(diaryRequest.getDiaryDate())).getDayOfWeek();

        Diary diary = Diary.builder()
                .user(user)
                .diaryContent(diaryRequest.getDiaryContent())
                .diaryDate(diaryRequest.getDiaryDate())
                .day(dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.US))
                .weather(diaryRequest.getWeather())
                .diaryThema(diaryRequest.getDiaryThema())
                .emotion(diaryRequest.getEmotion())
                .music(music)
                .gift(gift)
                .openGift(false)
                .build();
        if (multipartFile != null) uploadImg(diary, multipartFile);
        diaryRepository.save(diary);
        return diary;
    }

    @Override
    @Transactional
    public Diary updateDiary(Long diaryNo, MultipartFile multipartFile, DiaryUpdateRequest diaryUpdateRequest) {
        Diary diary = diaryRepository.findByDiaryNo(diaryNo).orElse(null);
        Music music = musicRepository.findByMusicNo(diaryUpdateRequest.getMusicNo()).orElse(null);
        Gift gift = giftRepository.findByGiftNo(diaryUpdateRequest.getGiftNo()).orElse(null);
        if (diary.getGift() != gift) diary.closeGift();
        if (diary != null) {
            diary.updateDiary(
                    diaryUpdateRequest.getDiaryContent(),
                    diaryUpdateRequest.getWeather(),
                    diaryUpdateRequest.getDiaryThema(),
                    diaryUpdateRequest.getEmotion(),
                    music,
                    gift
            );
        }
        if (multipartFile != null) uploadImg(diary, multipartFile);
        diaryRepository.save(diary);
        return diary;
    }

    @Override
    public List<DiaryListMapping> getDiaryMonthList(Long userNo, String date) throws ParseException {
        Calendar cal = Calendar.getInstance();
        String year = date.split("-")[0];
        String month = date.split("-")[1];
        cal.set(Integer.parseInt(year),Integer.parseInt(month)-1,1);

        String startDateStr = year+"-"+month+"-01";
        String endDateStr = year+"-"+month+"-"+Integer.toString(cal.getActualMaximum(Calendar.DAY_OF_MONTH));

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = formatter.parse(startDateStr);
        Date endDate = formatter.parse(endDateStr);

        List<DiaryListMapping> diaries = diaryRepository.findByUser_UserNoAndDiaryDateBetween(userNo, startDate, endDate);
        if(diaries != null) return diaries;
        else return null;
    }

    @Override
    public Diary getByDiaryNo(Long diaryNo) {
        return diaryRepository.findByDiaryNo(diaryNo).orElse(null);
    }

    @Override
    @Transactional
    public int deleteDiary(Long diaryNo) {
        Diary diary;
        try {
            diary = diaryRepository.findByDiaryNo(diaryNo).get();
        } catch (Exception e) {
            return 0;
        }
        if (diary.getDiaryImg() != null) {
            deleteS3Img(diary);
        }
        diaryRepository.deleteByDiaryNo(diaryNo);
        return 1;
    }

    @Override
    public InterestMusic addInterestMusic(String userId, Long musicNo) {
        User user = userRepository.findByUserId(userId).orElse(null);
        Music music = musicRepository.findByMusicNo(musicNo).orElse(null);
        InterestMusic interestMusic = InterestMusic.builder()
                .user(user)
                .music(music)
                .build();
        interestMusicRepository.save(interestMusic);
        return interestMusic;
    }

    @Override
    public InterestGift addInterestGift(String userId, Long giftNo) {
        User user = userRepository.findByUserId(userId).orElse(null);
        Gift gift = giftRepository.findByGiftNo(giftNo).orElse(null);
        InterestGift interestGift = InterestGift.builder()
                .user(user)
                .gift(gift)
                .build();
        interestGiftRepository.save(interestGift);
        return interestGift;
    }

    @Override
    @Transactional
    public int openGift(Long diaryNo) {
        Diary diary = diaryRepository.findByDiaryNo(diaryNo).orElse(null);
        if (diary == null) return 0;
        else {
            diary.openGift();
            diaryRepository.save(diary);
            return 1;
        }
    }

    @Override
    public int uploadImg(Diary diary, MultipartFile multipartFile) {

        try {
            if (diary.getDiaryImg() != null) {
                deleteS3Img(diary);
            }
            String filename = fileNameFilter(multipartFile.getOriginalFilename());
            String s3FileName = UUID.randomUUID() + "-" + filename;

            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentLength(multipartFile.getInputStream().available());
            amazonS3.putObject(bucket, s3FileName, multipartFile.getInputStream(), objectMetadata);

            String fileUrl = amazonS3.getUrl(bucket, s3FileName).toString();
            diary.updateImg(fileUrl);
        } catch (Exception e) {
            return 0;
        }
        return 1;
    }

    public int deleteS3Img(Diary diary) {
        try {
            String filename = diary.getDiaryImg().substring(diary.getDiaryImg().lastIndexOf("/") + 1);
            filename = URLDecoder.decode(filename, "UTF-8");
            System.out.println(filename);
            amazonS3.deleteObject(bucket, filename);
        } catch (Exception e) {
            return 0;
        }
        return 1;
    }

    public String fileNameFilter(String filename) {
        return filename.replaceAll("[^a-zA-Z0-9가-힣_.]", "").replaceAll(" ", "");
    }

}