package com.bcnc.bcncalbum.controller;

import com.bcnc.bcncalbum.model.response.UserResponseModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatusCode;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void getUserWithPostAndAlbums_ReturnsUser() {
        int userId = 1;
        webTestClient.get()
                .uri("/api/users/{userId}", userId)
                .exchange()
                .expectStatus().isOk()
                .expectBody(UserResponseModel.class)
                .consumeWith(response -> {
                    Assertions.assertNotNull(response.getResponseBody());
                    Assertions.assertEquals("Bret",response.getResponseBody().getUsername());
                    Assertions.assertEquals(10,response.getResponseBody().getAlbums().size());
                    Assertions.assertEquals(10,response.getResponseBody().getPosts().size());
                });
    }

    @Test
    public void getUserWithPostAndAlbums_NoUser() {
        int userId = 999999;
        webTestClient.get()
                .uri("/api/users/{userId}", userId)
                .exchange()
                .expectStatus().is4xxClientError();
    }
}