package com.example.demo.web;

import static org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.document;
import static org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.documentationConfiguration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

/**
 * restdocs test for UserController with configuration done as documented in the Spring REST Docs documentation,
 * except WebApplicationContext is replaced by ApplicationContext: passes
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(RestDocumentationExtension.class)
public class UserControllerWithApplicationContextDocTest {

    private WebTestClient webTestClient;

    @BeforeEach
    public void setUp(ApplicationContext applicationContext,
                      RestDocumentationContextProvider restDocumentation) {
        this.webTestClient = WebTestClient.bindToApplicationContext(applicationContext)
                                          .configureClient()
                                          .filter(documentationConfiguration(restDocumentation))
                                          .build();
    }

    @Test
    void shouldGetUser() {
        webTestClient.get()
                     .uri("/users/{id}", 42L)
                     .exchange()
                     .expectStatus().isOk()
                     .expectBody()
                     .consumeWith(document("users/get3"));
    }
}
