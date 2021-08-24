package com.test.springboot.study.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/*
    http://localhost:8080/hello

    Method
        GET, POST, PUT, DELETE, OPTIONS

    @RestController
        JSON(name : hong)으로 변환하는 컨트롤러
        Spring에서의 @ResponseBody에 해당
    @GetMapping / @PostMapping / @PutMapping / @DeleteMapping
       select       insert        update           delete

    HTTP Error Code(Status Code)
    Google : RFC HTTP, 404
    1xx : Trying message -> 접속 시도
            A --------------------------------> B (100 Trying)
            A <-------------------------------- 180 Ringing

    2xx : Ok message
            A --------------------------------> B
            A <----------- 200 ----------------

    3xx : Temporary Error, Redirection Error -> 일시적인 에러
            A --------------------------------> B 100
            A <----------- 300 C --------------
            A ----------------------------------------------------------------> C 100
            A <---------------------------- 180 -------------------------------

    4xx : Permanent Error, Client Error
            404 Not Found, 403 Forbidden, 405 Method Not Allowed
            a.jsp
            a.jxp

    5xx : Server Error
            501 GateWay Error, 504 GateWay Timeout

    6xx : Global Error
            Server, Client Ok / Routing Table Error 등의 이유로 서버에 접근할 수 없는 경우


 */

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
}