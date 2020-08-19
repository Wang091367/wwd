package com.xiaoshu.controller;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.xiaoshu.entity.Person;

import redis.clients.jedis.JedisPool;

public class MyMessageListener implements MessageListener{

	
	@Override
	public void onMessage(Message message) {
		// TODO Auto-generated method stub
		TextMessage m = (TextMessage) message; 
		try {
			String text = m.getText();
			Person person = JSONObject.parseObject(text, Person.class);
			System.out.println(person);
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
}
