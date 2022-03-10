package com.sabi.framework.globaladminintegration.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ListResponseData {

    private Long id;
    private String name;
    private String code;
    private Long countryId;
    private Long stateId;
    private Long lgaId;
    private String countryName;
    private String lgaName;
    private String stateName;
    private String createdDate;
    private String updatedDate;
    private Long createdBy;
    private Long updatedBy;
    private int status;
}
