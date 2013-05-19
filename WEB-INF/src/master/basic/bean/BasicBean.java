package master.basic.bean;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>基本設定Bean</p>
 */
public class BasicBean implements Serializable{
	private static final long serialVersionUID = -3014303879780894300L;

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
	private String kisyuYear;
	private String kisyuMonth;
	private String kisyuDate;
	private String kimatsuYear;
	private String kimatsuMonth;
	private String kimatsuDate;
	private String kessan;
	private String tax;
	private BigDecimal taxVal;
	private String calc;
	private String calcHasu;
	private String updDate;

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
	public String getKisyuYear() {
		return kisyuYear;
	}
	public void setKisyuYear(String kisyuYear) {
		this.kisyuYear = kisyuYear;
	}
	public String getKisyuMonth() {
		return kisyuMonth;
	}
	public void setKisyuMonth(String kisyuMonth) {
		this.kisyuMonth = kisyuMonth;
	}
	public String getKisyuDate() {
		return kisyuDate;
	}
	public void setKisyuDate(String kisyuDate) {
		this.kisyuDate = kisyuDate;
	}
	public String getKessan() {
		return kessan;
	}
	public void setKessan(String kessan) {
		this.kessan = kessan;
	}
	public String getTax() {
		return tax;
	}
	public void setTax(String tax) {
		this.tax = tax;
	}
	public BigDecimal getTaxVal() {
		return taxVal;
	}
	public void setTaxVal(BigDecimal taxVal) {
		this.taxVal = taxVal;
	}
	public String getCalc() {
		return calc;
	}
	public void setCalc(String calc) {
		this.calc = calc;
	}
	public String getCalcHasu() {
		return calcHasu;
	}
	public void setCalcHasu(String calcHasu) {
		this.calcHasu = calcHasu;
	}
	public String getKimatsuYear() {
		return kimatsuYear;
	}
	public void setKimatsuYear(String kimatsuYear) {
		this.kimatsuYear = kimatsuYear;
	}
	public String getKimatsuMonth() {
		return kimatsuMonth;
	}
	public void setKimatsuMonth(String kimatsuMonth) {
		this.kimatsuMonth = kimatsuMonth;
	}
	public String getKimatsuDate() {
		return kimatsuDate;
	}
	public void setKimatsuDate(String kimatsuDate) {
		this.kimatsuDate = kimatsuDate;
	}
	public String getUpdDate() {
		return updDate;
	}
	public void setUpdDate(String updDate) {
		this.updDate = updDate;
	}
}
