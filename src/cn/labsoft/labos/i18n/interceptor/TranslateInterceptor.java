package cn.labsoft.labos.i18n.interceptor;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts.Globals;
import org.apache.struts2.ServletActionContext;
import org.apache.xmlbeans.impl.jam.JSourcePosition;
import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;

import cn.labsoft.labos.i18n.annotation.Translator;
import cn.labsoft.labos.i18n.annotation.TranslatorType;
import cn.labsoft.labos.i18n.util.TranslateUtil;

/**
 * @author zongwei.yang 2015/3/31
 */

public class TranslateInterceptor extends EmptyInterceptor {
	private static final long serialVersionUID = -7300183928694718984L;
	private static final String regex = "option";

	boolean isUS() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession tempSession = request.getSession();
		Locale locale = (Locale) tempSession.getAttribute(Globals.LOCALE_KEY);
		return locale != null && locale.equals(Locale.US);
	}

	String getNewValue(String oldValue) {
		String transValue = TranslateUtil.get(oldValue);
		transValue = StringUtils.isEmpty(transValue) ? oldValue : transValue;
		return transValue;
	}
	
	String getNewSqlValue(String oldValue){
		StringBuilder builder = new StringBuilder();
		if(!StringUtils.isEmpty(oldValue)){
			char[] str = oldValue.toCharArray();
			boolean afterFrom = false;
			for (int i = 0; i < str.length; i++) {
				char a = str[i];
				if(!afterFrom && (a == 'A' || a == 'a')  && (i+2) < str.length){
					char s = str[i+1];
					char k = str[i+2];
					if( (s=='S' || s=='s') && k==' '){
						
						builder.append("AS ");
						int j = i+3;
						while(j < str.length && (str[j] != ',' && str[j]!=' ' && str[j]!='F' && str[j]!='f'))j++;
						if(j < str.length){
							
							if(( j+4 )<= str.length && (str[j]=='F' || str[j]=='f')){
								String ss = oldValue.substring(j, j+4);
								if(ss.toUpperCase().equals("FROM"))afterFrom = true;
							}
						
							String v = oldValue.substring(i+2,j);
							String newV = TranslateUtil.get(v);
							builder.append("'"+(newV !=null ? newV : v )+"'");
							builder.append(" "+str[j]);
							i = j;
						}
					}else{
						builder.append(str[i]);
					}
				}else{
					if(( i+4 )<= str.length && (str[i]=='F' || str[i]=='f')){
						String s = oldValue.substring(i, i+4);
						if(s.toUpperCase().equals("FROM"))afterFrom = true;
					}
					builder.append(str[i]);
				}
			}
		}
		return builder.toString();
	}
	
	String getNewHtmlValue(String oldValue) {
		StringBuilder builder = new StringBuilder();
		if(!StringUtils.isEmpty(oldValue)){
			char[] str = oldValue.toCharArray();
			boolean inOption = false;
			int start=0;
			boolean beginTranslate = false;
			for(int i = 0;i<oldValue.length();i++){
				if(inOption){
					if(str[i]=='>'){
						start = i+1;
						beginTranslate = true;
						builder.append(str[i]);
					}
					else if(beginTranslate && str[i]=='<' && (i+7) < oldValue.length() && oldValue.subSequence(i+1,i+8).equals("/option")){
						
						String v = oldValue.substring(start, i);
						String newV = TranslateUtil.get(v);
						builder.append(newV!=null?newV:v);
						builder.append('<');
						inOption =false;
						beginTranslate = false;
						start = 0;
					}
				}
				else{
					builder.append(str[i]);
					if(str[i]=='<'){
						int j = i+7;
						if(j<oldValue.length()){
							String sub = oldValue.substring(i+1, j);
							if(sub.equals(regex)){
								i=j;
								builder.append(regex);
								inOption = true;
							}
						}
					}
				}
			}
		}
		return builder.toString();
	}

	@Override
	public boolean onLoad(Object entity, Serializable id, Object[] stateValue,
			String[] propertyNames, Type[] types) {

		if (entity != null) {
			Map<String, Object> objMap = new HashMap<String, Object>();
			Map<String, Integer> indexMap = new HashMap<String, Integer>();
			for (int i = 0; i < propertyNames.length; i++) {
				objMap.put(propertyNames[i], stateValue[i]);
				indexMap.put(propertyNames[i], i);
			}

			Field[] fields = entity.getClass().getDeclaredFields();
			try {
				for (Field f : fields) {
					Translator translator = f.getAnnotation(Translator.class);
					if (translator != null && isUS()) {
						Object oldValue = objMap.get(f.getName());
						if (oldValue != null) {
							String value = String.valueOf(oldValue);
							if ( TranslatorType.HTML == translator.type()  ) {
								value = getNewHtmlValue(value);
							}
							else if(TranslatorType.SQL == translator.type() ){
								value = getNewSqlValue(value);
							}
							else {
								value = getNewValue(value);
							}
							stateValue[indexMap.get(f.getName())] = value;
						}
					}
				}
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

}
