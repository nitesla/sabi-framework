package com.sabi.framework.models;


import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;

@EqualsAndHashCode(callSuper=false)
@Data
@Entity
public class Role extends CoreEntity{


    private String name;
    private String description;
    private Long clientId;



}
