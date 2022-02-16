package com.sabi.framework.globaladminintegration.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.ArrayList;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ListResponse {
    private String code;
    private String description;
    private ArrayList<ListResponseData> data;
}
