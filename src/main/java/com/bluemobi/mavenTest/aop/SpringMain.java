package com.bluemobi.mavenTest.aop;

import com.bluemobi.mavenTest.aop.service.impl.OrderServiceImpl;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bluemobi.mavenTest.aop.service.OrderService;

import java.util.concurrent.TimeUnit;

public class SpringMain {
	
	public static void main(String[] args) {
		
		//1. 创建 IOC 容器
		//ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-aop.xml");
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-aop-xml.xml");

		//2. 从 IOC 容器中获取 bean 实例
		OrderService orderService = (OrderService) context.getBean("orderServiceImpl");

		//3. 调用 bean 的方法
		Integer count = orderService.addOrder(888, "haoj~~");
		System.out.println("----->count:"+count);
		
		String result = orderService.selectOrder("天空蓝~~");
//		String result = orderService.selectOrder(null);
		System.out.println("----->result:"+result);
		
		//4. 关闭容器
		context.close();
		
	}

}
