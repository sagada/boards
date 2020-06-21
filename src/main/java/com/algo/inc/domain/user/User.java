package com.algo.inc.domain.user;


import com.algo.inc.domain.BaseTimeEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String account;
    private String email;
    private String phoneNumber;

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
