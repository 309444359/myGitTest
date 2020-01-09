package com.bluemobi.mavenTest.reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

public class PhantomReferenceTest {

	public static void main(String[] args) {
		
		String s = new String("hello");
		
		PhantomReference<String> ph = new PhantomReference<String>(s, new ReferenceQueue<String>());
		
		s = null; //若强引用存在，System.gc() 的时候对象不会被回收
		
		System.out.println(ph.get());//用于为null，源码直接返回null
		
		System.out.println("gc前："+ph.isEnqueued()); //返回是否被垃圾回收器标记为即将回收的垃圾
		
		System.gc(); // 通知JVM的gc进行垃圾回收, 对象只有若引用关联的时候会被回收
		
		System.out.println("gc后："+ph.isEnqueued()); //返回是否被垃圾回收器标记为即将回收的垃圾

	}

}
