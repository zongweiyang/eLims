package cn.labsoft.labos.framework.common.filter;

import cn.labsoft.labos.framework.common.sesseionutils.HttpSessionContextDataHolder;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class HttpSessionContextDataFilter implements Filter {
	private static Log log = LogFactory
			.getLog(HttpSessionContextDataFilter.class);

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		if (request == null) {
			return;
		}
		HttpSessionContextDataHolder.setContext(((HttpServletRequest) request)
				.getSession());
		chain.doFilter(request, response);
	}

	public void init(FilterConfig filterConfig) throws ServletException {
	}
}