package com.fbg.cn.rent_springboot.service.impl;

import com.fbg.cn.rent_springboot.common.annotation.BaseServiceA;
import com.fbg.cn.rent_springboot.common.base.BaseServiceImpl;
import com.fbg.cn.rent_springboot.dao.mapper.CategoryMapper;
import com.fbg.cn.rent_springboot.dao.model.Category;
import com.fbg.cn.rent_springboot.dao.model.CategoryExample;
import com.fbg.cn.rent_springboot.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* CategoryService实现
* Created by thinkam on 19-03-14.
*/
@Service
@Transactional
@BaseServiceA
public class CategoryServiceImpl extends BaseServiceImpl<CategoryMapper, Category, CategoryExample> implements CategoryService {

    private static Logger _log = LoggerFactory.getLogger(CategoryServiceImpl.class);

    @Autowired
    CategoryMapper categoryMapper;

}