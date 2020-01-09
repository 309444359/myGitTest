package com.bluemobi.mavenTest.thread.synchronized_lock.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test {

	public static void main(String[] args) {
		
		//System.out.println(TimeUtil.getFormatTime(1504517026000, "yyyy-MM-dd HH:mm:ss"));
		
		Service service = new Service();
		
		Runnable runnable11 = new ReadWriteRunnable(1,service);//读任务
		Runnable runnable12 = new ReadWriteRunnable(1,service);//读任务
		Runnable runnable13 = new ReadWriteRunnable(1,service);//读任务
		Runnable runnable14 = new ReadWriteRunnable(1,service);//读任务
		
		Runnable runnable21 = new ReadWriteRunnable(2,service);//写任务
		Runnable runnable22 = new ReadWriteRunnable(2,service);//写任务
		Runnable runnable23 = new ReadWriteRunnable(2,service);//写任务
		
		ExecutorService threadPool = Executors.newFixedThreadPool(8);
		

		threadPool.execute(runnable11);//执行读
		threadPool.execute(runnable12);//执行读
		
		threadPool.execute(runnable21);//执行写
		
		threadPool.execute(runnable13);//执行读
		threadPool.execute(runnable14);//执行读
		
		
		threadPool.execute(runnable22);//执行写
		threadPool.execute(runnable23);//执行写
		
		
	}
	
	

	

}
