package com.ssafy.mgmgproject.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class StatisticsEmotionDto {
    private String emotion;
    private long percent;
}
