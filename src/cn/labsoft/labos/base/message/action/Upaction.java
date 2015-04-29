package cn.labsoft.labos.base.message.action;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import cn.labsoft.labos.framework.common.exception.GlobalException;

import com.opensymphony.xwork2.ActionSupport;

public class Upaction extends ActionSupport {

	/**
	 * 
	 */
	private File up;
	private String upFileName;

	public File getUp() {
		return up;
	}

	public void setUp(File up) {
		this.up = up;
	}

	public String getUpFileName() {
		return upFileName;
	}

	public void setUpFileName(String upFileName) {
		this.upFileName = upFileName;
	}

	private static final long serialVersionUID = 5077507954998028170L;

	@Override
	public String execute() throws GlobalException {
		String extName = ""; // 保存文件拓展名
		String newFileName = ""; // 保存新的文件名
		String nowTimeStr = ""; // 保存当前时间
		SimpleDateFormat sDateFormat;
		Random r = new Random();

		String savePath = ServletActionContext.getServletContext().getRealPath(
				""); // 获取项目根路径
		savePath = savePath + "/file/";
		File f1 = new File(savePath);

		if (!f1.exists()) {

			f1.mkdirs();

		}

		/*
		 * 拼串组成要上传保存文件的路径，即：D:\Program Files \apache-tomcat-6.0.20\webapps
		 * \(项目名)\pic\secondhand 这样的路径
		 */
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setCharacterEncoding("utf-8");
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("utf-8"); // 务必，防止返回文件名是乱码
			if (upFileName.lastIndexOf(".") >= 0) {
				extName = upFileName.substring(upFileName.lastIndexOf("."));
			}
			String url = new Date().getTime()+extName;
			up.renameTo(new File(savePath + url));; // 保存文件
			response.getWriter().print(url + getText("upload.success"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		}// 向页面端返回结果信息
		return null; // 这里不需要页面转向，所以返回空就可以了
	}

}
