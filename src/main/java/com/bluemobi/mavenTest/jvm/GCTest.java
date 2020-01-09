package com.bluemobi.mavenTest.jvm;

// Java虚拟机参数：
// 1、-verbose:gc -XX:+PrintGCDetails
// 2、-Xmx20M -Xms20M -Xmn10M -verbose:gc -XX:+PrintGCDetails -XX:+UseSerialGC -XX:SurvivorRatio=8
// 3、一般不建议分配年轻代内存大小  -Xmn10M

public class GCTest {
	
	public Object install;
	
	public GCTest(){
		
	}

	public static void main(String[] args) {
		
		byte[] bb1 = new byte[2*1024*1024];
		byte[] bb2 = new byte[2*1024*1024];
		byte[] bb3 = new byte[2*1024*1024];
		byte[] bb4 = new byte[2*1024*1024];

		
		System.gc();
		
		/*GCTest t1 = new GCTest();
		GCTest t2 = new GCTest();
		
		t1.install = t2;
		t2.install = t1;
		
		System.gc();*/

	}

}
