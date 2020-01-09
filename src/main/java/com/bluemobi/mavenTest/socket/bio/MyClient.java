package com.bluemobi.mavenTest.socket.bio;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyClient implements Runnable{

	public static void main(String[] args) throws UnknownHostException, IOException, InterruptedException {
		
		ExecutorService pool = Executors.newCachedThreadPool();
		
		for(int i=0 ;i<100;i++){
			MyClient task = new MyClient();
			pool.submit(task);
		}
	}
	
	@Override
	public void run() {
		try {
			this.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void start() throws UnknownHostException, IOException, InterruptedException{
		Socket socket = new Socket("localhost",7000);
		InputStream is = socket.getInputStream();
		OutputStream os = socket.getOutputStream();
		String msg = "你好！13671677488";
		for(int i=0;i<10000;i++){
			MessageUtil.send(os, msg);
			String s = MessageUtil.receive(is);
			System.out.println("---------->"+i);
		}
		socket.close();
	}

	

}
