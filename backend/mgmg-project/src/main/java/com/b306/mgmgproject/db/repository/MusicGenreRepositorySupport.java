package com.b306.mgmgproject.db.repository;

import com.b306.mgmgproject.db.entity.User;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.b306.mgmgproject.db.entity.QMusicGenre;
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

