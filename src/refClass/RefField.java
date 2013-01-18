package refClass;

import java.lang.reflect.Field;

public class RefField {
	private Class refClass;
	private Object refObject;
	private Field refField;
	private Field beforefield;

	public RefField(Object refobject, String fieldname) {
		try {
			refClass = refobject.getClass();
			refObject = refobject;
			refField = refClass.getDeclaredField(fieldname);
			refField.setAccessible(true);
//			System.out.println("Proxy called RefClass (ClassName : " + refClass.getName() + ", FieldName : " + refField.getName());

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void beforeField() {
		beforefield = refField;
	}

	public int afterField() {
		if (beforefield != refField) {
			return -1;
		}

		return 0;
	}

	public boolean equalsField(String str) {
		try {
			if (str.equals(refField.get(refObject))) {
				return true;
			} else {
				return false;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	public Object getField() {
		try {
			return refField.get(refObject);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
}
