package com.sabi.framework.dto.responseDto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sabi.framework.models.User;

import java.io.Serializable;
import java.time.LocalDateTime;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccessTokenWithUserDetails implements Serializable{


    @JsonProperty("accessToken")
    private String accessToken;

    @JsonProperty("phone")
    private String phone;

    @JsonProperty("email")
    private String email;

    @JsonProperty("menu")
    private String menu;


    @JsonProperty("lastName")
    private String lastName;

    @JsonProperty("firstName")
    private String firstName;


    @JsonProperty("lastLogin")
    private LocalDateTime lastLogin;



    @JsonProperty("tokenExpiry")
    private long tokenExpiry;

    @JsonProperty("userId")
    private long userId;

    @JsonProperty("agentId")
    private String agentId;

    @JsonProperty("referralCode")
    private String referralCode;

    @JsonProperty("isEmailVerified")
    private String isEmailVerified ;

    @JsonProperty("partnerCategory")
    private String partnerCategory;







    public AccessTokenWithUserDetails(String token, User user, String menu, long tokenExpiry,
                                      String agentId,String referralCode,String isEmailVerified,String partnerCategory) {
        this.accessToken = token;

        this.email = user.getEmail();
        this.phone = user.getPhone();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.lastLogin = user.getLastLogin();
        this.menu = menu;
        this.tokenExpiry = tokenExpiry;
        this.userId=user.getId();
        this.agentId=agentId;
        this.referralCode=referralCode;
        this.isEmailVerified=isEmailVerified;
        this.partnerCategory=partnerCategory;




    }

}
