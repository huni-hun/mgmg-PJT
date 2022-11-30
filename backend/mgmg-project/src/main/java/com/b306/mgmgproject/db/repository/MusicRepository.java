package com.b306.mgmgproject.db.repository;

import com.b306.mgmgproject.db.entity.Music;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MusicRepository extends JpaRepository<Music, Long> {

    Optional<Music> findByMusicNo(Long musicNo);

}

