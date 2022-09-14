package com.ssafy.mgmgproject.db.repository;

import com.ssafy.mgmgproject.db.entity.Gift;
import com.ssafy.mgmgproject.db.entity.InterestMusic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InterestMusicRepository extends JpaRepository<InterestMusic, Long> {
}

