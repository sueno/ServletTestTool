package refClass;

import java.lang.reflect.Field;

public class RefObject {

	public static Object getField(Object reflectObj, String fieldName) {
		try {
			Class<?> reflectClass = reflectObj.getClass();
			Field reflectField = reflectClass.getDeclaredField(fieldName);
			reflectField.setAccessible(true);
			return reflectField.get(reflectObj);
		} catch (Exception ex) {
			return null;
		}
	}

}
