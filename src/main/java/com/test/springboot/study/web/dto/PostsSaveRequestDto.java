package com.test.springboot.study.web.dto;

import com.test.springboot.study.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/*
    1-15.
    DTO -> Controller -> Service 형태로 프로그램의 구조를 잡는 순서 1

    id :________________
    pw :________________ Login
    select * from users where id='ID' and pw='PW'
    id : ' or 2>1 --
    select * from users where id='' or 2>1 -- ' and pw ='PW';
    사용자 입력값 검사
        html5 : pattern=''
    name : ________________ 홍길동, 홍吉동
    for( lenth(name) ) {
        if(name[i]>'a' || name[i]<='z' , #$%@...0,1,,9)
    }

 */
@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {

    private String title;
    private String content;
    private String author;

    @Builder
    public PostsSaveRequestDto(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    // toEntry() -> toEntity()
    public Posts toEntity() {
        return Posts.builder().title(title).content(content).author(author).build();
    }
}