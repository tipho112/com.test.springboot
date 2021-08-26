package com.test.springboot.study.web;
/*
2-1 PostsApiController
    http://localhost:8080/api/v1/posts
    Controller가 Service한테 save()를 요청하는 구조
    주고 받는 데이터 단위 RequestDto 형태
    PostMapping(Insert), GetMapping, DeleteMapping, PutMapping (Update)
    Dto -> Controller 저장 요청 --> Service 저장 수행
    com.test.springboot.study.service 패키지 만들고
    PostsService 객체를 만들 예정(현재 이 코드는 서비스가 없어서 에러)
 */

import com.test.springboot.study.service.PostsService;
import com.test.springboot.study.web.dto.PostsResponseDto;
import com.test.springboot.study.web.dto.PostsSaveRequestDto;
import com.test.springboot.study.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/*
2-3 단위테스트를 위해 PostsApiControllerTest
    현재 : main.java.com.test.springboot.study.web.PostsApiController
    테스   test.java.com.test.springboot.study.web.PostsApiControllerTest
 */

@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto) {
        return  postsService.save(requestDto);
    }


    /*
    2-9 Update : PutMapping
     */

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto) {
        System.out.println("+++++++++++++++++++++++++++++++ update PUT !!");
        return postsService.update(id, requestDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id) {
        System.out.println("+++++++++++++++++++++++++++++++ update GET !!");
        return postsService.findById(id);
    }
}