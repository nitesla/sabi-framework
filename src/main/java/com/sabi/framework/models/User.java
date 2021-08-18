package com.sabi.framework.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@EqualsAndHashCode(callSuper=false)
@Entity
@Data
public class User extends CoreEntity{

    @Column(nullable = false)
    private int failedPasswordAttemptCount;

    @Column(nullable = false)
    private String isLocked;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Date lastLoginDate;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String middleName;

    @JsonManagedReference
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public Set<UserRoleFunction> roles;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String resetToken;

    @Column(nullable = false)
    private Date resetTokenExpirationDate;
}
