package com.bcnc.bcncalbum.service.impl;

import com.bcnc.bcncalbum.mapper.UserMapper;
import com.bcnc.bcncalbum.mapper.impl.UserMapperImpl;
import com.bcnc.bcncalbum.model.response.UserResponseModel;
import com.bcnc.bcncalbum.model.restclient.AlbumRestClientModel;
import com.bcnc.bcncalbum.model.restclient.PostRestClientModel;
import com.bcnc.bcncalbum.model.restclient.UserRestClientModel;
import com.bcnc.bcncalbum.restclient.AlbumRestClient;
import com.bcnc.bcncalbum.restclient.PostRestClient;
import com.bcnc.bcncalbum.restclient.UserRestClient;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
public class UserRestClientModelServiceImplTest {

    @MockBean
    private UserRestClient userRestClient;

    @MockBean
    private PostRestClient postRestClient;

    @MockBean
    private AlbumRestClient albumRestClient;
    @SpyBean
    private UserMapper userMapper;
    @Autowired
    private UserServiceImpl userService;



    @Test
    void getUserWithAlbumAndPostsTest() {
        int userId = 1;
        UserRestClientModel userRestClientModel = UserRestClientModel.builder().id(userId).build();
        List<PostRestClientModel> postRestClientModel = List.of(PostRestClientModel.builder()
                        .userId(userId)
                        .id(1)
                        .title("Post Title")
                        .body("Post Body")
                .build());
        List<AlbumRestClientModel> albumRestClientModel = List.of(AlbumRestClientModel.builder()
                        .userId(userId)
                        .id(1)
                        .title("Album Title")
                        .build());
        UserResponseModel expectedUserResponseModel = userMapper.toUserResponseModel(userRestClientModel);
        expectedUserResponseModel.setPosts(userMapper.toPostResponseModel(postRestClientModel));
        expectedUserResponseModel.setAlbums(userMapper.toAlbumResponseModel(albumRestClientModel));

        when(userRestClient.getUserById(userId))
                .thenReturn(Mono.just(userRestClientModel));
        when(postRestClient.getPostByUserId(userId))
                .thenReturn(Mono.just(postRestClientModel));
        when(albumRestClient.getAlbumByUserId(userId))
                .thenReturn(Mono.just(albumRestClientModel));


        Mono<UserResponseModel> result = userService.getUserWithAlbumAndPosts(userId);

        StepVerifier.create(result)
                .expectNext(expectedUserResponseModel)
                .verifyComplete();
    }

    @Test
    void getUserWithAlbumAndPosts_userFetchFails() {
        int userId = 1;

        when(userRestClient.getUserById(userId)).thenReturn(Mono.error(new Exception("User fetch failed")));
        Mono<UserResponseModel> result = userService.getUserWithAlbumAndPosts(userId);

        StepVerifier.create(result)
                .expectErrorMessage("User fetch failed")
                .verify();
    }

    @Test
    void getUserWithAlbumAndPosts_postNull() {
        int userId = 1;
        UserRestClientModel user = new UserRestClientModel();
        List<AlbumRestClientModel> albumRestClientModel = List.of(AlbumRestClientModel.builder()
                .userId(userId)
                .id(1)
                .title("Album Title")
                .build());

        when(userRestClient.getUserById(userId)).thenReturn(Mono.just(user));
        when(postRestClient.getPostByUserId(userId)).thenReturn(Mono.error(new Exception("Post fetch failed")));
        when(albumRestClient.getAlbumByUserId(userId))
                .thenReturn(Mono.just(albumRestClientModel));
        UserResponseModel expectedUserResponseModel = userMapper.toUserResponseModel(user);
        expectedUserResponseModel.setAlbums(userMapper.toAlbumResponseModel(albumRestClientModel));
        Mono<UserResponseModel> result = userService.getUserWithAlbumAndPosts(userId);

        StepVerifier.create(result)
                .expectErrorMessage("Post fetch failed")
                .verify();
    }

    @Test
    void getUserWithAlbumAndPosts_albumFails() {
        int userId = 1;
        UserRestClientModel user = new UserRestClientModel();

        List<PostRestClientModel> postRestClientModel = List.of(PostRestClientModel.builder()
                .userId(userId)
                .id(1)
                .title("Post Title")
                .body("Post Body")
                .build());

        when(userRestClient.getUserById(userId)).thenReturn(Mono.just(user));
        when(albumRestClient.getAlbumByUserId(userId)).thenReturn(Mono.error(new Exception("Album fetch failed")));
        when(postRestClient.getPostByUserId(userId))
                .thenReturn(Mono.just(postRestClientModel));
        Mono<UserResponseModel> result = userService.getUserWithAlbumAndPosts(userId);

        StepVerifier.create(result)
                .expectErrorMessage("Album fetch failed")
                .verify();
    }


    @Test
    void getUserWithAlbumAndPosts_userNotFound() {
        int userId = 1;

        when(userRestClient.getUserById(userId)).thenReturn(Mono.empty());

        Mono<UserResponseModel> result = userService.getUserWithAlbumAndPosts(userId);

        StepVerifier.create(result)
                .expectNextCount(0)
                .verifyComplete();
    }

}