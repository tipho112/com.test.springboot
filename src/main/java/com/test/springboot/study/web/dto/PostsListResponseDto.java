package com.test.springboot.study.web.dto;

import com.test.springboot.study.domain.posts.Posts;
import lombok.Getter;

import java.time.LocalDateTime;

/*
3-5 PostsListResponseDto
    글 목록보기를 위한 List에 들어갈 객체를 만들어준다.
    다 만든 다음에 List에 넣는 작업을 해준다.
    Posts 객체를 DTO로 변환해주는 작업만 현재 수행하고있다.
    service/PostsService
 */
@Getter
public class PostsListResponseDto {
    private Long id;
    private String title;
    private String author;
    private LocalDateTime modifiedDate;

    public PostsListResponseDto(Posts entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.author = entity.getAuthor();
        this.modifiedDate = entity.getModifiedDate();
    }
}