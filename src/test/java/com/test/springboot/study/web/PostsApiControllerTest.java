package com.test.springboot.study.web;

/*
2-4 PostApiControllerTest
 */

import com.test.springboot.study.domain.posts.Posts;
import com.test.springboot.study.domain.posts.PostsRepository;
import com.test.springboot.study.web.dto.PostsSaveRequestDto;
import com.test.springboot.study.web.dto.PostsUpdateRequestDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/*
2-4 미리 설명을 써 놓습니다.
    HelloControllerTest에서는 @WebMvcTest를 사용했는데
    @WebMvcTest의 경우 JPA 기능이 작동하지 않기 때문에
    Controller 등 외부 연동과 관련된 부분만 활성화뇐다.
    따라서, 지금처럼 JPA 기능까지 한번에 테스트 할 때는
    @SpringBootTest와 TestRestTemplate 객체 사용한다.
    현재 코드에서 postsRepository.findAll()을 통해
    모든 데이터는 List Collector에 넣는 것을 확인할 수 있다.
    맨 첫번째 데이터는 지금 넣은 데이터이기 때문에
    지금 막 넣은 데이터와 첫번째 데이터가 같은지 확인
    TODO
    수정/조회
    dto/PostsSaveRequestDto
    dto/PostsUpdateRequestDto.java
 */

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostsApiControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private PostsRepository postsRepository;

    @AfterEach
    public void tearDown() throws Exception{
        postsRepository.deleteAll();
    }

    @Test
    public void PostsCheckRegister() throws Exception {
        String title = "test title";
        String content = "test content";

        PostsSaveRequestDto requestDto = PostsSaveRequestDto
                .builder()
                .title(title)
                .content(content)
                .author("tester")
                .build();

        // 접속 URL
        // http://localhost:12345/api/v1/posts
        // 단위 테스트 할때는 원 서비스에 영향을 미치지 않기 위해 랜덤 포트
        String url = "http://localhost:" + port + "/api/v1/posts";
        ResponseEntity<Long> responseEntity = restTemplate
                .postForEntity(url, requestDto, Long.class);

        // Request에 대한 Response 체크
        assertThat(responseEntity.getStatusCode()).isEqualByComparingTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        // Save 후에 모든 데이터를 가져온다. Select *
        // B C D -- A 추가 -- A(0) B C D
        List<Posts> all = postsRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(title);
        assertThat(all.get(0).getContent()).isEqualTo(content);
    }

    /*
        2-9 단위 테스트에 update기능 추가
     */

    @Test
    public void postsCheckUpdate() throws Exception {
        Posts savedPosts = postsRepository.save(Posts.
                builder()
                .title("title")
                .content("content")
                .author("author")
                .build());
        Long updateId = savedPosts.getId();
        String expectedTitle = "title";
        String expectedContent = "content";

        PostsUpdateRequestDto requestDto = PostsUpdateRequestDto
                .builder()
                .title(expectedTitle)
                .content(expectedContent)
                .build();
        // http://localhost:8080/api/v1/posts/아이디
        // Update : PUT Method
        //          수신 : PutMapping
        String url = "http://localhost:" + port + "/api/v1/posts/" + updateId;
        HttpEntity<PostsUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);
        /*
        ResponseEntity<Long> responseEntity = restTemplate.exchange(
                                                            url,
                                                            HttpMethod.PUT,
                                                            requestEntity,
                                                            Long.class);
        */
        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long.class);

        assertThat(responseEntity.getStatusCode()).isEqualByComparingTo(HttpStatus.OK);

        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Posts> all = postsRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(expectedTitle);
        assertThat(all.get(0).getContent()).isEqualTo(expectedContent);

    }

}