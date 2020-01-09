package com.bluemobi.mavenTest.jvm.tools;

import java.util.Scanner;

import com.bluemobi.mavenTest.util.Confirm;

// Java虚拟机参数：
// 1、-verbose:gc -XX:+PrintGCDetails
// 2、-Xmx20M -Xms20M -Xmn10M -verbose:gc -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=7 
// 3、一般不建议分配年轻代内存大小  -Xmn10M
//	 设置新生代的最大年龄： -XX:MaxTenuringThreshold=7   默认值7，允许设定范围1-15，jdk8下自定义1-7有效，自定义7-15为7，若设定为7，那么第7次gc的时候会转移到老年代
//	打印时间戳：-XX:+PrintGCTimeStamps  gc前后打印堆内存信息：-XX:+PrintHeapAtGC

public class JStatTest {
	
	public JStatTest(){
		
	}

	public static void main(String[] args) {
		
		Confirm.confirm();
		
		byte[] bb1 = new byte[100*1024];
		
		for(int i=0;i<100000;i++){
			byte[] bb = new byte[10*1024];
			//System.gc();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		/*GCTest t1 = new GCTest();
		GCTest t2 = new GCTest();
		
		t1.install = t2;
		t2.install = t1;
		
		System.gc();*/

	}

}
