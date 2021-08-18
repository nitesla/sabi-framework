package com.sabi.framework.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
@ToString
@MappedSuperclass
public abstract class CoreEntity implements Serializable {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "YYYY-MM-dd HH:mm:ss")
    @Column(columnDefinition = "timestamp default CURRENT_TIMESTAMP")
    private Date createdDate;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "YYYY-MM-dd HH:mm:ss")
    @Column(columnDefinition = "timestamp default CURRENT_TIMESTAMP")
    private Date updatedDate;

    private Boolean Isactive;
    private String responseCode;
}
