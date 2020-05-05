package com.bluemobi.mavenTest.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
/**
 * AOP 的 helloWorld
 * 1. 加入 jar 包
 * com.springsource.net.sf.cglib-2.2.0.jar
 * com.springsource.org.aopalliance-1.0.0.jar
 * com.springsource.org.aspectj.weaver-1.6.8.RELEASE.jar
 * spring-aspects-4.0.0.RELEASE.jar
 * 
 * 2. 在 Spring 的配置文件中加入 aop 的命名空间。 
 * 
 * 3. 基于注解的方式来使用 AOP
 * 3.1 在配置文件中配置自动扫描的包: <context:component-scan base-package="com.atguigu.spring.aop"></context:component-scan>
 * 3.2 加入使 AspjectJ 注解起作用的配置: <aop:aspectj-autoproxy></aop:aspectj-autoproxy>
 * 为匹配的类自动生成动态代理对象. 
 * 
 * 4. 编写切面类: 
 * 4.1 一个一般的 Java 类
 * 4.2 在其中添加要额外实现的功能. 
 *
 * 5. 配置切面
 * 5.1 切面必须是 IOC 中的 bean: 实际添加了 @Component 注解
 * 5.2 声明是一个切面: 添加 @Aspect
 * 5.3 声明通知: 即额外加入功能对应的方法. 
 * 5.3.1 前置通知: @Before("execution(* com.bluemobi.mavenTest.aop.service.*.*(..))")
 * @Before 表示在目标方法执行之前执行 @Before 标记的方法的方法体. 
 * @Before 里面的是切入点表达式: 
 * 
 * 6. 在通知中访问连接细节: 可以在通知方法中添加 JoinPoint 类型的参数, 从中可以访问到方法的签名和方法的参数. 
 * 
 * 7. @After 表示后置通知: 在方法执行之后执行的代码. 
 */


@Order(2)	//可以使用 @Order 注解指定切面的优先级, 值越小优先级越高
@Aspect		//通过添加 @Aspect 注解声明一个 bean 是一个切面!
@Component
public class LogAspect {
	
	/**
	 * 定义一个方法, 用于声明切入点表达式. 一般地, 该方法中再不需要添入其他的代码. 
	 * 使用 @Pointcut 来声明切入点表达式. 
	 * 后面的其他通知直接使用方法名来引用当前的切入点表达式. 
	 */
	@Pointcut("execution(* com.bluemobi.mavenTest.aop.service.*.*(..))")
	public void serviceJoinPoint(){};
	
	@Before("serviceJoinPoint()")
	//@Before("LogAspect().serviceJoinPoint()") //同包不同类写法
	//@Before("com.bluemobi.mavenTest.aop.LogAspect.serviceJoinPoint()") //不同包的写法
	//@Before("execution(* com.bluemobi.mavenTest.aop.service.*.*(..))")
	public void beforeMethod(JoinPoint joinPoint){
		String methodName = joinPoint.getSignature().getName();
		Object[] args = joinPoint.getArgs();
		System.out.println("调用方法【" + methodName + "】 开始之前！方法参数：【" + Arrays.asList(args)+ "】" );
	}
	
	@After("serviceJoinPoint()")
	public void afterMethod(JoinPoint joinPoint){
		String methodName = joinPoint.getSignature().getName();
		System.out.println("调用方法【" + methodName + "】 结束之后！" );
	}
	
	
	@AfterThrowing(value="serviceJoinPoint()",throwing="e")
	public void afterThrowing(JoinPoint joinPoint,Exception e){
		String methodName = joinPoint.getSignature().getName();
		System.out.println("调用方法【" + methodName + "】异常抛出后！异常信息：【 "+e.toString()+"】" );
	}
	
	@AfterReturning(value="execution(* com.bluemobi.mavenTest.aop.service.*.*(..))",returning="result")
	public void afterReturning(JoinPoint joinPoint,Object result){
		String methodName = joinPoint.getSignature().getName();
		System.out.println("调用方法【" + methodName + "】 返回结果后！返回结果：【"+ result +"】");
	}

	/**
	 * 环绕通知需要携带 ProceedingJoinPoint 类型的参数. 
	 * 环绕通知类似于动态代理的全过程: ProceedingJoinPoint 类型的参数可以决定是否执行目标方法.
	 * 且环绕通知必须有返回值, 返回值即为目标方法的返回值
	 */
	//@Around("execution(* com.bluemobi.mavenTest.aop.service.*.*(..))")
	public Object aroundMethod(ProceedingJoinPoint pjp){
		Object result = null;
		String methodName = pjp.getSignature().getName();
		Object[] args = pjp.getArgs();
		
		try {
			//1、Before
			System.out.println("--Around--调用方法【" + methodName + "】 开始之前！方法参数：【" + Arrays.asList(args)+ "】" );
			
			result = pjp.proceed();
			
			//2、After
			System.out.println("--Around--调用方法【" + methodName + "】 结束之后！" );
		} catch (Throwable e) {
			e.printStackTrace();
			//3、AfterThrowing
			System.out.println("--Around--调用方法【" + methodName + "】异常抛出后！异常信息：【 "+e.toString()+"】" );
			//异常需要再次抛出！
			throw new RuntimeException(e);
		}
		//4、AfterReturning
		System.out.println("--Around--调用方法【" + methodName + "】 返回结果后！返回结果：【"+ result +"】");
		return result;
	}

}
