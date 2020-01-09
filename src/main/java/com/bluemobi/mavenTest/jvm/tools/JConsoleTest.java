package com.bluemobi.mavenTest.jvm.tools;

import java.util.ArrayList;
import java.util.List;

import com.appcore.core.pool.ThreadPoolFactory;
import com.bluemobi.mavenTest.util.Confirm;

//Java虚拟机参数：
//1、-verbose:gc -XX:+PrintGCDetails
//2、-Xmx20M -Xms20M -Xmn10M -verbose:gc -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=7 
//3、一般不建议分配年轻代内存大小  -Xmn10M
//	 设置新生代的最大年龄： -XX:MaxTenuringThreshold=7   默认值7，允许设定范围1-15，jdk8下自定义1-7有效，自定义7-15为7，若设定为7，那么第7次gc的时候会转移到老年代
//	打印时间戳：-XX:+PrintGCTimeStamps  gc前后打印堆内存信息：-XX:+PrintHeapAtGC
public class JConsoleTest {

	public static List<Object> list = new ArrayList<Object>();

	public static void main(String[] args) {
		
		//JConsoleTest.testMemory();
		JConsoleTest.testThread();
	
	}

	//监控内存情况
	public static void testMemory() {
		
		Confirm.confirm();

		for (int i = 0; i < 100000; i++) {
			byte[] bb = new byte[10 * 1024];
			if (i % 100 == 0) {
				byte[] bb1 = new byte[100 * 1024];
				list.add(bb1);
			}
			// System.gc();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	//监控线程情况
	public static void testThread(){
		
		Confirm.confirm();
		ThreadPoolFactory.submit("WhileTrue", new MyRunableWhileTrue());
		
		Confirm.confirm();
		ThreadPoolFactory.submit("Wait", new MyRunableWait());
		
		Confirm.confirm();
		ThreadPoolFactory.submit("Sleep", new MyRunableSleep());
		
		Confirm.confirm();
		Object obj1 = new Object();
		Object obj2 = new Object();
		ThreadPoolFactory.submitToCached("DeadLock", new MyRunableDeadLock(obj1,obj2,"T1"));
		ThreadPoolFactory.submitToCached("DeadLock", new MyRunableDeadLock(obj1,obj2,"T2"));
		
	}
	
	//运行线程
	public static class MyRunableWhileTrue implements Runnable{

		@Override
		public void run() {
			while(true){
				
			}
		}
		
	}
	
	//等待线程
	public static class MyRunableWait implements Runnable{
		
		@Override
		public void run() {
			synchronized(this){
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
	}

	//睡眠线程
	public static class MyRunableSleep implements Runnable{
		
		@Override
		public void run() {
			try {
				Thread.sleep(1000000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	//死锁线程  name分别传入  T1 T2
	public static class MyRunableDeadLock implements Runnable{
		
		public Object obj1;
		public Object obj2;
		public String name;
		
		public MyRunableDeadLock(Object obj1, Object obj2, String name){
			this.obj1 = obj1;
			this.obj2 = obj2;
			this.name = name;
		}
		
		@Override
		public void run() {
			if("T1".equals(name)){
				synchronized(obj1){
					System.out.println("线程 T1 拿到 锁 obj1");
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					synchronized(obj2){
						System.out.println("线程 T1 拿到 锁 obj2");
					}
				}
			}else if("T2".equals(name)){
				synchronized(obj2){
					System.out.println("线程 T2拿到 锁 obj2");
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					synchronized(obj1){
						System.out.println("线程 T2 拿到 锁 obj1");
					}
				}
			}
		}
	}
	
	

}
