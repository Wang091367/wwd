package com.xiaoshu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaoshu.dao.DeptMapper;
import com.xiaoshu.dao.EmpMapper;
import com.xiaoshu.dao.UserMapper;
import com.xiaoshu.entity.Dept;
import com.xiaoshu.entity.Emp;
import com.xiaoshu.entity.EmpExample;
import com.xiaoshu.entity.EmpExample.Criteria;
import com.xiaoshu.entity.EmpVo;
@Service
public class EmpService {

	@Autowired
	UserMapper userMapper;

	@Autowired
	EmpMapper empMapper;
	
	@Autowired
	DeptMapper deptMapper;

	// 新增
	public void addUser(Emp e) throws Exception {
		empMapper.insert(e);
	};

	// 修改
	public void updateUser(Emp e) throws Exception {
		empMapper.updateByPrimaryKeySelective(e);
	};

	// 删除
	public void deleteUser(Integer id) throws Exception {
		empMapper.deleteByPrimaryKey(id);
	};



	// 通过用户名判断是否存在，（新增时不能重名）
		public Emp existUserWithUserName(String userName) throws Exception {
			EmpExample example = new EmpExample();
			Criteria criteria = example.createCriteria();
			criteria.andENameEqualTo(userName);
			List<Emp> userList = empMapper.selectByExample(example);
			return userList.isEmpty()?null:userList.get(0);
		};

	//展示
	public PageInfo<EmpVo> findUserPage(EmpVo empVo, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<EmpVo> list = empMapper.findEmp(empVo);
		return new PageInfo<>(list);
	}

	//查询部门
	public List<Dept> findDept(){
		return deptMapper.selectAll();
	}

}
