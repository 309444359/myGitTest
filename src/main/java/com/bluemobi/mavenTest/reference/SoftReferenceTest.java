package com.bluemobi.mavenTest.reference;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
//为了达到测试效果，需在java虚拟机启动参数里面设置最大内存为3m： -Xmx3m
public class SoftReferenceTest {

	public static void main(String[] args) {
		//软引用，内存不足的时候会被回收
		SoftReference<List<String>> sr = new SoftReference<List<String>>(new ArrayList<String>());
		List<HashMap> list = new ArrayList<HashMap>();
		long l = 0;
		
		while(true){
			if(sr.get()!=null){
				l++;
				if(l<=100000){//为了达到测试效果，需在java虚拟机启动参数里面设置最大内存为3m： -Xmx3m
					if(l==1){
						System.out.println("开始往软引用中的List中添加字符串..."+l);
					}			
					sr.get().add("1234567890");	
					if(l==100000){
						System.out.println("往软引用中的List中添加字符串结束..."+l);
					}
				}else{
					if(l==100001){
						System.out.println("开始创建HashMap消耗内存..."+l);
					}
					list.add(new HashMap());
				}
			}else{
				System.out.println("sr.get()返回null，软引用内存不足被回收, l="+l);
				return;
			}
			
		}
		

	}

}
