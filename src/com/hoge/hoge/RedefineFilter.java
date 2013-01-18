package com.hoge.hoge;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

import logger.Weave;

/**
 * Servlet implementation class RedefineFilter
 */
public class RedefineFilter implements Filter{
	private static final long serialVersionUID = 1L;
      
	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain chain) throws IOException, ServletException {
		chain.doFilter(arg0, arg1);
	}

	@Override
	public void init(FilterConfig config) throws ServletException {

//		try {
//		Method m = ClassLoader.class.getDeclaredMethod("findLoadedClass", new Class[]{String.class});
//		m.setAccessible(true);
//		Class<?> s = (Class<?>)m.invoke(Weave.class.getClassLoader(), "bank.Bank");
//		System.out.println(s);
//		} catch (Exception ex) {
//		ex.printStackTrace();	
//		}
//		
//		System.out.println("Filter init.");
//		String servletPath = config.getServletContext().getRealPath("/")+"/WEB-INF/classes";
//		logger.Weave.weave(servletPath);
		
	}


}
