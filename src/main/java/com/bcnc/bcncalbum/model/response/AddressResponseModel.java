package com.bcnc.bcncalbum.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressResponseModel {
    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private GeoResponseModel geo;
}
