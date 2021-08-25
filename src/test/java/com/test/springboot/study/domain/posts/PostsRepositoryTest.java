package com.test.springboot.study.domain.posts;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
/*
1-12
    @AfterEach
    각 단위 테스트가 끝날때 마다 수행하는 작업 정의 <--> @BeforeEach
    JUnit4 @After에 해당 녀석
    다음 테스트가 다른 데이터를 침범하는 것을 방지
    posts : Insert / Update 구분하는 방법
    Insert : id가 없으면 글 등록
    Update : id가 있으면 글 수정으로 판단
 1-13
    디비 쿼리가 제대로 수행되는지 확인할 방법이 없다.
    이를 해결하는 방법은, property 설정으로 가능한데
    src/main/resources/application.properties 파일에 속성을 설정한다.
    cf. CRUL
    curl http://www.daum.net
    apt-get docker.io
    curl docker.io | grep ... sh
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PostsRepositoryTest {
    @Autowired
    PostsRepository postsRepository;
    @AfterEach
    public void cleanup() {
        postsRepository.deleteAll();
    }
    @Test
    public void saveAndLoad() {
        String title = "테스트 제목";
        String content = "테스트 본문";
        postsRepository.save(
                Posts
                        .builder()
                        .title(title)
                        .content(content)
                        .author("user@test.com")
                        .build());
        // insert --> List
        /*
            C
            D
            E  <--- A를 추가
            A(0)
            C
            D
            E
            create table posts (
                id  long auto_increment,
                title char(500),
                content text,
                author char(255),
                primary key(id)
            );
            이렇게 MySQL, MariaDB, Oracle의 속성에 맞게 처리하도록 변경하고 싶다.. ===> properties에서
         */
        List<Posts> postsList = postsRepository.findAll();
        // SELECT * 수행후 목록 결과를 List Collection에 넣는 작업

        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }
}