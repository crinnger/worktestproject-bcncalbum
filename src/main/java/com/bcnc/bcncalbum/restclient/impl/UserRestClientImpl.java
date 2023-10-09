package com.bcnc.bcncalbum.restclient.impl;

import com.bcnc.bcncalbum.exceptions.UserFetchException;
import com.bcnc.bcncalbum.model.restclient.UserRestClientModel;
import com.bcnc.bcncalbum.restclient.UserRestClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Component
public class UserRestClientImpl implements UserRestClient {

    private final WebClient webClient;


    @Override
    public Mono<UserRestClientModel> getUserById(int userId) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("users/{userId}")
                        .build(userId))
                .retrieve()
                .bodyToMono(UserRestClientModel.class)
                .onErrorMap(error->new UserFetchException(error.getMessage()));
    }
}
