package com.bluemobi.mavenTest.thread.callableAndRunnable;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicLong;

public class CallableTask implements Callable<Long> {
	
	public AtomicLong c = new AtomicLong();
	
	Map<String,Long> resultMap = new HashMap<String,Long>();
	
	public CallableTask(){
	}
	
	@Override
	public Long call() {
		for(int i=0;i<100000000; i++){
			c.incrementAndGet();
		}
		return c.get();
	}
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		
		CallableTask task = new CallableTask();
		
		ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
		Future<Long> furure = singleThreadExecutor.submit(task);
		
		Long result = furure.get(); //类似 join， 等待线程执行完毕并返回结果
		
		System.out.println("---> "+result);
		
	}

}
