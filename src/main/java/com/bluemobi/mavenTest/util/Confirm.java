package com.bluemobi.mavenTest.util;

import java.util.Scanner;
/**
 * 阻塞程序，直到确认为止
 * @author haoj 309444359@qq.com
 * @date 2018年3月29日 下午2:50:44
 */
public class Confirm {
	
	public static void confirm(){
		
		Scanner sc = new Scanner(System.in);
		System.out.println("可以开始了吗？（Y/N）");
		String s = sc.next().trim().toUpperCase();
		while(!("Y".equals(s)||"YES".equals(s))){
			s = sc.next().trim().toUpperCase();;
		}
		System.out.println("OK!");
		
	}

}
