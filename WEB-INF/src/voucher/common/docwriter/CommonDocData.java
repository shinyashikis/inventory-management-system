package voucher.common.docwriter;

import fw.common.docwriter.DocData;

/**
 * <p>伝票文書ファイル共通データクラス</p>
 */
public class CommonDocData implements DocData {
	private static final long serialVersionUID = -4994743685281388446L;

	private String name;
	private String postCode1;
	private String postCode2;
	private String addr1;
	private String addr2;
	private String tel;
	private String fax;
	private String bank;
	private String branch;
	private String accountNo;
	private String accountName;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPostCode1() {
		return postCode1;
	}
	public void setPostCode1(String postCode1) {
		this.postCode1 = postCode1;
	}
	public String getPostCode2() {
		return postCode2;
	}
	public void setPostCode2(String postCode2) {
		this.postCode2 = postCode2;
	}
	public String getAddr1() {
		return addr1;
	}
	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}
	public String getAddr2() {
		return addr2;
	}
	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
}
