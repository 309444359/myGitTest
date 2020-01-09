package com.bluemobi.mavenTest.socket.nio;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;

public class MyNioServer {
	
	private volatile boolean isRun = true;
	
	public static int PORT_NUMBER = 7000;
	
	public void startServer() throws Exception{
		
		//1、创建 ServerSocketChannel
		ServerSocketChannel ssc = ServerSocketChannel.open();//打开一个未绑定的serversocketchannel
		ssc.configureBlocking(false);//设置为非阻塞模式
		
		//2、通过ServerSocketChannel创建一个 ServerSocket
		ServerSocket serverSocket = ssc.socket(); //得到一个ServerSocket去和它绑定 
		serverSocket.bind(new InetSocketAddress(PORT_NUMBER)); //设置server channel将会监听的端口
		
		//3、创建Selector并跟ServerSocketChannel注册
		// 将选择器绑定到监听信道,只有非阻塞信道才可以注册选择器.并在注册过程中指出该信道可以进行Accept操作
		//一个server socket channel准备好接收新进入的连接称为“接收就绪”
		Selector selector = Selector.open(); //创建一个Selector供下面使用
		ssc.register(selector, SelectionKey.OP_ACCEPT); //将serverSocketChannel注册到Selector中
		
		while(isRun){
			System.out.println("----------0---------------");
            int readyChannels = selector.select(); //查询每个通道是否有到达事件，如果没有事件，则一直阻塞在那里
            if(readyChannels == 0) continue; 
            
			Set<SelectionKey> keySet = selector.selectedKeys();
			Iterator<SelectionKey> it = keySet.iterator();
			System.out.println("----------1---------------");
			while(it.hasNext()){
				SelectionKey key = it.next();
				
				if(key.isAcceptable()){  
                    System.out.println("Acceptable...");  
                }
				
				if(key.isReadable()){
					System.out.println("Readable...");  
				}
				
				if(key.isWritable()){
					System.out.println("Writable...");  
				}
				
				// 移除处理过的键
				it.remove();
				
			}

		}
		
		
	}
	
	public static void main(String[] args) throws Exception {
		
		MyNioServer server = new MyNioServer();
		server.startServer();
		
	}
	
}