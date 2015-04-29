package cn.labsoft.labos.framework.common.log;

import java.lang.reflect.Method;

import org.springframework.aop.AfterReturningAdvice;

/**
 * 
 * <strong>Title : EventLogInterceptor </strong>. <br>
 * <strong>Description : 方法被调用后的日志记录</strong> <br>
 * <strong>Create on : Nov 13, 2009 11:41:35 AM </strong>. <br>
 * <p>
 * <strong>Copyright (C) cn.com.common</strong> <br>
 * </p>
 * 
 * @author TonyLee<br>
 * @version <strong>rsLab v 1.0.0</strong> <br>
 *          <br>
 *          <strong>修改历史: .</strong> <br>
 *          修改人� 修改日期 修改描述<br>
 *          -------------------------------------------<br>
 *          <br>
 *          <br>
 */
public class EventLogInterceptor implements AfterReturningAdvice {

	public void afterReturning(Object returnObj, Method method, Object[] args,
			Object targetObj) throws Throwable {
		// (joinPoint.getTarget().getClass());
		// logger.warn("Beginning method : " + joinPoint.getTarget().getClass()
		// + "." + joinPoint.getSignature().getName()+ "()");
		// long startTime = System.currentTimeMillis();
		// Object result=null;
		// try{
		// result = joinPoint.proceed();
		// return result;
		// }catch(Exception e){
		// logger.warn( joinPoint.getTarget().getClass() + "." +
		// joinPoint.getSignature().getName() + "() invoke error" );
		// logger.warn("error info ["+e.getMessage()+"]");
		// return result;
		// }finally{
		// logger.warn("Ending method : " + joinPoint.getTarget().getClass() +
		// "." + joinPoint.getSignature().getName() + "()");
		// // logger.warn("Method invocation time : " +
		// (System.currentTimeMillis() - startTime) + " ms.");
		//
		// }
		//logger.warn(targetObj.getClass() + "." + method.getName() + "()");
	}

}