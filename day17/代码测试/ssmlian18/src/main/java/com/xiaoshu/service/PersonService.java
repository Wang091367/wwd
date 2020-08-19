package com.xiaoshu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaoshu.dao.CompanyMapper;
import com.xiaoshu.dao.PersonMapper;
import com.xiaoshu.dao.UserMapper;
import com.xiaoshu.entity.Company;
import com.xiaoshu.entity.Person;
import com.xiaoshu.entity.PersonVo;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Service
public class PersonService {

	@Autowired
	UserMapper userMapper;
	
	@Autowired
	PersonMapper personMapper;
	
	@Autowired
	CompanyMapper companyMapper;
	
	// 新增
	public void addUser(Person p) throws Exception {
		personMapper.insert(p);
		Jedis jedis = new Jedis("127.0.0.1",6379);
		Person pp = findByName(p.getExpressName());
		jedis.set(pp.getId()+"", pp.toString());
		
	};

	public Person findByName(String expressName){
		Person p = new Person();
		p.setExpressName(expressName);
		return personMapper.selectOne(p);
	}
	
	// 修改
	public void updateUser(Person p) throws Exception {
		personMapper.updateByPrimaryKeySelective(p);
	};

	// 删除
	public void deleteUser(Integer id) throws Exception {
		personMapper.deleteByPrimaryKey(id);
	};

	

	public PageInfo<PersonVo> findPersonPage(PersonVo personVo, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<PersonVo> list = personMapper.findPersonPage(personVo);
		return new PageInfo<>(list);
	}
	
	public List<Company> findCompany(){
		return companyMapper.selectAll();
	}

	public List<PersonVo> exportPerson(PersonVo personVo) {
		// TODO Auto-generated method stub
		return personMapper.findPersonPage(personVo);
	}

	public List<PersonVo> echartsPerson() {
		// TODO Auto-generated method stub
		return personMapper.echartsPerson();
	}


}
