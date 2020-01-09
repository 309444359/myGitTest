package com.bluemobi.mavenTest.socket.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class MyServer extends Thread{
	
	private volatile boolean isRun = true;
	
	public static CountDownLatch countDownLatch = new CountDownLatch(100);
	
	public static AtomicInteger c = new AtomicInteger();

	public static void main(String[] args) throws Exception {
		MyServer server = new MyServer();
		server.start();
		
		MyServer.countDownLatch.await();
		System.out.println("------------------->处理消息次数："+MyServer.c.get());
		
	}

	@Override
	public void run() {
		try {
			this.startServer();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void startServer() throws IOException, InterruptedException, ExecutionException{
		
		ServerSocket serverSocket = new ServerSocket(7000);
		ExecutorService pool = Executors.newCachedThreadPool();
		
		while(isRun){
			Socket socket = serverSocket.accept();
			//每接收到一个连接就创建一个Task来处理，阻塞的io需要为每一个链接创建一个线程！！！
			SocketTask task = new SocketTask(socket);
			pool.submit(task);
			//String s = future.get();  //若执行时候有异常，get的时候才会抛出
			//System.out.println("----------------->"+s);
		}
		
	}

}
