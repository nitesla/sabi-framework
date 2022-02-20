package com.sabi.framework.globaladminintegration.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Pageable {
    private Sort sort;
    private int pageSize;
    private int pageNumber;
    private int offset;
    private boolean unpaged;
    private boolean paged;
}
