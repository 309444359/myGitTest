package com.bluemobi.mavenTest.thread;

public class VolatileTask implements Runnable {
	
	//1. 对volatile变量的写会立即刷新到主存
	//2. 对volatile变量的读会读主存中的新值
	volatile boolean running = true;
	
	long i = 0;

	@Override
	public void run() {
		while(running){
			i++;
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		
		VolatileTask.getFromMain();
		
	}
	
	public static void getFromMain() throws InterruptedException {
		VolatileTask task = new VolatileTask();
		Thread thread = new Thread(task);
		thread.start();
		
		Thread.sleep(10);
		task.running = false;
		Thread.sleep(1000);
		System.out.println(task.i/10000);
		
		System.out.println("程序停止");
	}
	
	public static void setToMain() throws InterruptedException {
		VolatileTask task = new VolatileTask();
		Thread thread = new Thread(task);
		thread.start();
		for(int i=0;i<10;i++){
			Thread.sleep(200);
			System.out.println(task.i/10000);
		}
		System.out.println("程序停止");
	}

}
