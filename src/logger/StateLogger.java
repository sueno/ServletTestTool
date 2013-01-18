package logger;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Observable;

public class StateLogger extends Observable {

	private Map<String, Object> map;
	private boolean loggingStatus;
	private Object target = null;

	public StateLogger() {
		super.addObserver(Log.getInstance());
		map = new LinkedHashMap<String, Object>();
		loggingStatus = false;
	}

	public void startLogging() {
		if (loggingStatus) {
			stopLogging();
		}
		loggingStatus = true;
		map = new LinkedHashMap<String, Object>();
	}
	public void startLogging(Object t) {
		target = t;
		startLogging();
	}
	public void stopLogging() {
		setChanged();
		super.notifyObservers(map);
		clearChanged();
		loggingStatus = false;
	}
	
	public void put(String key, Object value) {
		map.put(key, value);
	}
	
	public void put(String key, boolean value) {
		map.put(key, value);
	}
	
	public Object getObject() {
		return target;
	}
	
	public static StateLogger getInstance () {
		return new StateLogger();
	}

}
