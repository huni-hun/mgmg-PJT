package com.ssafy.mgmgproject.db.repository;


import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.mgmgproject.api.dto.StatisticsDto;
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

        NumberPath<Long> aliasQuantity = Expressions.numberPath(Long.class, "percent");
        final List<StatisticsDto> statisticsDtos = jpaQueryFactory
                .select(Projections.constructor(StatisticsDto.class, qDiary.emotion, qDiary.count().as(aliasQuantity)))
                .from(qDiary)
                .where(qDiary.user.userNo.eq(userNo).and(qDiary.diaryDate.between(startDate,endDate)))
                .groupBy(qDiary.emotion)
                .orderBy(aliasQuantity.desc())
                .fetch();
         return statisticsDtos;
    }

}

