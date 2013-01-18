package bank;
public interface BankInterface {
    public int doOpen(String name, String passwd); /* ����J�� */
    public int doClose(String name, String passwd); /* ������ */
    public int doDeposit(String name, int amount); /* �a�� */
    public int doWithdraw(String name, int amount, String passwd); /* �����߂� */
    public int doBalance(String name, String passwd); /* �c���Ɖ� */
    public int changePassword(String name, String oldPassword, String newPasswd); /* �Ïؔԍ��̕ύX */
}

