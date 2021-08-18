package com.sabi.framework.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Email {

    private String to;
    private String subject;
    private String body;

}
