package cn.labsoft.labos.utils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.BeansException;
import org.springframework.beans.FatalBeanException;
import org.springframework.util.Assert;

import cn.labsoft.labos.framework.common.log.Log4J;
/**
 * 
 * <strong>Title : BeanUtils </strong>. <br>
 * <strong>Description : TODO�</strong> <br>
 * <strong>Create on : Mar 15, 2014 6:30:49 PM  </strong>. <br>
 * <p>
 * <strong>Copyright (C) Labsoft Pte,Ltd.</strong> <br>
 * </p>
 *
 * @author Carson Liu <br>
 * @version <strong>LabOSM1 v 1.0.0</strong> <br>
 *          <br>
 *          <strong>修改历史: .</strong> <br>
 *          修改人� 修改日期 修改描述<br>
 *          -------------------------------------------<br>
 *          <br>
 *          <br>
 */
public abstract class BeanUtils extends org.springframework.beans.BeanUtils {
	
	public static void copyProperties(Object source, Object target)throws BeansException {
		BeanUtils.copyProperties(source, target, new String[]{});
	}
	
	public static void copyProperties(Object source, Object target,String[] ignoreProperty)
			throws BeansException {
		Assert.notNull(source, "Source must not be null");
		Assert.notNull(target, "Target must not be null");
		Class<?> targetClass = target.getClass();
		PropertyDescriptor[] targetPds = getPropertyDescriptors(targetClass);
		for (PropertyDescriptor targetPd : targetPds) {
			if(ignoreProperty(targetPd.getName(), ignoreProperty))
				continue;
			
			if (targetPd.getWriteMethod() != null) {
				PropertyDescriptor sourcePd = getPropertyDescriptor(source.getClass(), targetPd.getName());
				if (sourcePd != null && sourcePd.getReadMethod() != null) {
					 
					try {
						Method readMethod = sourcePd.getReadMethod();
						if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
							readMethod.setAccessible(true);
						}
						Object value = readMethod.invoke(source);
						
						//数据类型是否匹配
						if (!sourcePd.getPropertyType().equals(targetPd.getPropertyType())){
							//Log4J.out(sourcePd.getName()+":"+sourcePd.getPropertyType().getName()+"->"+targetPd.getPropertyType()+"【"+ source.getClass());
							//target是String强行复制，否则进入下次循环
							if(targetPd.getPropertyType().equals(String.class)){
								value = String.valueOf(value);
							}else{
								continue;
							}
						}
						
						//如果 source 为 null 则不复制
						if (null != value) {
							Method writeMethod = targetPd.getWriteMethod();
							if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
								writeMethod.setAccessible(true);
							}
							writeMethod.invoke(target, value);
						}
					} catch (Throwable ex) {
						throw new FatalBeanException(
								"Could not copy properties from source to target",ex);
					}
				}
			}
		}
	}
	
	private static boolean ignoreProperty(String property,String[] ignoreProperty){
		if(null!=ignoreProperty&&ignoreProperty.length>0){
			for(String tempProperty: ignoreProperty){
				if(property.equals(tempProperty)) return true;
			}
		}
		return false;
		
	}
	

	public static Map<String, String>  getValueMaps(Object obj){
		Assert.notNull(obj, "must not be null");
		Field[] files = obj.getClass().getDeclaredFields();
		Assert.notNull(files, " field must not be null");
		Map<String, String> valMap = new HashMap<String, String>();
		for(Field file:files){
			Object value = null;
			PropertyDescriptor propertyDescriptor = getPropertyDescriptor(obj.getClass(),file.getName());
			if(null==propertyDescriptor){
				continue;
			}
			Method method = propertyDescriptor.getReadMethod();
			try {
				value = method.invoke(obj);
			} catch (IllegalArgumentException e) {
				Log4J.error(e);
			} catch (IllegalAccessException e) {
				Log4J.error(e);
			} catch (InvocationTargetException e) {
				Log4J.error(e);
			}
			valMap.put(file.getName(), null==value?"":String.valueOf(value));
		}
		return valMap;
	}
	

	public static String getValues(Object obj,Map<String,String> aliasMap) {
		StringBuffer strInfo = new StringBuffer("");
		Assert.notNull(obj, "must not be null");
		Field[] files = obj.getClass().getDeclaredFields();
		Assert.notNull(files, " field must not be null");
		for(Field file:files){
			if(file.getType().equals(Set.class)){
				continue;
			}
			String lable = file.getName();
			
			Object value = null;
			PropertyDescriptor propertyDescriptor = getPropertyDescriptor(obj.getClass(),lable);
			if(null==propertyDescriptor){
				continue;
			}
			Method method = propertyDescriptor.getReadMethod();
			//内循环不处理
			if(obj.getClass().equals(method.getReturnType())){
				continue;
			}
			
			try {
				value = method.invoke(obj);
			} catch (IllegalArgumentException e) {
				Log4J.error(e);
			} catch (IllegalAccessException e) {
				Log4J.error(e);
			} catch (InvocationTargetException e) {
				Log4J.error(e);
			}
			if(null!=value){
				if(null!=aliasMap)
					lable = aliasMap.get(lable);
				if(value instanceof String || value instanceof Integer
						||value instanceof Float ||value instanceof Double
						||value instanceof Date ||value instanceof Boolean){
					strInfo.append(lable+"="+value);
					strInfo.append(";");
				}else{
					strInfo.append(value);
				}
			}
		}
		return strInfo.toString();
	}
	
}