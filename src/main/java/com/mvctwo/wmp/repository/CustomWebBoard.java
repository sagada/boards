package com.mvctwo.wmp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface CustomWebBoard {
    public Page<Object[]> getCustomPage(String type, String keyword, Pageable pageable);
}
