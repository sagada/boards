package com.algo.inc.web.service;

import com.algo.inc.domain.Board;
import com.algo.inc.web.dto.BoardResponseDto;
import com.algo.inc.web.dto.BoardSaveRequestDto;
import com.algo.inc.web.dto.BoardUpdateRequestDto;
import com.algo.inc.web.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    public Long save(BoardSaveRequestDto requestDto)
    {
        return boardRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, BoardUpdateRequestDto updateRequestDto)
    {
        Board board = boardRepository
                .findById(id)
                .orElseThrow(()-> new IllegalArgumentException("해당 사용자가 없습니다. id = "+ id));

        board.update(updateRequestDto.getTitle(), updateRequestDto.getContent());

        return id;
    }

    public BoardResponseDto findById(Long id)
    {
        Board entity = boardRepository
                .findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 사용자가 없습니다. id = "+ id));

        return new BoardResponseDto(entity);
    }

}
