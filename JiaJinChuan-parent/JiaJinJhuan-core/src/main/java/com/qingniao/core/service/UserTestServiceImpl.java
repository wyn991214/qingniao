package com.qingniao.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qingniao.core.dao.UserTestMapper;
import com.qingniao.core.pojo.UserTest;

@Service("userTestService")
public class UserTestServiceImpl implements UserTestService{
	@Autowired
	private UserTestMapper userTestMapper;



	public void insertUserTest2(UserTest userTest) {

		userTestMapper.insertUesrTest(userTest);
	}

}
