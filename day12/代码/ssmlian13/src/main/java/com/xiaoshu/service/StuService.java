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
import com.xiaoshu.dao.StuMapper;
import com.xiaoshu.dao.UserMapper;
import com.xiaoshu.entity.Major;
import com.xiaoshu.entity.Stu;
import com.xiaoshu.entity.StuVo;

import redis.clients.jedis.Jedis;

@Service
public class StuService {

	@Autowired
	UserMapper userMapper;

	@Autowired
	StuMapper stuMapper;
	
	@Autowired
	MajorMapper majorMapper;
	
	@Autowired
	JmsTemplate jmsTemplate;
	
	//destination
	@Autowired
	Destination queueTextDestination;
	
	// 新增
	public void addUser(Stu s) throws Exception {
		stuMapper.insert(s);
	};

	// 修改
	public void updateUser(Stu s) throws Exception {
		stuMapper.updateByPrimaryKeySelective(s);
	};

	// 删除
	public void deleteUser(Integer id) throws Exception {
		stuMapper.deleteByPrimaryKey(id);
	};

	//展示
	public PageInfo<StuVo> findStuPage(StuVo StuVo, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<StuVo> list = stuMapper.findStu(StuVo);
		return new PageInfo<>(list);
	}
	
	//查询部门
	public List<Major> findMajor(){
		return majorMapper.selectAll();
		
	}

	//导出
	public List<StuVo> findExportStu(StuVo stuVo) {
		return stuMapper.findStu(stuVo);
	}

	public List<StuVo> findEchartsStu() {
		// TODO Auto-generated method stub
		return stuMapper.findEchartsStu();
	}

	public void addStu1(final Major major) {
		majorMapper.insert(major);
		Jedis jedis = new Jedis("127.0.0.1",6379);
		Major byName = findMajorByName(major.getMdname());
		jedis.set(byName.getMdname(), byName.getMdId()+"");
		
		jmsTemplate.send(queueTextDestination, new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				String json = JSONObject.toJSONString(major);
				return session.createTextMessage(json);
			}
		});
		
	}
	
	public Major findMajorByName(String mdname){
		Major major = new Major();
		major.setMdname(mdname);
		return  majorMapper.selectOne(major);
		 
	}


}
