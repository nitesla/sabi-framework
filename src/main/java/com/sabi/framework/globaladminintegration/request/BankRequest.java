package com.sabi.framework.globaladminintegration.request;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BankRequest {

    private String name;
    private String code;
    private Long countryId;
    private Long stateId;
    private Long lgaId;
    private String permissionType;
    private String appPermission;
    private int page;
    private int pageSize;
}
