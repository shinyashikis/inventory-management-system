package book.item.docwriter.profit;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

import fw.common.docwriter.DocData;

public class BookProfitDocData implements DocData {

	private static final long serialVersionUID = 5049808631015906748L;

	private String nendo;
	private String from;
	private String to;

	/** 行データ(key:商品コード、value:RowData **/
	private Map<String, RowData> rowDataMap = new LinkedHashMap<String, BookProfitDocData.RowData>();

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

	void setProfit(String code, BigDecimal profit) {
		createRowData(code);
		rowDataMap.get(code).setProfit(profit);
	}

	public BigDecimal getProfit(String code) {
		return (rowDataMap.get(code) == null)
			? null : rowDataMap.get(code).getProfit();
	}

	void setJanProfit(String code, BigDecimal janProfit) {
		createRowData(code);
		rowDataMap.get(code).setJanProfit(janProfit);
	}

	public BigDecimal getJanProfit(String code) {
		return (rowDataMap.get(code) == null)
			? null : rowDataMap.get(code).getJanProfit();
	}

	void setFebProfit(String code, BigDecimal febProfit) {
		createRowData(code);
		rowDataMap.get(code).setFebProfit(febProfit);
	}

	public BigDecimal getFebProfit(String code) {
		return (rowDataMap.get(code) == null)
			? null : rowDataMap.get(code).getFebProfit();
	}

	void setMarProfit(String code, BigDecimal marProfit) {
		createRowData(code);
		rowDataMap.get(code).setMarProfit(marProfit);
	}

	public BigDecimal getMarProfit(String code) {
		return (rowDataMap.get(code) == null)
			? null : rowDataMap.get(code).getMarProfit();
	}

	void setAplProfit(String code, BigDecimal aplProfit) {
		createRowData(code);
		rowDataMap.get(code).setAplProfit(aplProfit);
	}

	public BigDecimal getAplProfit(String code) {
		return (rowDataMap.get(code) == null)
			? null : rowDataMap.get(code).getAplProfit();
	}

	void setMayProfit(String code, BigDecimal mayProfit) {
		createRowData(code);
		rowDataMap.get(code).setMayProfit(mayProfit);
	}

	public BigDecimal getMayProfit(String code) {
		return (rowDataMap.get(code) == null)
			? null : rowDataMap.get(code).getMayProfit();
	}

	void setJunProfit(String code, BigDecimal junProfit) {
		createRowData(code);
		rowDataMap.get(code).setJunProfit(junProfit);
	}

	public BigDecimal getJunProfit(String code) {
		return (rowDataMap.get(code) == null)
			? null : rowDataMap.get(code).getJunProfit();
	}

	void setJlyProfit(String code, BigDecimal jlyProfit) {
		createRowData(code);
		rowDataMap.get(code).setJlyProfit(jlyProfit);
	}

	public BigDecimal getJlyProfit(String code) {
		return (rowDataMap.get(code) == null)
			? null : rowDataMap.get(code).getJlyProfit();
	}

	void setAugProfit(String code, BigDecimal augProfit) {
		createRowData(code);
		rowDataMap.get(code).setAugProfit(augProfit);
	}

	public BigDecimal getAugProfit(String code) {
		return (rowDataMap.get(code) == null)
			? null : rowDataMap.get(code).getAugProfit();
	}

	void setSepProfit(String code, BigDecimal sepProfit) {
		createRowData(code);
		rowDataMap.get(code).setSepProfit(sepProfit);
	}

	public BigDecimal getSepProfit(String code) {
		return (rowDataMap.get(code) == null)
			? null : rowDataMap.get(code).getSepProfit();
	}

	void setOctProfit(String code, BigDecimal octProfit) {
		createRowData(code);
		rowDataMap.get(code).setOctProfit(octProfit);
	}

	public BigDecimal getOctProfit(String code) {
		return (rowDataMap.get(code) == null)
			? null : rowDataMap.get(code).getOctProfit();
	}

