package com.sabi.framework.notification.service;


import com.sabi.framework.helpers.API;
import com.sabi.framework.notification.model.Notification;
import com.sabi.framework.notification.model.RecipientRequest;
import com.sabi.framework.notification.repository.NotificationRepository;
import com.sabi.framework.notification.requestDto.NotificationRequest2Dto;
import com.sabi.framework.notification.requestDto.NotificationRequestDto;
import com.sabi.framework.notification.responseDto.NotificationResponseDto;
import com.sabi.framework.service.ExternalTokenService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@SuppressWarnings("ALL")
@Slf4j
@Service
public class NotificationService {

    @Value("${space.notification.url}")
    private String multipleNotification;

    @Value("${authKey.notification}")
    private String authKey;

    @Value("${phoneNo.notification}")
    private String phoneNo;


    @Autowired
    ExternalTokenService externalTokenService;

    @Autowired
    private API api;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private NotificationRepository notificationRepository;
    private final ModelMapper mapper;

    public NotificationService(NotificationRepository notificationRepository, ModelMapper mapper) {
        this.notificationRepository = notificationRepository;
        this.mapper = mapper;
    }

    public NotificationResponseDto emailNotificationRequest (NotificationRequestDto notification){

        RecipientRequest recipient = RecipientRequest.builder()
                            .email(notification.getEmail())
                            .phoneNo(phoneNo)
                            .build();
        NotificationRequest2Dto request = NotificationRequest2Dto.builder()
                        .email(true)
                        .inApp(false)
                        .message(notification.getMessage())
                        .recipient(recipient)
                        .sms(false)
                        .title(notification.getTitle())
                        .build();
        String extToken = externalTokenService.getToken().toString();
        NotificationResponseDto response = null;
        Map<String,String> map = new HashMap();
        map.put("fingerprint", notification.getFingerprint());
        map.put("auth-key", authKey.trim());
        map.put("Authorization", "bearer"+ " " +extToken);
        response = api.post(multipleNotification, request, NotificationResponseDto.class, map);
        Notification notification1 = mapper.map(response, Notification.class);
        notification1 = notificationRepository.save(notification1);
        return mapper.map(notification1, NotificationResponseDto.class);

    }

    public NotificationResponseDto smsNotificationRequest (NotificationRequestDto notification){

        RecipientRequest recipient = RecipientRequest.builder()
                .email(notification.getEmail())
                .phoneNo(phoneNo)
                .build();
        NotificationRequest2Dto request = NotificationRequest2Dto.builder()
                .email(false)
                .inApp(false)
                .message(notification.getMessage())
                .recipient(recipient)
                .sms(true)
                .title(notification.getTitle())
                .build();
        String extToken = externalTokenService.getToken().toString();
        NotificationResponseDto response = null;
        Map<String,String> map = new HashMap();
        map.put("fingerprint", notification.getFingerprint().trim());
        map.put("auth-key", authKey.trim());
        map.put("Authorization", "bearer"+ " " +extToken);
        response = api.post(multipleNotification, request, NotificationResponseDto.class, map);
        Notification notification1 = mapper.map(response, Notification.class);
        notification1 = notificationRepository.save(notification1);
        return mapper.map(notification1, NotificationResponseDto.class);

    }


}
