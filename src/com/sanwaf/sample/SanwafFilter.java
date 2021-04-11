package com.sanwaf.sample;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sanwaf.core.Sanwaf;

public class SanwafFilter implements Filter {
	private static Sanwaf sanwaf;

	static {
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		try {
			sanwaf = new Sanwaf(new com.sanwaf.log.SimpleLogger(), "/sanwaf.xml");
		} catch (IOException ioe) {
			System.out.println("Exception Raised Instanciating Sanwaf: " + ioe.getMessage() + "; " + ioe);
		}
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
System.out.println("doFilter Fired.");
		
  if (request.getParameterNames() != null && request.getParameterNames().hasMoreElements()) {
			if (sanwaf.isThreatDetected(request)) {
			  System.out.println("doFilter Fired. threat detected");
				Error.handle(request, response);
				return;
			}
	    else {
        System.out.println("doFilter Fired. threat NOT detected");
	      Success.handle(request, response);
	      return;
	    }
		}
    else {
      //Success.handle(request, response);
    }
    System.out.println("doFilter Fired. doing filter chain");
		
		filterChain.doFilter(servletRequest, servletResponse);
	}

	@Override
	public void destroy() {}

}
