package cn.labsoft.labos.framework.common.interceptor;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import com.opensymphony.xwork2.ActionContext;

import cn.labsoft.labos.base.logs.dao.ILabLogRecordDAO;
import cn.labsoft.labos.base.logs.entity.LabLogRecord;
import cn.labsoft.labos.framework.common.po.AbstractBasePo;
import cn.labsoft.labos.framework.common.servicefactory.SystemInstance;
import cn.labsoft.labos.framework.common.sesseionutils.SessionContainer;


@SuppressWarnings("unchecked")
public class LogAroundAdvice implements MethodInterceptor {
	
	public Object invoke(MethodInvocation arg0) throws Throwable {
		String method = arg0.getMethod().getName();
		String methodName = getOperatorMethod(method);
		if (!(methodName == null || "".equals(methodName))) {
			ILabLogRecordDAO dao = (ILabLogRecordDAO) SystemInstance.getInstance().getBean(ILabLogRecordDAO.class);

			// user Name
			Object[] object = arg0.getArguments();
			// operator method name
			// operator send object
			if(!(object[0] instanceof AbstractBasePo)){
				return arg0.proceed();
			}
			String tableName = getListMethodName(object[0]);
			
			String moduleName = getListMoudleName(object[0]);
			String content = getInstanceContent((AbstractBasePo)object[0]);
			if(null==ActionContext.getContext()||null==ActionContext.getContext().getSession().get(SessionContainer.Session_Container)){
				return arg0.proceed();
			}
			SessionContainer sessionContainer =(SessionContainer) ActionContext.getContext().getSession().get(SessionContainer.Session_Container);
			if (!(tableName == null || "".equals(tableName))) {
				LabLogRecord systemLog = new LabLogRecord();
				systemLog.setOperatorid(sessionContainer.getUserId());
				systemLog.setOperator(sessionContainer.getUserName());
				systemLog.setContent(content);
				systemLog.setModule(moduleName);
				systemLog.setWorkTable(tableName);
				systemLog.setMethod(methodName);
				systemLog.setDateTime(new Date());
				systemLog.setIp(sessionContainer.getIp());
				dao.addLabLogrecord(systemLog);	
			}
		}
		return arg0.proceed();
	}

	private <T extends AbstractBasePo> String getInstanceContent(T object) {
		if(null!=object){
			String str=object.toString();
			if(null!=str&&!"".equals(str)){
//				if (7 < str.length()) {
//					return str.substring(0, str.length() - 7);
//				}
				return str;
			}
		}
		return "";
	}
/**
 * 
 * @Title:返回操作方法  
 * @Description: TODO
 * @param @param method
 * @param @return  
 * @return String 
 * @throws
 */
	public String getOperatorMethod(String method) {
		String methodName = "";
		if (method.startsWith(LogCommonInformation.SAVEORUPDATE)) {
			methodName = LogCommonInformation.SAVEORUPDATE1;
		} else {
			if (method.startsWith(LogCommonInformation.UPDATE)) {
				methodName = LogCommonInformation.UPDATE1;
			} else {
				if (method.startsWith(LogCommonInformation.DELETE)) {
					methodName = LogCommonInformation.DELETE1;
				} else {
					if (method.startsWith(LogCommonInformation.SAVE)) {
						methodName = LogCommonInformation.ADD1;
					} else {
						if (method.startsWith(LogCommonInformation.ADD)) {
							methodName = LogCommonInformation.ADD1;
						}
					}
				}
			}
		}
		return methodName;
	}
	/**
	 * send data name
	 * 
	 * @param object
	 * @return
	 */
	public <T extends AbstractBasePo> String getSendObject(T object) {
		return object.getTableName();
	}
 /**
  * 
  * @Title:返回方名称列表  
  * @Description: TODO
  * @param @param object
  * @param @return  
  * @return String 
  * @throws
  */
	public String getListMethodName(Object object) {
		if (object instanceof ArrayList) {
			ArrayList list = (ArrayList) object;
			StringBuilder builder = new StringBuilder();
			for (Iterator it = list.iterator(); it.hasNext();) {
				builder.append(getSendObject((AbstractBasePo)it.next()) + ",");
			}
			return builder.substring(0, builder.length() - 1);
		} else {
			return getSendObject((AbstractBasePo)object);
		}
	}
	/**
	 * 
	 * @Title:  
	 * @Description: TODO
	 * @param @param object
	 * @param @return  
	 * @return String
	 * @throws
	 */
	public <T extends AbstractBasePo> String getOneMoudleName(T object) {
		return object.getModelName();
	}
    /**
     * 
     * @Title:  
     * @Description: TODO
     * @param @param object
     * @param @return  
     * @return 返回类型 
     * @throws
     */
	public String getListMoudleName(Object object) {
		if (object instanceof ArrayList) {
			ArrayList list = (ArrayList) object;
			StringBuilder builder = new StringBuilder();
			for (Iterator it = list.iterator(); it.hasNext();) {
				builder.append(getOneMoudleName((AbstractBasePo)it.next()) + ",");
			}
			return builder.substring(0, builder.length() - 1);
		} else {
			return getOneMoudleName((AbstractBasePo)object);
		}
	}
}
