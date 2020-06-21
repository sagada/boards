package com.algo.inc.web.controller;

import com.algo.inc.web.dto.BoardResponseDto;
import com.algo.inc.web.dto.BoardSaveRequestDto;
import com.algo.inc.web.dto.BoardUpdateRequestDto;
import com.algo.inc.web.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController(value = "/api/board")
public class BoardApiController {

    private final BoardService boardService;

    @PostMapping
    public Long save(@RequestBody BoardSaveRequestDto requestDto)
    {
        return boardService.save(requestDto);
    }

    @PutMapping("/{id}")
    public Long update(@PathVariable Long id, @RequestBody BoardUpdateRequestDto updateRequestDto)
    {
        return boardService.update(id, updateRequestDto);
    }

    @GetMapping("/{id}")
    public BoardResponseDto findById(@PathVariable Long id)
    {
        return boardService.findById(id);
    }
}
