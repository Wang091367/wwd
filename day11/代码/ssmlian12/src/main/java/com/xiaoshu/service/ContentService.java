package com.xiaoshu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaoshu.dao.ContentMapper;
import com.xiaoshu.dao.ContentcategoryMapper;
import com.xiaoshu.dao.UserMapper;
import com.xiaoshu.entity.Content;
import com.xiaoshu.entity.ContentVo;
import com.xiaoshu.entity.Contentcategory;
import com.xiaoshu.entity.User;

@Service
public class ContentService {

	@Autowired
	UserMapper userMapper;

	@Autowired
	ContentMapper contentMapper;

	@Autowired
	ContentcategoryMapper contentcategoryMapper;
	// 新增
	public void addUser(Content c) throws Exception {
		contentMapper.insert(c);
	};

	// 修改
	public void updateUser(Content c) throws Exception {
		contentMapper.updateByPrimaryKeySelective(c);
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
	
	public List<Contentcategory> findContentcategory(){
		return contentcategoryMapper.selectAll();
	}

	/*public Content existUserWithContentPrice(Integer price) {
		Content content = new Content();
		content.setPrice(price);
		return contentMapper.selectOne(content);
	}*/

	public List<ContentVo> echartsContent() {
		// TODO Auto-generated method stub
		return contentMapper.echartsContent();
	}


}
