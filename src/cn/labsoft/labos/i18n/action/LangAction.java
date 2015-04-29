package cn.labsoft.labos.i18n.action;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.Globals;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import cn.labsoft.labos.framework.common.action.BaseAction;

import com.opensymphony.xwork2.ActionContext;

@SuppressWarnings("serial")
@Controller
public class LangAction extends BaseAction {
	
	@Override
	public String execute() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String localeStr = request.getParameter("request_locale");
		Locale locale = null;
		if(localeStr.equals("en_US")){
			locale = new Locale("en","US");
		}else if(localeStr.equals("zh_CN")){
			locale = new Locale("zh","CN");
		}
		request.getSession().setAttribute(Globals.LOCALE_KEY, locale);
		ActionContext.getContext().setLocale(locale);
//		System.out.println("lang.action "+locale);
		return "success";
	}
	
}
