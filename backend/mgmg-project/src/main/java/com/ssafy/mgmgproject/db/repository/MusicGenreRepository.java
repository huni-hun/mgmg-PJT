package com.ssafy.mgmgproject.db.repository;

import com.ssafy.mgmgproject.db.entity.MusicGenre;
import com.ssafy.mgmgproject.db.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MusicGenreRepository extends JpaRepository<MusicGenre, Long> {
    List<MusicGenre> findByUser(User user);
}
