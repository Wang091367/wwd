package com.xiaoshu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaoshu.dao.GoodsMapper;
import com.xiaoshu.dao.GoodstypeMapper;
import com.xiaoshu.dao.UserMapper;
import com.xiaoshu.entity.Goods;
import com.xiaoshu.entity.GoodsVo;
import com.xiaoshu.entity.Goodstype;
import com.xiaoshu.entity.User;

@Service
public class GoodsService {

	@Autowired
	UserMapper userMapper;
	
	@Autowired
	GoodsMapper goodsMapper;

	@Autowired
	GoodstypeMapper goodstypeMapper;

	// 新增
	public void addUser(Goods g) throws Exception {
		goodsMapper.insert(g);
	};

	// 修改
	public void updateUser(Goods g) throws Exception {
		goodsMapper.updateByPrimaryKeySelective(g);
	};

	// 删除
	public void deleteUser(Integer id) throws Exception {
		goodsMapper.deleteByPrimaryKey(id);
	};

	//展示
	public PageInfo<GoodsVo> findGoodsPage(GoodsVo goodsVo, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<GoodsVo> list = goodsMapper.findGoods(goodsVo);
		return new PageInfo<>(list);
	}
	
	//查询部门
	public List<Goodstype> findType(){
		return goodstypeMapper.selectAll();
	}


}
