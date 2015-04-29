package cn.labsoft.labos.utils.message;

import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.utils.PropertiesTools;
import cn.labsoft.labos.utils.StrUtils;

/**
 * 
 * <strong>Title : Mail </strong>. <br>
 * <strong>Description : TODO�</strong> <br>
 * <strong>Create on : Oct 10, 2014 5:54:30 PM  </strong>. <br>
 * <p>
 * <strong>Copyright (C) Labsoft Pte,Ltd.</strong> <br>
 * </p>
 *
 * @author Carson Liu <br>
 * @version <strong>LabOSM1 v 1.0.0</strong> <br>
 *          <br>
 *          <strong>修改历史: .</strong> <br>
 *          修改人� 修改日期 修改描述<br>
 *          -------------------------------------------<br>
 *          <br>
 *          <br>
 */
class Mail implements Runnable {
	private static String sends;
	private static String subject;
	private static String content;
	private static String host;
	private static String fromMail ;
	private static String pwd;
	private static boolean isSend = false;
	static{
		try {
			Properties p = new PropertiesTools().read("resources/msg.properties");
			host = p.getProperty("mail.host");
			fromMail = p.getProperty("mail.fromMail");
			pwd = p.getProperty("mail.pwd");
			isSend = Boolean.getBoolean(p.getProperty("mail.isSend"));
		} catch (GlobalException e) {
			Log4J.info("获取短信配置信息出错");
		}
	}
	
	Mail() {
		
	}

	Mail(String sends, String subject, String content){
		this.sends = sends;
		this.subject = subject;
		this.content = content;
	}
	
	String sendMail(String sends, String subject, String msg) {
		if(!isSend)
			return "发送失败,未开启发送功能";
		if(null==msg||"".equals(msg))
			return "内容为空";
		if(null==sends||"".equals(sends))
			return "收件人为空";
		
		String result = "";
		try {
			Properties props = new Properties();
			props.put("mail.smtp.host", host);
			props.put("mail.smtp.auth", true);
			Session session = Session.getDefaultInstance(props, null);
			session.setDebug(false);

			MimeMessage mimeMessage = new MimeMessage(session);
			mimeMessage.setFrom(new InternetAddress(fromMail));
			sends = sends.replace("，", ",");
			List<String> tomails = StrUtils.splitList(sends, ',');
			InternetAddress[] internetAddresses = new InternetAddress[tomails.size()];
			if (null != tomails && tomails.size() > 0) {
				for (int i = 0; i < tomails.size(); i++) {
					internetAddresses[i] = new InternetAddress(tomails.get(i).trim());
				}
			}

			mimeMessage.setRecipients(Message.RecipientType.TO, internetAddresses);
			mimeMessage.setSentDate(new Date());
			mimeMessage.setSubject(subject);
			mimeMessage.setContent(msg, "text/html;charset=UTF-8");
			mimeMessage.saveChanges();// 存储邮件信息

			Transport transport = session.getTransport("smtp");
			transport.connect(host, fromMail, pwd);// 以smtp方式登录邮箱
			transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());// 发送邮件,其中第二个参数是所有
			transport.close();
			
			result = "发送成功";
		} catch (Exception e) {
			e.printStackTrace();
			result = "发送失败";
		}
		return result;
	
	}
	
	@Override
	public void run() {
		sendMail(sends, subject, content);
	}

	
	public static void main(String[] args) {
		new Mail().sendMail("304164146@qq.com", "kaka",  "kaka");
	}
}
