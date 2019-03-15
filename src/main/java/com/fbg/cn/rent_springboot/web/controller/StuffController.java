package com.fbg.cn.rent_springboot.web.controller;

import com.fbg.cn.rent_springboot.common.base.Result;
import com.fbg.cn.rent_springboot.dao.model.Stuff;
import com.fbg.cn.rent_springboot.dao.model.StuffExample;
import com.fbg.cn.rent_springboot.dao.model.wrap.PageQuery;
import com.fbg.cn.rent_springboot.service.CategoryService;
import com.fbg.cn.rent_springboot.service.StuffService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;

// * 商品管理


@RestController
@Api(value = "物品管理", description = "物品管理")
public class StuffController {
	private static Logger logger = LoggerFactory.getLogger(StuffController.class);
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private StuffService stuffService;

	@ApiOperation("物品列表")
	@GetMapping("/stuff")
	public Result list() {
		List<Stuff> stuff = stuffService.selectByExample(new StuffExample());
		return new Result(true, stuff);
	}

	@ApiOperation("添加物品")
	@PostMapping("/stuff")
	public Result add(@RequestBody Stuff stuff, HttpServletRequest request) {
		//log
		logger.debug("method add get param:" + stuff);
		//判断空 trim 判断已有 判断类别、状态
		//插入DB
		stuffService.insert(stuff);
		//返回添加的对象
		StuffExample stuffExample = new StuffExample();
		stuffExample.createCriteria().andNameEqualTo(stuff.getName());
		int id = stuffService.selectByExample(stuffExample).get(0).getStuffId();
		stuff.setStuffId(id);
		return new Result(true, stuff);
	}
	
	//上传文件
	@ApiOperation("上传物品图片")
	@PostMapping("/stuff/upload")
	public Result upload(HttpServletRequest request){
//		String path2= request.getServletContext().getRealPath("/");
//		System.out.println(path2);
		MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
		MultipartFile multipartFile = mRequest.getFile("imageFile");// 获得文件
//		String realfileName = multipartFile.getOriginalFilename();
		CommonsMultipartFile cf = (CommonsMultipartFile) multipartFile;
		DiskFileItem fi = (DiskFileItem) cf.getFileItem();
		String path= request.getServletContext().getRealPath("/")+"/resources/upload/"+multipartFile.getOriginalFilename();
//		String path = request.getRequestURI()+multipartFile.getOriginalFilename();
		try {
			multipartFile.transferTo(new File(path));
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		return new Result(true);
	}
	
	//删除物品
	@ApiOperation("删除物品")
	@DeleteMapping("/stuff/{id}")
	public Result delete(@PathVariable(value="id") Integer id){
		int resultId = stuffService.deleteByPrimaryKey(id);
		return new Result(true, resultId);
	}
	//修改物品
	@ApiOperation("获取商品详情")
	@GetMapping("/stuff/{id}")
	public Result findStuffById(@PathVariable(value="id") Integer id){
		Stuff stuff = stuffService.selectByPrimaryKey(id);
		return new Result(true,stuff);
	}
	
	//分页查询商品
	@ApiOperation("分页查询商品列表")
	@PostMapping("/stuffPage")
	public Result findstuffs(@RequestBody PageQuery pageQuery){
		PageQuery page = new PageQuery();
		page.setContent(pageQuery.getContent());
		page.setOffset(pageQuery.getOffset());//start 
		page.setLimit(pageQuery.getLimit());//page 多少页
		List<Stuff> stuffs = stuffService.findStuffs(page);
//		int total = stuffs.size();//总计多少条数据
		List<Stuff> stuffsAll = stuffService.selectByExample(new StuffExample());
		int total =stuffsAll.size();
		int row = total/page.getLimit()+1;  //每页多少条
		return new Result(true, stuffs, total,row);
	}
}
