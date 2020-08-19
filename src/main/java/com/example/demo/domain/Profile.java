package com.example.demo.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@ToString(exclude = "member")
@Getter
@Setter
@Table(name ="tbl_profile")
@Entity
@EqualsAndHashCode(of = "fno")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fno;

    @ManyToOne
    private Member member;

    private String fname;
    private boolean current;
}
