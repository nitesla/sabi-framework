package com.sabi.framework.globaladminintegration.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SingleResponseData {

    private Long id;

    private String name;

    private String code;

    private String createdDate;

    private String updatedDate;

    private Long createdBy;

    private Long updatedBy;
}
