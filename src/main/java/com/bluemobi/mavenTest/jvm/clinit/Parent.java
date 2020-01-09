package com.bluemobi.mavenTest.jvm.clinit;
// 子类的 <clinit>() 执行之前，必须保证父类的  <clinit>() 执行完毕
public class Parent {
	
	public static int A = 1;
	
	static{
		A = 2;
	}
	
	static class Sub extends Parent{
		
		public static int B = A;
		
	}
	
	public static void main(String[] args){
		
		System.out.println(Sub.B);
		
	}

}
