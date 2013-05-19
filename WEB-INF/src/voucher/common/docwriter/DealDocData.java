package voucher.common.docwriter;

import fw.common.docwriter.DocData;

/**
 * <p>取引先文書ファイルデータ</p>
 */
public class DealDocData implements DocData {

	private static final long serialVersionUID = 5930423322049991213L;

	private String dealKind;
	private String dealName;
	private String dealCode;
	private String dealClassCode;
	private String dealClassName;
	private String dealStaff;
	private String dealStaffKeisyo;
	private String dealBusyo;
	private String tel;
	private String postCode;
	private String addr1;
	private String addr2;
	private String shimebi;
	private String kessai;
	private String dealDivision;
	private String dealDivisionCode;
	private String priceDivision;
	private String staff;
	private String staffCode;
	private String busyo;

	public String getDealKind() {
		return dealKind;
	}
	public void setDealKind(String dealKind) {
		this.dealKind = dealKind;
	}
	public String getDealName() {
		return dealName;
	}
	public void setDealName(String dealName) {
		this.dealName = dealName;
	}
	public String getDealCode() {
		return dealCode;
	}
	public void setDealCode(String dealCode) {
		this.dealCode = dealCode;
	}
	public String getDealClassCode() {
		return dealClassCode;
	}
	public void setDealClassCode(String dealClassCode) {
		this.dealClassCode = dealClassCode;
	}
	public String getDealClassName() {
		return dealClassName;
	}
	public void setDealClassName(String dealClassName) {
		this.dealClassName = dealClassName;
	}
	public String getDealStaff() {
		return dealStaff;
	}
	public void setDealStaff(String dealStaff) {
		this.dealStaff = dealStaff;
	}
	public String getDealStaffKeisyo() {
		return dealStaffKeisyo;
	}
	public void setDealStaffKeisyo(String dealStaffKeisyo) {
		this.dealStaffKeisyo = dealStaffKeisyo;
	}
	public String getDealBusyo() {
		return dealBusyo;
	}
	public void setDealBusyo(String dealBusyo) {
		this.dealBusyo = dealBusyo;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getPostCode() {
		return postCode;
	}
	public void setPostCode(String postCode) {
		this.postCode = postCode;
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
	public String getShimebi() {
		return shimebi;
	}
	public void setShimebi(String shimebi) {
		this.shimebi = shimebi;
	}
	public String getKessai() {
		return kessai;
	}
	public void setKessai(String kessai) {
		this.kessai = kessai;
	}
	public String getDealDivision() {
		return dealDivision;
	}
	public void setDealDivision(String dealDivision) {
		this.dealDivision = dealDivision;
	}
	public String getDealDivisionCode() {
		return dealDivisionCode;
	}
	public void setDealDivisionCode(String dealDivisionCode) {
		this.dealDivisionCode = dealDivisionCode;
	}
	public String getPriceDivision() {
		return priceDivision;
	}
	public void setPriceDivision(String priceDivision) {
		this.priceDivision = priceDivision;
	}
	public String getStaff() {
		return staff;
	}
	public void setStaff(String staff) {
		this.staff = staff;
	}
	public String getStaffCode() {
		return staffCode;
	}
	public void setStaffCode(String staffCode) {
		this.staffCode = staffCode;
	}
	public String getBusyo() {
		return busyo;
	}
	public void setBusyo(String busyo) {
		this.busyo = busyo;
	}
}
