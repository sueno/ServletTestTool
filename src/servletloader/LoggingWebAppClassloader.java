package servletloader;

import org.apache.catalina.loader.WebappClassLoader;

public class LoggingWebAppClassloader extends WebappClassLoader{

	public LoggingWebAppClassloader() {
		// TODO Auto-generated constructor stub
		System.out.println("This is a LoggingClassLoader!");
	}
	
}
