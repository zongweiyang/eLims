package cn.labsoft.labos.framework.common.interceptor;

import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.Globals;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;


@SuppressWarnings( { "serial" })
public class BaseInterceptor extends AbstractInterceptor {
	protected HttpServletRequest getRequest(){
		return ServletActionContext.getRequest();
	}
	
	protected HttpServletResponse getResponse(){
		return ServletActionContext.getResponse();
	}
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		Locale locale = (Locale)request.getSession().getAttribute(Globals.LOCALE_KEY);
		System.out.println("base inteceptor: "+locale);
		ActionContext.getContext().setLocale(locale);
		
		return invocation.invoke();
	}
	
}
