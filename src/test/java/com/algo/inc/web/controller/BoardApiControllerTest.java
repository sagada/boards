package com.algo.inc.web.controller;

import com.algo.inc.domain.board.Board;
import com.algo.inc.web.dto.BoardSaveRequestDto;
import com.algo.inc.web.dto.BoardUpdateRequestDto;
import com.algo.inc.web.repository.BoardRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BoardApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private BoardRepository boardRepository;

    @After
    public void tearDown() throws Exception
    {
        boardRepository.deleteAll();
    }

    @Test
    public void board_등록된다() throws Exception
    {
        //given
        String title = "title";
        String content = "content";

        BoardSaveRequestDto requestDto = BoardSaveRequestDto.builder()
                .title(title)
                .content(content)
                .build();

        String url = "http://localhost:" + port + "/api/board";

        //when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Board> all = boardRepository.findAll();

        assertThat(all.get(0).getTitle()).isEqualTo(title);
        assertThat(all.get(0).getContent()).isEqualTo(content);
    }

    @Test
    public void board_수정된다() throws Exception
    {
        //given
        Board savedBoard = boardRepository.save(Board.builder()
                        .title("title")
                        .content("content")
                        .build()
        );

        Long updateId = savedBoard.getId();

        String expectedTitile = "title2";
        String expectedContent = "content2";

        BoardUpdateRequestDto requestDto = BoardUpdateRequestDto.builder()
                .title(expectedTitile)
                .content(expectedContent)
                .build();
        String url = "http://localhost:" + port + "/" + updateId;

        HttpEntity<BoardUpdateRequestDto>  requestEntity = new HttpEntity<>(requestDto);

        //when
        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT,
                requestEntity, Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Board> all =  boardRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(expectedTitile);
        assertThat(all.get(0).getContent()).isEqualTo(expectedContent);

    }
}
