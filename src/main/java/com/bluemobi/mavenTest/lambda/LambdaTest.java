package com.bluemobi.mavenTest.lambda;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class LambdaTest extends JFrame{
	
	private JButton jb;
	
	public LambdaTest(){
		
		this.setBounds(200, 200, 400, 200);
		this.setTitle("Lambda 测试");
		jb = new JButton("click");
		/*jb.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("clicked");
				
			}
		});*/
		
		jb.addActionListener(event -> System.out.println("Hello"));
		
		this.add(jb);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);//关闭程序
		
	}

	public static void main(String[] args) {
		
		new LambdaTest();

	}

}
