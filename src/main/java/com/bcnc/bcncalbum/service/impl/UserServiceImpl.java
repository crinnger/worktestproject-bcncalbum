package com.bcnc.bcncalbum.service.impl;

import com.bcnc.bcncalbum.mapper.UserMapper;
import com.bcnc.bcncalbum.model.response.AlbumResponseModel;
import com.bcnc.bcncalbum.model.response.PostResponseModel;
import com.bcnc.bcncalbum.model.response.UserResponseModel;
import com.bcnc.bcncalbum.restclient.AlbumRestClient;
import com.bcnc.bcncalbum.restclient.PostRestClient;
import com.bcnc.bcncalbum.restclient.UserRestClient;
import com.bcnc.bcncalbum.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRestClient userRestClient;
    private final PostRestClient postRestClient;
    private final AlbumRestClient albumRestClient;
    private final UserMapper userMapper;

    @Override
    public Mono<UserResponseModel> getUserWithAlbumAndPosts(int userId) {
        return userRestClient.getUserById(userId)
                .map(userMapper::toUserResponseModel)
                .flatMap(user ->
                        Mono.zip(
                                getPosts(userId).doOnNext(user::setPosts),
                                getAlbuns(userId).doOnNext(user::setAlbums)
                        ).thenReturn(user)
                );
    }

    private Mono<List<PostResponseModel>> getPosts(int userId) {
        return postRestClient.getPostByUserId(userId)
                .map(userMapper::toPostResponseModel);
    }

    private Mono<List<AlbumResponseModel>> getAlbuns(int userId) {
        return albumRestClient.getAlbumByUserId(userId)
                .map(userMapper::toAlbumResponseModel);
    }
}
