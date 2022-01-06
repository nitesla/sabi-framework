package com.sabi.framework.models;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;



@Data
@Entity
public class AuditTrail {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String username;
        private String event;
        private String flag;
        private String request;
        private String ipAddress;
        private int status;
        private LocalDateTime requestTime = LocalDateTime.now();





}
