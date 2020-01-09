package com.bluemobi.mavenTest.jvm.clinit;
/**
 * 类不初始化的情况：
	1、通过子类调用父类的静态字段，子类不会被初始化
	2、调用类的常量（被final修饰的）
	3、通过数组定义来引用类（创建该类的数组对象）
 */
public class Main {

	public static void main(String[] args) {
		test2();
	}
	
	public static void test1(){
		//通过子类调用父类的静态字段，只初始化父类，子类不会被初始化
		System.out.println(Sub.i);
	}
	
	public static void test2(){
		//访问类的常量（final修饰），类不会被初始化
		System.out.println(Par.FINAL_I);
	}
	
	public static void test3(){
		//通过数组定义来引用类（创建该类的数组对象）
		Par[] pp = new Par[10];
	}
	
	

}
