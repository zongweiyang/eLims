package cn.labsoft.labos.framework.common.interceptor;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.Globals;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;

public class I18nInterceptor extends BaseInterceptor {
	private static final long serialVersionUID = -1980150153455555044L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		
		HttpServletRequest request = getRequest();
		Locale locale = (Locale)request.getSession().getAttribute(Globals.LOCALE_KEY);
		System.out.println("I18n Interceptor "+locale);
		ActionContext.getContext().setLocale(locale);
		
		return invocation.invoke();
	}

}
