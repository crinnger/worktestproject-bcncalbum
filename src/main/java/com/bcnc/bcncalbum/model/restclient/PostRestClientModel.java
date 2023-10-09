package com.bcnc.bcncalbum.model.restclient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostRestClientModel {
    private int userId;
    private int id;
    private String title;
    private String body;
}
