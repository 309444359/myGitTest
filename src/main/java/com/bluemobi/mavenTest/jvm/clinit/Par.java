package com.bluemobi.mavenTest.jvm.clinit;

public class Par {
	
	public static int i = 10;
	
	public static final int FINAL_I = 100;
	
	static{
		System.out.println("Par...");
	}

}
