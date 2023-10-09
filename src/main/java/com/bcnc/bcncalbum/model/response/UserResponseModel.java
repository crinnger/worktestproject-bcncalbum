package com.bcnc.bcncalbum.model.response;

import com.bcnc.bcncalbum.model.response.AlbumResponseModel;
import com.bcnc.bcncalbum.model.response.PostResponseModel;
import com.bcnc.bcncalbum.model.restclient.AddressRestClientMoldel;
import com.bcnc.bcncalbum.model.restclient.CompanyRestClientModel;
import com.bcnc.bcncalbum.model.restclient.UserRestClientModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponseModel {

    private int id;
    private String name;
    private String username;
    private String email;
    private AddressResponseModel address;
    private String phone;
    private String website;
    private CompanyResponseModel company;

    private List<AlbumResponseModel> albums;
    private List<PostResponseModel> posts;
}
