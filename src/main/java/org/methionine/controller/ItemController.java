package org.methionine.controller;

import org.methionine.model.Result;
import org.methionine.model.dto.ItemDTO;
import org.methionine.model.po.Item;
import org.methionine.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("item")
@CrossOrigin
public class ItemController {

    @Autowired
    ItemService itemService;

    @GetMapping("getAllItem")
    public Result getAllItem(HttpSession httpSession,
                             @RequestParam(defaultValue = "0") Integer page,
                             @RequestParam(defaultValue = "20") Integer size){
        Integer userId = (Integer) httpSession.getAttribute("id");

        Page<Item> items = itemService.getAllItemByUserId(userId,page, size);
        if (items != null){
            return new Result("查询成功", items);
        }else {
            return new Result("查询失败", null);
        }

    }

    @GetMapping("getOneItem")
    public Result getOneItem(Integer id){
        Item item = itemService.getOneItem(id);
        if (item != null){
            return new Result("查询成功", item);
        }else {
            return new Result("查询失败", null);
        }

    }

    @PostMapping("addItem")
    public Result addItem(HttpSession httpSession, ItemDTO itemDTO){
        Integer userId = (Integer) httpSession.getAttribute("id");
        Item newItem = itemService.addItem(itemDTO, userId);
        if (newItem != null){
            return new Result("插入成功", newItem);
        }else {
            return new Result("插入失败", null);
        }
    }

    @GetMapping("deleteItem")
    public Result deleteItem(Integer id){
        boolean deleteItem=itemService.deleteItemById(id);
        if(deleteItem=true){
            return new Result("删除成功", null);
        }else{
            return new Result("删除失败", null);
        }
    }
    @PostMapping("updateItem")
    public Result updateItem(ItemDTO itemDTO){
        Item newItem = itemService.updateItemById(itemDTO);
        if (newItem != null){
            return new Result("更新成功", newItem);
        }else {
            return new Result("更新失败", null);
        }
    }

    @PostMapping("queryItem")
    public Result queryItem(String condition,
                             @RequestParam(defaultValue = "0") Integer page,
                             @RequestParam(defaultValue = "20") Integer size){

        Page<Item> items = itemService.queryItem(condition,page,size);
        if (items != null){
            return new Result("查询成功", items);
        }else {
            return new Result("查询失败", null);
        }

    }
}
