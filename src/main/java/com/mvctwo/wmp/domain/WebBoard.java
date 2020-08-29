package com.mvctwo.wmp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

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

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "board", cascade = CascadeType.ALL)
    private List<WebReply> replies;

}
