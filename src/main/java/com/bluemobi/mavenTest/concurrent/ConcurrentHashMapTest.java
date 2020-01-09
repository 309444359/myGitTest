package com.bluemobi.mavenTest.concurrent;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapTest {

	public static void main(String[] args) {
		test2();
	}
	
	public static void test2(){
		ConcurrentHashMap<String,String> map = new ConcurrentHashMap<String,String>();
		String key = "key-1";
		String r1 = map.put(key, "haoj");
		String r2 = map.putIfAbsent(key, "aaaaa");
		System.out.println("r1=="+r1);
		System.out.println("r2=="+r2);
		System.out.println("---->"+map.get(key));
	}
	
	public static void test1(){
		ConcurrentHashMap<Integer,String> map = new ConcurrentHashMap<Integer,String>();
		for(int i=0;i<10;i++){
			map.put(i, "hao-"+i);
		}
		
		for(Integer key:map.keySet()){
			
			System.out.println("key--->"+key);
			
			if(key==5){
				map.remove(5);
			}
		}
	}

}
