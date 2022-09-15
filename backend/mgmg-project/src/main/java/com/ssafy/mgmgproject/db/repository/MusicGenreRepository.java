package com.ssafy.mgmgproject.db.repository;

import com.ssafy.mgmgproject.db.entity.MusicGenre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MusicGenreRepository extends JpaRepository<MusicGenre, Long> {
}
