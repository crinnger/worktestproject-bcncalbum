package com.bcnc.bcncalbum.model.restclient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GeoRestClientModel {
    private String lat;
    private String lng;
}
