package com.test.springboot.study.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/*
1-11 . PostsRepository Interface를 생성

3-5 DB에서 가져오는 순서를 프로그래머가 정하는 방법
    SELECT * FROM Posts as p ORDER BY p.id DESC
    SELECT long_name_field as x, id FROM Posts as p ORDER BY p.id DESC, x ASC
    Named SQL이라는 문법을 공부가 조금 필요
    JPQL이라고도 한다.
    주의할 점은 딱 하나..
    Posts 이름을 p로 alias시키면
    Select p 여야한다.
    TODO
    DB에서 데이터를 List형태로 찾아오는 일 까지 했으니까,
    이 데이터는 던져주어야한다.
    DTO 새로 추가해야 한다는 말 PostsListResponseDto.java 새로 만들어보자.
    web/PostsListResponseDto.java
 */

public interface PostsRepository extends JpaRepository<Posts, Long> {

    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    List<Posts> findAllDesc();
}