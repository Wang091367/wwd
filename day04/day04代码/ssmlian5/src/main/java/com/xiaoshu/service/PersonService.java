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
	};

	// 修改
	public void updateUser(Person p) throws Exception {
		personMapper.updateByPrimaryKeySelective(p);
	};

	// 删除
	public void deleteUser(Integer id) throws Exception {
		personMapper.deleteByPrimaryKey(id);
	};

	//展示
	public PageInfo<PersonVo> findUserPage(PersonVo personVo, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<PersonVo> list = personMapper.findPerson(personVo);
		return new PageInfo<>(list);
	}

	//查询部门
	public List<Company> findCompany(){
		return companyMapper.selectAll();
	}

}
