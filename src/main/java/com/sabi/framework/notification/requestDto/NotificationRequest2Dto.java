package com.sabi.framework.notification.requestDto;


import com.sabi.framework.notification.model.RecipientRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * This class collects the request and map it to the entity class
 */

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class NotificationRequest2Dto {


    private String message;
    private String title;
    private Boolean email;
    private Boolean inApp;
    private RecipientRequest recipient;
    private Boolean sms;

}
