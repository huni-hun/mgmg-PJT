package com.ssafy.mgmgproject.db.repository;

import com.ssafy.mgmgproject.db.entity.InterestMusic;
import com.ssafy.mgmgproject.db.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InterestMusicRepository extends JpaRepository<InterestMusic, Long> {
    List<InterestMusic> findByUser(User user);
    void deleteByUserAndInterestMusicNo(User user, long musicNo);
    Optional<InterestMusic> findByUser_UserNoAndMusic_MusicNo(Long userNo, Long musicNo);
}

