package com.test.springboot.study.service;

import com.test.springboot.study.domain.posts.Posts;
import com.test.springboot.study.domain.posts.PostsRepository;
import com.test.springboot.study.web.dto.PostsListResponseDto;
import com.test.springboot.study.web.dto.PostsResponseDto;
import com.test.springboot.study.web.dto.PostsSaveRequestDto;
import com.test.springboot.study.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

/*
    2-2. PostsService를 추가
    Dto -> Controller -> Service -> Repository save()
       2-8.
            해야하는 절차.
        update id O <------------------> save id X
        update 수행하기 전에 id를 가지고 원본데이터를 찾는 일이 수행해야 함.
 */
@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }
    // For Update
    // update posts set ..... where id='1';
    /*
    2-8. findById()는 아이디로 Repository를 찾아서 entity를 생성
    entity를 PostsResponseDto 객체를 반환
    save :  http://localhost:8080/api/v1/posts
    update : (id)
            http://localhost:8080/api/v1/posts/34
    GetMapping, PostMapping
    Insert : PostMapping
    Update : PutMapping
    SELECT * : GetMapping
    TODO:
        Service에 update 기능이 추가 되었으니까,
        단위 테스트에서 현재는 save()만 처리
        여기에 update()기능도 단위테스트 되어야 한다.
     */

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository
                .findById(id)
                .orElseThrow(()->new IllegalArgumentException("찾는 원본 게시글 없다. id =" + id));

        System.out.println("+++++++++++++++++++++++++++++++ SERVICE update() !!");

        posts.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository
                .findById(id)
                .orElseThrow(()->new IllegalArgumentException("찾는 원본 게시글 없다. id =" + id));
        return new PostsResponseDto(entity);
    }

    /*
3-6 List Collection  findAllDesc()순으로 채운다.
    .map(PostsListResponseDto::new) 람다식 표현은
    .map(posts->new PostsListResposeDto(posts)) 와 완전일치하는 문법
    import Transaction을 javax -> springframework으로 변경
    javax의 Transaction은 Option메소드를 허용하지 않는다.
    조회기능만 수행하기 때문에 성능향상을 위해서 변경해주는 것이 바람직하다.
 */
    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository
                .findAllDesc()
                .stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }

}