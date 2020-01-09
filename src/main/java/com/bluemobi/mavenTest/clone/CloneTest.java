package com.bluemobi.mavenTest.clone;

import java.util.Date;

public class CloneTest {
	
	
	public static void main(String[] args) {
		
		Date d1 = new Date();
		Date d2 = (Date)d1.clone();
		d2.setSeconds(11);
		
		System.out.println(d1);
		System.out.println(d2);
		
		int[] ii1 = {1,2,3};
		int[] ii2 = ii1.clone();
		System.out.println(ii1);
		System.out.println(ii2);
		
	}

}
