package cn.labsoft.labos.framework.common.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.labsoft.labos.utils.MD5Utils;

public class MobilePhoneMessage extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public MobilePhoneMessage() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	@Override
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String msg = request.getParameter("msg");
		String phoneNum = request.getParameter("phoneNum");

		StringBuffer sb = new StringBuffer("");

		String str = "";

		if (null == msg || null == phoneNum) {
			str  = "请在地址栏中输入型如：<a href=\""+basePath+"servlet/MobilePhoneMessage?msg=测试短息噢~~~~&phoneNum=15991450559\">"+basePath+"servlet/MobilePhoneMessage?msg=测试短息噢~~~~&phoneNum=15991450559</a>";
		}else{
			String username = "labsoft";
			String password = "xalabfoft";
			String time = String.valueOf(Math.round(new Date().getTime()/1000));
			String pwd = MD5Utils.MD5(username+password+time);
			String code = "";
			str = "传入参数正确，点击后面链接发送：<a href=\"http://211.147.222.37:8080/sendsms/?username="+username+"&pwd="+pwd+"&dt="+time+"&msg="+msg+"&mobiles="+phoneNum+"&code="+code+"\" >http://211.147.222.37:8080/sendsms/?username="+username+"&pwd="+pwd+"&dt="+time+"&msg="+msg+"&mobiles="+phoneNum+"&code="+code+"</a>";
		}
		
		sb
				.append("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		sb.append("<HTML>");
		sb.append("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		sb.append("  <BODY>");
		sb.append(str);
		sb.append("  </BODY>");
		sb.append("</HTML>");

		out.print(sb.toString());

		out.flush();
		out.close();
	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	@Override
	public void init() throws ServletException {
		// Put your code here
	}

}
