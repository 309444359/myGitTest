package com.bluemobi.mavenTest.singleton;

/**
 * 懒汉式：需要的时候创建
 * @author hao
 *
 */
public class Singleton2 {
	
	private static Singleton2 s = null;
	
	private Singleton2(){}   
	
	public synchronized static Singleton2 getInstall(){
		if(s==null){
			s = new Singleton2();
		}
		return s;
	}

}
