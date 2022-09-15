package com.ssafy.mgmgproject.db.repository;


import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.mgmgproject.api.dto.StatisticsDto;
import com.ssafy.mgmgproject.db.entity.Diary;
import com.ssafy.mgmgproject.db.entity.QDiary;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Repository
@RequiredArgsConstructor
public class DiaryRepositorySurport {
    private final JPAQueryFactory jpaQueryFactory;

    QDiary qDiary = QDiary.diary;

    public List<StatisticsDto> findByUser_UserNoAndDiaryDateBetweenGroupByEmotionName(Long userNo, Date startDate, Date endDate) {

        return jpaQueryFactory
                .select(Projections.fields(StatisticsDto.class,
                        qDiary.emotion, qDiary.count()))
                .from(qDiary)
                .where(qDiary.user.userNo.eq(userNo).and(qDiary.diaryDate.between(startDate,endDate)))
                .groupBy(qDiary.emotion)
                .orderBy(qDiary.count().desc())
                .fetch();
    }

}

