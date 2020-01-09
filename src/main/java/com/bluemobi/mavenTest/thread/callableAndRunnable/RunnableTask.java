package com.bluemobi.mavenTest.thread.callableAndRunnable;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicLong;

public class RunnableTask implements Runnable { 
	
	public AtomicLong c = new AtomicLong();
	
	Map<String,Long> resultMap = new HashMap<String,Long>();
	
	public RunnableTask(){
	}
	@Override
	public void run() {
		for(int i=0;i<100000000; i++){
			c.incrementAndGet();
		}
		resultMap.put("result", c.get()) ;
		
	}

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		
		RunnableTask task = new RunnableTask();
		
		ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
		Future<Map<String,Long>> furure = singleThreadExecutor.submit(task, task.resultMap);
		
		Map<String,Long> resultMap = furure.get(); //类似 join， 等待线程执行完毕并返回结果
		
		System.out.println("---> "+resultMap.get("result"));
		System.out.println("---> "+task.resultMap.get("result"));
		
	}

}
