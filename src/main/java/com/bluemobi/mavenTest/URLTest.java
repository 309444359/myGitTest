package com.bluemobi.mavenTest;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class URLTest {

	public static void main(String[] args) {
		System.out.println(URLTest.urlDecode("\\U83b7\\U53d6\\U5b66\\U751f\\U7528\\U6237\\U4fe1\\U606f\\U5931\\U8d25"));

	}
	
	
	public static String urlDecode(String str) {
		String result = null;
		try {
			result = URLDecoder.decode(str, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}

}
