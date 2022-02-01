package com.sabi.framework.service;


import com.sabi.framework.dto.requestDto.TokenRequest;
import com.sabi.framework.dto.responseDto.TokenResponse;
import com.sabi.framework.exceptions.NotFoundException;
import com.sabi.framework.helpers.API;
import com.sabi.framework.models.ExternalToken;
import com.sabi.framework.repositories.ExternalTokenRepository;
import com.sabi.framework.utils.CustomResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class ExternalTokenService {

    @Value("${space.login.url}")
    private String login;

    @Value("${unique.id}")
    private String uniqueId;

    @Value("${auth.username}")
    private String username;

    @Value("${password}")
    private String password;

    @Autowired
    private API api;

    private ExternalTokenRepository externalTokenRepository;
    private final ModelMapper mapper;

    public ExternalTokenService(ExternalTokenRepository externalTokenRepository,ModelMapper mapper) {
        this.externalTokenRepository = externalTokenRepository;
        this.mapper = mapper;
    }


//    @Scheduled(cron="${cronExpression}")
//    public void getNewToken() {
//        log.info("::Cron Job Started at :   %s", new Date());
//        externalTokenRequest();
//    }

    public TokenResponse externalTokenRequest ()  {
        TokenRequest request = TokenRequest.builder()
                .username(username.trim())
                .password(password.trim())
                .build();
        Map map=new HashMap();
        map.put("fingerprint",uniqueId.trim());
        TokenResponse response = api.post(login, request, TokenResponse.class,map);
        saveToken(response);
        return response;
    }


    public void saveToken(TokenResponse response) {
        ExternalToken token = ExternalToken.builder()
                .token(response.getToken())
                .build();
        if (externalTokenRepository.count() == 0) {
            externalTokenRepository.save(token);
        }else {
            ExternalToken extToken = externalTokenRepository.findById(1l)
                    .orElseThrow(() -> new NotFoundException(CustomResponseCode.NOT_FOUND_EXCEPTION,
                            "Requested id does not exist!"));
            extToken.setToken(response.getToken());
            externalTokenRepository.save(extToken);
        }
    }




    public String getToken(){
        ExternalToken extToken = externalTokenRepository.findById(1l)
                .orElseThrow(() -> new NotFoundException(CustomResponseCode.NOT_FOUND_EXCEPTION,
                        "Requested id does not exist!"));

        String result = extToken.getToken();
        return result;

    }

}
