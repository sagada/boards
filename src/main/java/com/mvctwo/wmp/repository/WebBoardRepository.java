package com.mvctwo.wmp.repository;

import com.mvctwo.wmp.domain.QWebBoard;
import com.mvctwo.wmp.domain.WebBoard;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface WebBoardRepository extends JpaRepository<WebBoard, Long>{

}
