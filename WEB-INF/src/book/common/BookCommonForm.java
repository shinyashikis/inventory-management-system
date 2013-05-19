package book.common;

import org.apache.struts.validator.ValidatorForm;

/**
 * <p>(帳簿)情報フォーム</p>
 */
public class BookCommonForm extends ValidatorForm {
	private static final long serialVersionUID = -3168830477222483514L;

	private String dispTarget;
	private String dispTargetDetail;
	private String selectDateRange;
	private String searchDateMonthFrom;
	private String searchDateDayFrom;
	private String searchDateMonthTo;
	private String searchDateDayTo;
	private String shimebi;
	private String nendo;
	private String selectDispSort;
	private String selectSort;
	private String selectSortKind;

	public String getDispTarget() {
		return dispTarget;
	}

	public void setDispTarget(String dispTarget) {
		this.dispTarget = dispTarget;
	}

	public String getDispTargetDetail() {
		return dispTargetDetail;
	}

	public void setDispTargetDetail(String dispTargetDetail) {
		this.dispTargetDetail = dispTargetDetail;
	}

	public String getSelectDateRange() {
		return selectDateRange;
	}

	public void setSelectDateRange(String selectDateRange) {
		this.selectDateRange = selectDateRange;
	}

	public String getSearchDateMonthFrom() {
		return searchDateMonthFrom;
	}

	public void setSearchDateMonthFrom(String searchDateMonthFrom) {
		this.searchDateMonthFrom = searchDateMonthFrom;
	}

	public String getSearchDateDayFrom() {
		return searchDateDayFrom;
	}

	public void setSearchDateDayFrom(String searchDateDayFrom) {
		this.searchDateDayFrom = searchDateDayFrom;
	}

	public String getSearchDateMonthTo() {
		return searchDateMonthTo;
	}

	public void setSearchDateMonthTo(String searchDateMonthTo) {
		this.searchDateMonthTo = searchDateMonthTo;
	}

	public String getSearchDateDayTo() {
		return searchDateDayTo;
	}

	public void setSearchDateDayTo(String searchDateDayTo) {
		this.searchDateDayTo = searchDateDayTo;
	}

	public String getShimebi() {
		return shimebi;
	}

	public void setShimebi(String shimebi) {
		this.shimebi = shimebi;
	}

	public String getNendo() {
		return nendo;
	}

	public void setNendo(String nendo) {
		this.nendo = nendo;
	}

	public String getSelectDispSort() {
		return selectDispSort;
	}

	public void setSelectDispSort(String selectDispSort) {
		this.selectDispSort = selectDispSort;
	}

	public String getSelectSort() {
		return selectSort;
	}

	public void setSelectSort(String selectSort) {
		this.selectSort = selectSort;
	}

	public String getSelectSortKind() {
		return selectSortKind;
	}

	public void setSelectSortKind(String selectSortKind) {
		this.selectSortKind = selectSortKind;
	}

}
