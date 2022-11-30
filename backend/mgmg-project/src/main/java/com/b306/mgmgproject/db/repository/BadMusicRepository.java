package com.b306.mgmgproject.db.repository;

import com.b306.mgmgproject.db.entity.BadMusic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BadMusicRepository extends JpaRepository<BadMusic, Long> {

    Optional<BadMusic> findByUser_UserNoAndMusic_MusicNo(Long userNo, Long musicNo);
    
}

