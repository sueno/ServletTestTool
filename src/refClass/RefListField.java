package refClass;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class RefListField {

	private Object refObject;
	private Field refField;
	private Field refnext;
	private ArrayList blist;
	private ArrayList alist;

	public RefListField(Object refobject, String fieldname, String nextname) {
		try {
			Class refClass = refobject.getClass();
			refObject = refobject;
			refField = refClass.getDeclaredField(fieldname);
			refField.setAccessible(true);
			refnext = refClass.getDeclaredField(nextname);
			refnext.setAccessible(true);
//			System.err.println("Proxy called ListClassref ( ClassName : " + refClass.getName() + ", FieldName : " + refField.getName() + " )" + refField.getType().getName());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public boolean isNull() {
		if (refObject==null || refField==null || refnext==null) {
			return true;
		} else {
			return false;
		}
	}
	
	public void beforeField() {
		blist = getList(refObject, new ArrayList());
	}

//	public int afterField() {
//		alist = getList(refObject, new ArrayList());
//		if (blist.size() != alist.size()) {
//			int i;
//			for (i = 0; i < alist.size()&&i<blist.size(); ++i) {
//				
//				if (blist.get(i).equals(blist.get(i))) {
//				} else {
//					return i;
//				}
//			}
//			return i;
//		}
//
//		return -1;
//	}
	public int afterField(String fieldname) {
		try {
			alist = getList(refObject, new ArrayList());
			if (blist.size() != alist.size()) {
				int i;
				for (i = 0; i < alist.size() && i < blist.size(); ++i) {
					RefField arf = new RefField(alist.get(i), fieldname);
					RefField brf = new RefField(blist.get(i), fieldname);
					if (arf.equalsField(brf.getField().toString())) {
					} else {
						return i;
					}
				}
				return i;
			}

			return -1;
		} catch (Exception ex) {
			return -1;
		}
	}

//	public int afterFieldXM() {
//		alist = getList(refObject, new ArrayList());
//		if (blist.size() != alist.size()) {
//			int i;
//			for (i = 0; i < alist.size()&&i<blist.size(); ++i) {
//				
//				if (blist.get(i).equals(blist.get(i))) {
//				} else {
//					return i;
//				}
//			}
//			return i;
//		}
//
//		return -1;
//	}
	public int afterFieldXM(String fieldname) {
		try {
		alist = getList(refObject, new ArrayList());
		int i;
		for (i = 0; i < alist.size() && i < blist.size(); ++i) {
			RefField arf = new RefField(alist.get(i), fieldname);
			RefField brf = new RefField(blist.get(i), fieldname);
			if (arf.equalsField(brf.getField().toString())) {
			} else {
				if (i == 0) {
					return -1;
				} else if (alist.size() <= i && blist.size() <= i) {
					return 1;
				} else {
					return 0;
				}
			}
		}
		return 1;
		} catch (Exception ex){
			return 0;
		}

	}

	public boolean boolpositionField(String fieldname, String name) {
		alist = getList(refObject, new ArrayList());
		int i;
		for (i = 0; i < alist.size() && i < blist.size(); ++i) {
			RefField arf = new RefField(alist.get(i), fieldname);
			if (arf.equalsField(name)) {
				return true;
			} else {
			}
		}
		return false;
	}

	public boolean changeField(String fieldname) {
		alist = getList(refObject, new ArrayList());
		if (blist.size() != alist.size()) {
			return true;
		} else {
			return false;
		}
	}

	public boolean positionFirst(String fieldname) {
		alist = getList(refObject, new ArrayList());
		int i;
		for (i = 0; i < alist.size() && i < blist.size(); ++i) {
			try {
			RefField arf = new RefField(alist.get(i), fieldname);
			RefField brf = new RefField(blist.get(i), fieldname);
			if (arf.equalsField(brf.getField().toString())) {
			} else {
				if (i == 0) {
					return true;
				} else {
					return false;
				}
			}
			} catch (Exception ex) {
				
			}
		}
		if (i == 0) {
			return true;
		} else {
			return false;
		}
	}

	public boolean positionLast(String fieldname) {
		alist = getList(refObject, new ArrayList());
		int i;
		for (i = 0; i < alist.size() && i < blist.size(); ++i) {
			try {
			RefField arf = new RefField(alist.get(i), fieldname);
			RefField brf = new RefField(blist.get(i), fieldname);
			if (arf.equalsField(brf.getField().toString())) {
			} else {
				if (blist.size() == alist.size()) {
					return true;
				} else {
					return false;
				}
			}
			} catch (Exception ex) {
				
			}
		}
		return true;
	}

	public int findField(String fieldname, String name) {
		ArrayList list = getList(refObject, new ArrayList());

		if (list != null) {
			int i;
			for (i = 0; i < list.size(); ++i) {
				RefField rf = new RefField(list.get(i), fieldname);
				if (rf.equalsField(name)) {
					return i;
				}
			}
			return -1;
		} else {
			return -2;
		}
	}

	public boolean equalsField(String fieldname, String name, String equalfield, String equalstr) {
		ArrayList list = getList(refObject, new ArrayList());

		if (list != null) {
			int i;
			for (i = 0; i < list.size(); ++i) {
				RefField rf = new RefField(list.get(i), fieldname);
				if (rf.equalsField(name)) {
					RefField erf = new RefField(list.get(i), equalfield);
					if (erf.equalsField(equalstr)) {
						return true;
					}
				}
			}
			return false;
		} else {
			return false;
		}
	}

	public Object getField(String fieldname, String name) {
		ArrayList list = getList(refObject, new ArrayList());

		if (list != null) {
			int i;
			for (i = 0; i < list.size(); ++i) {
				RefField rf = new RefField(list.get(i), fieldname);
				if (rf.equalsField(name)) {
					return list.get(i);
				}
			}
			return null;
		} else {
			return null;
		}
	}

	public int sizeField() {
		return getList(refObject, new ArrayList()).size();
	}

	public int beforesizeField() {
		return blist.size();
	}

	public boolean containsField(int num) {
		if (getList(refObject, new ArrayList()).size() <= num) {
			return false;
		} else {
			return true;
		}
	}

	public boolean containsField() {
		return containsField(0);
	}

	public boolean beforecontainsField(int num) {
		if (blist.size() <= num) {
			return false;
		} else {
			return true;
		}
	}

	public boolean beforecontainsField() {
		return beforecontainsField(0);
	}

	public Object getField(int num) {
		ArrayList list = getList(refObject, new ArrayList());
		return list.get(num);
	}

	public ArrayList getFields() {
		return getList(refObject, new ArrayList());
	}

	private ArrayList getList(Object obj, ArrayList al) {
		try {
			if (refnext.get(obj) != null) {
				if (refField.get(obj) != null) {
					al.add(refField.get(obj));
				}
				return getList(refnext.get(obj), al);
			} else {
				if (refField.get(obj) != null) {
					al.add(refField.get(obj));
				}
				return al;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

}
