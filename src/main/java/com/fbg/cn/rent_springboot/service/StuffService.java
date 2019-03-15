package com.fbg.cn.rent_springboot.service;

import com.fbg.cn.rent_springboot.common.base.BaseService;
import com.fbg.cn.rent_springboot.dao.model.Stuff;
import com.fbg.cn.rent_springboot.dao.model.StuffExample;
import com.fbg.cn.rent_springboot.dao.model.wrap.PageQuery;

import java.util.List;

/**
* StuffService接口
* Created by thinkam on 19-03-14.
*/
public interface StuffService extends BaseService<Stuff, StuffExample> {

    List<Stuff> findStuffs(PageQuery pageQuery);

}