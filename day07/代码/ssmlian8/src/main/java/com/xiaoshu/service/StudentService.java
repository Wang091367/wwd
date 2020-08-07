package com.xiaoshu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.xiaoshu.dao.StudentMapper;
import com.xiaoshu.dao.UserMapper;
import com.xiaoshu.entity.Student;
import com.xiaoshu.entity.User;
import com.xiaoshu.entity.UserExample;
import com.xiaoshu.entity.UserExample.Criteria;

@Service
public class StudentService {

	@Autowired
	StudentMapper studentMapper;

	// 新增
	public void addUser(Student t) throws Exception {
		studentMapper.insert(t);
	};

	// 修改
	public void updateUser(Student t) throws Exception {
		studentMapper.updateByPrimaryKeySelective(t);
	};

	// 删除
	public void deleteUser(Integer id) throws Exception {
		studentMapper.deleteByPrimaryKey(id);
	};


	public PageInfo<Student> findUserPage(Student student, Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<Student> userList = studentMapper.findPage(student);
		PageInfo<Student> pageInfo = new PageInfo<Student>(userList);
		return pageInfo;
	}

	public List<Student> getEcharts() {
		// TODO Auto-generated method stub
		return studentMapper.getEcharts();
	}


}
