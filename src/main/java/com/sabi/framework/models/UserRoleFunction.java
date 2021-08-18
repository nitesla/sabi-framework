package com.sabi.framework.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Collection;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper=false)
@Data
@Entity
public class UserRoleFunction extends CoreEntity{

    @ManyToOne
    @JoinColumn(name = "user_id", nullable=false)
    @JsonManagedReference(value = "user")
    private User user;

    @ManyToOne
    @JoinColumn(name="function_id", referencedColumnName="id", nullable=false)
    @JsonBackReference(value = "function")
    private Function function;

    @Column(nullable = false)
    private String roleName;

}
