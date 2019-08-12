package priv.thinkam.rent.service;

import java.util.List;

import priv.thinkam.rent.common.base.BaseService;
import priv.thinkam.rent.dao.model.Item;
import priv.thinkam.rent.dao.model.ItemExample;
import priv.thinkam.rent.dao.model.wrap.PageQuery;

/**
* ItemService接口
* Created by thinkam on 17-12-19.
*/
public interface ItemService extends BaseService<Item, ItemExample> {

	List<Item> findItems(PageQuery page);
}