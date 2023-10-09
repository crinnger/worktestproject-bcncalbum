package com.bcnc.bcncalbum.controller;


import com.bcnc.bcncalbum.model.response.UserResponseModel;
import com.bcnc.bcncalbum.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users/")
public class UserController {

    private final UserService userService;

    @GetMapping("{userId}")
    public Mono<UserResponseModel> getUserWithPostAndAlbuns(@PathVariable int userId){
        return userService.getUserWithAlbumAndPosts(userId);
    }
}
