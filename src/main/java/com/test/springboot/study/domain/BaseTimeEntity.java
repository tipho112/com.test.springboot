package com.test.springboot.study.domain;


import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/*
2-11 JPA Auditing
    @MappedSuperclass
    JPA Entity Class들이  BaseTimeEntity를 상속하는 경우 필드(create..., modi..)도 칼럼으로 인식해라
    @EntityListeners
    BaseTimeEntity 클래스에 Auditing 기능을 포함해라.
    Posts : 현재 만든 BaseTimeEntity를 상속만 받으면,,
 */
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseTimeEntity {
    @CreatedDate
    private LocalDateTime   createDate;

    @LastModifiedDate
    private LocalDateTime   modifiedDate;

}