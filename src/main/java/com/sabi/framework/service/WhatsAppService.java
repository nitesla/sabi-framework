package com.sabi.framework.service;


import com.sabi.framework.helpers.API;
import com.sabi.framework.notification.requestDto.WhatsAppRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("ALL")
@Slf4j
@Service
public class WhatsAppService {


    @Value("${authKey.whatsapp}")
    private String whatsAuthKey;

    @Value("${notification.unique.id}")
    private String uniqueId;

    @Value("${whatsapp.notification.url}")
    private String whatsAppNotification;
    @Autowired
    ExternalTokenService externalTokenService;

    @Autowired
    private API api;


    public void whatsAppNotification(WhatsAppRequest whatsAppRequest){

        Map<String,String> map = new HashMap();
        map.put("auth-key", whatsAuthKey.trim());
        map.put("fingerprint", uniqueId.trim());

        api.postNotification(whatsAppNotification.trim(), whatsAppRequest, map);


    }
}
