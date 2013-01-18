package logger;

import java.io.IOException;
import java.lang.instrument.ClassDefinition;
import java.lang.instrument.Instrumentation;
import java.lang.reflect.Method;
import java.util.List;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtConstructor;
import javassist.CtField;
import javassist.CtMethod;
import javassist.NotFoundException;
import logger.annotation.TargetClass;

public class Weave {

	public static Instrumentation inst;
	
	public static void weave(String... args) {
		try {
			if (args!=null) {
				TargetList.searchPath(args);
			}
			classMaker();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	
	public static void premain (String args, Instrumentation i) {
		inst = i;
		
		
//		premain(null);
	}

	private static void classMaker() throws NotFoundException, CannotCompileException, IOException, ClassNotFoundException, Exception {
		ClassPool cp = ClassPool.getDefault();

		System.err.println("Class Search");
		List<CtClass> list = TargetList.getTargets();
		System.err.println("Define Class");
		for (CtClass logger : list) {

			System.err.println("Class : "+logger.getName());
			Object[] annList = logger.getAnnotations();
			CtClass target = null;

			for (Object ann : annList) {
				if (ann instanceof TargetClass) {
					String targetName = ((TargetClass) ann).value();
					target = cp.get(targetName);
				} else {
				}
			}

			System.err.println("Target : "+target.getName());
			CtField f = new CtField(logger, "loggerField",target);
			target.addField(f);
			
			CtConstructor[] cc = target.getDeclaredConstructors();
			for (CtConstructor c : cc) {
				c.insertAfter("try{loggerField = new "+logger.getName()+"();}catch(Exception ex){ex.printStackTrace();}");
			}

			CtMethod[] methods = logger.getDeclaredMethods();
			for (CtMethod method : methods) {
				try {
				CtMethod m = target.getDeclaredMethod(method.getName());
				m.insertBefore("{try{loggerField.startLogging(this);loggerField.__before();loggerField." + method.getName() + "($$);}catch(Exception ex){ex.printStackTrace();}}");
				m.insertAfter("{try{loggerField.after_" + method.getName() + "($$);loggerField.__after();loggerField.stopLogging();}catch(Exception ex){ex.printStackTrace();}}");
				}catch(Exception ex) {
				}
			}

			// define changeClass & createClass
//			target.writeFile();
//			defineClass(Weave.class.getClassLoader(),target.getName(), target.toBytecode());
			
			Class c = target.toClass(Thread.currentThread().getContextClassLoader());
//			try {
//			Object o = c.newInstance();
//			System.out.println("Define : "+o.getClass());
//			}catch(Exception ex) {
//				ex.printStackTrace();
//			}
//			try {
//			Method m = ClassLoader.class.getDeclaredMethod("findLoadedClass", new Class[]{String.class});
//			m.setAccessible(true);
//			Class<?> s = (Class<?>)m.invoke(Thread.currentThread().getContextClassLoader().getParent(), "bank.Bank");
//			System.out.println(s);
//			} catch (Exception ex) {
//			ex.printStackTrace();	
//			}
			
//			Class<logger.Weave> a = (Class<logger.Weave>)Thread.currentThread().getContextClassLoader().loadClass("logger.Weave");
//			Instrumentation aa = a.newInstance().inst;
//			inst.redefineClasses(new ClassDefinition(target.getClass(), target.toBytecode()));
//			System.err.println("end");
		}

	}

	public static void defineClass(ClassLoader classLoader, String className, byte[] classCode) {
		try {
			ClassLoader currentLoader = classLoader;
			Object[] params = new Object[] { className, classCode, new Integer(0), new Integer(classCode.length) };
			Class<?>[] types = new Class[] { String.class, byte[].class, int.class, int.class };

			Method defineMethod = ClassLoader.class.getDeclaredMethod("defineClass", types);
			defineMethod.setAccessible(true);
			System.out.println("Classloader.defineClass : "+defineMethod.toString());
			defineMethod.invoke(currentLoader, params);
		} catch (Throwable th) {
			th.printStackTrace();
		}
	}
}
