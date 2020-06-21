package com.algo.inc.web.repository;

import com.algo.inc.domain.board.Board;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
public class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @After
    public void cleanUp()
    {
        boardRepository.deleteAll();
    }

    @Test
    @Transactional
    public void 게시글저장_불러오기()
    {
        //given
        String title = "윤종신 - 좋니";
        String content = "좋으니 사랑해서 ~ 사라알을 시작할때 니가 얼마나 예쁜지 모르지이이";

        boardRepository.save(
                               Board.builder()
                                    .title(title)
                                    .content(content)
                                    .build());

        //when
        List<Board> boardLists = boardRepository.findAll();

        //then
        Board board = boardLists.get(0);
        assertThat(board.getContent()).isEqualTo(content);
        assertThat(board.getTitle()).isEqualTo(title);
    }

    @Test
    public void BaseTimeEntity_등록()
    {
        //given
        LocalDateTime now = LocalDateTime.of(2019,6,4,0,0,0);
        boardRepository.save(Board.builder()
                .content("content")
                .title("title")
                .build());

        //when
        List<Board> boardList = boardRepository.findAll();

        //then
        Board board = boardList.get(0);

        System.out.println(">>>>>>>>>>>>> createDate = " + board.getRegDt() +", modifiedDate = " + board.getChgDt());

        assertThat(board.getRegDt()).isAfter(now);
        assertThat(board.getChgDt()).isAfter(now);
    }
}
