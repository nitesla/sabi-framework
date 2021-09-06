package com.sabi.framework.models;


import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper=false)
@Data
@Entity
public class RolePermission extends CoreEntity{


    private Long roleId;


    @OneToMany(targetEntity = Permission.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "permission_fk", referencedColumnName = "id")
    private List<Permission> permissions;



}
