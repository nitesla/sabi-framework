package com.sabi.framework.models;


import lombok.*;

import javax.persistence.Entity;

@EqualsAndHashCode(callSuper=false)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class RolePermission extends CoreEntity{


    private Long roleId;
    private Long permissionId;
    private String permissionName;

//    @Transient
//    private String permission;





}
