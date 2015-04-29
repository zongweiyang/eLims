package cn.labsoft.labos.framework.common.log;

import java.io.IOException;
import java.io.Serializable;

import org.springframework.aop.ThrowsAdvice;

/**
 * 
 * <strong>Title : LogThrowingAdvice </strong>. <br>
 * <strong>Description : 抛出异常后的记录对象</strong> <br>
 * <strong>Create on : Nov 13, 2009 11:41:35 AM </strong>. <br>
 * <p>
 * <strong>Copyright (C) cn.com.common</strong> <br>
 * </p>
 * 
 * @author TonyLee<br>
 * @version <strong>rsLab v 1.0.0</strong> <br>
 *          <br>
 *          <strong>修改历史: .</strong> <br>
 *          修改人� 修改日期 修改描述<br>
 *          -------------------------------------------<br>
 *          <br>
 *          <br>
 */
public class LogThrowingAdvice implements ThrowsAdvice ,Serializable{// 使用Throw通知类型来实现Advice

	/** 
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	 */ 
	
	private static final long serialVersionUID = 1L;

	public void afterThrowing(Throwable e) throws Throwable {
		/*
		String time = DateUtils.format(new Date(),
				DateUtils.formatStr_yyyyMMddHHmmss);
		InputStream in=LogThrowingAdvice.class.getClassLoader().getResourceAsStream("resources/log4j.properties");
		Properties pro=new Properties();
		pro.load(in);
		String projectname=pro.getProperty("log4j.projectname");
		if(StringUtils.isBlank(projectname)){
			projectname="default";
		}
		String str = "ERROR:" + "\t" + time.toString() + "\t" + e.toString();
		String error = "ERROR:" + "\t" + time.toString() + "\t" + e.toString();
		StackTraceElement[] s = e.getStackTrace();
		for (Object obj : s) {
			if (null != obj) {
				str += obj.toString() + "\n" + "\t";
				error += obj.toString() + "\n" + "\t";
			}
		}
		String name = DateUtils.format(new Date(), DateUtils.YYYY
				+ DateUtils.MM + DateUtils.DD);
		// String name=new SimpleDateFormat("yyyyMMddHHmmss").format(new
		// Date());
		String exceptiondir=pro.getProperty("exceptiondir");
		if(StringUtils.isBlank(exceptiondir)){
			exceptiondir=".."+File.separator+"logs";
		}
		String dirAdd = exceptiondir;
		String address = exceptiondir+File.separator+projectname+"Exception" + name + ".log";
		readToBuffer(dirAdd, address, str);
	*/
	}

	@SuppressWarnings("unused")
	private void readToBuffer(String dirAdd, String address, String str)
			throws IOException {
		/*
		String line = "";
		String read = "";
		File dirF = new File(dirAdd);
		if (!dirF.exists()) {
			dirF.mkdir();
		} else {
			File f = new File(address);
			if (!f.exists()) {
				f.createNewFile();
			}
			BufferedReader input = new BufferedReader(new FileReader(f));
			while (null != (line = input.readLine())) {
				read += line + "\n";
			}
			if (null == read || "".equals(read)) {
				read = "";
			}
			str = read + str;
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(address)));
			out.write(str, 0, str.length());
			out.flush();
			out.close();
			input.close();
		}
 	*/
	}
}
