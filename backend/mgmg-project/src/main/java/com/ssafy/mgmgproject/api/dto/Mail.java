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
    private String toAddress; // 받는 이메일 주소

    private String title; // 이메일 제목

    private String message; // 이메일 내용

    private String fromAddress; // 보내는 이메일 주소
}
