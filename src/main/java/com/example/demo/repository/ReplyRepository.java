package com.example.demo.repository;

import com.example.demo.domain.FreeBoardReply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyRepository extends JpaRepository<FreeBoardReply, Long> {
}
