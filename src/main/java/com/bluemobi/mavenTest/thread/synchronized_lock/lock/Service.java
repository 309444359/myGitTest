package com.bluemobi.mavenTest.thread.synchronized_lock.lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

public class Service{
	
	private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
	private ReadLock readLock = lock.readLock();
	private WriteLock writeLock = lock.writeLock();
	
	//private Condition readCondition = readLock.newCondition();
	//private Condition writeCondition = writeLock.newCondition();
	
	public void read(){
		readLock.lock();
		
		System.out.println("线程：【"+Thread.currentThread().getName()+"】获取【Read】读锁");
		try{
			this.readNoLock();
		}finally{
			readLock.unlock();
			System.out.println("线程：【"+Thread.currentThread().getName()+"】释放【Read】读锁");
		}
	}
	
	public void write(){
		writeLock.lock();
		System.out.println("线程：【"+Thread.currentThread().getName()+"】获取【Write】写锁");
		try{
			this.writeNoLock();
		}finally{
			writeLock.unlock();
			System.out.println("线程：【"+Thread.currentThread().getName()+"】释放【Write】写锁");
		}
	}
	
	
	private void readNoLock(){
		for(int i=0;i<11;i++){
			System.out.println("我在读......."+Thread.currentThread().getName()+"，循环次数："+i);
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	private void writeNoLock(){
		for(int i=1;i<11;i++){
			System.out.println("我在写......."+Thread.currentThread().getName()+"，循环次数："+i);
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
