package cn.labsoft.labos.framework.common.interceptor;

import java.util.Map;
import java.util.regex.Pattern;

import com.opensymphony.xwork2.ActionInvocation;

/**
 * 
 * <strong>Title : SecurityInterceptor </strong>. <br>
 * <strong>Description : 安全拦截器，拦截具有攻击行为的字符串</strong> <br>
 * <strong>Create on : 2014-2-25 下午04:35:45  </strong>. <br>
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
public class SecurityInterceptor extends BaseInterceptor {

	private static final long serialVersionUID = -374803404527649448L;
	
	private static Pattern scriptPattern = Pattern.compile("script", Pattern.CASE_INSENSITIVE);

	@Override
	public String intercept(ActionInvocation actionInvocation) throws Exception {
		Map<String, Object> parameters = actionInvocation.getInvocationContext().getParameters();
		for (String key : parameters.keySet()) {
			Object value = parameters.get(key);
			if (value instanceof String[]) {
				String[] values = (String[]) value;
				for (int i = 0; i < values.length; i++) {
					values[i] = scriptPattern.matcher(values[i]).replaceAll("&#x73;cript");
					values[i] = values[i].replaceAll("<", "&lt;");
					values[i] = values[i].replaceAll(">", "&gt;");
				}
				parameters.put(key, values);
			}
		}
		return actionInvocation.invoke();
	}

}