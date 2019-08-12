package priv.thinkam.rent.service.impl;

import java.util.List;

import priv.thinkam.rent.common.annotation.BaseService;
import priv.thinkam.rent.common.base.BaseServiceImpl;
import priv.thinkam.rent.dao.mapper.ItemMapper;
import priv.thinkam.rent.dao.model.Item;
import priv.thinkam.rent.dao.model.ItemExample;
import priv.thinkam.rent.dao.model.ItemExample.Criteria;
import priv.thinkam.rent.dao.model.wrap.PageQuery;
import priv.thinkam.rent.service.ItemService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* ItemService实现
* Created by thinkam on 17-12-19.
*/
@Service
@Transactional
@BaseService
public class ItemServiceImpl extends BaseServiceImpl<ItemMapper, Item, ItemExample> implements ItemService {

    private static Logger logger = LoggerFactory.getLogger(ItemServiceImpl.class);

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