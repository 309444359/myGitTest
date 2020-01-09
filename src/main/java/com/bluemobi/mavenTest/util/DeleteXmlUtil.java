package com.bluemobi.mavenTest.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 此工具类用来去除文本文件中，{} 中的  <xxx> 字样
 */
public class DeleteXmlUtil {
	
	public static void main(String[] args) {
		//只需要修改这个名字就可以
		String fileName = "caiwufenxibaobiao333";
		
		String dir = "d:/xmlfile/";
		//原文件
		String inputFile = dir + fileName + ".xml";
		//修改后的文件  输出文件夹要先创建好！
		String outputFile = dir + "output/" + fileName + ".xml";
		String outputJava = dir + "output/" + fileName + ".java";
		DeleteXmlUtil.deleteXml(inputFile, outputFile);
		DeleteXmlUtil.putDefaultValue(outputFile, outputJava);
		
	}

	public static void deleteXml(String inputFile, String outputFile){
		
		List<String> list = DeleteXmlUtil.readLines(new File(inputFile));
		System.out.println(list.size());
		StringBuilder sb = new StringBuilder();
		for(String line:list){
			String outLine = line;
			Pattern p = Pattern.compile("\\{(.*?)\\}");//正则表达式，取=和|之间的字符串，不包括=和|  
			Matcher m = p.matcher(line);  
			//1、找到所有在 {} 之间的字符串,并遍历处理
			while(m.find()) {  
				//2、把{}中的字符串替换成 TTTTTTTTTT
				String content = m.group(1); //此处不能trim();
				System.out.println("content===【"+content+"】");
				outLine = outLine.replaceFirst(content, "TTTTTTTTTT");
				
				//3、把{}中的字符串中的<xxx>都替换为空
				Pattern p2 = Pattern.compile("\\<(.*?)\\>");//正则表达式，取=和|之间的字符串，不包括=和|  
				Matcher m2 = p2.matcher(content);  
				while(m2.find()) {
					String deleteStr = m2.group(0);
					System.out.println("deleteStr：【"+deleteStr+"】");
				    content = content.replaceFirst(deleteStr, "");   
				 }  
				//3、把TTTTTTTTTT替换成 去除 <xxxx> 之后的字符串
				content = content.trim();
				System.out.println("afterDel==【"+content+"】");
				outLine = outLine.replaceFirst("TTTTTTTTTT",content.trim());
			}
			sb.append(outLine).append("\n");
		}
		DeleteXmlUtil.writeToFile(outputFile, sb.toString());
	}
	
	/**
	 * 生成所有的 map.put("key","value"); 赋默认值代码
	 */
	public static void putDefaultValue(String inputFile, String outputFile){
		
		List<String> list = DeleteXmlUtil.readLines(new File(inputFile));
		StringBuilder sb = new StringBuilder();
		for(String line:list){
			Pattern p = Pattern.compile("\\{(.*?)\\}");//正则表达式，取=和|之间的字符串，不包括=和|  
			Matcher m = p.matcher(line);  
			//1、找到所有在 {} 之间的字符串,并遍历处理
			while(m.find()) {  
				String str = m.group(1).trim();//m.group(0)包含这两个字符串，  m.group(1)不包括这两个字符  
				String code = "\tmap.put(\""+str+"\",\"\");";
				System.out.println(code);
				sb.append(code).append("\n");
			}
		}
		DeleteXmlUtil.writeToFile(outputFile, sb.toString());
	}
	
	/**
	 * 读取一个文本文件，将每行非空的字符串放入到list中并返回
	 */
	public static List<String> readLines(File file) {

		if (!file.isFile()) {
			throw new RuntimeException("文件" + file.getName() + "不是一个标准文件!");
		}

		List<String> list = new ArrayList<String>();

		FileReader fr = null;
		BufferedReader br = null;
		try {
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			String line = br.readLine().trim();
			while (line != null && line.length() > 0) {
				list.add(line);
				line = br.readLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (fr != null) {
				try {
					fr.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return list;
	}
	
	/**
	 * 把字符串写到文件中
	 */
	public static void writeToFile(String outputFile, String str) {
		try {
			FileOutputStream out = new FileOutputStream(outputFile);
			out.write(str.getBytes("UTF-8"));
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void test(){
		String line = "//aa@bb张小名cc :dd 25分//@ dd李小花: 43分//@王力: 100分";  
		Pattern p = Pattern.compile("\\@(.*?)\\:");//正则表达式，取=和|之间的字符串，不包括=和|  
		Matcher m = p.matcher(line); 
		while(m.find()) { 
			String str = m.group(0);//m.group(1)不包括这两个字符  
			System.out.println("["+str+"]");
			
			str = m.group(1);//m.group(1)不包括这两个字符  
			System.out.println("["+str+"]");
		}
	}
}
