package com.mvctwo.wmp.repository;

import com.mvctwo.wmp.domain.WebBoard;
import com.mvctwo.wmp.domain.WebReply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WebReplyRepository extends JpaRepository<WebReply, Long> {

    @Query("SELECT r FROM WebReply r WHERE r.board = ?1 AND r.rno > 0 ORDER BY r.rno ASC")
    public List<WebReply> getRepliesOfBoard(WebBoard board);
}
