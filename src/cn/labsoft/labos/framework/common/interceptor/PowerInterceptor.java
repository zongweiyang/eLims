package cn.labsoft.labos.framework.common.interceptor;

import javax.annotation.Resource;

import cn.labsoft.labos.base.function.service.ILabPowerService;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;

/**
 * 
 * <strong>Title : PowerInterceptor </strong>. <br>
 * <strong>Description : 权限拦截器</strong> <br>
 * <strong>Create on : 2014-2-25 下午04:36:58 </strong>. <br>
 * <p>
 * <strong>Copyright (C) Labsoft Pte,Ltd.</strong> <br>
 * </p>
 * 
 * @author Charles Xi <br>
 * @version <strong>LabOSM1 v 1.0.0</strong> <br>
 *          <br>
 *          <strong>修改历史: .</strong> <br>
 *          修改人� 修改日期 修改描述<br>
 *          -------------------------------------------<br>
 *          <br>
 *          <br>
 */
@SuppressWarnings( { "serial" })
public class PowerInterceptor extends BaseInterceptor {
	private ILabPowerService labPowerService;

	@Resource
	public void setLabPowerService(ILabPowerService labPowerService) {
		this.labPowerService = labPowerService;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		String nameSpace = invocation.getProxy().getNamespace().trim();
		if (null != nameSpace && !"".equals(nameSpace)) {
			if ("/".equals(nameSpace)) {
				// 说明是跟路径 不需要+ "/"
			} else {
				nameSpace += "/";
			}
		}
		String uri = (nameSpace + invocation.getProxy().getActionName() + ".action").trim();
		String URI = uri.toUpperCase();
		boolean specialURI = (URI != null && uri.length() > 0 && ( (URI.startsWith("/LANG")) || URI.startsWith("/COREEXTEND/EXTEND") || URI.startsWith("/PORTLETS") || URI.startsWith("/MESSAGE") || URI.contains("LABUSER4CENTER")));
		if (specialURI || labPowerService.hasPower(uri)) {
			return invocation.invoke();
		} else {
			return Action.LOGIN;
		}
	}
}
