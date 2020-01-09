package com.bluemobi.mavenTest.lambda;

import java.util.Arrays;
import java.util.List;

public class TestList {

	public static void main(String[] args) {
		
		TestList t = new TestList();
		List<Integer> list = Arrays.asList(10,20,30,40);
		int r = t.add(list);
		System.out.println(r);

	}
	
	public int add(List<Integer> list){
		//list.parallelStream().forEachOrdered(System.out::println);
		int r = list.parallelStream().mapToInt(a -> a).sum();
		return r;
	}

}
