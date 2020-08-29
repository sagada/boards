package com.mvctwo.wmp.repository;

import com.mvctwo.wmp.domain.QWebBoard;
import com.mvctwo.wmp.domain.QWebReply;
import com.mvctwo.wmp.domain.WebBoard;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.JPQLQuery;
import lombok.extern.java.Log;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Log
public class CustomCrudRepositoryImpl extends QuerydslRepositorySupport implements CustomWebBoard {

    public CustomCrudRepositoryImpl()
    {
        super(WebBoard.class);
    }

    @Override
    public Page<Object[]> getCustomPage(String type, String keyword, org.springframework.data.domain.Pageable pageable) {
        log.info("=================================");
        log.info("Type : " + type);
        log.info("Keyword : " + keyword);
        log.info("Page : " + pageable);
        log.info("=================================");

        QWebBoard b = QWebBoard.webBoard;
        QWebReply r = QWebReply.webReply;
        /*
            SELECT
                b.bno, b.title, b.writer, b.regdate
            FROM
                webBoard b
                LEFT OUTER JOIN webReply r
                ON b.bno = r.board_bno
            ....
        */

        JPQLQuery<WebBoard> query = from(b);
        JPQLQuery<Tuple> tuple = query.select(b.bno, b.title, r.count(), b.writer, b.regdate);
        tuple.leftJoin(r);
        tuple.on(b.bno.eq(r.board.bno));
        tuple.where(b.bno.gt(0L));

        if (type != null)
        {
            switch (type.toLowerCase())
            {
                case "t":
                    tuple.where(b.title.like("%" + keyword + "%"));
                    break;
                case "c":
                    tuple.where(b.content.like("%" + keyword + "%"));
                    break;
                case "w":
                    tuple.where(b.writer.like("%" + keyword + "%"));
                    break;
            }
        }
        tuple.groupBy(b.bno);
        tuple.orderBy(b.bno.desc());
        tuple.offset(pageable.getOffset());
        tuple.limit(pageable.getPageSize());

        List<Tuple> list = tuple.fetch();
        List<Object[]> resultList = list.stream().map(Tuple::toArray).collect(Collectors.toList());

        long total = tuple.fetchCount();

        return new PageImpl<>(resultList, pageable, total);
    }
}
