package cn.labsoft.labos.framework.common.interceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import cn.labsoft.labos.base.function.service.ILabFunctionService;
import cn.labsoft.labos.base.function.vo.LabFunctionVo;
import cn.labsoft.labos.base.user.service.ILabUserService;
import cn.labsoft.labos.base.user.vo.LabUserVo;
import cn.labsoft.labos.constants.Constants_Base;
import cn.labsoft.labos.framework.common.sesseionutils.SessionContainer;

import com.opensymphony.xwork2.ActionInvocation;

@SuppressWarnings( { "serial" })
public class SessionInterceptor4Admin extends BaseInterceptor {

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
		HttpSession session = request.getSession();
		Object sessionObject = session.getAttribute(SessionContainer.Session_Container);
		String nameSpace = invocation.getProxy().getNamespace().toUpperCase().trim();
		if (null != nameSpace && !"".equals(nameSpace)) {
			if ("/".equals(nameSpace)) {
				//说明是跟路径  不需要+ "/"
			} else {
				nameSpace += "/";
			}
		}
		String URL = (nameSpace + invocation.getProxy().getActionName() + ".action").toUpperCase().trim();
		if (null != funIdNew && !"".equals(funIdNew)) {
			session.setAttribute("funId", funIdNew);
		}

		//判断是否是后台用户的操作
		String type = "";
		if (null != sessionObject) {
			SessionContainer sessionContainer = (SessionContainer) sessionObject;
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

		//判断是否是登陆、首页Portal、消息
		if (URL != null && URL.length() > 0 && URL.startsWith("/ADMIN/COREEXTEND/EXTEND")) {
			if (URL.startsWith("/ADMIN/COREEXTEND/EXTEND/LOGINSYSTEM") && false) {
				return "AdminLogin";
			}
			return invocation.invoke();
		}
		//判断sessionObject  是否为空
		if (sessionObject == null) {
			return "AdminLogin";
		}

		if (null != funIdNew) {
			LabFunctionVo labFunctionVo = labFunctionService.getLabFunction(funIdNew);
			if (labFunctionVo != null && !"".equals(labFunctionVo.getName())) {
				session.setAttribute("funName", labFunctionVo.getName());
			}
		}

//		//是否是 修改个人信息
//		String userInfoURL = "/lab/user/updateLabUser.action".toUpperCase().trim();
//		if (userInfoURL.equals(URL)) {
//			return invocation.invoke();
//		}
//		if ("/lab/user/updateLabUser4fun.action".toUpperCase().trim().equals(URL)) {
//			return invocation.invoke();
//		}
//		if ("/lab/user/updateLabUser4Self.action".toUpperCase().trim().equals(URL)) {
//			return invocation.invoke();
//		}
		//得到SessionContainer 
		SessionContainer sessionContainer = (SessionContainer) sessionObject;
		if (null != funIdNew && !"".equals(funIdNew.trim())) {
			sessionContainer.setFunId(funIdNew);
		}
		//获得当前用户所具有的角色
		if (null == sessionContainer.getRoleIds() || sessionContainer.getRoleIds().length == 0) {
			//没有任何角色
			return "AdminLogin";
		}
		return invocation.invoke();
	}
}
