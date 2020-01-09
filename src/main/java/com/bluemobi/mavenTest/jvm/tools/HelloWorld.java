package com.bluemobi.mavenTest.jvm.tools;

public class HelloWorld {

	public static void main(String[] args) {
		Service s = new ServiceImpl();
		int res = s.add(1, 2);

	}

}

interface Service {
	int add(int a, int b);

	long addlong(long a, long b);

	Integer addInteger(Integer a, Integer b);

}

class ServiceImpl implements Service {

	public int add(int a, int b) {
		return a + b;
	}

	public long addlong(long a, long b) {
		return a + b;
	}

	public Integer addInteger(Integer a, Integer b) {
		return a + b;
	}

}
