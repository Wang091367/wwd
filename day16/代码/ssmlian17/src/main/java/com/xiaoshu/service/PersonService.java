package com.xiaoshu.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaoshu.dao.BankMapper;
import com.xiaoshu.dao.PersonMapper;
import com.xiaoshu.dao.UserMapper;
import com.xiaoshu.entity.Bank;
import com.xiaoshu.entity.Person;
import com.xiaoshu.entity.PersonVo;

import redis.clients.jedis.Jedis;

@Service
public class PersonService {

	@Autowired
	UserMapper userMapper;
	
	@Autowired
	PersonMapper personMapper;
	
	@Autowired
	BankMapper bankMapper;


	// 新增
	public void addUser(Person p) throws Exception {
		personMapper.insert(p);
	};

	// 修改
	public void updateUser(Person p) throws Exception {
		personMapper.updateByPrimaryKeySelective(p);
	};

	// 删除
	public void deleteUser(Integer id) throws Exception {
		personMapper.deleteByPrimaryKey(id);
	};

	
	public PageInfo<PersonVo> findPersonPage(PersonVo PersonVo, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<PersonVo> list = personMapper.findPersonPage(PersonVo);
		return new PageInfo<>(list);
	}
	
	//查询部门
		public List<Bank> findBank(){
			return bankMapper.selectAll();
		}
		
		public List<PersonVo> echartsPerson() {
			return personMapper.echartsPerson();
		}
		
		public void add1Person(Bank bank) {
			bank.setCreatetime(new Date());
			bankMapper.insert(bank);
			
			Jedis jedis = new Jedis("127.0.0.1",6379);
			Bank b = findByBname(bank.getbName());
			jedis.set(b.getbId()+"", b.toString());
		}
		
		public Bank findByBname(String bName){
			Bank bank = new Bank();
			bank.setbName(bName);
			return bankMapper.selectOne(bank);
		}

		public List<PersonVo> exportPerson(PersonVo personVo) {
			return personMapper.findPersonPage(personVo);
		}


}
