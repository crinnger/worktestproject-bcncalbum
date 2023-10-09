package com.bcnc.bcncalbum.restclient.impl;

import com.bcnc.bcncalbum.exceptions.AlbumFetchException;
import com.bcnc.bcncalbum.exceptions.UserFetchException;
import com.bcnc.bcncalbum.model.restclient.AlbumRestClientModel;
import com.bcnc.bcncalbum.restclient.AlbumRestClient;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@RequiredArgsConstructor
@Component
public class AlbumRestClientImpl implements AlbumRestClient {

    private final WebClient webClient;

    @Override
    public Mono<List<AlbumRestClientModel>> getAlbumByUserId(int userId) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("albums")
                        .queryParam("userId", userId)
                        .build())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<AlbumRestClientModel>>() {})
                .onErrorMap(error->new AlbumFetchException(error.getMessage()));
    }
}
