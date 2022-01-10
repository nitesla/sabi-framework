package com.sabi.framework.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Transient;

@EqualsAndHashCode(callSuper=false)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class RolePermission extends CoreEntity{


    private Long roleId;


//    @OneToMany(targetEntity = Permission.class, cascade = CascadeType.ALL)
//    @JoinColumn(name = "permission_fk", referencedColumnName = "id")
//    @ElementCollection
//    @CollectionTable(name = "my_list", joinColumns = @JoinColumn(name = "id"))
    private Long permissionId;


    @Transient
    private String permission;





}
