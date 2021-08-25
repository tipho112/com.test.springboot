package com.test.springboot.study;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


// 1-4. 패키지 생성
/*
    build.gradle 파일이 변경되면 Sync를 해준다
    @SpringBootApplication : SpringBoot의 시작
*/
// Auto import : Alt + Enter
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}