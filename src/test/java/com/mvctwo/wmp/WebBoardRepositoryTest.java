package com.mvctwo.wmp;

import com.mvctwo.wmp.domain.WebBoard;
import com.mvctwo.wmp.repository.WebBoardRepository;
import lombok.extern.java.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.IntStream;

@RunWith(SpringRunner.class)
@SpringBootTest
@Commit
@Log
public class WebBoardRepositoryTest {

    @Autowired
    private WebBoardRepository webBoardRepository;

    @Test
    public void insertBoardDummies()
    {
        IntStream.range(0, 300).forEach(i->{
            WebBoard board = new WebBoard();
            board.setTitle("Sample Board Title");
            board.setContent("Content Sample ... " + i + " of Board");
            board.setWriter("user0" + (i%10));
            webBoardRepository.save(board);
        });
    }

    @Test
    public void testList1()
    {
        Pageable pageable = PageRequest.of(0, 20, Sort.Direction.DESC, "bno");

        Page<WebBoard> result = webBoardRepository.findAll(webBoardRepository.makePredicate("w", "09"), pageable);

        log.info("PAGE: " + result.getPageable());
        log.info("--------------");

        result.getContent().forEach(s-> log.info("" + s));
    }


}
