package com.ssafy.mgmgproject.db.repository;

import com.ssafy.mgmgproject.api.response.DiaryListMapping;
import com.ssafy.mgmgproject.db.entity.Diary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DiaryRepository extends JpaRepository<Diary, Long> {
    List<DiaryListMapping> findByUser_UserNoAndDiaryDateContaining(Long userNo, String date);
}

