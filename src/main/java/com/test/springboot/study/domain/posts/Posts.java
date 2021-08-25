package com.test.springboot.study.domain.posts;
/*
1-10 Posts라는 클래스 생성(일관된게 Post에 s를 붙인다)
    게시판 : 제목, 내용, 작성자 (날짜 등은 나중에 추가할 예정)
    id : 자동으로 결정되기 때문에 id항목은 별도로 필드로 두지 않는다.
    @Entity : JPA에서 필요한 Annotation
        테이블과 클래스를 매핑
        Posts.java ---> posts DB table 내부적으로 자동으로 만들어준다
        MyPost.java ---> my_post DB table을 내부적로 만들어준다.
    @Id : 데이터베이스의 키 값
    @GeneratedValue : 키 생성
 */

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Getter
@NoArgsConstructor
@Entity
public class Posts {
    @Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    private Long id;  // 글번호
    @Column(length = 500, nullable = false)
    private String title;
    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }
}