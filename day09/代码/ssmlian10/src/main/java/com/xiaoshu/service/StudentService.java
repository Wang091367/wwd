package com.xiaoshu.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaoshu.dao.CourseMapper;
import com.xiaoshu.dao.StudentMapper;
import com.xiaoshu.dao.UserMapper;
import com.xiaoshu.entity.Course;
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
	CourseMapper courseMapper;


	
	// 新增
	public void addUser(Student s) throws Exception {
		studentMapper.insert(s);
	};

	// 修改
	public void updateUser(Student s) throws Exception {
		studentMapper.updateByPrimaryKeySelective(s);
	};


	//展示
	public PageInfo<StudentVo> findStudentPage(StudentVo studentVo, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<StudentVo> list = studentMapper.findStudent(studentVo);
		return new PageInfo<>(list);
	}

	//查询部门
	public List<Course> findCourse(){
		return courseMapper.selectAll();
	}

	//导出
	public List<StudentVo> findpage(StudentVo studentVo) {
		return studentMapper.findStudent(studentVo);
	}
	
	//报表
	public List<StudentVo> echartsStudent(){
		return studentMapper.echartsStudent();
	}

	//添加课程
	public void add1Course(Course course) {
		course.setCreatetime(new Date());
		courseMapper.insert(course);
		Jedis jedis = new Jedis("127.0.0.1",6379);
		Course c = findByNameById(course.getName());
		jedis.set(c.getId()+"", c.toString());
		
	}
	
	public Course findByNameById(String name){
		Course course = new Course();
		course.setName(name);
		return courseMapper.selectOne(course);
	}
	
	

}
