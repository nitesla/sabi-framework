package com.sabi.framework.models;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Transient;
import java.time.LocalDateTime;
import java.util.Date;




@EqualsAndHashCode(callSuper=false)
@Entity
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User extends CoreEntity{


//    private Long loginAttempts;
    private int loginAttempts;
    private LocalDateTime failedLoginDate;
    private LocalDateTime lastLogin;
    private String password;
    private String passwordExpiration;
    private Date lockedDate;
    private String firstName;
    private String lastName;
    private String middleName;
    private String username;
    private Long roleId;
    @Transient
    private String roleName;
    private LocalDateTime passwordChangedOn;
    private String cardBin;
    private String cardLast4;
    @Transient
    private boolean loginStatus;
    private String email;
    private String phone;
    // TODO validate not less than 4 digits and not more than 6 digits
    private String transactionPin;
    private String transactionPinStatus;
    private String userCategory;
    private String resetToken;
    private String resetTokenExpirationDate;
    private Long clientId;
    private Long wareHouseId;
    private String photo;

// --------logistic  userType -----------

    @Transient
    private String userType;

// ------AGENT USER DETAILS-----
    @Transient
    private long agentId;
    @Transient
    private Long agentCategoryId;
    @Transient
    private String scope;
    @Transient
    private String referralCode;
    @Transient
    private String referrer;
    @Transient
    private String address;
    @Transient
    private String bvn;
    @Transient
    private String agentType;
    @Transient
    private Long creditLimit;
    @Transient
    private Integer payBackDuration;
    @Transient
    private Long balance;
    @Transient
    private Date verificationDate;
    @Transient
    private Long supervisorId;
    @Transient
    private int verificationStatus;
    @Transient
    private String comment;
    @Transient
    private String cardToken;
    @Transient
    private int status;
    @Transient
    private String walletId;
    @Transient
    private String picture;
    @Transient
    private Boolean hasCustomizedTarget;
    @Transient
    private Long creditLevelId;
    @Transient
    private Long idTypeId;
    @Transient
    private String idCard;
    @Transient
    private Long stateId;
    @Transient
    private Long bankId;
    @Transient
    private Long countryId;
    @Transient
    private boolean accountNonLocked;
    @Transient
    private String registrationToken;
    @Transient
    private String registrationTokenExpiration;
    @Transient
    private Boolean isEmailVerified ;

    @Transient
    private String agentCategoryName;

}
