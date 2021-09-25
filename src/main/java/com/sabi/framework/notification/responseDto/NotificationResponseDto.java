package com.sabi.framework.notification.responseDto;


import lombok.Data;



/**
 *
 * This class collects the response and map it to the entity class
 */

@Data
public class NotificationResponseDto {

     private String code;
     private Object data;
     private String message;
}
