package com.bluemobi.mavenTest.thread;

import java.util.concurrent.atomic.AtomicInteger;

public class ThreadLocalTask implements Runnable {
	//说明：开启两个线程，循环给 i1 和 i2 增加级数，打印结果 i2的值一直为i1的2倍左右，说明i1是各线程独立计数，i2是全局计数。
	ThreadLocal<Integer> i1 = new ThreadLocal<Integer>();
	AtomicInteger i2 = new AtomicInteger(0);
	
	ThreadLocal<Object> o = new ThreadLocal<Object>();
	Object obj = null;

	@Override
	public void run() {
		i1.set(0);
		Object temp = new Object();
		System.out.println(Thread.currentThread() + "  temp="+temp);
		
		o.set(temp); //每个线程都有自己独立的值
		obj = temp;  //后面启动的线程会覆盖前面线程赋的值
		
		while(true){
			i1.set(i1.get()+1);
			i2.incrementAndGet();
			if(i1.get()%10000000 == 0){
				System.out.println(Thread.currentThread() + " - ThreadLocal: i1="+i1.get() + ", o=" + o.get() + ", obj=" + obj);
				System.out.println(Thread.currentThread() + " - AtomicInteger: i2="+i2.get() );
			}
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		
		ThreadLocalTask task = new ThreadLocalTask();
		Thread t1 = new Thread(task);
		Thread t2 = new Thread(task);
		t1.start();
		t2.start();
		
		Thread.sleep(10000);
		System.out.println("程序停止");
		System.exit(1);
		
	}
	


}
