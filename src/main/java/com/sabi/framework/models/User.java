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



}
