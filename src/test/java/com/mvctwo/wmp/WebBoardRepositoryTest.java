package com.mvctwo.wmp;

import com.mvctwo.wmp.domain.WebBoard;
import com.mvctwo.wmp.domain.WebReply;
import com.mvctwo.wmp.repository.WebBoardRepository;
import lombok.extern.java.Log;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Commit
@Log
public class WebBoardRepositoryTest {

    @Autowired
    private WebBoardRepository webBoardRepository;

    @Test
    public void initBoard()
    {
        String userName = "지성빠랙";
        String content = "content";
        String title = "title";

        for (int i = 0 ; i < 1000; i++)
        {
            WebBoard board = new WebBoard();

            board.setContent(content + i);
            board.setTitle(title + i);
            board.setWriter(userName + i);

            List<WebReply> replies = Lists.newArrayList(
                    WebReply
                            .builder()
                            .replyer("박지성")
                            .board(board)
                            .replyText("팍 팍 팍 지서엉")
                            .build(),

                    WebReply
                            .builder()
                            .replyer("기성용")
                            .replyText("기라드")
                            .board(board)
                            .build(),
                    WebReply
                            .builder()
                            .replyer("구자")
                            .replyText("아랍왕")
                            .board(board)
                            .build(),

                    WebReply
                            .builder()
                            .replyer("박주호")
                            .replyText("슈돌")
                            .board(board)
                            .build()
            );

            board.setReplies(replies);
            webBoardRepository.save(board);
        }
    }
}
