package book.item.docwriter.shipSum;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

import fw.common.docwriter.DocData;

public class BookShipSumDocData implements DocData {

	private static final long serialVersionUID = 6538185528299252450L;

	private String nendo;
	private String from;
	private String to;

	/** 行データ(key:商品コード、value:RowData **/
	private Map<String, RowData> rowDataMap = new LinkedHashMap<String, BookShipSumDocData.RowData>();

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

	void setSumCount(String code, String sumCount) {
		createRowData(code);
		rowDataMap.get(code).setSumCount(sumCount);
	}

	public String getSumCount(String code) {
		return (rowDataMap.get(code) == null)
			? null : rowDataMap.get(code).getSumCount();
	}

	void setSumPrice(String code, BigDecimal sumPrice) {
		createRowData(code);
		rowDataMap.get(code).setSumPrice(sumPrice);
	}

	public BigDecimal getSumPrice(String code) {
		return (rowDataMap.get(code) == null)
			? null : rowDataMap.get(code).getSumPrice();
	}

	void setJanCount(String code, String janCount) {
		createRowData(code);
		rowDataMap.get(code).setJanCount(janCount);
	}

	public String getJanCount(String code) {
		return (rowDataMap.get(code) == null)
			? null : rowDataMap.get(code).getJanCount();
	}

	void setFebCount(String code, String febCount) {
		createRowData(code);
		rowDataMap.get(code).setFebCount(febCount);
	}

	public String getFebCount(String code) {
		return (rowDataMap.get(code) == null)
			? null : rowDataMap.get(code).getFebCount();
	}

	void setMarCount(String code, String marCount) {
		createRowData(code);
		rowDataMap.get(code).setMarCount(marCount);
	}

	public String getMarCount(String code) {
		return (rowDataMap.get(code) == null)
			? null : rowDataMap.get(code).getMarCount();
	}

	void setAplCount(String code, String aplCount) {
		createRowData(code);
		rowDataMap.get(code).setAplCount(aplCount);
	}

	public String getAplCount(String code) {
		return (rowDataMap.get(code) == null)
			? null : rowDataMap.get(code).getAplCount();
	}

	void setMayCount(String code, String mayCount) {
		createRowData(code);
		rowDataMap.get(code).setMayCount(mayCount);
	}

	public String getMayCount(String code) {
		return (rowDataMap.get(code) == null)
			? null : rowDataMap.get(code).getMayCount();
	}

	void setJunCount(String code, String junCount) {
		createRowData(code);
		rowDataMap.get(code).setJunCount(junCount);
	}

	public String getJunCount(String code) {
		return (rowDataMap.get(code) == null)
			? null : rowDataMap.get(code).getJunCount();
	}

	void setJlyCount(String code, String jlyCount) {
		createRowData(code);
		rowDataMap.get(code).setJlyCount(jlyCount);
	}

	public String getJlyCount(String code) {
		return (rowDataMap.get(code) == null)
			? null : rowDataMap.get(code).getJlyCount();
	}

	void setAugCount(String code, String augCount) {
		createRowData(code);
		rowDataMap.get(code).setAugCount(augCount);
	}

	public String getAugCount(String code) {
		return (rowDataMap.get(code) == null)
			? null : rowDataMap.get(code).getAugCount();
	}

	void setSepCount(String code, String sepCount) {
		createRowData(code);
		rowDataMap.get(code).setSepCount(sepCount);
	}

	public String getSepCount(String code) {
		return (rowDataMap.get(code) == null)
			? null : rowDataMap.get(code).getSepCount();
	}

	void setOctCount(String code, String octCount) {
		createRowData(code);
		rowDataMap.get(code).setOctCount(octCount);
	}

	public String getOctCount(String code) {
		return (rowDataMap.get(code) == null)
			? null : rowDataMap.get(code).getOctCount();
	}

	void setNovCount(String code, String novCount) {
		createRowData(code);
		rowDataMap.get(code).setNovCount(novCount);
	}

	public String getNovCount(String code) {
		return (rowDataMap.get(code) == null)
			? null : rowDataMap.get(code).getNovCount();
	}

	void setDecCount(String code, String decCount) {
		createRowData(code);
		rowDataMap.get(code).setDecCount(decCount);
	}

	public String getDecCount(String code) {
		return (rowDataMap.get(code) == null)
			? null : rowDataMap.get(code).getDecCount();
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
		private String sumCount;
		private BigDecimal sumPrice;
		private String janCount;
		private String febCount;
		private String marCount;
		private String aplCount;
		private String mayCount;
		private String junCount;
		private String jlyCount;
		private String augCount;
		private String sepCount;
		private String octCount;
		private String novCount;
		private String decCount;
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
		private String getSumCount() {
			return sumCount;
		}
		private void setSumCount(String sumCount) {
			this.sumCount = sumCount;
		}
		private BigDecimal getSumPrice() {
			return sumPrice;
		}
		private void setSumPrice(BigDecimal sumPrice) {
			this.sumPrice = sumPrice;
		}
		private String getJanCount() {
			return janCount;
		}
		private void setJanCount(String janCount) {
			this.janCount = janCount;
		}
		private String getFebCount() {
			return febCount;
		}
		private void setFebCount(String febCount) {
			this.febCount = febCount;
		}
		private String getMarCount() {
			return marCount;
		}
		private void setMarCount(String marCount) {
			this.marCount = marCount;
		}
		private String getAplCount() {
			return aplCount;
		}
		private void setAplCount(String aplCount) {
			this.aplCount = aplCount;
		}
		private String getMayCount() {
			return mayCount;
		}
		private void setMayCount(String mayCount) {
			this.mayCount = mayCount;
		}
		private String getJunCount() {
			return junCount;
		}
		private void setJunCount(String junCount) {
			this.junCount = junCount;
		}
		private String getJlyCount() {
			return jlyCount;
		}
		private void setJlyCount(String jlyCount) {
			this.jlyCount = jlyCount;
		}
		private String getAugCount() {
			return augCount;
		}
		private void setAugCount(String augCount) {
			this.augCount = augCount;
		}
		private String getSepCount() {
			return sepCount;
		}
		private void setSepCount(String sepCount) {
			this.sepCount = sepCount;
		}
		private String getOctCount() {
			return octCount;
		}
		private void setOctCount(String octCount) {
			this.octCount = octCount;
		}
		private String getNovCount() {
			return novCount;
		}
		private void setNovCount(String novCount) {
			this.novCount = novCount;
		}
		private String getDecCount() {
			return decCount;
		}
		private void setDecCount(String decCount) {
			this.decCount = decCount;
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
