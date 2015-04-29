package cn.labsoft.labos.utils;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GzipFilter implements Filter{
	public void init(FilterConfig filterConfig)
		throws ServletException {
		
	}
	
	public void destroy() {
		
	}
	
	public void doFilter(ServletRequest request,
						ServletResponse response,
						FilterChain chain)
				throws IOException,
						ServletException {
		String url = ((HttpServletRequest) request).getServletPath();
		if (url.endsWith(".gz.js")) {
			((HttpServletResponse)response).setHeader("Content-Encoding", "gzip");
		}
		
		chain.doFilter(request, response);
	}

}
