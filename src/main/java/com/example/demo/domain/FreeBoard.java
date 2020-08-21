package com.example.demo.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@ToString(exclude = "replyList")
@Getter
@Setter
@Entity
@Table(name = "tbl_boards")
@EqualsAndHashCode(of = "bno")
public class FreeBoard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bno;
    private String title;
    private String writer;
    private String content;

    @CreationTimestamp
    private Timestamp regDate;

    @UpdateTimestamp
    private Timestamp updateDate;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<FreeBoardReply> replyList;

}
