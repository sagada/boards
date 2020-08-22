package com.mvctwo.wmp.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@Table
@EqualsAndHashCode(of="bno")
@ToString
@Entity
public class WebBoard {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long bno;

    private String title;
    private String content;
    private String writer;
    @CreationTimestamp
    private Timestamp regdate;

    @UpdateTimestamp
    private Timestamp updatedate;

}
