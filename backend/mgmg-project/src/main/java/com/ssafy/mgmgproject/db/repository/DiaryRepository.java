package com.ssafy.mgmgproject.db.repository;

import com.ssafy.mgmgproject.api.response.DiaryListMapping;
import com.ssafy.mgmgproject.db.entity.Diary;
import com.ssafy.mgmgproject.db.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface DiaryRepository extends JpaRepository<Diary, Long> {

    Optional<Diary> findByDiaryNo(Long diaryNo);
    List<DiaryListMapping> findByUser_UserNoAndDiaryDateContaining(Long userNo, String date);
}

