package com.bluemobi.mavenTest.thread.wait_Condtion;

import java.util.concurrent.atomic.AtomicInteger;

public class WaitTask implements Runnable {
	
	public AtomicInteger c = new AtomicInteger();
	
	public WaitTask(){
	}

	@Override
	public void run() {
		
		try {
			while(true){
				this.print();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public synchronized void print() throws InterruptedException{
		System.out.println(Thread.currentThread().getName()+ " --->"+c.getAndIncrement());
		Thread.sleep(1000);
		this.notify();
		this.wait();
	}

	public static void main(String[] args) throws InterruptedException {
		
		WaitTask task = new WaitTask();
		Thread t1 = new Thread(task);
		Thread t2 = new Thread(task);
		
		t1.start();
		t2.start();
		
	}

}
