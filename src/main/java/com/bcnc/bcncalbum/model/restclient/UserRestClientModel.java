package com.bcnc.bcncalbum.model.restclient;

import com.bcnc.bcncalbum.model.restclient.AddressRestClientMoldel;
import com.bcnc.bcncalbum.model.restclient.CompanyRestClientModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRestClientModel {
    private int id;
    private String name;
    private String username;
    private String email;
    private AddressRestClientMoldel address;
    private String phone;
    private String website;
    private CompanyRestClientModel company;
}
