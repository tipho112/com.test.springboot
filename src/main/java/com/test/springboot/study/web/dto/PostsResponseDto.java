package com.test.springboot.study.web.dto;

import com.test.springboot.study.domain.posts.Posts;
import lombok.Getter;

/*
2-6. PostsResponseDto
    응답으로 Posts 객체를 받아서 응답 객체 만들기
    나중에 추가 예정
    id, time, update time
    게시판 제목, 작성자, 내용 이중에서 수정 제목, 내용만 가능하고
    작성자는 변경 불가능한 구조..
    제목, 내용을 파라미터로 받아서 수정하는 메소드.
    현재는 save()만
    update() 기능을 추가..
    Posts.java
 */
@Getter
public class PostsResponseDto {
    private Long id;
    private String title;
    private String content;
    private String author;

    public PostsResponseDto(Posts entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
    }
}