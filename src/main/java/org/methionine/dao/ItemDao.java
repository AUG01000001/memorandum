package org.methionine.dao;

import org.methionine.model.po.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ItemDao extends JpaRepository<Item, Integer>,JpaSpecificationExecutor {

    Page<Item> findByUserId(Pageable pageable, Integer userId);
    Page<Item> findByTitleLikeOrContentLike(Pageable pageable,String condition1,String condition2);
}
