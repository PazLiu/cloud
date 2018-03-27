package com.shty.collect.web.Filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter(filterName = "RestFilter", urlPatterns = "/rest/*")
public class RestFilter implements Filter {

	FilterConfig filterConfig;

	/**
	 * Default constructor.
	 */
	public RestFilter() {
		System.out.println("RestFilter :");
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		System.out.println("RestFilter : 过滤器销毁");
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		String remateIp = request.getRemoteAddr();
		System.out.println(remateIp + " : " + request.getRemoteHost() + " : " + request.getRemotePort());

		if (remateIp.indexOf("192.168") >= 0 || remateIp.equals("127.0.0.1") || remateIp.equals("0:0:0:0:0:0:0:1")) {
			chain.doFilter(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		filterConfig = fConfig;
		System.out.println("RestFilter : 过滤器初始化");
	}
}
