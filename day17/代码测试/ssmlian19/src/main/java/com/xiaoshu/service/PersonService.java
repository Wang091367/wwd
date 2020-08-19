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
import com.xiaoshu.entity.User;

import redis.clients.jedis.Jedis;

@Service
public class PersonService {

	@Autowired
	UserMapper userMapper;


	@Autowired
	CompanyMapper companyMapper;
	
	@Autowired
	PersonMapper personMapper;
	
	// 新增
	public void addUser(Person p) throws Exception {
		personMapper.insert(p);
	};
	
	public void addUser1(Person p) throws Exception {
		personMapper.insert(p);
		Jedis jedis = new Jedis("127.0.0.1",6379);
		Person ppp = findByPersonId(p.getpName());
		jedis.set(ppp.getpName(), ppp.toString());
	};
	
	public Person findByPersonId(String pName){
		Person person = new Person();
		person.setpName(pName);		
		return personMapper.selectOne(person);
		
		
	}

	// 修改
	public void updateUser(Person p) throws Exception {
		personMapper.updateByPrimaryKeySelective(p);
	};

	// 删除
	public void deleteUser(Integer id) throws Exception {
		personMapper.deleteByPrimaryKey(id);
	};


	
	public PageInfo<PersonVo> findPerPage(PersonVo personVo, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<PersonVo> list = personMapper.findPerPage(personVo);
		return new PageInfo<>(list);
	}
	
	public List<Company> findCompany(){
		return companyMapper.selectAll();
		
	}

	public List<PersonVo> findExport(PersonVo personVo) {
		// TODO Auto-generated method stub
		return personMapper.findPerPage(personVo);
	}


}
