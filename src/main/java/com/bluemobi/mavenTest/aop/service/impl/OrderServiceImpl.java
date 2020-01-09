package com.bluemobi.mavenTest.aop.service.impl;

import org.springframework.stereotype.Service;

import com.bluemobi.mavenTest.aop.service.OrderService;
@Service
public class OrderServiceImpl implements OrderService {

	@Override
	public Integer addOrder(Integer id, String name) {
		return 111;
	}

	@Override
	public Integer deleteOrder(Integer id) {
		return 222;
	}

	@Override
	public Integer updateOrder(Integer id, String name) {
		return 333;
	}

	@Override
	public String selectOrder(String name) {
		if(name==null){
			throw new RuntimeException("模拟异常！");
		}
		return "id=1001,name="+name;
	}

}
