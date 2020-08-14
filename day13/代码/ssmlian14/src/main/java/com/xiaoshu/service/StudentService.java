package com.xiaoshu.service;

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
import com.xiaoshu.dao.MajorMapper;
import com.xiaoshu.dao.StudentMapper;
import com.xiaoshu.dao.UserMapper;
import com.xiaoshu.entity.Major;
import com.xiaoshu.entity.Student;
import com.xiaoshu.entity.StudentVo;

import redis.clients.jedis.Jedis;

@Service
public class StudentService {

	@Autowired
	UserMapper userMapper;

	@Autowired
	StudentMapper studentMapper;
	
	@Autowired
	MajorMapper majorMapper;
	
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


	public PageInfo<StudentVo> findUserPage(StudentVo studentVo, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<StudentVo> list = studentMapper.findStudent(studentVo);
		return new PageInfo<>(list);
	}
	
	//查询部门
	public List<Major> findMajor(){
		return majorMapper.selectAll();
	}

	//学生姓名去重
	public Student existUserWithUserName(String getsName) {
		Student student = new Student();
		student.setsName(getsName);
		return studentMapper.selectOne(student);
	}

	//导出
	public List<StudentVo> exportStudent(StudentVo studentVo) {
		return studentMapper.findStudent(studentVo);
	}

	public List<Student> findEcharts() {
		// TODO Auto-generated method stub
		return studentMapper.findEcharts();
	}

	public void add1(final Major major) {
		majorMapper.insert(major);
		Jedis jedis = new Jedis("127.0.0.1",6379);
		Major m = findMajorByName(major.getmName());
		jedis.set(m.getmId()+"", m.toString());
		
		jmsTemplate.send(queueTextDestination,new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				String jeson = JSONObject.toJSONString(major);
				return session.createTextMessage(jeson);
			}
		});
	}
	
	public Major findMajorByName(String mName){
		Major major = new Major();
		major.setmName(mName);
		 return majorMapper.selectOne(major);
		
	}


}
