package com.fbg.cn.rent_springboot.service.impl;

import com.fbg.cn.rent_springboot.common.annotation.BaseServiceA;
import com.fbg.cn.rent_springboot.common.base.BaseServiceImpl;
import com.fbg.cn.rent_springboot.dao.mapper.StuffMapper;
import com.fbg.cn.rent_springboot.dao.model.Stuff;
import com.fbg.cn.rent_springboot.dao.model.StuffExample;
import com.fbg.cn.rent_springboot.dao.model.StuffExample.Criteria;
import com.fbg.cn.rent_springboot.dao.model.wrap.PageQuery;
import com.fbg.cn.rent_springboot.service.StuffService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* StuffService实现
* Created by thinkam on 19-03-14.
*/
@Service
@Transactional
@BaseServiceA
public class StuffServiceImpl extends BaseServiceImpl<StuffMapper, Stuff, StuffExample> implements StuffService {

    private static Logger _log = LoggerFactory.getLogger(StuffServiceImpl.class);

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