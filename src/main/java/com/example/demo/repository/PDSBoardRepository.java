package com.example.demo.repository;

import com.example.demo.domain.PDSBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface PDSBoardRepository extends JpaRepository<PDSBoard, Long> {
    @Modifying
    @Query("UPDATE PDSFile f SET f.pdsfile = ?2 WHERE f.fno = ?1")
    int updatePDSFile(Long fno, String newFileName);
}
