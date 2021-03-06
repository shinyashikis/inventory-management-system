package book.item.docwriter.gross;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

import fw.common.docwriter.DocData;

public class BookGrossDocData implements DocData {

	private static final long serialVersionUID = 5049808631015906748L;

	private String nendo;
	private String from;
	private String to;

	/** 行データ(key:商品コード、value:RowData **/
	private Map<String, RowData> rowDataMap = new LinkedHashMap<String, BookGrossDocData.RowData>();

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

	public Map<String, RowData> getRowDataMap() {
		return rowDataMap;
	}

	private void createRowData(String code) {
		RowData rowData = rowDataMap.get(code);
		if (rowData == null) {
			rowData = new RowData();
			rowDataMap.put(code, rowData);
		}
	}

	void setClassName(String code, String className) {
		createRowData(code);
		rowDataMap.get(code).setClassName(className);
	}

	public String getClassName(String code) {
		return (rowDataMap.get(code) == null)
			? null : rowDataMap.get(code).getClassName();
	}

	void setKana(String code,String kana) {
		createRowData(code);
		rowDataMap.get(code).setKana(kana);
	}

	public String getKana(String code) {
		return (rowDataMap.get(code) == null)
			? null : rowDataMap.get(code).getKana();
	}

	void setName(String code, String name) {
		createRowData(code);
		rowDataMap.get(code).setName(name);
	}

	public String getName(String code) {
		return (rowDataMap.get(code) == null)
			? null : rowDataMap.get(code).getName();
	}

	void setGross(String code, BigDecimal gross) {
		createRowData(code);
		rowDataMap.get(code).setGross(gross);
	}

	public BigDecimal getGross(String code) {
		return (rowDataMap.get(code) == null)
			? null : rowDataMap.get(code).getGross();
	}

	void setJanPrice(String code, BigDecimal janPrice) {
		createRowData(code);
		rowDataMap.get(code).setJanPrice(janPrice);
	}

	public BigDecimal getJanPrice(String code) {
		return (rowDataMap.get(code) == null)
			? null : rowDataMap.get(code).getJanPrice();
	}

	void setFebPrice(String code, BigDecimal febPrice) {
		createRowData(code);
		rowDataMap.get(code).setFebPrice(febPrice);
	}

	public BigDecimal getFebPrice(String code) {
		return (rowDataMap.get(code) == null)
			? null : rowDataMap.get(code).getFebPrice();
	}

	void setMarPrice(String code, BigDecimal marPrice) {
		createRowData(code);
		rowDataMap.get(code).setMarPrice(marPrice);
	}

	public BigDecimal getMarPrice(String code) {
		return (rowDataMap.get(code) == null)
			? null : rowDataMap.get(code).getMarPrice();
	}

	void setAplPrice(String code, BigDecimal aplPrice) {
		createRowData(code);
		rowDataMap.get(code).setAplPrice(aplPrice);
	}

	public BigDecimal getAplPrice(String code) {
		return (rowDataMap.get(code) == null)
			? null : rowDataMap.get(code).getAplPrice();
	}

	void setMayPrice(String code, BigDecimal mayPrice) {
		createRowData(code);
		rowDataMap.get(code).setMayPrice(mayPrice);
	}

	public BigDecimal getMayPrice(String code) {
		return (rowDataMap.get(code) == null)
			? null : rowDataMap.get(code).getMayPrice();
	}

	void setJunPrice(String code, BigDecimal junPrice) {
		createRowData(code);
		rowDataMap.get(code).setJunPrice(junPrice);
	}

	public BigDecimal getJunPrice(String code) {
		return (rowDataMap.get(code) == null)
			? null : rowDataMap.get(code).getJunPrice();
	}

	void setJlyPrice(String code, BigDecimal jlyPrice) {
		createRowData(code);
		rowDataMap.get(code).setJlyPrice(jlyPrice);
	}

	public BigDecimal getJlyPrice(String code) {
		return (rowDataMap.get(code) == null)
			? null : rowDataMap.get(code).getJlyPrice();
	}

	void setAugPrice(String code, BigDecimal augPrice) {
		createRowData(code);
		rowDataMap.get(code).setAugPrice(augPrice);
	}

	public BigDecimal getAugPrice(String code) {
		return (rowDataMap.get(code) == null)
			? null : rowDataMap.get(code).getAugPrice();
	}

