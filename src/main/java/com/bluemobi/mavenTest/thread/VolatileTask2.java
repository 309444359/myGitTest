package com.bluemobi.mavenTest.thread;

//1.进入同步块，访问共享变量会去读取主存
//2.退出同步块，本地内存对共享变量的修改会立即刷新到主存
public class VolatileTask2 implements Runnable {
	
	boolean running = true;
	int i = 0;

	@Override
	public void run() {
		while(this.isRunning()){
			i++;
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		
		VolatileTask2.getFromMain();
		
	}
	
	public static void getFromMain() throws InterruptedException {
		VolatileTask2 task = new VolatileTask2();
		Thread thread = new Thread(task);
		thread.start();
		Thread.sleep(10);
		task.setRunning(false);
		Thread.sleep(1000);
		System.out.println(task.i/10000);
		System.out.println("程序停止");
	}

	//1.进入同步块，访问共享变量会去读取主存
	//2.退出同步块，本地内存对共享变量的修改会立即刷新到主存
	public synchronized boolean isRunning() {
		synchronized(this){
			synchronized(this){
				synchronized(this.getClass()){
					return running;
				}
			}
		}
	}

	//1.进入同步块，访问共享变量会去读取主存
	//2.退出同步块，本地内存对共享变量的修改会立即刷新到主存
	public synchronized void setRunning(boolean running) {
		this.running = running;
	}
	
	
	
	

}
