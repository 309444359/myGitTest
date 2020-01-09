package com.bluemobi.mavenTest.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

import com.bluemobi.mavenTest.aop.service.OrderService;
import com.bluemobi.mavenTest.aop.service.UserService;
import com.bluemobi.mavenTest.aop.service.impl.OrderServiceImpl;
import com.bluemobi.mavenTest.aop.service.impl.UserServiceImpl;
/**
 * 此示例可代理所有的对象
 * @author haoj 309444359@qq.com
 * @date 2018年1月14日 下午1:57:37
 */
public class ServiceLogProxy {
	//要代理的对象
	private Object target;
	
	public ServiceLogProxy(Object target) {
		this.target = target;
	}
	
	public Object getLogProxy(){
		Object proxy = null;
		ClassLoader loader = target.getClass().getClassLoader();
		Class[] interfaces = target.getClass().getInterfaces();
		
		InvocationHandler h = new InvocationHandler(){

			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				
				Object result = null;
				try{
					//1、Before 前置通知
					System.out.println("调用方法前：【"+method.getName()+"】， 方法参数="+Arrays.asList(args));
					
					result = method.invoke(target, args);
					
					//2、After 后置通知
					System.out.println("调用方法后...");
					
				}catch(Exception e){
					e.printStackTrace();
					
					//3、AfterThrowing 异常通知
					System.out.println("出现异常后："+e.toString());
				}
				
				
				//4、AfterReturning 返回通知
				System.out.println("返回结果后：【"+result+"】");
				
				return result;
			}

		};
		
		proxy = Proxy.newProxyInstance(loader, interfaces, h);
		
		return proxy;
	}
	
	public static void main(String[] args) {
		
		UserService target = new UserServiceImpl();
		ServiceLogProxy proxy = new ServiceLogProxy(target);
		UserService userService = (UserService)proxy.getLogProxy();
		userService.select("haoj");
		
		OrderService targetOrder = new OrderServiceImpl();
		ServiceLogProxy proxyOrder = new ServiceLogProxy(targetOrder);
		OrderService orderService = (OrderService)proxyOrder.getLogProxy();
		orderService.selectOrder("商品订单");
		orderService.selectOrder(null);
	}
	
}
