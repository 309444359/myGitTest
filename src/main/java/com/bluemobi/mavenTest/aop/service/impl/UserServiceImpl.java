package com.bluemobi.mavenTest.aop.service.impl;

import org.springframework.stereotype.Service;

import com.bluemobi.mavenTest.aop.service.UserService;
@Service
public class UserServiceImpl implements UserService {

	@Override
	public Integer add(Integer id, String name) {
		return 1;
	}

	@Override
	public Integer delete(Integer id) {
		return 1;
	}

	@Override
	public Integer update(Integer id, String name) {
		return 1;
	}

	@Override
	public String select(String name) {
		return "id=1001,name="+name;
	}

}
