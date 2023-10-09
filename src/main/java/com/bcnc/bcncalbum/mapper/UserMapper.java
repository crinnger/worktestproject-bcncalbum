package com.bcnc.bcncalbum.mapper;

import com.bcnc.bcncalbum.model.response.AlbumResponseModel;
import com.bcnc.bcncalbum.model.response.PostResponseModel;
import com.bcnc.bcncalbum.model.response.UserResponseModel;
import com.bcnc.bcncalbum.model.restclient.AlbumRestClientModel;
import com.bcnc.bcncalbum.model.restclient.PostRestClientModel;
import com.bcnc.bcncalbum.model.restclient.UserRestClientModel;

import java.util.List;

public interface UserMapper {
    UserResponseModel toUserResponseModel(UserRestClientModel userRestClientModel);
    PostResponseModel toPostResponseModel(PostRestClientModel postRestClientModel);
    AlbumResponseModel toAlbumResponseModel(AlbumRestClientModel albumRestClientModel);
    List<PostResponseModel> toPostResponseModel(List<PostRestClientModel> listPost);
    List<AlbumResponseModel> toAlbumResponseModel(List<AlbumRestClientModel> listAlbum);
}
