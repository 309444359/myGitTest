package com.bluemobi.mavenTest.thread.join_Future_CountDownLatch;

import java.util.concurrent.atomic.AtomicLong;

public class JoinTask2 implements Runnable {
	
	public AtomicLong c = new AtomicLong();
	
	public JoinTask2(){
	}

	@Override
	public void run() {
		for(int i=0;i<100000000; i++){
			c.incrementAndGet();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		
		JoinTask2 task = new JoinTask2();
		Thread t = new Thread(task);
		t.start();
		//在某些情况下，主线程创建并启动了子线程，如果子线程中需要进行大量的耗时运算，主线程往往将早于子线程结束之前结束，
		//如果主线程想等待子线程执行完毕后，获得子线程中的处理完的某个数据，就要用到join方法了，方法join（）的作用是等待线程对象被销毁。
		t.join();
		System.out.println("---> "+task.c.get());
		
	}

}
