package com.bluemobi.mavenTest;

import java.util.Date;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

public class SpringMain {

	public static ApplicationContext context = null;
	public static int c = 0;
	
	public static void main(String[] args) {
		SpringMain springMain = new SpringMain();
		context = springMain.initSpring();
		
		springMain.mqProducer();
		//springMain.mqConsumer();
		
	}

	public ApplicationContext initSpring() {

		long begin = System.currentTimeMillis();

		System.out.println("开始初始化spring！");

		ApplicationContext context = new FileSystemXmlApplicationContext("classpath:spring-main.xml");

		long end = System.currentTimeMillis();

		System.out.println("初始化spring完成！耗时：【" + (end - begin) / 1000d + "】秒");

		return context;

	}

	public void mqProducer() {

		JmsTemplate jmsTemplate = (JmsTemplate) context.getBean("jmsTemplate");
		
		for(int i=0;i<100;i++){
			c = i;
			MessageCreator messageCreator = new MessageCreator() {
				public Message createMessage(Session session) throws JMSException {
					TextMessage message = session.createTextMessage();
					message.setText("mqProducer 发送消息 【" + new Date() + "】 - " + c);
					//message.setJMSReplyTo(destination);// 反馈信息
					return message;
				}
			};
			
			jmsTemplate.send(messageCreator);
		}
		
	}

	public void mqConsumer() {
		JmsTemplate jmsTemplate = (JmsTemplate) context.getBean("jmsTemplate");
		while (true) {
			try{
				TextMessage textMessage = (TextMessage) jmsTemplate.receive();
	            if(textMessage != null){
	                System.out.println("收到的消息:" + textMessage.getText());
	            }else {
	                break;
	            }
			}catch(Exception e){
				e.printStackTrace();
			}
            
        }
	}

}
