package com.mvctwo.wmp.repository;

import com.mvctwo.wmp.domain.WebBoard;
import org.springframework.data.repository.CrudRepository;

public interface CustomCrudRepository extends CrudRepository<WebBoard, Long>, CustomWebBoard{
}
