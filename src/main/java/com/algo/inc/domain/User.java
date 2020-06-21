package com.algo.inc.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String account;
    private String email;
    private String phoneNumber;

    private LocalDateTime chgDt;
    private LocalDateTime regDt;
    private String regId;
    private String chgId;

    @Builder
    public User(String account, String email, String phoneNumber, String regId, String chgId)
    {
        this.account = account;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.regId = regId;
        this.chgId = chgId;
    }
}
