package com.example.demo.domain;

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
@ToString
@Entity
@Table(name = "tbl_reply")
@EqualsAndHashCode(of="rno")
public class FreeBoardReply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rno;
    private String reply;
    private String replyer;

    @CreationTimestamp
    private Timestamp replyDate;

    @UpdateTimestamp
    private Timestamp updateDate;

    @ManyToOne
    private FreeBoard freeBoard;
}
