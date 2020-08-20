package com.example.demo.repository;

import com.example.demo.domain.PDSBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PDSBoardRepository extends JpaRepository<PDSBoard, Long> {
    @Modifying
    @Query("UPDATE PDSFile f SET f.pdsfile = ?2 WHERE f.fno = ?1")
    int updatePDSFile(Long fno, String newFileName);

    @Query("SELECT p, count (f) FROM PDSBoard p LEFT OUTER JOIN p.pdsFileList f WHERE p.pid > 0 GROUP BY p ORDER BY p.pid DESC")
    List<Object[]> getSummery();
}