	void setNovProfit(String code, BigDecimal novProfit) {
		createRowData(code);
		rowDataMap.get(code).setNovProfit(novProfit);
	}

	public BigDecimal getNovProfit(String code) {
		return (rowDataMap.get(code) == null)
			? null : rowDataMap.get(code).getNovProfit();
	}

	void setDecProfit(String code, BigDecimal decProfit) {
		createRowData(code);
		rowDataMap.get(code).setDecProfit(decProfit);
	}

	public BigDecimal getDecProfit(String code) {
		return (rowDataMap.get(code) == null)
			? null : rowDataMap.get(code).getDecProfit();
	}

	/**
	 * 行データ
	 */
	private class RowData {
		private String className;
		private String kana;
		private String name;
		private BigDecimal profit = new BigDecimal(0);
		private BigDecimal janProfit = new BigDecimal(0);
		private BigDecimal febProfit = new BigDecimal(0);
		private BigDecimal marProfit = new BigDecimal(0);
		private BigDecimal aplProfit = new BigDecimal(0);
		private BigDecimal mayProfit = new BigDecimal(0);
		private BigDecimal junProfit = new BigDecimal(0);
		private BigDecimal jlyProfit = new BigDecimal(0);
		private BigDecimal augProfit = new BigDecimal(0);
		private BigDecimal sepProfit = new BigDecimal(0);
		private BigDecimal octProfit = new BigDecimal(0);
		private BigDecimal novProfit = new BigDecimal(0);
		private BigDecimal decProfit = new BigDecimal(0);

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
		private BigDecimal getProfit() {
			return profit;
		}
		private void setProfit(BigDecimal profit) {
			this.profit = profit;
		}
		private BigDecimal getJanProfit() {
			return janProfit;
		}
		private void setJanProfit(BigDecimal janProfit) {
			this.janProfit = janProfit;
		}
		private BigDecimal getFebProfit() {
			return febProfit;
		}
		private void setFebProfit(BigDecimal febProfit) {
			this.febProfit = febProfit;
		}
		private BigDecimal getMarProfit() {
			return marProfit;
		}
		private void setMarProfit(BigDecimal marProfit) {
			this.marProfit = marProfit;
		}
		private BigDecimal getAplProfit() {
			return aplProfit;
		}
		private void setAplProfit(BigDecimal aplProfit) {
			this.aplProfit = aplProfit;
		}
		private BigDecimal getMayProfit() {
			return mayProfit;
		}
		private void setMayProfit(BigDecimal mayProfit) {
			this.mayProfit = mayProfit;
		}
		private BigDecimal getJunProfit() {
			return junProfit;
		}
		private void setJunProfit(BigDecimal junProfit) {
			this.junProfit = junProfit;
		}
		private BigDecimal getJlyProfit() {
			return jlyProfit;
		}
		private void setJlyProfit(BigDecimal jlyProfit) {
			this.jlyProfit = jlyProfit;
		}
		private BigDecimal getAugProfit() {
			return augProfit;
		}
		private void setAugProfit(BigDecimal augProfit) {
			this.augProfit = augProfit;
		}
		private BigDecimal getSepProfit() {
			return sepProfit;
		}
		private void setSepProfit(BigDecimal sepProfit) {
			this.sepProfit = sepProfit;
		}
		private BigDecimal getOctProfit() {
			return octProfit;
		}
		private void setOctProfit(BigDecimal octProfit) {
			this.octProfit = octProfit;
		}
		private BigDecimal getNovProfit() {
			return novProfit;
		}
		private void setNovProfit(BigDecimal novProfit) {
			this.novProfit = novProfit;
		}
		private BigDecimal getDecProfit() {
			return decProfit;
		}
		private void setDecProfit(BigDecimal decProfit) {
			this.decProfit = decProfit;
		}
	}
}
