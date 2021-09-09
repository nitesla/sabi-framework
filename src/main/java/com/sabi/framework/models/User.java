package com.sabi.framework.models;


import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;




@EqualsAndHashCode(callSuper=false)
@Entity
@Data
public class User extends CoreEntity{


    private int failedPasswordAttemptCount;

    private String isLocked;

    @Column(nullable = false)
    private String password;

    private Date lastLoginDate;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    private String middleName;

    public Long roleId;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String phone;

    // TODO validate not less than 4 digits and not more than 6 digits
    private String transactionPin;

    private String userCategory;

    private String resetToken;

    private String resetTokenExpirationDate;



}
