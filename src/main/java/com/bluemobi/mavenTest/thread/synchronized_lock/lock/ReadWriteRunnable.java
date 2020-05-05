package com.bluemobi.mavenTest.thread.synchronized_lock.lock;

public class ReadWriteRunnable implements Runnable {
	
	private int type; //类型：1、读  2、写
	private Service service;

	public ReadWriteRunnable(){
	}
	
	public ReadWriteRunnable(int type, Service service){
		this.type = type;
		this.service = service;
	}
	
	@Override 
	public void run() {
		if(type==1){
			service.read();
		}else if(type==2){
			service.write();
		}else{
			System.out.println("错误的类型！");
		}
	
	}



}
