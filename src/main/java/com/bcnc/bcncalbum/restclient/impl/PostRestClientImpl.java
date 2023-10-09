package com.bcnc.bcncalbum.restclient.impl;

import com.bcnc.bcncalbum.exceptions.PostFetchException;
import com.bcnc.bcncalbum.exceptions.UserFetchException;
import com.bcnc.bcncalbum.model.restclient.PostRestClientModel;
import com.bcnc.bcncalbum.restclient.PostRestClient;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@RequiredArgsConstructor
@Component
public class PostRestClientImpl implements PostRestClient {
    private final WebClient webClient;


    @Override
    public Mono<List<PostRestClientModel>> getPostByUserId(int userId) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("posts")
                        .queryParam("userId", userId)
                        .build())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<PostRestClientModel>>() {})
                .onErrorMap(error->new PostFetchException(error.getMessage()));
    }
}