	void setSepPrice(String code, BigDecimal sepPrice) {
		createRowData(code);
		rowDataMap.get(code).setSepPrice(sepPrice);
	}

	public BigDecimal getSepPrice(String code) {
		return (rowDataMap.get(code) == null)
			? null : rowDataMap.get(code).getSepPrice();
	}

	void setOctPrice(String code, BigDecimal octPrice) {
		createRowData(code);
		rowDataMap.get(code).setOctPrice(octPrice);
	}

	public BigDecimal getOctPrice(String code) {
		return (rowDataMap.get(code) == null)
			? null : rowDataMap.get(code).getOctPrice();
	}

	void setNovPrice(String code, BigDecimal novPrice) {
		createRowData(code);
		rowDataMap.get(code).setNovPrice(novPrice);
	}

	public BigDecimal getNovPrice(String code) {
		return (rowDataMap.get(code) == null)
			? null : rowDataMap.get(code).getNovPrice();
	}

	void setDecPrice(String code, BigDecimal decPrice) {
		createRowData(code);
		rowDataMap.get(code).setDecPrice(decPrice);
	}

	public BigDecimal getDecPrice(String code) {
		return (rowDataMap.get(code) == null)
			? null : rowDataMap.get(code).getDecPrice();
	}

	/**
	 * 行データ
	 */
	private class RowData {
		private String className;
		private String kana;
		private String name;
		private BigDecimal gross = new BigDecimal(0);
		private BigDecimal janPrice = new BigDecimal(0);
		private BigDecimal febPrice = new BigDecimal(0);
		private BigDecimal marPrice = new BigDecimal(0);
		private BigDecimal aplPrice = new BigDecimal(0);
		private BigDecimal mayPrice = new BigDecimal(0);
		private BigDecimal junPrice = new BigDecimal(0);
		private BigDecimal jlyPrice = new BigDecimal(0);
		private BigDecimal augPrice = new BigDecimal(0);
		private BigDecimal sepPrice = new BigDecimal(0);
		private BigDecimal octPrice = new BigDecimal(0);
		private BigDecimal novPrice = new BigDecimal(0);
		private BigDecimal decPrice = new BigDecimal(0);

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
		private BigDecimal getGross() {
			return gross;
		}
		private void setGross(BigDecimal gross) {
			this.gross = gross;
		}
		private BigDecimal getJanPrice() {
			return janPrice;
		}
		private void setJanPrice(BigDecimal janPrice) {
			this.janPrice = janPrice;
		}
		private BigDecimal getFebPrice() {
			return febPrice;
		}
		private void setFebPrice(BigDecimal febPrice) {
			this.febPrice = febPrice;
		}
		private BigDecimal getMarPrice() {
			return marPrice;
		}
		private void setMarPrice(BigDecimal marPrice) {
			this.marPrice = marPrice;
		}
		private BigDecimal getAplPrice() {
			return aplPrice;
		}
		private void setAplPrice(BigDecimal aplPrice) {
			this.aplPrice = aplPrice;
		}
		private BigDecimal getMayPrice() {
			return mayPrice;
		}
		private void setMayPrice(BigDecimal mayPrice) {
			this.mayPrice = mayPrice;
		}
		private BigDecimal getJunPrice() {
			return junPrice;
		}
		private void setJunPrice(BigDecimal junPrice) {
			this.junPrice = junPrice;
		}
		private BigDecimal getJlyPrice() {
			return jlyPrice;
		}
		private void setJlyPrice(BigDecimal jlyPrice) {
			this.jlyPrice = jlyPrice;
		}
		private BigDecimal getAugPrice() {
			return augPrice;
		}
		private void setAugPrice(BigDecimal augPrice) {
			this.augPrice = augPrice;
		}
		private BigDecimal getSepPrice() {
			return sepPrice;
		}
		private void setSepPrice(BigDecimal sepPrice) {
			this.sepPrice = sepPrice;
		}
		private BigDecimal getOctPrice() {
			return octPrice;
		}
		private void setOctPrice(BigDecimal octPrice) {
			this.octPrice = octPrice;
		}
		private BigDecimal getNovPrice() {
			return novPrice;
		}
		private void setNovPrice(BigDecimal novPrice) {
			this.novPrice = novPrice;
		}
		private BigDecimal getDecPrice() {
			return decPrice;
		}
		private void setDecPrice(BigDecimal decPrice) {
			this.decPrice = decPrice;
		}
	}
}
