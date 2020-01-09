package com.bluemobi.mavenTest.jvm.clinit;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class DemoThread {
	//<clinit>()方法是线程安全的，如果有多个线程同时初始化一个类，只有一个线程能执行clinit方法，
	//其他线程等待其执行完毕，如果执行时间过长，name会导致多个线程阻塞
	
	//创建对象时候会加载类（加载-连接（验证、准备、解析）-初始化）
	static class Hello{
		static{
			System.out.println(Thread.currentThread().getName()+" clinit...");
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		
		ExecutorService pool = Executors.newFixedThreadPool(20);
		int i=0;
		while(i++<20){
			Future f = pool.submit(new Runnable(){
				@Override
				public void run() {
					System.out.println(Thread.currentThread().getName()+" start...");
					new Hello(); //创建对象时候会加载类（加载-连接（验证、准备、解析）-初始化）
					System.out.println(Thread.currentThread().getName()+" end...");
				}
			});

		}
		

	}

}
