package cn.labsoft.labos.i18n.interceptor;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts.Globals;
import org.apache.struts2.ServletActionContext;
import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;

import cn.labsoft.labos.i18n.annotation.Translator;
import cn.labsoft.labos.i18n.util.TranslateUtil;

/**
 * @author zongwei.yang 
 * 2015/3/31
 */

public class TranslateInterceptor extends EmptyInterceptor {
	private static final long serialVersionUID = -7300183928694718984L;
	
	
	@Override
	public boolean onLoad(Object entity,Serializable id,Object[] stateValue,String[] propertyNames,Type[] types) {
//		Object obj = this.getEntity(entityName, id);
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession tempSession = request.getSession();
		
		Map<String,Object> objMap = new HashMap<String,Object>();
		Map<String,Integer> indexMap = new HashMap<String,Integer>();
		for(int i=0;i<propertyNames.length;i++){
			objMap.put(propertyNames[i], stateValue[i]);
			indexMap.put(propertyNames[i], i);
		}
		
		Locale locale = (Locale) tempSession.getAttribute(Globals.LOCALE_KEY);
		
		if(entity !=null && locale != null && locale.equals(Locale.US)){
			
			Field [] fields = entity.getClass().getDeclaredFields();
			try {
				for(Field f:fields){
					Translator translator = f.getAnnotation(Translator.class);
					if( translator != null ){
						
						Object oldValue = objMap.get(f.getName());
						String value = String.valueOf(oldValue);
//						System.out.println(value);
						if(!StringUtils.isEmpty(value)){
							String transValue = TranslateUtil.get(value);
							transValue = StringUtils.isEmpty(transValue) ? value : transValue;
							stateValue[indexMap.get(f.getName())] = transValue;
//							System.out.println("transte: "+transValue);
						}
					}
				}
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	/*@Override
	public Object getEntity(String entityName,Serializable id){
//		System.out.println(entityName + id);
//		SessionFactory sf = (SessionFactory) SpringContext.getBean("sessionFactory");
		
		Object obj = super.getEntity(entityName, id);
//		Object obj = this.getEntity(entityName, id);
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession tempSession = request.getSession();
		
		Locale locale = (Locale) tempSession.getAttribute(Globals.LOCALE_KEY);
		
		if(obj !=null && locale != null && locale.equals(Locale.US)){
			
			Field [] fields = obj.getClass().getDeclaredFields();
			try {
				for(Field f:fields){
					Translator translater = f.getAnnotation(Translator.class);
					if( translater != null ){
						f.setAccessible(true);
						String value = String.valueOf(f.get(obj));
						System.out.println(value);
						if(!StringUtils.isEmpty(value)){
							String transValue = TranslateUtil.get(value);
							transValue = StringUtils.isEmpty(transValue) ? value : transValue;
							f.set(obj, transValue);
							System.out.println(transValue);
						}
					}
				}
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return obj;
	}*/
}
