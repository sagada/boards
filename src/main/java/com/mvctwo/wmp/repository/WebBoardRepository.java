package com.mvctwo.wmp.repository;

import com.mvctwo.wmp.domain.QWebBoard;
import com.mvctwo.wmp.domain.WebBoard;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface WebBoardRepository extends JpaRepository<WebBoard, Long>, QuerydslPredicateExecutor<WebBoard> {

    public default Predicate makePredicate(String type, String keyword)
    {
        BooleanBuilder builder = new BooleanBuilder();
        QWebBoard board = QWebBoard.webBoard;

        builder.and(board.bno.gt(0));

        if (type == null)
            return builder;

        switch (type){
            case "t":
                builder.and(board.title.like("%" + keyword + "%"));
                break;
            case "c":
                builder.and(board.content.like("%" + keyword + "%"));
                break;
            case "w":
                builder.and(board.writer.like("%" + keyword + "%"));
                break;
        }
        return builder;
    }


}
