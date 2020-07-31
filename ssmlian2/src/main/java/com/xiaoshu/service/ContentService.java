package com.xiaoshu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaoshu.dao.CategoryMapper;
import com.xiaoshu.dao.ContentMapper;
import com.xiaoshu.dao.UserMapper;
import com.xiaoshu.entity.Category;
import com.xiaoshu.entity.Content;
import com.xiaoshu.entity.ContentVo;

@Service
public class ContentService {

	@Autowired
	UserMapper userMapper;
	
	@Autowired
	ContentMapper contentMapper;
	
	@Autowired
	CategoryMapper categoryMapper;

	
	// 新增
	public void addUser(Content content) throws Exception {
		contentMapper.insert(content);
	};

	// 修改
	public void updateUser(Content content) throws Exception {
		contentMapper.updateByPrimaryKeySelective(content);
	};

	// 删除
	public void deleteUser(Integer id) throws Exception {
		contentMapper.deleteByPrimaryKey(id);
	};

	//展示
	public PageInfo<ContentVo> findContentPage(ContentVo contentVo, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<ContentVo> list = contentMapper.findContent(contentVo);
		return new PageInfo<>(list);
	}

	//查询部门
	public List<Category> findCategory(){
		return categoryMapper.selectAll();
	}
	
	
	public int findCidByCname(String categoryname) {
		 Category category = new Category();
		  category.setCategoryname(categoryname);
		 Category one = categoryMapper.selectOne(category);
		  return one.getContentcategoryid();
		 }

}
