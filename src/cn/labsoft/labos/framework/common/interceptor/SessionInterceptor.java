package cn.labsoft.labos.framework.common.interceptor;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;

import cn.labsoft.labos.base.function.service.ILabFunctionService;
import cn.labsoft.labos.base.function.vo.LabFunctionVo;
import cn.labsoft.labos.base.user.service.ILabUserService;
import cn.labsoft.labos.base.user.vo.LabUserFunVo;
import cn.labsoft.labos.base.user.vo.LabUserVo;
import cn.labsoft.labos.constants.Constants_Base;
import cn.labsoft.labos.framework.common.sesseionutils.SessionContainer;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
public class SessionInterceptor extends BaseInterceptor {
	
	private static final long serialVersionUID = 7072691359854194332L;
	private ILabFunctionService labFunctionService;
	private ILabUserService labUserService ;
	public ILabFunctionService getLabFunctionService() {
		return labFunctionService;
	}
	@Resource
	public void setLabFunctionService(ILabFunctionService labFunctionService) {
		this.labFunctionService = labFunctionService;
	}
	public ILabUserService getLabUserService() {
		return labUserService;
	}
	@Resource
	public void setLabUserService(ILabUserService labUserService) {
		this.labUserService = labUserService;
	}
	@SuppressWarnings("unchecked")
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String funIdNew = request.getParameter("funId");
		
		Map session=ActionContext.getContext().getSession();
		SessionContainer sessionContainer = (SessionContainer) session.get(SessionContainer.Session_Container);
		String nameSpace = invocation.getProxy().getNamespace().toUpperCase().trim();
		
		if (null != nameSpace && !"".equals(nameSpace)) {
			if ("/".equals(nameSpace)) {
				//说明是跟路径  不需要+ "/"
			} else {
				nameSpace += "/";
			}
		}
		String URL = (nameSpace + invocation.getProxy().getActionName() + ".action").toUpperCase().trim();
		
//		System.out.println(String.format("funId=%s,url=%s",funIdNew,URL));
		
		if (null != funIdNew && !"".equals(funIdNew)) {
			session.put("funId", funIdNew);
			//sessionContainer.setFunId(funIdNew);
		}

		//判断是否是后台用户的操作
		String type = "";
		if (null != sessionContainer) {
			if (null != sessionContainer.getUserId()) {
				LabUserVo labUserVo = labUserService.getLabUser(sessionContainer.getUserId().toString().trim());
				type = labUserVo.getUserType();
			}
		}
		if (type.equals(Constants_Base.BACK) && URL != null && URL.length() > 0 && URL.startsWith("/COREEXTEND/EXTEND/LOGIN.ACTION")) {
			return "front";
		}
		if (type.equals(Constants_Base.FRONT) && URL != null && URL.length() > 0 && URL.startsWith("/ADMIN/COREEXTEND/EXTEND/LOGIN.ACTION")) {
			return "back";
		}
		//获取按钮菜单
		if (URL.startsWith("/COREEXTEND/EXTEND/LISTLABFUNCTION4ICONMENU.ACTION")
				||URL.startsWith("/COREEXTEND/EXTEND/TOPFRAME.ACTION")) {
			sessionContainer.setFunDataStr(null);
			return invocation.invoke();
		}

		//判断是否是登陆、首页Portal、消息
		if (URL != null && URL.length() > 0 && ((URL.startsWith("/COREEXTEND/EXTEND")) 
				|| (URL.startsWith("/PORTLETS"))
					|| ((URL.startsWith("/MESSAGE"))))) {
			if (URL.startsWith("/COREEXTEND/EXTEND/LOGINSYSTEM") && false) {
				return Action.LOGIN;
			}
			return invocation.invoke();
		}
		//判断sessionObject  是否为空
		if (sessionContainer == null) {
			return Action.LOGIN;
		}
		//得到SessionContainer 
		if (null != funIdNew && !"".equals(funIdNew.trim())) {
			LabFunctionVo labFunctionVo = labFunctionService.getLabFunction(funIdNew);
			if (labFunctionVo != null) {
				session.put("funName", labFunctionVo.getName());
				sessionContainer.setFunCode(labFunctionVo.getWfcode());
				LabUserFunVo ufVo=labUserService.getLabUserFun(sessionContainer.getUserId(),sessionContainer.getOrgId(),labFunctionVo.getId());
				if(ufVo!=null){
					sessionContainer.setFunDataStr(ufVo.getTenantId());
				}else if(!sessionContainer.getUserId().equals("0")){
					return Action.LOGIN;
				}
				
			}
			sessionContainer.setFunId(funIdNew);
		}
//		//是否是 修改个人信息
//		String userInfoURL = "/user/labUser/updateLabUser.action".toUpperCase().trim();
//		if (userInfoURL.equals(URL)) {
//			return invocation.invoke();
//		}
//		if ("/lab/user/updateLabUser4fun.action".toUpperCase().trim().equals(URL)) {
//			return invocation.invoke();
//		}
//		if ("/lab/user/updateLabUser4Self.action".toUpperCase().trim().equals(URL)) {
//			return invocation.invoke();
//		}
		
		//获得当前用户所具有的角色
		if (null == sessionContainer.getRoleIds() || sessionContainer.getRoleIds().length == 0) {
			//没有任何角色
			return Action.LOGIN;
		}
		return invocation.invoke();
	}
}
