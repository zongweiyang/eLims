package DBstep;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;

public class SignatureServer extends HttpServlet
implements Servlet{
	public SignatureServer() {
		super();
	}
	
	@Override
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("~~~~~doGet~~~`");
		doPost(request, response);
	}

	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("~~~~doPost~~~~~`");
	}

	
	@Override
	public void init() throws ServletException {
	}
	private static final String CONTENT_TYPE = "text/html; charset=GBK";
	private Signature signature;        
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
	        throws ServletException, IOException{
		resp.setContentType(CONTENT_TYPE);
		signature = new Signature();
		try {
			signature.ExecuteRun(req, resp);
		} catch (GlobalException e) {
			Log4J.error("", e);
		}
	}
}
