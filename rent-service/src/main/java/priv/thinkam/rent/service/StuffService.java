package priv.thinkam.rent.service;

import java.util.List;

import priv.thinkam.rent.common.base.BaseService;
import priv.thinkam.rent.dao.model.Stuff;
import priv.thinkam.rent.dao.model.StuffExample;
import priv.thinkam.rent.dao.model.wrap.PageQuery;

/**
* StuffService接口
* Created by thinkam on 17-12-19.
*/
public interface StuffService extends BaseService<Stuff, StuffExample> {

	List<Stuff> findStuffs(PageQuery pageQuery);
}