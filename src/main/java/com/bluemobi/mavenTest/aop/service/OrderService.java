package com.bluemobi.mavenTest.aop.service;

public interface OrderService {
	
	Integer addOrder(Integer id, String name);
	
	Integer deleteOrder(Integer id);
	
	Integer updateOrder(Integer id, String name);
	
	String selectOrder(String name);

}
