package com.bluemobi.mavenTest;

public class TestA {
	public static void main(String[] args){
		A a = new B();
		System.out.println(a.a);
	}
}


class A{
	public int a = 0;
	public A(){
		System.out.println("调用A的构造器");
	};
}

class B extends A{
	public int a = 1;
	public B(){
		System.out.println("调用B的构造器");
	};
}
