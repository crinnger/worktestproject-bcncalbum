package com.bcnc.bcncalbum.service;

import com.bcnc.bcncalbum.model.response.UserResponseModel;
import reactor.core.publisher.Mono;


public interface UserService {

    Mono<UserResponseModel> getUserWithAlbumAndPosts(int userId);
}
