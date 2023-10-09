package com.bcnc.bcncalbum.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompanyResponseModel {
    private String name;
    private String catchPhrase;
    private String bs;
}
