package com.ssafy.mgmgproject.api.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.ssafy.mgmgproject.api.request.DiaryRequest;
import com.ssafy.mgmgproject.api.request.SearchItemRequest;
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

import java.text.DateFormat;
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

    @Autowired
    BadMusicRepository badMusicRepository;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Autowired
    AmazonS3 amazonS3;

    @Override
    public Diary writeDiary(Long userNo, MultipartFile multipartFile, DiaryRequest diaryRequest) {
        User user = userRepository.findByUserNo(userNo).orElse(null);
        Music music = musicRepository.findByMusicNo(diaryRequest.getMusicNo()).orElse(null);
        Gift gift = giftRepository.findByGiftNo(diaryRequest.getGiftNo()).orElse(null);

        Diary alreadyDiary = diaryRepository.findByUser_UserNoAndDiaryDate(userNo, diaryRequest.getDiaryDate()).orElse(null);
        if(alreadyDiary != null) return null;

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
    public Diary updateDiary(Long userNo, Long diaryNo, MultipartFile multipartFile, DiaryUpdateRequest diaryUpdateRequest) {
        Diary diary = diaryRepository.findByUser_UserNoAndDiaryNo(userNo,diaryNo).orElse(null);
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

            if (multipartFile != null) {
                uploadImg(diary, multipartFile);
            } else if (diary.getDiaryImg() != null) {
                deleteS3Img(diary);
                diary.updateImg(null);
            }
        }
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
    public Diary getByDiaryNo(Long userNo, Long diaryNo) {
        return diaryRepository.findByUser_UserNoAndDiaryNo(userNo, diaryNo).orElse(null);
    }

    @Override
    @Transactional
    public int deleteDiary(Long userNo, Long diaryNo) {
        Diary diary;
        try {
            diary = diaryRepository.findByUser_UserNoAndDiaryNo(userNo, diaryNo).get();
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
    public String getUserInfo(Date birth, String gender, List<String> giftCategories, Long lowPrice, Long highPrice) {
        DateFormat dataFormat = new SimpleDateFormat("yyyy-mm-dd");
        String year = dataFormat.format(birth);

        Calendar now = Calendar.getInstance();
        Integer currentYear = now.get(Calendar.YEAR);

        Integer birthYear = Integer.parseInt(year.substring(0, 4));

        String age = (currentYear - birthYear + 1) + "";

        Character c = age.charAt(0);

        String generation = "";
        if(c.equals('1')){
            generation = "10대";
        }else if(c.equals('2')){
            generation = "20대";
        }else if(c.equals('3')){
            generation = "30대";
        }else if(c.equals('4')){
            generation = "40대";
        }else if(c.equals('5')){
            generation = "50대";
        }else if(c.equals('6')){
            generation = "60대";
        }

        String low = lowPrice + "원";
        String high = highPrice + "원";

        Random random = new Random();
        String randomCategory = giftCategories.get(random.nextInt(giftCategories.size()));

        String userInfo = generation + " " + gender + " " + low + "~" + high + " " + randomCategory;
        return userInfo;
    }

    @Override
    public Gift writeRecommendGift(SearchItemRequest searchItemRequest, Long diaryNo, User user) {
        Diary diary = diaryRepository.findByUser_UserNoAndDiaryNo(user.getUserNo(), diaryNo).orElse(null);
        if(diary == null) return null;

        openGift(diary, user);
        searchItemRequest.setTitle(searchItemRequest.getTitle().replace("<b>", ""));
        searchItemRequest.setTitle(searchItemRequest.getTitle().replace("</b>", ""));
        searchItemRequest.setTitle(searchItemRequest.getTitle().replace("&quot", ""));
        Gift gift = Gift.builder()
                .giftName(searchItemRequest.getTitle())
                .giftPrice(searchItemRequest.getLprice())
                .giftImg(searchItemRequest.getImage())
                .giftUrl(searchItemRequest.getLink())
                .build();
        diary.updateGift(gift);
        giftRepository.save(gift);
        return gift;
    }

    @Override
    @Transactional
    public void openGift(Diary diary, User user) {
            diary.openGift();
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

    @Override
    @Transactional
    public BadMusic addBadMusic(Long userNo, Long musicNo) {
        InterestMusic interestMusic = interestMusicRepository.findByUser_UserNoAndMusic_MusicNo(userNo,musicNo).orElse(null);
        if(interestMusic==null){
            User user = userRepository.findById(userNo).orElse(null);
            Music music = musicRepository.findById(musicNo).orElse(null);
            if(user!=null && music!=null){
                BadMusic badMusic = BadMusic.builder()
                        .user(user)
                        .music(music)
                        .build();
                badMusicRepository.save(badMusic);
                return badMusic;
            }
        }
        return null;
    }

    @Override
    @Transactional
    public int deleteBadMusic(Long userNo, Long musicNo){
        BadMusic badMusic = badMusicRepository.findByUser_UserNoAndMusic_MusicNo(userNo,musicNo).orElse(null);
        if(badMusic!=null){
            badMusicRepository.deleteById(badMusic.getBadMusicNo());
            return 1;
        }
        return 0;
    }

    @Override
    public String checkMusic(Long userNo, Long musicNo){
        InterestMusic interestMusic = interestMusicRepository.findByUser_UserNoAndMusic_MusicNo(userNo,musicNo).orElse(null);
        if(interestMusic!=null){
            return "good";
        }
        BadMusic badMusic = badMusicRepository.findByUser_UserNoAndMusic_MusicNo(userNo,musicNo).orElse(null);
        if(badMusic!=null){
            return "bad";
        }
        return "none";
    }

    @Override
    public String checkGift(Long userNo, Long giftNo){
        InterestGift interestGift = interestGiftRepository.findByUser_UserNoAndGift_GiftNo(userNo,giftNo).orElse(null);
        if(interestGift!=null){
            return "good";
        }
        return "none";
    }
}