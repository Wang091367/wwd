package com.xiaoshu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.xiaoshu.dao.TeacherMapper;
import com.xiaoshu.dao.UserMapper;
import com.xiaoshu.entity.Teacher;
import com.xiaoshu.entity.User;
import com.xiaoshu.entity.UserExample;
import com.xiaoshu.entity.UserExample.Criteria;

@Service
public class TeacherService {

	@Autowired
	TeacherMapper teacherMapper;



	public List<Teacher> findTeacher() {
		// TODO Auto-generated method stub
		return teacherMapper.selectAll();
	}


}
