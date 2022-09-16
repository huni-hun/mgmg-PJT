package com.ssafy.mgmgproject.db.repository;

import com.ssafy.mgmgproject.api.response.DiaryListMapping;
import com.ssafy.mgmgproject.db.entity.Diary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface DiaryRepository extends JpaRepository<Diary, Long> {

    Optional<Diary> findByDiaryNo(Long diaryNo);

    @Transactional
    Optional<Diary> deleteByDiaryNo(Long diaryNo);

    List<DiaryListMapping> findByUser_UserNoAndDiaryDateStartsWith(Long userNo, String date);
}

