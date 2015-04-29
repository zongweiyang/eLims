package cn.labsoft.labos.constants;

import java.io.IOException;
import javax.servlet.GenericServlet;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
 
 
/**
 * 以下是类似org.springframework.web.struts.DelegatingRequestProcessor的一个委托
 * 用于通过配置的方法，在Servlet中注入Service
 * @author liyinwei
 * 
 */
public class DelegatingServletProxy extends GenericServlet{
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String targetBean;
	private Servlet proxy;
 
	@Override
	public void service(ServletRequest req, ServletResponse res)
			throws ServletException, IOException {
		proxy.service(req, res);
	}
 
	/**
	 * 初始化
	 */
	@Override
	public void init() throws ServletException {
		this.targetBean = getServletName();
		getServletBean();
		proxy.init(getServletConfig());
	}
 
	/**
	 * 获取Bean
	 */
	private void getServletBean() {
		WebApplicationContext wac = WebApplicationContextUtils
				.getRequiredWebApplicationContext(getServletContext());
		this.proxy = (Servlet) wac.getBean(targetBean);
	}
 
}
