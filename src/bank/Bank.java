package bank;

public class Bank implements BankInterface {
	private Account[] customer = new Account[20];
	private int count;
	private int i, j;
	
	//
	Bank_Logger bLogger = new Bank_Logger();
	

	public Bank() {
		System.err.println("BankServlet init.");
		for (i = 0; i < 20; i++) {
			customer[i] = new Account("", "");
		}
		count = 0;
	}

	public int doOpen(String name) {
		return doOpen(name, "1111");
	}

	public int doOpen(String name, String passwd) {
		bLogger.startLogging(this);
		bLogger.__before();
		bLogger.after_doOpen(name, passwd);
		bLogger.doOpen(name, passwd);
		bLogger.__after();
		bLogger.stopLogging();
		
		if (count > 19) {
			return -1; //
		}
		for (i = 0; i < count; i++) {
			if (customer[i].getName().compareTo(name) == 0)
				return -2;
		}//
		try {
			int pass = Integer.parseInt(passwd);
		} catch (NumberFormatException e) {
			return -4;
		} //
		if (passwd.length() != 4) {
			return -4;
		} //
		if (Integer.parseInt(passwd) < 0) {
			return -4;
		}

		for (i = 0; i < count; i++) {
			if (name.compareTo(customer[i].getName()) < 0) {
				for (j = count; j > i; j--) {
					customer[j] = customer[j - 1];
				}
				customer[i] = new Account(name, passwd); //
				count++; //
				return 0;
			}
		}

		customer[i] = new Account(name, passwd); //
		count++; //
		return 0;
	} /**/

	public int doClose(String name) {
		return doClose(name, "1111");
	}

	public int doClose(String name, String passwd) {
		bLogger.startLogging(this);
		bLogger.__before();
//		bLogger.after_doClose(name, passwd);
		bLogger.doClose(name, passwd);
		bLogger.__after();
		bLogger.stopLogging();
		try {
			int pass = Integer.parseInt(passwd);
		} catch (NumberFormatException e) {
			return -4;
		} //
		if (passwd.length() != 4) {
			return -4;
		} //
		for (i = 0; i < count; i++) {
			if (customer[i].getName().compareTo(name) == 0) {
				if (customer[i].doBalance(passwd) == -4) {
					return -4;
				}
				if (customer[i].doBalance(passwd) != 0) {
					return -1;
				}

				for (i = i + 1; i < count; i++) {
					customer[i - 1] = customer[i];
				} //
				customer[i - 1] = new Account("", ""); //
				count--; //
				return 0;
			}
		} //

		return -2;

	} /**/

	public int doDeposit(String name, int amount) {
		bLogger.startLogging(this);
		bLogger.__before();
//		bLogger.after_doDeposit(name, amount);
		bLogger.doDeposit(name, amount);
		bLogger.__after();
		bLogger.stopLogging();
		for (i = 0; i < count; i++) {
			if (customer[i].getName().compareTo(name) == 0) {
				return customer[i].doDeposit(amount);
			} //
		} //
		return -2;

	} /**/

	public int doWithdraw(String name, int amount) {
		return doWithdraw(name, amount, "1111");
	}

	public int doWithdraw(String name, int amount, String passwd) {
		bLogger.startLogging(this);
		bLogger.__before();
//		bLogger.after_doWithdraw(name, passwd);
		bLogger.doWithdraw(name, amount, passwd);
		bLogger.__after();
		bLogger.stopLogging();
		try {
			int pass = Integer.parseInt(passwd);
		} catch (NumberFormatException e) {
			return -4;
		} //
		if (passwd.length() != 4) {
			return -4;
		} //
		for (i = 0; i < count; i++) {
			if (customer[i].getName().compareTo(name) == 0) {
				return customer[i].doWithdraw(amount, passwd);
			} //
		} //
		return -2;

	} /**/

	public int doBalance(String name) {
		return doBalance(name, "1111");
	}

	public int doBalance(String name, String passwd) {
		try {
			int pass = Integer.parseInt(passwd);
		} catch (NumberFormatException e) {
			return -4;
		} //
		if (passwd.length() != 4) {
			return -4;
		} //

		for (i = 0; i < count; i++) {
			if (customer[i].getName().compareTo(name) == 0) {
				return customer[i].doBalance(passwd);
			} //
		} //
		return -2;
	} /**/

	public int changePassword(String name, String oldPassword, String newPasswd) {
		try {
			int pass = Integer.parseInt(oldPassword);
		} catch (NumberFormatException e) {
			return -4;
		} //
		if (oldPassword.length() != 4) {
			return -4;
		} //
		if (Integer.parseInt(oldPassword) < 0) {
			return -4;
		}

		for (i = 0; i < count; i++) {
			if (customer[i].getName().compareTo(name) == 0) {
				return customer[i].changePassword(oldPassword, newPasswd);
			} //
		} //
		return -2;

	} /**/

	public void print() {

	}

}
