package bank;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;


import logger.*;
import logger.annotation.*;

@TargetClass("bank.Bank")
public class Bank_Logger extends StateLogger {

	private Bank bank;
	private Map<String, String> customerMap;
	private Map<String, Integer> amountMap; 

	public void __before() {
		try {
			bank = (Bank) getObject();
			System.err.println(bank);
			Class<?> c = bank.getClass();
			Field customerField = c.getDeclaredField("customer");
			customerField.setAccessible(true);
			Object customerObj = customerField.get(bank);
			if (customerObj.getClass().isArray()) {
				customerMap = new HashMap<String, String>();
				amountMap = new HashMap<String,Integer>();
				Class<?> cl = Account.class;
				Field passField = cl.getDeclaredField("password");
				passField.setAccessible(true);
				Field amoField = cl.getDeclaredField("balance");
				amoField.setAccessible(true);
				for (int i = 0; i < Array.getLength(customerObj); ++i) {
					Account acc = (Account) Array.get(customerObj, i);
					if (acc != null && acc.getName() != "") {
						customerMap.put(acc.getName(), (String) passField.get(acc));
						amountMap.put(acc.getName(), (Integer)amoField.getInt(acc));
					}
				}
			}
//			for (Map.Entry<String, String> acc : customer.entrySet()) {
//				System.err.print("["+acc.getKey()+":"+acc.getValue()+":"+amount.get(acc.getKey())+"] ");
//			}System.err.println(" length : "+customer.size());
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}
	public void __after() {
	}

	public int changePassword(String name, String oldPassword, String newPasswd) {
		put("Method",Thread.currentThread().getStackTrace()[1].getMethodName());
		put("name",name);
		put("oldPassword",oldPassword);
		put("newPasswd",newPasswd);
		checkPass(name, newPasswd);
		checkAccount(name, oldPassword);
		return 0;
	}

	public int doBalance(String name, String passwd) {
		put("Method",Thread.currentThread().getStackTrace()[1].getMethodName());
		put("name",name);
		put("passwd",passwd);
		checkAccount(name, passwd);
		return 0;
	}

	public int doClose(String name, String passwd) {
		put("Method",Thread.currentThread().getStackTrace()[1].getMethodName());
		put("name",name);
		put("passwd",passwd);
		if (checkAccount(name, passwd)) {
			if (amountMap.get(name)!=0) {
				put("預金あり", "T");
			} else {
				put("預金あり", "F");
			}
		}
		
		return 0;
	}

	public int doDeposit(String name, int amount) {
		put("Method",Thread.currentThread().getStackTrace()[1].getMethodName());
		put("name",name);
		put("amount",amount);
		checkAccount(name, "");
		checkAmount(amount);
		return 0;
	}

	public void doOpen (String name, String passwd) {
		put("Method",Thread.currentThread().getStackTrace()[1].getMethodName());
		put("name",name);
		put("passwd",passwd);
		if (customerMap.size()==20) {
			put("口座数20", "T");
		} else {
			put("口座数20", "F");
		}
	}
	
	public int after_doOpen(String name, String passwd) {
		checkPass(name, passwd);
		checkAccount(name, passwd);
		return 0;
	}

	public int doWithdraw(String name, int amount, String passwd) {
		put("Method",Thread.currentThread().getStackTrace()[1].getMethodName());
		put("name",name);
		put("passwd",passwd);
		put("amount",amount);
		if (checkAccount(name, passwd)) {
			if (amountMap.get(name)>=amount) {
				put("預金あり", "T");
			} else {
				put("預金あり", "F");
			}
		}
		checkAmount(amount);
		return 0;
	}

	/**
	 * パスワードの不正確認
	 * 
	 * @param name
	 * @param passwd
	 * @return
	 */
	private void checkPass(String name, String passwd) {
		try {
			if (Integer.parseInt(passwd) >= 0) {
				put("パスワード自然数", "T");
			} else {
				put("パスワード自然数", "F");
			}
			put("パスワード数字", "T");
		} catch (NumberFormatException e) {
			put("パスワード数字", "F");
		}
		if (passwd.length() == 4) {
			put("パスワード4桁", "T");
		} else {
			put("パスワード4桁", "F");
		}
	}

	private boolean checkAccount(String name, String passwd) {
		if (customerMap.containsKey(name)) {
			put("該当口座あり", "T");
			if (customerMap.get(name).equals(passwd)) {
				put("パスワード一致", "T");
			} else {
				put("パスワード一致", "F");
			}
			return true;
		} else {
			put("該当口座あり", "F");
			return false;
		}
	}
	
	private void checkAmount (int amount) {
		if (amount>0) {
			put("金額1以上", "T");
		} else {
			put("金額1以上", "F");
		}
	}
}