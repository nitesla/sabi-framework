package com.sabi.framework.globaladminintegration;


import com.sabi.framework.exceptions.NotFoundException;
import com.sabi.framework.helpers.API;
import com.sabi.framework.models.GlobalAccessToken;
import com.sabi.framework.repositories.AccessTokenRepository;
import com.sabi.framework.utils.CustomResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class AccessTokenService {

    @Value("${global.login.url}")
    private String globalLogin;

    @Value("${global.admin.username}")
    private String globalUsername;

    @Value("${global.admin.password}")
    private String globalPassword;

    @Autowired
    private API api;

    private AccessTokenRepository accessTokenRepository;

    public AccessTokenService(AccessTokenRepository accessTokenRepository) {
        this.accessTokenRepository = accessTokenRepository;
    }


    public void globalTokenRequest ()  {
        AccesTokenRequest request = AccesTokenRequest.builder()
                .username(globalUsername.trim())
                .password(globalPassword.trim())
                .build();
        Map map=new HashMap();
        AccessTokenResponse response = api.post(globalLogin, request, AccessTokenResponse.class,map);
        saveGlobalToken(response);
    }



    public void saveGlobalToken(AccessTokenResponse response) {
        GlobalAccessToken token = GlobalAccessToken.builder()
                .accessToken(response.getAccessToken())
                .build();
        if (accessTokenRepository.count() == 0) {
            accessTokenRepository.save(token);
        }else {
            GlobalAccessToken extToken = accessTokenRepository.findById(1l)
                    .orElseThrow(() -> new NotFoundException(CustomResponseCode.NOT_FOUND_EXCEPTION,
                            "Requested id does not exist!"));
            extToken.setAccessToken(response.getAccessToken());
            accessTokenRepository.save(extToken);
        }
    }



    public String getGlobalToken(){
        GlobalAccessToken extToken = accessTokenRepository.findById(1l)
                .orElseThrow(() -> new NotFoundException(CustomResponseCode.NOT_FOUND_EXCEPTION,
                        "Requested id does not exist!"));

        String result = extToken.getAccessToken();
        return result;

    }
}
