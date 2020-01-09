package com.bluemobi.mavenTest.socket.bio;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
/**
 * 消息首发器
 * @author haoj 309444359@qq.com
 * @date 2017年12月5日 下午5:25:25
 */
public class MessageUtil {
	
	public static void send(OutputStream os, String msg) throws IOException{
		byte[] bb = null;
		bb = msg.getBytes("UTF-8");
		int len = bb.length;
		byte[] bbLen = ByteUtil.intToByteArray(len);
		byte[] bbMsg = ByteUtil.mergeByteArray(bbLen, bb);
		os.write(bbMsg);
		os.flush();
		System.out.println("发送消息成功，消息内容：【"+msg+"】");
		
	}
	
	public static String receive(InputStream is) throws IOException{
		byte[] bbLen = new byte[4];//读取4字节长度（int类型）
		is.read(bbLen);
		int len = ByteUtil.byteArrayToInt(bbLen);
		byte[] bb = new byte[len];
		is.read(bb);
		String msg = new String(bb, "UTF-8");
		System.out.println("收到消息：【"+msg+"】");
		return msg;
	}

}
