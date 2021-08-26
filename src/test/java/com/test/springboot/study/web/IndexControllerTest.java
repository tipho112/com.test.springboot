package com.test.springboot.study.web;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.assertj.core.api.Assertions.assertThat;

/*  3-1
    index.mustache 의 body부분의 텍스트를 가져오는지 확인하는 절차

 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class IndexControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void loadMappingPage() {
        String body = this.restTemplate.getForObject("/", String.class);
        assertThat(body).contains("bootstrap");
    }
}
