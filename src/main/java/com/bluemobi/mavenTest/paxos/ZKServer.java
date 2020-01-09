package com.bluemobi.mavenTest.paxos;

import java.util.ArrayList;
import java.util.List;
/**
 * 接下来以两个假设的场景来演绎BasicPaxos；参谋和将军需要遵循一些基本的规则
	1） 参谋以两阶段提交（prepare/commit）的方式来发起提议，在prepare阶段需要给出一个编号；
	2） 在prepare阶段产生冲突，将军以编号大小来裁决，编号大的参谋胜出；
	3） 参谋在prepare阶段如果收到了将军返回的已接受进攻时间，在commit阶段必须使用这个返回的进攻时间；
	
	自己总结：
		1、准备阶段，acceptor只要收到大的编号就保存
		2、提交阶段，acceptor只要当前保存编号跟提交编号一致，就更改value
		3、准备阶段，proposal只要收到多数派ok，就可以提交，提交前判断是否有已接受的value，若有就用编号大的value
		4、提交阶段，如果proposal收到拒绝，就重新准备，并使用编号交大的已接受的value
	
 * @author hao
 *
 */
public class ZKServer {
	
	public static final String OK = "ok";
	public static final String NO = "no";
	public static final String ACCEPTED = "accepted";//接受
	public static final String REJECTED = "rejected";//拒绝
	public static final String FAIL = "fail";
	public static final String SUCCESS = "success";
	
	/**准备消息*/
	public static final int PROTOCOL_NO_10 = 10;
	/**接受准备*/
	public static final int PROTOCOL_NO_11 = 11;
	/**拒绝准备 - 编号已增加*/
	public static final int PROTOCOL_NO_12 = 12;
	/**接受准备 - 已接收提议*/
	public static final int PROTOCOL_NO_13 = 13;
	
	/**提交提议*/
	public static final int PROTOCOL_NO_20 = 20;
	/**接受提交*/
	public static final int PROTOCOL_NO_21 = 21;
	/**拒绝提交 - 编号已增加*/
	public static final int PROTOCOL_NO_22 = 22;
	/**拒绝提交 - 已接受提议*/
	public static final int PROTOCOL_NO_23 = 23;
	
	
	/**当前记录的编号*/
	private int currentNo;
	/**当前已经接受的消息*/
	private Message msg;
	/**是否已接受*/
	private boolean isAccepted;
	/**已接受自己提议的节点列表*/
	private List accepterList = new ArrayList();
	
	/**准备阶段收到的 已接受提议 列表*/
	private List<Message> acceptedList = new ArrayList<Message>();
	
	
	// 收到准备消息
	public void receivePrepare(int no){
		if(no > currentNo){
			currentNo = no;//准备阶段任然保存更大的编号
		}
		if(this.isAccepted){	
			this.sendMessage(ZKServer.PROTOCOL_NO_13, this.msg.toString());
			return;
		}
		if(no > currentNo){
			this.sendMessage(ZKServer.PROTOCOL_NO_11, "");
		}else{
			this.sendMessage(ZKServer.PROTOCOL_NO_12, "");
		}
	}
	
	// 收到提交消息
	public void receiveCommit(Message msg){
		if(this.isAccepted){//拒绝并发送当前已经接受的消息
			this.sendMessage(ZKServer.PROTOCOL_NO_23, this.msg.toString());
			return;
		}
		if(msg.getNo() == currentNo){//判断当前编号是否跟消息编号一致 ，若一致，说明准备阶段也是此节点提出
			currentNo = msg.getNo();
			this.msg = msg;
			this.isAccepted = true; //已接受
			this.sendMessage(ZKServer.PROTOCOL_NO_21, "");
		}else{
			this.sendMessage(ZKServer.PROTOCOL_NO_22, this.currentNo+"");
		}
	}
	
	//准备
	public void prepare(){
		//发送自己当前的编号
		this.broadcastMessage(ZKServer.PROTOCOL_NO_10,  ++currentNo+"");
	}
	
	//提交
	public void commit(){
		msg = new Message();
		msg.setNo(this.currentNo);
		msg.setValue("abc");
		this.broadcastMessage(ZKServer.PROTOCOL_NO_20, this.msg.toString() );
	}
	
	//发送消息
	public void sendMessage(int protocolNo, String msg){
		
	}
	
	//广播消息
	public void broadcastMessage(int protocolNo, String msg){
		
	}
	
	public void messageReceived(int protocloNo, String msg){
		switch (protocloNo) {
		case ZKServer.PROTOCOL_NO_11:
			//处理接受准备逻辑
			break;
		case ZKServer.PROTOCOL_NO_12:
			//处理因【编号已增加】 拒绝准备逻辑
			break;
		case ZKServer.PROTOCOL_NO_13:
			//处理因【已接收提议】 拒绝准备逻辑
			break;
		case ZKServer.PROTOCOL_NO_21:
			//处理接受提交逻辑
			break;
		case ZKServer.PROTOCOL_NO_22:
			//处理因【编号已增加】 拒绝提交逻辑
			break;
		case ZKServer.PROTOCOL_NO_23:
			//处理因【已接收提议】 拒绝提交逻辑
			break;
		default:
			break;
		}
		
	}
	
	public void loop(){
		//1、发起提议
		this.prepare();
	}
	
	
	
}

class Message{
	//编号
	private int no = 0;
	//当前值
	private String value = null;
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	@Override
	public String toString() {
		return "[no="+this.no+",value="+this.value+"]";
	}
	
	
}