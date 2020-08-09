package com.xiaoshu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.xiaoshu.dao.SchoolMapper;
import com.xiaoshu.dao.StudentMapper;
import com.xiaoshu.dao.UserMapper;
import com.xiaoshu.entity.School;
import com.xiaoshu.entity.Student;
import com.xiaoshu.entity.StudentVo;
import com.xiaoshu.entity.User;
import com.xiaoshu.entity.UserExample;
import com.xiaoshu.entity.UserExample.Criteria;

@Service
public class StudentService {

	@Autowired
	UserMapper userMapper;
	
	@Autowired
	StudentMapper studentMapper;

	
	@Autowired
	SchoolMapper schoolMapper;


	

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

	

	public PageInfo<StudentVo> findUserPage(StudentVo studentVo, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<StudentVo> list = studentMapper.findStudent(studentVo);
		return new PageInfo<>(list);
	}

	public List<School> findSchool(){
		return schoolMapper.selectAll();
	}

	public List<StudentVo> findStudent1(StudentVo studentVo) {
		return studentMapper.findStudent(studentVo);
	}
	
	public Student findByName(String name){
		Student student = new Student();
		student.setName(name);
		return studentMapper.selectOne(student);
	}

}
