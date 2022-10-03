package com.ssafy.mgmgproject.db.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.mgmgproject.db.entity.QMusicGenre;
import com.ssafy.mgmgproject.db.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MusicGenreRepositorySupport {
    
    private final JPAQueryFactory jpaQueryFactory;

    QMusicGenre qMusicGenre = QMusicGenre.musicGenre;

    public void deleteByUser(User user) {
        jpaQueryFactory.delete(qMusicGenre).where(qMusicGenre.user.eq(user)).execute();
    }

}

