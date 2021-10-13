package com.sabi.framework.models;


import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.time.LocalDateTime;
import java.util.Date;




@EqualsAndHashCode(callSuper=false)
@Entity
@Data
public class User extends CoreEntity{


    private Long loginAttempts;
    private LocalDateTime failedLoginDate;
    private LocalDateTime lastLogin;
    @Column(nullable = false)
    private String password;
    private Date lockedDate;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    private String middleName;
    private String username;
    public Long roleId;
    private LocalDateTime passwordChangedOn;
    @Transient
    private boolean loginStatus;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String phone;
    // TODO validate not less than 4 digits and not more than 6 digits
    private String transactionPin;
    private String userCategory;
    private String resetToken;
    private String resetTokenExpirationDate;

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
