package com.bluemobi.mavenTest.thread.deadlock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DeadLockTest {

	public static void main(String[] args) {
		Object obj1 = new Object();
		Object obj2 = new Object();
		DeadLockTask task1 = new DeadLockTask(obj1,obj2,1);
		DeadLockTask task2 = new DeadLockTask(obj1,obj2,2);
		
		ExecutorService pool = Executors.newFixedThreadPool(2);
		pool.submit(task1);
		pool.submit(task2);
		
		/*
		Thread t1 = new Thread(task1);
		Thread t2 = new Thread(task2);
		t1.start();
		t2.start();
		*/
	}

}
