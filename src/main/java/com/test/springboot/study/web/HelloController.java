package com.test.springboot.study.web;

import com.test.springboot.study.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/*
    http://localhost:8080/hello
    Method
        GET, POST, PUT, DELETE, OPTIONS
    @RestControll
        JSON(name : hong)으로 변환하는 컨트롤러
        Spring에서의 @ResponseBody에 해당
     @GetMapping / @PostMapping / @PutMapping / @DeleteMapping
       select        insert         update       delete
    HTTP Error Code(Status Code)
    Google : RFC HTTP , 404
    1xx : Trying
                A --------------------> B (100 Trying)
                A <-------------------- 180 Ringing
    2xx : OK
                A ---------------------->B
                A <----------200 --------
    3xx : Temporary Error, Redirection Error
                A----------------------->B 100
                A <------- 300 C -------
                A ---------------------------------------------------->C 100
                A <-------------------- 180 --------------------------
    4xx : Permanent Error, Client Error
                404 Not Found, 403 Forbidden, 405 Method Not Allowed
                a.jsp
                a.jxp
    5xx : Server Error
                501 GateWay Error, 504 Gateway Timeout
    6xx : Global Error
        Server, Client OK, Routing Table Error 등의 이유로 서버에 접근할 수 없는 경우
 */
/*
    1-7 롬복(Lombok)
        Getter, Setter, Default Constructor, toString등을 Annotation으로 자동 생성
        롬복 설치 (Ctrl + Shift + A)
            plugins -> Lombok 필요한 경우 설치
            File>Settings> Build, Excuting , Deployment > Compiler > Annotation Processors
            화면의 오른쪽에 Enable annotation processing을 체크 해준다.
 */

// http://localhost:8080/hello/dto
/*
    1-8 dto를 getter로 추가하는 방법
 http://localhost:8080/hello/dto?name=홍길동&age=99
 */

@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/hello/dto")
    public HelloResponseDto helloDto(@RequestParam("name") String name, @RequestParam("age") int age) {
        return new HelloResponseDto(name, age);
    }
}