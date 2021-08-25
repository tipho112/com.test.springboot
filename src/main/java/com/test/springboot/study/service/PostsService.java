package com.test.springboot.study.service;

import com.test.springboot.study.domain.posts.PostsRepository;
import com.test.springboot.study.web.dto.PostsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/*
    2-2. PostsService를 추가

    Dto -> Controller -> Service -> Repository save()
 */

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }
}