package com.test.springboot.study.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

/*
1-11 . PostsRepository Interface를 생성
 */

public interface PostsRepository extends JpaRepository<Posts, Long> {

}