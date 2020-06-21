package com.algo.inc.web.dto;

import com.algo.inc.domain.board.Board;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class BoardSaveRequestDto {

    private String title;
    private String content;

    @Builder
    public BoardSaveRequestDto(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Board toEntity()
    {
        return Board.builder()
                .title(title)
                .content(content)
                .writer("TestUser")
                .build();
    }
}
