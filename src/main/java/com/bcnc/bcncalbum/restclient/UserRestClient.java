package com.bcnc.bcncalbum.restclient;

import com.bcnc.bcncalbum.model.restclient.UserRestClientModel;
import reactor.core.publisher.Mono;

public interface UserRestClient {

    Mono<UserRestClientModel> getUserById(int userId);
}
