package com.ssafy.mgmgproject.db.repository;


import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.mgmgproject.api.dto.StatisticsEmotionDto;
import com.ssafy.mgmgproject.db.entity.QDiary;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Repository
@RequiredArgsConstructor
public class DiaryRepositorySupport {
    private final JPAQueryFactory jpaQueryFactory;

    QDiary qDiary = QDiary.diary;

    public List<StatisticsEmotionDto> findByUser_UserNoAndDiaryDateBetweenGroupByEmotionName(Long userNo, Date startDate, Date endDate) {

        NumberPath<Long> aliasQuantity = Expressions.numberPath(Long.class, "percent");
        final List<StatisticsEmotionDto> statisticsDtos = jpaQueryFactory
                .select(Projections.constructor(StatisticsEmotionDto.class, qDiary.emotion, qDiary.count().as(aliasQuantity)))
                .from(qDiary)
                .where(qDiary.user.userNo.eq(userNo).and(qDiary.diaryDate.between(startDate,endDate)))
                .groupBy(qDiary.emotion)
                .orderBy(aliasQuantity.desc())
                .fetch();
        return statisticsDtos;
    }

    public List<StatisticsEmotionDto> findAllByUser_UserNoAndDayGroupByEmotionName(Long userNo, String day) {

        NumberPath<Long> aliasQuantity = Expressions.numberPath(Long.class, "percent");
        final List<StatisticsEmotionDto> statisticsDtos = jpaQueryFactory
                .select(Projections.constructor(StatisticsEmotionDto.class, qDiary.emotion, qDiary.count().as(aliasQuantity)))
                .from(qDiary)
                .where(qDiary.user.userNo.eq(userNo).and(qDiary.day.eq(day)))
                .groupBy(qDiary.emotion)
                .orderBy(qDiary.emotion.asc())
                .fetch();
        return statisticsDtos;
    }

    public StatisticsEmotionDto findByUser_UserNoAndDayGroupByEmotionName(Long userNo, String day) {
        NumberPath<Long> aliasQuantity = Expressions.numberPath(Long.class, "percent");
        final List<StatisticsEmotionDto> statisticsDtos = jpaQueryFactory
                .select(Projections.constructor(StatisticsEmotionDto.class, qDiary.emotion, qDiary.count().as(aliasQuantity)))
                .from(qDiary)
                .where(qDiary.user.userNo.eq(userNo).and(qDiary.day.eq(day)))
                .groupBy(qDiary.emotion)
                .orderBy(aliasQuantity.desc())
                .fetch();
        if(statisticsDtos==null || statisticsDtos.size()==0){
            return null;
        }else{
            return statisticsDtos.get(0);
        }
    }

}

