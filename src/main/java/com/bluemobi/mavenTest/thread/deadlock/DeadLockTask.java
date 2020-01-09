package com.bluemobi.mavenTest.thread.deadlock;

public class DeadLockTask implements Runnable {
	
	private Object obj1;
	private Object obj2;
	private int flag;

	public DeadLockTask(Object obj1,Object obj2,int flag){
		this.obj1 = obj1;
		this.obj2 = obj2;
		this.flag = flag;
	}
	
	@Override
	public void run() {
		if(flag==1){
			this.test1();
		}else{
			this.test2();
		}
	}
	
	public void test1(){
		synchronized(obj1){
			System.out.println("测试死锁1,获取到锁【obj1】:"+Thread.currentThread().toString());
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("测试死锁1,等待获取锁【obj2】:"+Thread.currentThread().toString());
			synchronized(obj2){
				System.out.println("测试死锁1:"+Thread.currentThread().toString());
			}
			
		}
	}
	
	public void test2(){
		synchronized(obj2){
			System.out.println("测试死锁2,获取到锁【obj2】:"+Thread.currentThread().toString());
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("测试死锁2,等待获取锁【obj1】:"+Thread.currentThread().toString());
			synchronized(obj1){
				System.out.println("test2测试死锁2:"+Thread.currentThread().toString());
			}
			
		}
	}

}
