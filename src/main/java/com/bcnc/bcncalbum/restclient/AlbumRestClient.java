package com.bcnc.bcncalbum.restclient;

import com.bcnc.bcncalbum.model.restclient.AlbumRestClientModel;
import reactor.core.publisher.Mono;

import java.util.List;

public interface AlbumRestClient {

    Mono<List<AlbumRestClientModel>> getAlbumByUserId(int userId );
}
