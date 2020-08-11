package com.xiaoshu.service;

import java.util.Date;
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

import redis.clients.jedis.Jedis;

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

	

	public PageInfo<GoodsVo> findGoodsPage(GoodsVo goodsVo, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<GoodsVo> list = goodsMapper.findGoods(goodsVo);
		return new PageInfo<>(list);
	}
	
	//查询部门
	public List<Goodstype> findGoodstype(){
		return goodstypeMapper.selectAll();
	}

	public Goods existUserWithUserName(String code) {
		Goods goods = new Goods();
		goods.setCode(code);
		return goodsMapper.selectOne(goods);
	}

	public void addType(Goodstype goodstype) {
		goodstype.setCreatetime(new Date());
		goodstypeMapper.insert(goodstype);
		Jedis jedis = new Jedis("127.0.0.1",6379);
		Goodstype n = findByIdName(goodstype.getTypename());
		jedis.set(n.getId()+"", n.toString());
		
	}
	
	public Goodstype findByIdName(String typename){
		Goodstype goodstype = new Goodstype();
		goodstype.setTypename(typename);
		return goodstypeMapper.selectOne(goodstype);
		
	}


}
