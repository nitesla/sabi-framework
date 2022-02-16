package com.sabi.framework.globaladminintegration.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.ArrayList;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PagedData {

    private ArrayList<Content> content;
    private Pageable pageable;
    private int totalPages;
    private int totalElements;
    private boolean last;
    private int numberOfElements;
    private boolean first;
    private int size;
    private int number;
    private Sort sort;
    private boolean empty;
}
