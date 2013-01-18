package bank;


/* Student ID:  206025          */
/* class     :  1               */
/* Neme      :  Satoshi Ueno    */
// Account 5-1


public class Account {
    private String name; /* ����� */
    private int balance; /* ����̎c�� */
    private String password; /* �p�X���[�h */

    public Account(String name, String passwd) {
	this.name = name;
	this.password = passwd;
	balance = 0;
    } /* �R���X�g���N�^ */

    public int doDeposit(int amount/* �a���z */) {
	if (amount<=0){
	    return -3;
	} //amount��0�ȉ��̊z��a�����悤�Ƃ����Ƃ�-3��Ԃ�
	balance = balance + amount;
	return 0; //����I��
    } /* �a�� */

    public int doWithdraw(int amount/* �o���z */, String passwd/* �p�X���[�h */) {
	if (passwd.equals(password)){
	    if (amount<=0){
		return -3;
	    } //amount��0�ȉ��̊z����o�����Ƃ����Ƃ�-3��Ԃ�
	    if (amount>balance){
		return -1;
	    } //amount��balance���傫���Ƃ�-1��Ԃ�
	    balance = balance - amount;
	    return 0; //����I��
	}
	else {
	    return -4;
	} //�p�X���[�h���Ⴄ�Ƃ�-4��Ԃ�
    } /* �����߂�*/

    public int doBalance(String passwd/* �p�X���[�h */) {
	if (passwd.equals(password)){
	    return balance; //balance�̒l��Ԃ�
	}
	else {
	    return -4;
	} //�p�X���[�h���Ⴄ�Ƃ�-4��Ԃ�
    } /* �c���Ɖ� */

    public String getName() {
	return this.name; //name��Ԃ�
    } /* �������Ԃ� */

    public int changePassword(String oldPassword, String newPasswd) {
	try{
	    int pass = Integer.parseInt(oldPassword);
	}catch (NumberFormatException e){
	    return -4;
	} //�p�X���[�h�������łȂ��ꍇ-4
	if (oldPassword.length()!=4){
	    return -4;
	} //�p�X���[�h���S���łȂ��ꍇ-4
	try{
	    int pass = Integer.parseInt(newPasswd);
	}catch (NumberFormatException e){
	    return -4;
	} //�p�X���[�h�������łȂ��ꍇ-4
	if (newPasswd.length()!=4){
	    return -4;
	} //�p�X���[�h���S���łȂ��ꍇ-4
	if (Integer.parseInt(newPasswd)<0) {
		return -4;
	}
	if (oldPassword.equals(password)){
	    password = newPasswd;
	    return 0;
	}
	else {
	    return -4;
	} //�p�X���[�h���Ⴄ�Ƃ�-4��Ԃ�
	
    } /* �Ïؔԍ��̕ύX �i�ǉ��j*/ 
}
