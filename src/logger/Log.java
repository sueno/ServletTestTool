package logger;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Observable;
import java.util.Observer;

public class Log implements Observer {

	private static Log log = new Log();
	private static List<Map<String, Object>> list;
	private static Map<String,Object> logMap;

	private Log() {
		list = new ArrayList<Map<String, Object>>();
		logMap = new LinkedHashMap<String, Object>();
	}

	@SuppressWarnings("unchecked")
	public void update(Observable o, Object arg) {
		try {
			Map<String, Object> map = (Map<String, Object>) arg;
			System.err.println("*********Map**********");
			for (Entry<String, Object> m : map.entrySet()) {
				System.err.println("* "+m.getKey()+" : "+m.getValue());
			}
			System.err.println("**********************\n");
			list.add(map);
			int n = logMap.size();
			logMap.put("log"+n, map);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static Log getInstance() {
		return log;
	}

	public static List<Map<String, Object>> getList() {
		return list;
	}
	
	public static Map<String,Object> getMap() {
		return logMap;
	}
}
