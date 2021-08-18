package com.sabi.framework.models;

import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper=false)
@Data
@Entity
public class Function extends CoreEntity{

    @Column(nullable = false)
    private String code;

}
