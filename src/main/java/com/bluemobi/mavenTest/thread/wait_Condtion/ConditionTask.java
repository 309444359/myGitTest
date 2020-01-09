package com.bluemobi.mavenTest.thread.wait_Condtion;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionTask  implements Runnable {
	
	private ReentrantLock lock = new ReentrantLock();
	
	Condition condition = lock.newCondition();
	
	public AtomicInteger c = new AtomicInteger();

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
	
	public void print() throws InterruptedException{
		lock.lock();
		try{
			System.out.println(Thread.currentThread().getName()+ " --->"+c.getAndIncrement());
			Thread.sleep(1000);
			condition.signalAll();
			condition.await();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
		
	}

	public static void main(String[] args) throws InterruptedException {
		
		ConditionTask task = new ConditionTask();
		Thread t1 = new Thread(task);
		Thread t2 = new Thread(task);
		
		t1.start();
		t2.start();
		
	}

}


