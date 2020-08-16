package com.xiaoshu.service;

import java.util.Date;
import java.util.List;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaoshu.dao.StudentMapper;
import com.xiaoshu.dao.TeacherMapper;
import com.xiaoshu.dao.UserMapper;
import com.xiaoshu.entity.Student;
import com.xiaoshu.entity.StudentVo;
import com.xiaoshu.entity.Teacher;

@Service
public class StudentService {

	@Autowired
	UserMapper userMapper;
	
	@Autowired
	StudentMapper studentMapper;

	@Autowired
	TeacherMapper teacherMapper;
	
	@Autowired
	JmsTemplate jmsTemplate;
	
	@Autowired
	Destination queueTextDestination;

	// 新增
	public void addUser(Student s) throws Exception {
		studentMapper.insert(s);
	};

	// 修改
	public void updateUser(Student s) throws Exception {
		studentMapper.updateByPrimaryKeySelective(s);
	};

	// 删除
	public void deleteUser(Integer id) throws Exception {
		studentMapper.deleteByPrimaryKey(id);
	};

	
	

	public PageInfo<StudentVo> findStudentPage(StudentVo studentVo, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<StudentVo> list = studentMapper.findStudentPage(studentVo);
		return new PageInfo<>(list);
	}
	
	public List<Teacher> findTeacher(){
		return teacherMapper.selectAll();
	}

	public Student existUserWithUserName(String code) {
		Student student = new Student();
		student.setCode(code);
		return studentMapper.selectOne(student);
	}

	public List<StudentVo> findEcharts() {
		// TODO Auto-generated method stub
		return studentMapper.findEcharts();
	}

	public void add1Student(final Teacher teacher) {
		teacher.setEntrytime(new Date());
		teacherMapper.insert(teacher);
		
		jmsTemplate.send(queueTextDestination, new MessageCreator() {
			
			@Override
			public Message createMessage(Session session) throws JMSException {
				// TODO Auto-generated method stub
				String JSON = JSONObject.toJSONString(teacher);
				return session.createTextMessage(JSON);
			}
		});
	}

	



}
