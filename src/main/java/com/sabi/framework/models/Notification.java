package com.sabi.framework.notification.model;


import lombok.*;

import javax.persistence.*;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper=false)
@Data
@Entity
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Boolean email;
    private Boolean inApp;
    private String message;
    @OneToOne
    private RecipientRequest recipients;
    private Boolean sms;
    private String title;
    private String code;
    private String data;

}
