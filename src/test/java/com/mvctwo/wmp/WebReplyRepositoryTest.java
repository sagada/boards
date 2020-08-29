package com.mvctwo.wmp;

import com.mvctwo.wmp.repository.WebReplyRepository;
import lombok.extern.java.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootTest
@Log
@Commit
@RunWith(SpringRunner.class)
public class WebReplyRepositoryTest {

    @Autowired
    private WebReplyRepository webReplyRepository;


    @Test
    public void testInertReplies()
    {

    }
}
