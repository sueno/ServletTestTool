package logger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class TestCases extends HashMap<String, TestCases.OneCase> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1904364110382461655L;


	public void addList(Map<String,String> map) {
		String key = map.get("Class");
		if (containsKey(key)) {
			get(key).addList(map);
		} else {
			put(key, new OneCase(key, map));
		}
	}

	public OneCase getOneCase(String key) {
		return get(key);
	}


	static class OneCase {

		private String className;
		private Map<String, List<String>> methodMap;

		public OneCase(String className, Map<String, String> map) {
			this.className = className;
			methodMap = new HashMap<String, List<String>>();
			addList(map);
		}

		
		// getter

		/**
		 * get target Class Name
		 * @return
		 */
		public String getTarget() {
			return className;
		}

		/**
		 * get Target Class Method Name
		 * @return
		 */
		public Map<String,String> getMethods() {
			Map<String,String> methods = new HashMap<String,String>();
			for (Entry<String, List<String>> map : methodMap.entrySet()) {
				String[] str = map.getKey().replaceAll("\\(", " ").split(" ",0);
				methods.put(map.getKey(),str[0]);
			}
			return methods;
		}
		
		/**
		 * get Target Method arguments
		 * @param methodName
		 * @return
		 */
		public String getArgs(String methodName) {
			return "()";
		}
		
		/**
		 * get Target Method source code (list)
		 * @param methodName
		 * @return
		 */
		public List<String> getCodes(String methodName) {
			return methodMap.get(methodName);
		}

		/**
		 * get Target Method source code
		 * @param methodName
		 * @return
		 */
		public String getCode(String methodName) {
			StringBuilder sb = new StringBuilder();
			for (String code : methodMap.get(methodName)) {
				sb.append(code + ";");
			}
			return new String(sb);
		}
		/**
		 * get Target Method source code
		 * @param methodName
		 * @return
		 */
		public String getCode(String methodName, String callClass) {
			StringBuilder sb = new StringBuilder();
			for (String code : methodMap.get(methodName)) {
				sb.append("put(\""+code+"\","+callClass+"."+code + ");");
			}
			return new String(sb);
		}
		
		// setter

		public void addList(Map<String, String> map) {
			if (className.equals(map.get("Class"))) {
				addValue(map.get("Method"), map);
			}
		}

		public void addValue(String methodName, Map<String, String> map) {
			if (methodMap.containsKey(methodName)) {
				for (Entry<String, String> m : map.entrySet()) {
					if (m.getKey().startsWith("case_") && !methodMap.get(methodName).contains(m.getValue())) {
						methodMap.get(methodName).add(m.getValue());
					}
				}
			} else {
				methodMap.put(methodName, new ArrayList<String>());
				addValue(methodName, map);
			}
		}

	}
}
