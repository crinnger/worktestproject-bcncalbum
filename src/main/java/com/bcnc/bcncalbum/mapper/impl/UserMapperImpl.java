package com.bcnc.bcncalbum.mapper.impl;

import com.bcnc.bcncalbum.mapper.UserMapper;
import com.bcnc.bcncalbum.model.response.*;
import com.bcnc.bcncalbum.model.restclient.*;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Log4j2
public class UserMapperImpl implements UserMapper {

    public UserMapperImpl (){
       log.info("Inicializing mapper");
    }
    @Override
    public UserResponseModel toUserResponseModel(UserRestClientModel userRestClientModel) {
        return UserResponseModel.builder()
                .name(userRestClientModel.getName())
                .phone(userRestClientModel.getPhone())
                .email(userRestClientModel.getEmail())
                .id(userRestClientModel.getId())
                .website(userRestClientModel.getWebsite())
                .username(userRestClientModel.getUsername())
                .address(this.getAdress(userRestClientModel.getAddress()))
                .company(this.getCompany(userRestClientModel.getCompany()))
                .build();
    }

    private CompanyResponseModel getCompany(CompanyRestClientModel company) {
        if(company==null){
            return null;
        }
        return CompanyResponseModel.builder()
                .bs(company.getBs())
                .catchPhrase(company.getCatchPhrase())
                .name(company.getName())
                .build();
    }

    private AddressResponseModel getAdress(AddressRestClientMoldel address) {
        if(address==null){
            return null;
        }
        return AddressResponseModel.builder()
                .city(address.getCity())
                .suite(address.getSuite())
                .zipcode(address.getZipcode())
                .street(address.getStreet())
                .geo(getGeo(address.getGeo()))
                .build();
    }

    private static GeoResponseModel getGeo(GeoRestClientModel geo) {
        if(geo==null){
            return null;
        }
        return GeoResponseModel.builder()
                .lat(geo.getLat())
                .lng(geo.getLng())
                .build();
    }

    @Override
    public PostResponseModel toPostResponseModel(PostRestClientModel postRestClientModel) {
        return PostResponseModel.builder()
                .id(postRestClientModel.getId())
                .title(postRestClientModel.getTitle())
                .body(postRestClientModel.getBody())
                .build();
    }

    @Override
    public AlbumResponseModel toAlbumResponseModel(AlbumRestClientModel albumRestClientModel) {
        return AlbumResponseModel.builder()
                .id(albumRestClientModel.getId())
                .title(albumRestClientModel.getTitle())
                .build();
    }

    @Override
    public List<PostResponseModel> toPostResponseModel(List<PostRestClientModel> listPost) {
        if(listPost==null){
            return null;
        }
        return listPost.stream().map(this::toPostResponseModel).collect(Collectors.toList());
    }

    @Override
    public List<AlbumResponseModel> toAlbumResponseModel(List<AlbumRestClientModel> listAlbum) {
        if(listAlbum==null){
            return null;
        }
        return listAlbum.stream().map(this::toAlbumResponseModel).collect(Collectors.toList());
    }
}
