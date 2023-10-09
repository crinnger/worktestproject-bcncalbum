package com.bcnc.bcncalbum.model.restclient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressRestClientMoldel {
    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private GeoRestClientModel geo;
}
