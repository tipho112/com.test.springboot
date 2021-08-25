package com.test.springboot.study.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/*
    2.5 PostsUpdateRequestDto
    update에서는 DB에 쿼리하는 기능이 없다.
    이유: JPA의 지속성 특성(지속성 컨텍스트) 때문이다.
    엔티티를 영구 저장하는 환경
    파일시스템과 비슷한 구조
    Response에서 생기는 Dto작업
    dto.PostsResponseDto
 */
@Getter
@NoArgsConstructor
public class PostsUpdateRequestDto {
    private String title;
    private String content;

    @Builder
    public PostsUpdateRequestDto(String title, String content) {
        this.title = title;
        this.content = content;
    }
}