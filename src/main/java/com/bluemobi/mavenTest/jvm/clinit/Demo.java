package com.bluemobi.mavenTest.jvm.clinit;
// <clinit>() 是类构造器
// <clinit>() 类初始化, 主要是静态属性初始化，按照静态属性在代码中的顺序先后初始化
public class Demo {
	
	//静态语句块中只能访问定义在静态语句块之前的变量，定义在它之后的变量，可以赋值，但是不能访问
	static{
		i = 0; //可以赋值
		//System.out.println(i); //不能访问
 	}

	public static int i = 1;
	
}
