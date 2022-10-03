package com.ssafy.mgmgproject.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Mail {
    private String toAddress;
    private String title;
    private String message;
    private String fromAddress;
}
