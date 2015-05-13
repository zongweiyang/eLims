package cn.labsoft.labos.framework.common.interceptor;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.sesseionutils.SessionContainer;

import com.opensymphony.xwork2.ActionInvocation;

/**
 * 
 * <strong>Title : ParameterInterceptor </strong>. <br>
 * <strong>Description : TODO�</strong> <br>
 * <strong>Create on : Mar 1, 2014 10:29:14 AM  </strong>. <br>
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
public class ParameterInterceptor extends BaseInterceptor {

	private static final long serialVersionUID = 2365641900033439481L;

	@Override
	public String intercept(ActionInvocation actionInvocation) throws Exception {
		/*Map<String, Object> parameters = actionInvocation
				.getInvocationContext().getParameters();
		for (String key : parameters.keySet()) {
			Object value = parameters.get(key);
			if (value instanceof String[]) {
				String[] values = (String[]) value;
				for (int i = 0; i < values.length; i++) {
					values[i] = values[i].trim();
					System.out.println("para:"+values[i]);
				}
				parameters.put(key, values);
			}
		}
		*/
		String actionName = actionInvocation.getProxy().getActionName();
		//弹层页面
		if(Action.isSelectPage(actionName)){
			return actionInvocation.invoke();
		}
		
		//AJAX
		if(Action.isAJaxPage(actionName)){
			return actionInvocation.invoke();
		}
		
		//来自于中间页
		if(getParameters().contains("fromPage=next.jsp")){
			return actionInvocation.invoke();
		}
		
		//非List页面不处理
		if(!Action.isListPage(actionName)){
			
		}else{
			SessionContainer sessionContainer = SessionContainer.getSessionContainer();
			String url = String.valueOf(getRequest().getRequestURL());
			List<String[]> parametersList = getParametersList();
			String funIdNew = getFunId(actionInvocation);
			
			if("GET".equalsIgnoreCase(getRequest().getMethod())&&(null==funIdNew||"".equals(funIdNew))){
				//本来携带参数，则不处理
				if(null!=parametersList&&parametersList.size()>0){
					
				}else{
					getRequest().setAttribute("paramList", sessionContainer.getParametersList());
					getRequest().setAttribute("nextAction", url);
					return "nextAction";
				}
			}else{
				sessionContainer.setLastUrl(url);
				sessionContainer.setParametersList(parametersList);
				getRequest().getSession().setAttribute(SessionContainer.Session_Container, sessionContainer);
			}
		}
		return actionInvocation.invoke();
	}
	
	@SuppressWarnings("unchecked")
	private String getParameters() {
        Enumeration en =getRequest().getParameterNames();
        String re = "";
        String parameterName =  null;
        String parameterValue = null;
        int i=0;
        while(en.hasMoreElements()){
        	parameterName = (String)en.nextElement();
        	if(i++>0)re+="&";
            if (parameterName != null) {
            	if(parameterName.equals("formName")||parameterName.equals("fromPage")){
            		continue;
            	}
                re+= parameterName + "=";
                parameterValue= getRequest().getParameter(parameterName);
                if(parameterValue !=  null)
                   re += parameterValue;
            }
        }
       return re;
	}
	

	@SuppressWarnings("unchecked")
	private List<String[]> getParametersList(){
        Enumeration en =getRequest().getParameterNames();
        List<String[]> list = new ArrayList<String[]>();
        while(en.hasMoreElements()){
        	 String[] str = new String[2];
        	 str[0] = (String)en.nextElement();
        	 str[1] = getRequest().getParameter(str[0]);
        	if(str[0]!=null&&!str[0].equals("formName")&&!str[0].equals("fromPage")){
        		list.add(str);
        	}
        }
       return list;
	}
	
	private String getFunId(ActionInvocation actionInvocation) throws GlobalException{
		String funId=getRequest().getParameter("funId");
		String referer = getRequest().getHeader("Referer");
		if(null!=referer&&!"".equals(referer)){
			try {
				String[] arrays = referer.toUpperCase().split(".ACTION")[0].split("\\/");
				if(Action.extNameSpace(actionInvocation.getProxy().getNamespace())
						||Action.isTreePage(arrays[arrays.length-1])){
					funId = SessionContainer.getSessionContainer().getFunId();
				}
			} catch (Exception e) {
				throw new GlobalException("" + e.getMessage());
			}
		}
		return funId;
	}
	
	
}
