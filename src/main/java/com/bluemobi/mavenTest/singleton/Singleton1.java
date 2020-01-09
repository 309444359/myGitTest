package com.bluemobi.mavenTest.singleton;
/**
 * 饿汉式：一开始就创建好
 * @author hao
 *
 */
public class Singleton1 {
	
	private static Singleton1 s = new Singleton1();
	
	private Singleton1(){}    
	
	public static Singleton1 getInstall(){
		return s;
	}

}
