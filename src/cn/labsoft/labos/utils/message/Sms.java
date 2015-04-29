package cn.labsoft.labos.utils.message;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Properties;

import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.utils.PropertiesTools;

	class Sms implements Runnable {
	
	private String msg;//短信内容
	private String phoneNums;//号码
	
	private static String sn;//号码
	private static String pwd;//密码
	private static boolean isSend = false;//是否发送
	private static String sign="";
	
	static{
		try {
			Properties p = new PropertiesTools().read("resources/msg.properties");
			sign = p.getProperty("sms.isSend");
			sn = p.getProperty("sms.sn");
			pwd = p.getProperty("sms.pwd");
			isSend= Boolean.getBoolean(p.getProperty("sms.sign"));
		} catch (GlobalException e) {
			Log4J.info("获取短信配置信息出错");
		}
	}
	
	
	public Sms(){
		
	}
	
	public Sms(String msg,String phoneNums){
		msg+= sign;
		this.msg=msg;
		this.phoneNums=phoneNums;
	} 
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getPhoneNums() {
		return phoneNums;
	}
	public void setPhoneNums(String phoneNums) {
		this.phoneNums = phoneNums;
	}
	public  String sendSms(String msg, String phoneNums) {
		if(!isSend)
			return "发送失败,未开启发送功能";
		if(null==msg||"".equals(msg))
			return "短信内容为空";
		if(null==phoneNums||"".equals(phoneNums))
			return "电话号码为空";
		//输入软件序列号和密码
		String content=null;
		try {
			content = URLEncoder.encode(msg, "utf8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Client client=null;
		try {
			client = new Client(sn,pwd);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String result_mt = client.mdSmsSend_u(phoneNums, content, "", "", "");
		String result="";
		if(result_mt.startsWith("-")||result_mt.equals(""))//发送短信，如果是以负号开头就是发送失败。
		{
			//result = "发送失败！返回值为："+result_mt+"请查看webservice返回值对照表";
			result = "发送失败！返回值为："+result_mt;
		}
		//输出返回标识，为小于19位的正数，String类型的。记录您发送的批次。
		else
		{
			result = "发送成功";
		}
		return result;
		
	}
	@Override
	public void run() {
		sendSms(msg,phoneNums);
	}
		
}
