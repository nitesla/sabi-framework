package com.sabi.framework.models;




import javax.persistence.*;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper=false)
@Data
@Entity
public class Role extends CoreEntity{
    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String scope;

}
