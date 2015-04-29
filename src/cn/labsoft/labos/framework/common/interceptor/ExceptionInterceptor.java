package cn.labsoft.labos.framework.common.interceptor;

import java.io.IOException;
import java.sql.SQLException;

import cn.labsoft.labos.base.logs.service.ILabLogExceptionService;
import cn.labsoft.labos.base.logs.vo.LabLogExceptionVo;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.framework.common.servicefactory.SystemInstance;
import cn.labsoft.labos.framework.common.sesseionutils.SessionContainer;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.ExceptionHolder;
import com.opensymphony.xwork2.util.CompoundRoot;

/**
 * 
 * <strong>Title : ExceptionInterceptor </strong>. <br>
 * <strong>Description : TODO�</strong> <br>
 * <strong>Create on : Mar 6, 2014 7:57:04 PM  </strong>. <br>
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
@SuppressWarnings( { "serial" })
public class ExceptionInterceptor extends BaseInterceptor {

	 private void exception(Exception e) throws Exception {   
		   if (e instanceof GlobalException) {
			  return ; 
	       } else if (e instanceof SQLException) {   
	    	   
	       } else if (e instanceof ClassCastException) {   
	    	   
	       } else if (e instanceof NumberFormatException) {   
	    	   
	       } else if (e instanceof ClassNotFoundException) {   
	    	   
	       } else if (e instanceof ArrayIndexOutOfBoundsException) {   
	    	   
	       } else if (e instanceof IOException) {   
	    	   
	       } else if (e instanceof NoSuchMethodException) {   
	    	   
	       } else if (e instanceof NullPointerException) {   
	    	   
	       }  
		   
		   if(Log4J.getValue("log4j.printStackTrace")){
			   //e.printStackTrace();
		   }
		   if(e.getStackTrace()[0].getClassName().startsWith("cn.labsoft"))
			   saveMothed(e);
			   
		   
	       throw new GlobalException(e);
	  }

	private void saveMothed(Exception e) throws GlobalException {
		  
		   StackTraceElement stackTraceElement = e.getStackTrace()[0];
		   LabLogExceptionVo labLogExceptionVo = new LabLogExceptionVo();
		   labLogExceptionVo.setClazz(stackTraceElement.getClassName());
		   labLogExceptionVo.setLineNum(""+stackTraceElement.getLineNumber());
		   labLogExceptionVo.setException(e.toString());
		   labLogExceptionVo.setMethod(stackTraceElement.getMethodName());
		   
		   SessionContainer sessionContainer = SessionContainer.getSessionContainer();
		   labLogExceptionVo.setFunId(sessionContainer.getFunId());
		   labLogExceptionVo.setUserInfo(sessionContainer.getLoginName()+" : "+sessionContainer.getUserName());
		   labLogExceptionVo.setUri(getRequest().getRequestURI());
		   ILabLogExceptionService labLogExceptionService = (ILabLogExceptionService) SystemInstance
		   		.getInstance().getBean(ILabLogExceptionService.class);
		   labLogExceptionService.addLabLogException(labLogExceptionVo);
	}

	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		String result = arg0.invoke();
		CompoundRoot root = arg0.getStack().getRoot();
		Object obj = root.get(0);
		if(obj instanceof ExceptionHolder){
			ExceptionHolder exceptionHolder = (ExceptionHolder)obj;
			exception(exceptionHolder.getException());
			result = "exception";
		}
		
		return result;
	} 


}
