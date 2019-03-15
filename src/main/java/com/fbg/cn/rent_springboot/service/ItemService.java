package com.fbg.cn.rent_springboot.service;

import com.fbg.cn.rent_springboot.common.base.BaseService;
import com.fbg.cn.rent_springboot.dao.model.Item;
import com.fbg.cn.rent_springboot.dao.model.ItemExample;
import com.fbg.cn.rent_springboot.dao.model.wrap.PageQuery;

import java.util.List;

/**
* ItemService接口
* Created by thinkam on 19-03-14.
*/
public interface ItemService extends BaseService<Item, ItemExample> {

    List<Item> findItems(PageQuery page);
}