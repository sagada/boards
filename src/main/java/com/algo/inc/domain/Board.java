package com.algo.inc.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;


@Getter
@NoArgsConstructor
@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;
    private LocalDateTime regDt;
    private String writer;


    @Builder
    public Board(String title, String content, String writer, LocalDateTime regDt)
    {
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.regDt = regDt;
    }

    public void update(String title, String content)
    {
        this.title = title;
        this.content = content;
    }
}
