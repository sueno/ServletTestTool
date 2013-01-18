package com.hoge.hoge;

import java.util.LinkedHashMap;
import java.util.Map;

public class Log {

	private static Log log = new Log();
	private Map<String,Object> logMap;
	private Map<String,Object> map;
	
	private Log () {
		logMap = new LinkedHashMap<String, Object>();
		map = new LinkedHashMap<String, Object>();
	}
	
	public void startLog () {
		map = new LinkedHashMap<String, Object>();
	}
	public void stopLog () {
		int i = logMap.size();
		logMap.put("log"+i, map);
	}
	
	public void put(String key, Object value) {
		map.put(key, value);
	}
	
	public Map<String,Object> getMap () {
		return logMap;
	}
	
	public static Log getInstanse() {
		return log;
	}
	
}
