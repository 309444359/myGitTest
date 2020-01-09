package com.bluemobi.mavenTest.socket.bio;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.Callable;

public class SocketTask implements Callable<String> {

	Socket socket = null;
	InputStream is = null;
	OutputStream os = null;
	
	public SocketTask(Socket socket) throws IOException{
		this.socket = socket;
		this.is = socket.getInputStream();
		this.os = socket.getOutputStream();
	}
	
	
	@Override
	public String call() throws Exception {
		String result = "";
		try{
			result = this.receive();
		}catch(Exception e){
			//e.printStackTrace();  //不打印的话异常将无法显示
			throw e;
		}finally{
			System.out.println("Thread.currentThread()-------->"+Thread.currentThread());
			MyServer.countDownLatch.countDown();
		}
		
		return result;
	}
	
	public String receive() throws Exception {
		String result = "";
		while(socket.isConnected()){//一直接收消息并处理
			
			String msg = MessageUtil.receive(is);
			
			if(msg.length()>1){
				MyServer.c.getAndIncrement();
			}
			
			//处理消息
			result = "已经收到你的消息了！【"+msg+"】";
			MessageUtil.send(os, result);
			
			/*System.out.println("socket.isClosed()=="+socket.isClosed());
			System.out.println("socket.isConnected()=="+socket.isConnected());
			System.out.println("socket.isBound()=="+socket.isBound());
			System.out.println("socket.isInputShutdown()=="+socket.isInputShutdown());
			System.out.println("socket.isOutputShutdown()=="+socket.isOutputShutdown());*/
		}
		
		return result;
	}

}
