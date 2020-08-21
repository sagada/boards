package com.example.demo.board;

import com.example.demo.domain.FreeBoard;
import com.example.demo.domain.FreeBoardReply;
import com.example.demo.repository.ReplyRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BoardReplyTest {
    @Autowired
    private ReplyRepository replyRepository;

    @Test
    public void 댓글__추가_테스트(){
        FreeBoard board = new FreeBoard();
        board.setBno(199L);

        FreeBoardReply reply = new FreeBoardReply();
        reply.setReplyer("RRR");
        reply.setBoard(board);
        reply.setReplyer("Replyer");

        replyRepository.save(reply);
    }
}
