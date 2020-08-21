package com.example.demo.repository;

import com.example.demo.domain.FreeBoard;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BoardRepository extends JpaRepository<FreeBoard, Long> {

    List<FreeBoard> findByBnoGreaterThan(Long bno, Pageable page);

    @Query("SELECT b.bno, b.title, count(r) FROM  FreeBoard b LEFT OUTER JOIN b.replyList r WHERE b.bno > 0 GROUP BY b")
    List<Object[]> getPage(Pageable page);
}
