package org.methionine.service;

import org.methionine.dao.ItemDao;
import org.methionine.model.dto.ItemDTO;
import org.methionine.model.po.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;

@Service
public class ItemService {

    @Autowired
    ItemDao itemDao;

    public Item addItem(ItemDTO itemDTO, Integer userId){
        return itemDao.save(itemDTO2Item(itemDTO, userId));
    }

    public Page<Item> getAllItemByUserId(Integer userId, int page, int size){
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(page, size, sort);
        Page<Item> result = itemDao.findByUserId(pageable, userId);
        return result;
    }

    public Item getOneItem(Integer id){
        return itemDao.findOne(id);
    }

    private Item itemDTO2Item(ItemDTO itemDTO, Integer userId){
        Item item = new Item();
        item.setId(itemDTO.getId());
        item.setUserId(userId);
        item.setTitle(itemDTO.getTitle());
        item.setContent(itemDTO.getContent());
        return item;
    }

    //删除
    public boolean deleteItemById(Integer id){
        itemDao.delete(id);
        return true;
    }

    //修改
    public Item updateItemById(ItemDTO itemDTO){
        Item item = getOneItem(itemDTO.getId());
        item.setId(itemDTO.getId());
        item.setTitle(itemDTO.getTitle());
        item.setContent(itemDTO.getContent());

        Date date = new Date();
        item.setLastUpdateTime(new Timestamp(date.getTime()));
        return itemDao.save(item);
    }
    //条件查询
    public Page<Item> queryItem(String condition,int page, int size){
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(page, size, sort);
        condition = "%" + condition + "%";
        return itemDao.findByTitleLikeOrContentLike(pageable,condition,condition);
    }
}
