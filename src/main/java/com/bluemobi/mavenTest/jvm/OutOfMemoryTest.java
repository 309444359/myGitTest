package com.bluemobi.mavenTest.jvm;

import java.util.ArrayList;
import java.util.List;

// -verbose:gc //在控制台输出GC情况
// -XX:+PrintGCDetails  //在控制台输出详细的GC情况
// -Xloggc: filepath  //将GC日志输出到指定文件中
// 内存溢出时候dump堆快照：-XX:+HeapDumpOnOutOfMemoryError  存储堆快照的文件地址：-XX:HeapDumpPath=D:/logs/testOutOfMemoryError.bin

//Java虚拟机参数：    	-Xmx:最大堆大小  	 -Xms:初始堆大小  	-Xmn:设置年轻代大小 

//1、-verbose:gc -XX:+PrintGCDetails
//2、-Xmx20M -Xms20M -Xmn10M -verbose:gc -XX:+PrintGCDetails -XX:SurvivorRatio=8
//3、-Xmx20M -Xms20M -Xmn10M -verbose:gc -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:+HeapDumpOnOutOfMemoryError  -XX:HeapDumpPath=D:/logs/testOutOfMemoryError.bin

//命令行查看默认垃圾收集器： java -XX:+PrintCommandLineFlags -version

// -Xmx20M -Xms20M -XX:+PrintGCDetails -XX:+UseSerialGC
// -Xmx20M -Xms20M -XX:+PrintGCDetails -XX:+UseParallelGC
// -Xmx20M -Xms20M -XX:+PrintGCDetails -XX:+UseParNewGC
// -Xmx20M -Xms20M -XX:+PrintGCDetails -XX:+UseConcMarkSweepGC
// -Xmx20M -Xms20M -XX:+PrintGCDetails -XX:+UseG1GC

public class OutOfMemoryTest {

	public static List list = new ArrayList();

	public static void main(String[] args) {
		/*try {
			Thread.sleep(10000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/

		for (int i = 0; i < 10000000; i++) {
			GameWorld gw = new GameWorld();
			list.add(gw);
			if(i%20000==0){
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("-------------->" + i);
			}

		}

	}

}
