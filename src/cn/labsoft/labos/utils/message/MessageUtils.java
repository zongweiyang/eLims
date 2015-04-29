package cn.labsoft.labos.utils.message;
/**
 * 
 * <strong>Title : SmsUtils </strong>. <br>
 * <strong>Description : TODO�</strong> <br>
 * <strong>Create on : Oct 10, 2014 2:07:46 PM  </strong>. <br>
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
public class MessageUtils {
	/**
	 * 发送短信
	 * @Title:  
	 * @Description: TODO
	 * @param @param msg 短信内容
	 * @param @param toTels 多个以“,”分隔 
	 * @param @param isMultithreading 是否多线程 
	 * @return 返回类型 
	 * @throws
	 */
	public static String sendSms(String toTels,String msg,boolean isMultithreading) {
		String result = null;
		if(isMultithreading){
			Thread exportThread = new Thread(new Sms(msg, toTels));
			exportThread.run();
		}else{
			result = new Sms().sendSms(msg, toTels);
		}
		return result;
	}
	/**
	 * 发送 email 
	 * @Title:  
	 * @Description: TODO
	 * @param @param msg 短信内容
	 * @param @param subject 主题
	 * @param @param toMails 多个以“,”分隔 
	 * @param @param isMultithreading 是否多线程
	 * @return 返回类型 
	 * @throws
	 */
	public static String sendMail(String toMails, String subject, String msg,boolean isMultithreading) {
		String result = null;
		if(isMultithreading){
			Thread exportThread = new Thread(new Mail(toMails, subject,msg));
			exportThread.run();
		}else{
			result = new Mail().sendMail(toMails, subject,msg);
		}
		return result;
	}
	
	
}
