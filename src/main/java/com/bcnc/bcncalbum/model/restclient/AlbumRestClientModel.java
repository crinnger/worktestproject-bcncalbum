package com.bcnc.bcncalbum.model.restclient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AlbumRestClientModel {
    private int userId;
    private int id;
    private String title;
}
