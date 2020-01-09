package com.bluemobi.mavenTest.socket.bio;

import java.nio.ByteBuffer;

//0xFF = 255
public class ByteUtil {

	public static int byteArrayToInt(byte[] bb) {
		return 	   bb[3] & 0xFF 
				| (bb[2] & 0xFF) << 8 
				| (bb[1] & 0xFF) << 16 
				| (bb[0] & 0xFF) << 24;
	}

	public static byte[] intToByteArray(int i) {
		return new byte[] { 
				(byte) ((i >> 24) & 0xFF), 
				(byte) ((i >> 16) & 0xFF), 
				(byte) ((i >> 8) & 0xFF),
				(byte) (i & 0xFF) 
		};
	}
	
	public static int bytesToInt(byte[] bb){
		ByteBuffer byteBuffer = ByteBuffer.allocate(4);
		byteBuffer.put(bb);
		byteBuffer.flip();
		int i = byteBuffer.getInt();
		return i;
	}
	
	public static byte[] intToBytes(int i){
		ByteBuffer byteBuffer = ByteBuffer.allocate(4);
		byteBuffer.putInt(i);
		byteBuffer.flip();
		byte[] bb = new byte[byteBuffer.limit()];
		byteBuffer.get(bb);	
		return bb;
	}
	
	/**
	 * 将两个字节数组合并成一个数组
	 * @author haojian 309444359@qq.com
	 * Apr 22, 2013 10:29:35 AM
	 * @param bb1
	 * @param bb2
	 * @return
	 */
	public static byte[] mergeByteArray(byte[] bb1, byte[] bb2){
		int len1 = bb1.length;
		int len2 = bb2.length;
		byte[] bb = new byte[len1+len2];
		System.arraycopy(bb1, 0, bb, 0, len1);
		System.arraycopy(bb2, 0, bb, len1, len2);
		return bb;
	}

	public static void main(String[] args) {
		
		int i = 122349;
		byte[] bb = ByteUtil.intToByteArray(i);
		for (int j = 0; j < bb.length; j++) {
			System.out.println(bb[j]);
		}
		
		int i1 = ByteUtil.byteArrayToInt(bb);
		System.out.println("i1="+i1);
		System.out.println(0xFF);
	}

}
