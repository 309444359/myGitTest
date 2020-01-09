package com.bluemobi.mavenTest.reference;

import java.lang.ref.WeakReference;

public class WeakReferenceTest {

	public static void main(String[] args) {
		
		String s = new String("hello");
		
		WeakReference<String> wr = new WeakReference<String>(s);
		
		s = null; //若强引用存在，System.gc() 的时候对象不会被回收
		
		System.out.println(wr.get());
		
		System.gc(); // 通知JVM的gc进行垃圾回收, 对象只有若引用关联的时候会被回收
		
		System.out.println(wr.get());

	}

}
