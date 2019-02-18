package com.example.demo.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

/**
 * Simple (non-restdocs) test for UserController: passes
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void shouldGetUser() {
        webTestClient.get()
                     .uri("/users/{id}", 42L)
                     .exchange()
                     .expectStatus().isOk()
                     .expectBody();
    }
}
