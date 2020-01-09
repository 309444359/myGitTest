package com.bluemobi.mavenTest.singleton;

/**
 * 双重检查：不需要加锁的【懒汉式】单例
 * 
 * 原理：静态方法内部判断实例是否为空，不为空直接返回，此时不许要加锁；若为空，先加锁，然后再synchronized块内部判断实例是否为空。
 * 
 * @author hao
 *
 */
public class Singleton4 {
	
	private static Singleton4 s = null;
	
	private Singleton4(){}   
	
	public static Singleton4 getInstall(){
		if(s==null){
			synchronized(Singleton4.class){
				if(s==null){
					s = new Singleton4();
				}
			}
		}
		return s;
	}

}
