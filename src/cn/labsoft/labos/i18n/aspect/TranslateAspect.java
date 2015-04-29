package cn.labsoft.labos.i18n.aspect;

import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts.Globals;
import org.apache.struts2.ServletActionContext;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;

import cn.labsoft.labos.i18n.util.TranslateUtil;

public class TranslateAspect {
	
	private static final String TRANSLATER_ANNOTATION = "@annotation(cn.labsoft.labos.i18n.annotation.Translator)";
	private static final Locale EN_US = Locale.US;
	
	@Around(TRANSLATER_ANNOTATION)
	public String translater(ProceedingJoinPoint pjp) throws Throwable{
		//get local language.
		System.out.println("coming ");
		HttpSession session = ServletActionContext.getRequest().getSession();
		Locale locale = (Locale) session.getAttribute(Globals.LOCALE_KEY);
		String value = "";
		if(locale != null && locale.equals(EN_US)){
			
			String obj = String.valueOf(pjp.proceed());
			System.out.println(obj);
			if(!StringUtils.isEmpty(obj)){
				value = TranslateUtil.get(obj);
				value = StringUtils.isEmpty(value) ? obj : value;
			}
			System.out.println(value);
		}
		return value;
	}
	public static void main(String[] args) {
		Locale locale = new Locale("en", "US");
		System.out.println(locale.equals(EN_US));
		System.out.println(StringUtils.isEmpty(null));
	}
}
