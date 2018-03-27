package com.shty.collect.web.Filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter(servletNames = "ThirdFilter", urlPatterns = "/rest/threaduserinfo/*")
public class ThirdFilter implements Filter {

	FilterConfig filterConfig;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

		this.filterConfig = filterConfig;
		System.out.println("ThirdFilter : 过滤器初始化");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println(request.getRemoteAddr() + " : " + request.getRemoteHost() + " : " + request.getRemotePort());
	}

	@Override
	public void destroy() {
		System.out.println("ThirdFilter : 过滤器销毁");
	}
}
