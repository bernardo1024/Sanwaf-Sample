package com.sanwaf.sample;

import java.io.IOException;
import java.util.logging.Logger;

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
  private static final Logger LOGGER = Logger.getLogger(SanwafFilter.class.getName());
  private static Sanwaf sanwaf;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	  try {
	    sanwaf = new Sanwaf(new com.sanwaf.log.SimpleLogger(), "/sanwaf.xml");
		} catch (IOException ioe) {
		  LOGGER.log(java.util.logging.Level.SEVERE, "Exception Raised Instanciating Sanwaf: {0}", ioe.getMessage());
		}
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		
		if (request.getParameterNames() != null && request.getParameterNames().hasMoreElements()) {
			if (sanwaf.isThreatDetected(request)) {
			  Error.handle(request, response);
			}
	    else {
        Success.handle(request, response);
	    }
      return;
		}
		filterChain.doFilter(servletRequest, servletResponse);
	}

	@Override
	public void destroy() {}
}
