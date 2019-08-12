package priv.thinkam.rent.service.impl;

import java.util.List;

import priv.thinkam.rent.common.annotation.BaseService;
import priv.thinkam.rent.common.base.BaseServiceImpl;
import priv.thinkam.rent.dao.mapper.StuffMapper;
import priv.thinkam.rent.dao.model.Stuff;
import priv.thinkam.rent.dao.model.StuffExample;
import priv.thinkam.rent.dao.model.StuffExample.Criteria;
import priv.thinkam.rent.dao.model.wrap.PageQuery;
import priv.thinkam.rent.service.StuffService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* StuffService实现
* Created by thinkam on 17-12-19.
*/
@Service
@Transactional
@BaseService
public class StuffServiceImpl extends BaseServiceImpl<StuffMapper, Stuff, StuffExample> implements StuffService {

    private static Logger logger = LoggerFactory.getLogger(StuffServiceImpl.class);

    @Autowired
    StuffMapper stuffMapper;

	@Override
	public List<Stuff> findStuffs(PageQuery pageQuery) {
		StuffExample example = new StuffExample();
		example.setOffset(pageQuery.getOffset());
		example.setLimit(pageQuery.getLimit());
		Criteria criteria = example.createCriteria();
		if (pageQuery.getContent()!=null) {
		criteria.andNameLike("%"+pageQuery.getContent()+"%");
		}
		List<Stuff> stuffs = stuffMapper.selectByExample(example);
		return stuffs;
	}

}