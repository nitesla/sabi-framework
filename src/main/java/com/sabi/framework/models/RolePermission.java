package com.sabi.framework.models;


import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;

@EqualsAndHashCode(callSuper=false)
@Data
@Entity
public class RolePermission extends CoreEntity{


    private Long roleId;

    private Long permissionId;



}
