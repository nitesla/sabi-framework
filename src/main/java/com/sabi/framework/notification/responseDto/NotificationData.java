package com.sabi.framework.notification.responseDto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NotificationData {

    private String message;
    private NotificationResponseData data;

}
