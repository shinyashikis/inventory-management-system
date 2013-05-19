package master.basic.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;

import fw.common.db.dto.CommonDTO;

/**
 * <p>基本設定マスタDTO</p>
 */
public class MBasicDTO implements CommonDTO {
	private static final long serialVersionUID = 3201094223119764032L;

	public static final String COLUMN_NAME = "name";
	public static final String COLUMN_POST_CODE_1 = "postCode1";
	public static final String COLUMN_POST_CODE_2 = "postCode2";
	public static final String COLUMN_ADDR_1 = "addr1";
	public static final String COLUMN_ADDR_2 = "addr2";
	public static final String COLUMN_TEL = "tel";
	public static final String COLUMN_FAX = "fax";
	public static final String COLUMN_BANK = "bank";
	public static final String COLUMN_BRANCH = "branch";
	public static final String COLUMN_ACCOUNT_NO = "accountNo";
	public static final String COLUMN_ACCOUNT_NAME = "accountName";
	public static final String COLUMN_KISYU_YEAR = "kisyuYear";
	public static final String COLUMN_KISYU_MONTH = "kisyuMonth";
	public static final String COLUMN_KISYU_DATE = "kisyuDate";
	public static final String COLUMN_KIMATSU_YEAR = "kimatsuYear";
	public static final String COLUMN_KIMATSU_MONTH = "kimatsuMonth";
	public static final String COLUMN_KIMATSU_DATE = "kimatsuDate";
	public static final String COLUMN_KESSAN = "kessan";
	public static final String COLUMN_TAX = "tax";
	public static final String COLUMN_TAX_VAL = "taxVal";
	public static final String COLUMN_CALC = "calc";
	public static final String COLUMN_CALC_HASU = "calcHasu";
	public static final String COLUMN_UPD_DATE = "updDate";

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
	private Integer kisyuYear;
	private Integer kisyuMonth;
	private Integer kisyuDate;
	private Integer kimatsuYear;
	private Integer kimatsuMonth;
	private Integer kimatsuDate;
	private Integer kessan;
	private Integer tax;
	private BigDecimal taxVal;
	private Integer calc;
	private Integer calcHasu;
	private Timestamp updDate;

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
	public Integer getKisyuYear() {
		return kisyuYear;
	}
	public void setKisyuYear(Integer kisyuYear) {
		this.kisyuYear = kisyuYear;
	}
	public Integer getKisyuMonth() {
		return kisyuMonth;
	}
	public void setKisyuMonth(Integer kisyuMonth) {
		this.kisyuMonth = kisyuMonth;
	}
	public Integer getKisyuDate() {
		return kisyuDate;
	}
	public void setKisyuDate(Integer kisyuDate) {
		this.kisyuDate = kisyuDate;
	}
	public Integer getKessan() {
		return kessan;
	}
	public void setKessan(Integer kessan) {
		this.kessan = kessan;
	}
	public Integer getTax() {
		return tax;
	}
	public void setTax(Integer tax) {
		this.tax = tax;
	}
	public BigDecimal getTaxVal() {
		return taxVal;
	}
	public void setTaxVal(BigDecimal taxVal) {
		this.taxVal = taxVal;
	}
	public Integer getCalc() {
		return calc;
	}
	public void setCalc(Integer calc) {
		this.calc = calc;
	}
	public Integer getCalcHasu() {
		return calcHasu;
	}
	public void setCalcHasu(Integer calcHasu) {
		this.calcHasu = calcHasu;
	}
	public Integer getKimatsuYear() {
		return kimatsuYear;
	}
	public void setKimatsuYear(Integer kimatsuYear) {
		this.kimatsuYear = kimatsuYear;
	}
	public Integer getKimatsuMonth() {
		return kimatsuMonth;
	}
	public void setKimatsuMonth(Integer kimatsuMonth) {
		this.kimatsuMonth = kimatsuMonth;
	}
	public Integer getKimatsuDate() {
		return kimatsuDate;
	}
	public void setKimatsuDate(Integer kimatsuDate) {
		this.kimatsuDate = kimatsuDate;
	}
	public Timestamp getUpdDate() {
		return updDate;
	}
	public void setUpdDate(Timestamp updDate) {
		this.updDate = updDate;
	}

}
