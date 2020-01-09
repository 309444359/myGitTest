package com.bluemobi.mavenTest.thread.join_Future_CountDownLatch;

public class JoinTask implements Runnable {
	
	private String name;
	
	public JoinTask(){
	}
	public JoinTask(String name){
		this.name = name;
	}

	@Override
	public void run() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("执行完成的线程："+name);

	}

	public static void main(String[] args) throws InterruptedException {
		
		long b = System.currentTimeMillis();
		
		JoinTask task1 = new JoinTask("线程一");
		JoinTask task2 = new JoinTask("线程二");
		JoinTask task3 = new JoinTask("线程三");
		
		Thread t1 = new Thread(task1);
		Thread t2 = new Thread(task2);
		Thread t3 = new Thread(task3);
		
		t1.start();
		System.out.println("---------");
		t1.join();
		System.out.println("----1----");
		//Thread.currentThread().join(10000);
		t2.start();
		System.out.println("---------");
		t2.join();
		System.out.println("----2----");
		
		t3.start();
		System.out.println("---------");
		//t1.join();
		//t2.join();
		t3.join();
		System.out.println("----3----");
		
		long e = System.currentTimeMillis();
		
		System.out.println("执行耗时："+(e-b)/1000d+" 秒");
		
	}

}
