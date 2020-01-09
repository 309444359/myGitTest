package com.bluemobi.mavenTest.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

import com.bluemobi.mavenTest.aop.service.UserService;
import com.bluemobi.mavenTest.aop.service.impl.UserServiceImpl;
/**
 * 单一Service的代理类，跟实际差距较大，容易看懂
 * @author haoj 309444359@qq.com
 * @date 2018年1月14日 下午1:59:41
 */
public class UserServiceLogProxy {
	//要代理的对象
	private UserService target;
	
	public UserServiceLogProxy(UserService target) {
		this.target = target;
	}
	
	public UserService getLogProxy(){
		UserService proxy = null;
		ClassLoader loader = target.getClass().getClassLoader();
		Class[] interfaces = new Class[]{UserService.class};
		
		InvocationHandler h = new InvocationHandler(){

			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				System.out.println("开始调用方法：【"+method.getName()+"】， 方法参数="+Arrays.asList(args));
				Object result = null;
				result = method.invoke(target, args);
				System.out.println("调用方法结束返回结果：【"+result+"】");
				return result;
			}

		};
		
		proxy = (UserService)Proxy.newProxyInstance(loader, interfaces, h);
		
		return proxy;
	}
	
	public static void main(String[] args) {
		
		UserService target = new UserServiceImpl();
		UserServiceLogProxy proxy = new UserServiceLogProxy(target);
		
		UserService userService = proxy.getLogProxy();
		userService.select("haoj");
		
	}
	
}
