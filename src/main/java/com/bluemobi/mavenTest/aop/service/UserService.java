package com.bluemobi.mavenTest.aop.service;

public interface UserService {
	
	Integer add(Integer id, String name);
	
	Integer delete(Integer id);
	
	Integer update(Integer id, String name);
	
	String select(String name);

}
