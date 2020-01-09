package com.bluemobi.mavenTest.singleton;

/**
 * 静态内部类：不需要加锁的【懒汉式】单例， 最好的单例模式
 * 
 * 原理：创建类的对象或者访问类的属性的时候，会初始化这个类；类初始化时候，会初始化类的静态变量，而且只会执行一次（类初始化都是线程安全的）
 * 
 * 1、在单例类里面写一个静态内部类
 * 2、在静态内部类里面创建静态的外部类
 * 3、通过访问静态内部类的静态属性来获取单例
 * 4、各种单例模式都必须有静态无参构造器
 * 
 * @author hao
 *
 */
public class Singleton3 {
	
	private Singleton3(){
		System.out.println("开始创建对象...");
	}  
	
	//通过访问静态内部类的静态属性来获取单例
	public static Singleton3 getInstall(){
		return InnerSingleton.s;
	}

	//在单例类里面写一个静态内部类
	private static class InnerSingleton{
		//在静态内部类里面创建静态的外部类
		private static Singleton3 s = new Singleton3();  
	}
	
	
	
	
	
	public static void main(String[] args){
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Singleton3.getInstall();
		
	}
	
}

