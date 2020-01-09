package com.bluemobi.mavenTest.jvm;

import java.util.ArrayList;
import java.util.List;

//Java虚拟机参数：
//1、-verbose:gc -XX:+PrintGCDetails
//2、-Xmx20M -Xms20M -Xmn10M -verbose:gc -XX:+PrintGCDetails -XX:SurvivorRatio=8
//3、-Xmx20M -Xms20M -Xmn10M -verbose:gc -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:+HeapDumpOnOutOfMemoryError  -XX:HeapDumpPath=D:/logs/testOutOfMemoryError.bin
// 内存溢出时候dump堆快照：-XX:+HeapDumpOnOutOfMemoryError  存储堆快照的文件地址：-XX:HeapDumpPath=D:/logs/testOutOfMemoryError.bin


public class OutOfMemoryTest {
	
	//public static List<byte[]> list = new ArrayList<byte[]>();
	public static List list = new ArrayList();

	public static void main(String[] args) {
		
		for(int i=0;i<10000000;i++){
			//byte[] bb = new byte[20*1024];
			char[] cc = new char[20*1024];
			list.add(cc);
			System.out.println("-------------->"+i);
		}
		

	}

}
