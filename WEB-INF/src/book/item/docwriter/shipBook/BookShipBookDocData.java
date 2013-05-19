package book.item.docwriter.shipBook;

import java.util.ArrayList;
import java.util.List;

import fw.common.docwriter.DocData;

public class BookShipBookDocData implements DocData {

	private static final long serialVersionUID = 6538185528299252450L;

	private String nendo;
	private String from;
	private String to;

	/** 行データ **/
	private List<RowData> rowDataList = new ArrayList<RowData>();

	public String getNendo() {
		return nendo;
	}

	void setNendo(String nendo) {
		this.nendo = nendo;
	}

	public String getFrom() {
		return from;
	}

	void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	void setTo(String to) {
		this.to = to;
	}

	public List<RowData> getRowDataList() {
		return rowDataList;
	}

	private void createRowData(int index) {
		if (rowDataList.size() == 0 ||
				rowDataList.size() <= index) {
			rowDataList.add(new RowData());
		}
	}

	void setVoucherDate(int index, String voucherDate) {
		createRowData(index);
		rowDataList.get(index).setVoucherDate(voucherDate);
	}

	public String getVoucherDate(int index) {
		return (rowDataList.get(index) == null)
			? null : rowDataList.get(index).getVoucherDate();
	}

	void setVoucherNo(int index, String voucherNo) {
		createRowData(index);
		rowDataList.get(index).setVoucherNo(voucherNo);
	}

	public String getVoucherNo(int index) {
		return (rowDataList.get(index) == null)
			? null : rowDataList.get(index).getVoucherNo();
	}

	void setClassName(int index, String className) {
		createRowData(index);
		rowDataList.get(index).setClassName(className);
	}

	public String getClassName(int index) {
		return (rowDataList.get(index) == null)
			? null : rowDataList.get(index).getClassName();
	}

	void setKana(int index,String kana) {
		createRowData(index);
		rowDataList.get(index).setKana(kana);
	}

	public String getKana(int index) {
		return (rowDataList.get(index) == null)
			? null : rowDataList.get(index).getKana();
	}

	void setName(int index, String name) {
		createRowData(index);
		rowDataList.get(index).setName(name);
	}

	public String getName(int index) {
		return (rowDataList.get(index) == null)
			? null : rowDataList.get(index).getName();
	}

	void setCount(int index, String count) {
		createRowData(index);
		rowDataList.get(index).setCount(count);
	}

	public String getCount(int index) {
		return (rowDataList.get(index) == null)
			? null : rowDataList.get(index).getCount();
	}


	/**
	 * 行データ
	 */
	private class RowData {
		private String voucherDate;
		private String voucherNo;
		private String className;
		private String kana;
		private String name;
		private String count;

		private String getVoucherDate() {
			return voucherDate;
		}
		private void setVoucherDate(String voucherDate) {
			this.voucherDate = voucherDate;
		}
		private String getVoucherNo() {
			return voucherNo;
		}
		private void setVoucherNo(String voucherNo) {
			this.voucherNo = voucherNo;
		}
		private String getClassName() {
			return className;
		}
		private void setClassName(String className) {
			this.className = className;
		}
		private String getKana() {
			return kana;
		}
		private void setKana(String kana) {
			this.kana = kana;
		}
		private String getName() {
			return name;
		}
		private void setName(String name) {
			this.name = name;
		}
		private String getCount() {
			return count;
		}
		private void setCount(String count) {
			this.count = count;
		}
	}
}
