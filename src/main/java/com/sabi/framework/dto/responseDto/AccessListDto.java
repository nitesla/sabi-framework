package com.sabi.framework.dto.responseDto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccessListDto {

    private String name;
}
