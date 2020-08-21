package com.example.demo.board;

import com.example.demo.domain.FreeBoard;
import com.example.demo.domain.FreeBoardReply;
import com.example.demo.repository.BoardRepository;
import com.example.demo.repository.ReplyRepository;
import lombok.extern.java.Log;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;


import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;


@RunWith(SpringRunner.class)
@SpringBootTest
@Log
@Commit
public class FreeBoardTests {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private ReplyRepository replyRepository;

    @Test
    public void insert_게시글()
    {
        IntStream.range(1, 200).forEach(i->{
            FreeBoard board = new FreeBoard();
            board.setTitle("Free Board ... " + i);
            board.setContent("Free content "+ i);
            board.setWriter("user"+ (i%10));

            boardRepository.save(board);
        });
    }

    @Test
    @Transactional
    public void insert_댓글()
    {
        Optional<FreeBoard> result = boardRepository.findById(199L);

        result.ifPresent(board->{
            List<FreeBoardReply> replies = board.getReplyList();
            FreeBoardReply reply = new FreeBoardReply();

            reply.setReply("Reply........");
            reply.setReplyer("replyer00");
            reply.setBoard(board);

            replies.add(reply);

            board.setReplyList(replies);
            boardRepository.save(board);
        });
    }

    @Test
    @Transactional
    public void testList1(){
        Pageable pageable = PageRequest.of(0, 10, Sort.Direction.DESC, "bno");
        boardRepository.findByBnoGreaterThan(0L, pageable).forEach(board->{
            log.info(board.getBno() + " : " + board.getTitle() +  " / " + board.getReplyList().size());
        });
    }

    @Test
    public void testList3(){
        Pageable page = PageRequest.of(0, 10, Sort.Direction.DESC, "bno");
        boardRepository.getPage(page).forEach(Arrays::toString);
    }

}
