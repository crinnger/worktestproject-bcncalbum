package com.bcnc.bcncalbum.restclient;

import com.bcnc.bcncalbum.model.restclient.PostRestClientModel;
import reactor.core.publisher.Mono;

import java.util.List;

public interface PostRestClient {

    Mono<List<PostRestClientModel>> getPostByUserId(int userId);
}
