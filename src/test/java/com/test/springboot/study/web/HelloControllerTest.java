package com.test.springboot.study.web;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
/*
1.6 HelloController의 테스트모듈 만들때는 뒤에 Test라는 이름을 갖는다.
    반드시 위쪽(main)과 패키지 구조가 같다.
    @ExtendWidth : Juin4에서 @RunWith의 Junit5 버전
        Springboot 테스트와 JUnit 연결기능 수행
   @Autowired : 자동 주입, 스프링이 관리하는 Bean을 자동 주입
   private MockMvc
        웹 API 테스
        스프링 MVC 테스트의 시작점
        HTTP, GET, POST에 대한 API 테스트
   STATUS - OK : 200
   메소드 체이닝(Method Chaining) : mvc.perform().andExpect(status)
                                  mvc.perform().andExpect(content) 을 단순화
        mvc.perform().andExpect(status).andExpect(content);
  단위테스트는 동작이 정상적인지만 확인
  실제 서비스를 돌리려면 Application을 실행 후
  http://localhost:8080/hello
  화면에 hello 글씨가 뜨면 일단 성공
 */
@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = HelloController.class)
public class HelloControllerTest {
    @Autowired
    private MockMvc mvc;
    @Test
    public void helloReturnTest() throws Exception {
        String hello = "hello";
        mvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string(hello));

    }

    /*
        jsonPath()
            JSON 응답값을 필드별로 검증하는 메소드
            $단위로 변수를 구분하고, 앞에 Dot(.)을 써준다. 예: $.name
            Application 을 실행(전체실행)
            주소창에
            http://localhost:8080/hello/dto?name=이순신&age=123
     */

    @Test
    public void helloDtoTest() throws Exception {
        String name = "HongKilDong";
        int age = 12;
        mvc.perform(get("/hello/dto")
                        .param("name", name)
                        .param("age", String.valueOf(age)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.age", is(age)));

    }
}