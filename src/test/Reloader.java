package test;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

public class Reloader {
	public static final String REPOSITORY = "./lib/";
	  private Reloader() {
	    
	  }
	  public static final void main(String[] args) throws Exception {
	    Reloader reloader = new Reloader();

	    ClassLoader current = Thread.currentThread().getContextClassLoader();

	    // v1
	    log("HelloWorldLogic_v1 is now loading.");
	    ClassLoader loader = reloader.load(REPOSITORY + "HelloWorldLogic_v1.jar");
	    Thread.currentThread().setContextClassLoader(loader);        // switch context class loader

	    Object objv1 = loader.loadClass("HelloWorldLogic").newInstance();
	    Method method = objv1.getClass().getMethod("getHello", new Class[0]);
	    
	    // getHello()
	    Object msg = method.invoke(objv1, new Object[0]);
	    log("answer from v1 is [" + msg.toString() + "]");

	    // v2
	    loader = null;
	    log("HelloWorldLogic_v2 is now loading.");
	    loader = reloader.load(REPOSITORY + "HelloWorldLogic_v2.jar");
	    Thread.currentThread().setContextClassLoader(loader);        // switch context class loader

	    Object objv2 = loader.loadClass("HelloWorldLogic").newInstance();
	    method = objv2.getClass().getMethod("getHello", new Class[0]);
	    
	    // getHello()
	    msg = method.invoke(objv2, new Object[0]);
	    log("answer from v2 is [" + msg.toString() + "]");

	    Thread.currentThread().setContextClassLoader(current);
	  }
	  private static final void log(String msg) {
	    System.out.println("log: " + msg);
	  }
	  private URLClassLoader load(String jarname) throws Exception {
	    final String base = (new File(jarname).getCanonicalFile().toURL()).toString();
	    URL url = new URL("jar:" + base + "!/");
	    URLClassLoader loader = new URLClassLoader(new URL[]{url});
	    
	    return loader;
	  }
}
