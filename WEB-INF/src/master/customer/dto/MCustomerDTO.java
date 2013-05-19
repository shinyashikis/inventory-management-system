package master.customer.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;

import fw.common.db.dto.CommonDTO;

/**
 * <p>得意先マスタDTO</p>
 */
public class MCustomerDTO implements CommonDTO {
	private static final long serialVersionUID = -739674377841323476L;

	public static final String CODE = "code";
	public static final String NAME = "name";
	public static final String KANE = "kana";
	public static final String CLACC_CODE = "classCode";
	public static final String ADDR1 = "addr1";
	public static final String ADDR2 = "addr2";
	public static final String POSTCODE1 = "postCode1";
	public static final String POSTCODE2 = "postCode2";
	public static final String TEL = "tel";
	public static final String FAX = "fax";
	public static final String CUSTOMER_STAFF_SEI = "customerStaffSei";
	public static final String CUSTOMER_STAFF_NAME= "customerStaffName";
	public static final String KEISYO = "keisyo";
	public static final String BUSYO = "busyo";
	public static final String DEAL_DIVISION = "dealDivision";
	public static final String KESSAI_DIVISION = "kessaiDivision";
	public static final String SALE_PRICE_DIVISION = "salePriceDivision";
	public static final String KISYU_BALANCE = "kisyuBalance";
	public static final String SHIMEBI_MONTHLY = "shimebiMonthly";
	public static final String SHIMEBI_KESSAI = "shimebiKessai";
	public static final String KESSAI_MONTHLY = "kessaiMonthly";
	public static final String STAFF_CODE = "staffCode";
	public static final String UPDDATE = "updDate";

	private String code;
	private String name;
	private String kana;
	private Integer classCode;
	private String addr1;
	private String addr2;
	private String postCode1;
	private String postCode2;
	private String tel;
	private String fax;
	private String customerStaffSei;
	private String customerStaffName;
	private Integer keisyo;
	private String busyo;
	private Integer dealDivision;
	private Integer kessaiDivision;
	private Integer salePriceDivision;
	private BigDecimal kisyuBalance;
	private Integer shimebiMonthly;
	private Integer shimebiKessai;
	private Integer kessaiMonthly;
	private Integer staffCode;
	private Timestamp updDate;

	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getKana() {
		return kana;
	}
	public void setKana(String kana) {
		this.kana = kana;
	}
	public Integer getClassCode() {
		return classCode;
	}
	public void setClassCode(Integer classCode) {
		this.classCode = classCode;
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
	public String getCustomerStaffSei() {
		return customerStaffSei;
	}
	public void setCustomerStaffSei(String customerStaffSei) {
		this.customerStaffSei = customerStaffSei;
	}
	public String getCustomerStaffName() {
		return customerStaffName;
	}
	public void setCustomerStaffName(String customerStaffName) {
		this.customerStaffName = customerStaffName;
	}
	public Integer getKeisyo() {
		return keisyo;
	}
	public void setKeisyo(Integer keisyo) {
		this.keisyo = keisyo;
	}
	public String getBusyo() {
		return busyo;
	}
	public void setBusyo(String busyo) {
		this.busyo = busyo;
	}
	public Integer getDealDivision() {
		return dealDivision;
	}
	public void setDealDivision(Integer dealDivision) {
		this.dealDivision = dealDivision;
	}
	public Integer getKessaiDivision() {
		return kessaiDivision;
	}
	public void setKessaiDivision(Integer kessaiDivision) {
		this.kessaiDivision = kessaiDivision;
	}
	public Integer getSalePriceDivision() {
		return salePriceDivision;
	}
	public void setSalePriceDivision(Integer salePriceDivision) {
		this.salePriceDivision = salePriceDivision;
	}
	public BigDecimal getKisyuBalance() {
		return kisyuBalance;
	}
	public void setKisyuBalance(BigDecimal kisyuBalance) {
		this.kisyuBalance = kisyuBalance;
	}
	public Integer getShimebiMonthly() {
		return shimebiMonthly;
	}
	public void setShimebiMonthly(Integer shimebiMonthly) {
		this.shimebiMonthly = shimebiMonthly;
	}
	public Integer getShimebiKessai() {
		return shimebiKessai;
	}
	public void setShimebiKessai(Integer shimebiKessai) {
		this.shimebiKessai = shimebiKessai;
	}
	public Integer getKessaiMonthly() {
		return kessaiMonthly;
	}
	public void setKessaiMonthly(Integer kessaiMonthly) {
		this.kessaiMonthly = kessaiMonthly;
	}
	public Integer getStaffCode() {
		return staffCode;
	}
	public void setStaffCode(Integer staffCode) {
		this.staffCode = staffCode;
	}
	public Timestamp getUpdDate() {
		return updDate;
	}
	public void setUpdDate(Timestamp updDate) {
		this.updDate = updDate;
	}

}
