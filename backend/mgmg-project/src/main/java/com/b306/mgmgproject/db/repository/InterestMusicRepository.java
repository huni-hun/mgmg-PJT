package com.b306.mgmgproject.db.repository;

import com.b306.mgmgproject.db.entity.InterestMusic;
import com.b306.mgmgproject.db.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InterestMusicRepository extends JpaRepository<InterestMusic, Long> {

    List<InterestMusic> findByUser(User user);

    void deleteByUserAndMusic_MusicNo(User user, long musicNo);

    Optional<InterestMusic> findByUser_UserNoAndMusic_MusicNo(Long userNo, Long musicNo);
    
}

