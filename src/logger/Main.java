package logger;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;



import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.CtNewConstructor;
import javassist.CtNewMethod;
import javassist.NotFoundException;
import logger.TestCases;

public class Main {

	public static void main(String[] argv) {

		// read DecisionTable
		List<List<String>> dt = logger.TableCreate.readDecisionTable(argv[1]);

		logger.TableCreate.outputDecisionTable(dt);

		Map<String, logger.TestCases.OneCase> tc = logger.TableCreate.createTestCases(dt);
		for (Entry<String, TestCases.OneCase> c : tc.entrySet()) {
			try {
//				classMaker(c.getValue());
				logger.Weave.weave();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

		//execute targetClass
		try {
			// dump OutputStream 
			PrintStream ps = System.out;
			System.setOut(new PrintStream(new FileOutputStream("/dev/null")));
			// run TestDriver
			Class<?> execClass = Class.forName(argv[0]);
			Method execMethod = execClass.getDeclaredMethod("main", new Class[] { String[].class });
			execMethod.setAccessible(true);
			execMethod.invoke(null, new Object[] { new String[0] });
			System.setOut(ps);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private static void classMaker(TestCases.OneCase oc) throws NotFoundException, CannotCompileException, IOException {
		ClassPool cp = ClassPool.getDefault();
		// Make Class
		// create newClass
		CtClass logger = cp.makeClass(oc.getTarget() + "_Logger");
		logger.setSuperclass(cp.get("logger.StateLogger"));
		logger.addConstructor(CtNewConstructor.make("public " + logger.getName() + "(){super();}", logger));
		// get changeClass
		CtClass targetClass = cp.get(oc.getTarget());

		// make Method & invoke
		/**/String mget = "putMethod(Thread.currentThread().getStackTrace()[1].getMethodName(),$args);";
		CtMethod addMI = CtNewMethod.make("protected void putMethod(String methodName, Object[] args){put(\"Method\", methodName);for (int i=0;i<args.length;++i) {put(\"arg\"+i,args[i]);}}",logger);
		logger.addMethod(addMI);
		
		Map<String, String> methodList = oc.getMethods();
		for (Entry<String, String> method : methodList.entrySet()) {
			System.err.println("Method [" + method.getKey() + "] :: " + oc.getCode(method.getKey()));
			CtMethod addM = CtNewMethod.make("public void " + method.getKey() + "{try{startLogging();"+mget + oc.getCode(method.getKey(), TargetList.getCtClass().getName()) + "stopLogging();}catch(Exception ex){ex.printStackTrace();}}", logger);
			logger.addMethod(addM);
			CtMethod m = targetClass.getDeclaredMethod(method.getValue());
			m.insertBefore("{try{" + logger.getName() + " loggingInstance = new " + logger.getName() + "();loggingInstance." + method.getValue() + "($$);}catch(Exception ex){ex.printStackTrace();}}");
		}

		// define changeClass & createClass
		logger.writeFile();
		defineClass(logger.getName(), logger.toBytecode());
		targetClass.writeFile();
		defineClass(targetClass.getName(), targetClass.toBytecode());

	}

	public static void defineClass(String className, byte[] classCode) {
		try {
			ClassLoader currentLoader = Thread.currentThread().getContextClassLoader();
			Object[] params = new Object[] { className, classCode, new Integer(0), new Integer(classCode.length) };
			Class<?>[] types = new Class[] { String.class, byte[].class, int.class, int.class };

			Method defineMethod = ClassLoader.class.getDeclaredMethod("defineClass", types);
			defineMethod.setAccessible(true);
			defineMethod.invoke(currentLoader, params);
		} catch (Throwable th) {
			th.printStackTrace();
		}
	}
}
