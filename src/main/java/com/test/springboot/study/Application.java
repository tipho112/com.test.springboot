package com.test.springboot.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

// 1-4. 패키지 만든다.
/*
@@ -13,8 +14,15 @@
        내장  WAS (Built-in)
    File > Settings > Editor > General > Code Completion > Match case 해제 되었는지 확인
    2-12 이 어플리케이션이 JPA Auditing 어노테이션을 활성화하라고 명령
    저장소와 관련해서, 생성, 수정하는 경우에 Auditing 이 동작
    동작을 검증하기 위해 저장소와 관련된 단위테스트
    PostsRepositoryTest 오디팅 추가
 */
// Auto import : Alt + Enter
@EnableJpaAuditing
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}