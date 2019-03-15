package com.fbg.cn.rent_springboot.service.impl;

import com.fbg.cn.rent_springboot.common.annotation.BaseServiceA;
import com.fbg.cn.rent_springboot.common.base.BaseServiceImpl;
import com.fbg.cn.rent_springboot.dao.mapper.ItemMapper;
import com.fbg.cn.rent_springboot.dao.model.Item;
import com.fbg.cn.rent_springboot.dao.model.ItemExample;
import com.fbg.cn.rent_springboot.dao.model.ItemExample.Criteria;
import com.fbg.cn.rent_springboot.dao.model.wrap.PageQuery;
import com.fbg.cn.rent_springboot.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* ItemService实现
* Created by thinkam on 19-03-14.
*/
@Service
@Transactional
@BaseServiceA
public class ItemServiceImpl extends BaseServiceImpl<ItemMapper, Item, ItemExample> implements ItemService {

    private static Logger _log = LoggerFactory.getLogger(ItemServiceImpl.class);

    @Autowired
    ItemMapper itemMapper;

    @Override
    public List<Item> findItems(PageQuery page) {
        ItemExample itemExample = new ItemExample();
        itemExample.setOffset(page.getOffset());
        itemExample.setLimit(page.getLimit());
        Criteria criteria = itemExample.createCriteria();
		/*if (page.getContent()!=null) {

		}*/
        List<Item> items = itemMapper.selectByExample(itemExample);
        return items;
    }

}