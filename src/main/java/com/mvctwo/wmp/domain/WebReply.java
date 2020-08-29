package com.mvctwo.wmp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;


@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name="tbl_webreplyes")
@EqualsAndHashCode(of="rno")
@ToString
public class WebReply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rno;
    private String replyText;
    private String replyer;
    @CreationTimestamp
    private Timestamp regdate;
    @UpdateTimestamp
    private Timestamp updatedate;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private WebBoard board;

    @Builder
    public WebReply(String replyText, String replyer, WebBoard board)
    {
        this.board = board;
        this.replyText = replyText;
        this.replyer = replyer;
    }

}
