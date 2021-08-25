package com.test.springboot.study.web.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


/*
    1.7 lombok 설치후에 getter, constructor 자동 생성위해
    @Getter, @RequiredArgsConstructor를 추가
    @Getter
        필드의 get method를 자동 생성
    @RequiredArgsConstructor
        선언된 final field가 포함된 생성자 자동 생성
    단위테스트를 위해서 패키지 구조를 일치, 클래스이름 현재클래스Test
 */

@Getter
@RequiredArgsConstructor
public class HelloResponseDto {

    private final String name;
    private final int age;

}