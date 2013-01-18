package logger;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import au.com.bytecode.opencsv.CSVReader;


public class TableCreate {

	/**
	 * Read DecisionTable to csv File 
	 * @param fileName
	 * @return decisionTable list
	 */
	public static List<List<String>> readDecisionTable(String fileName) {
		try {
			List<List<String>> table = new ArrayList<List<String>>();
			
			CSVReader reader = new CSVReader(new FileReader(fileName));
			String nextLine[];
			if ((nextLine = reader.readNext())!=null) {
				for (String str : nextLine) {
					List<String> list = new ArrayList<String>();
					list.add(str);
					table.add(list);
				}
			}
			
			while ((nextLine = reader.readNext())!=null) {
				for (int i=0;i<nextLine.length;++i) {
					table.get(i).add(nextLine[i]);
				}
			}
			
			//remove comment
			for (int i=0;i<table.size();++i) {
				if (table.get(i).get(0).startsWith("#")) {
					table.remove(i);
					--i;
				}
			}
			
			return table;
			
		} catch (Throwable th) {
			th.printStackTrace();
			return null;
		}
	}

	public static void outputDecisionTable(List<List<String>> table) {
		for (int i = 0; i < table.get(0).size(); ++i) {
			for (List<String> lineList : table) {
				System.out.print(String.format("%20s |",lineList.get(i)));
			}
			System.out.println("");
		}
		try {
		Thread.sleep(400);
		} catch (Exception ex) {
		}
		System.out.println("");
	}

	public static TestCases createTestCases (List<List<String>> table) {
		TestCases testCase = new TestCases();
		for (int i=1;i<table.size();++i) {
			testCase.addList(createMap(table.get(0),table.get(i)));
		}
		return testCase;
	}
	private static Map<String,String> createMap(List<String> keys, List<String> value) {
		Map<String,String> map = new HashMap<String, String>();
		map.put("Class",value.get(keys.indexOf("Class")));
		map.put("Method", value.get(keys.indexOf("Method")));
		for (int i=0;i<value.size();++i) {
			String s = value.get(i);
			if (s.equals("T")||s.equals("F")) {
				map.put("case_"+keys.get(i), keys.get(i));
			}
		}
		return map;
	}
	
	
}
