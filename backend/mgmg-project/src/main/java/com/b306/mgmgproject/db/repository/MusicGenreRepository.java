package com.b306.mgmgproject.db.repository;

import com.b306.mgmgproject.db.entity.MusicGenre;
import com.b306.mgmgproject.db.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MusicGenreRepository extends JpaRepository<MusicGenre, Long> {

    List<MusicGenre> findByUser(User user);

}
