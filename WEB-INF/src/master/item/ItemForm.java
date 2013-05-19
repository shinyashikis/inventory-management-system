package master.item;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;

/**
 * <p>商品フォーム</p>
 */
public class ItemForm extends ValidatorForm {
	private static final long serialVersionUID = 8220197777741648549L;

	private String code;
	private String name;
	private String kana;
	private String kikaku;
	private String classCode;
	private String stock;
	private String properStock;
	private String kisyuStock;
	private String kicyuStockUpDown;
	private String unit;
	private String price;
	private String kisyuPrice;
	private String standardPrice;
	private String sellingPrice2;
	private String sellingPrice3;
	private String sellingPrice4;
	private String sellingPrice5;
	private String updDate;
	private String dispUpdDate;
	private boolean updFlg = false;

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
	public String getKikaku() {
		return kikaku;
	}
	public void setKikaku(String kikaku) {
		this.kikaku = kikaku;
	}
	public String getClassCode() {
		return classCode;
	}
	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}
	public String getStock() {
		return stock;
	}
	public void setStock(String stock) {
		this.stock = stock;
	}
	public String getProperStock() {
		return properStock;
	}
	public void setProperStock(String properStock) {
		this.properStock = properStock;
	}
	public String getKisyuStock() {
		return kisyuStock;
	}
	public void setKisyuStock(String kisyuStock) {
		this.kisyuStock = kisyuStock;
	}
	public String getKicyuStockUpDown() {
		return kicyuStockUpDown;
	}
	public void setKicyuStockUpDown(String kicyuStockUpDown) {
		this.kicyuStockUpDown = kicyuStockUpDown;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getKisyuPrice() {
		return kisyuPrice;
	}
	public void setKisyuPrice(String kisyuPrice) {
		this.kisyuPrice = kisyuPrice;
	}
	public String getStandardPrice() {
		return standardPrice;
	}
	public void setStandardPrice(String standardPrice) {
		this.standardPrice = standardPrice;
	}
	public String getSellingPrice2() {
		return sellingPrice2;
	}
	public void setSellingPrice2(String sellingPrice2) {
		this.sellingPrice2 = sellingPrice2;
	}
	public String getSellingPrice3() {
		return sellingPrice3;
	}
	public void setSellingPrice3(String sellingPrice3) {
		this.sellingPrice3 = sellingPrice3;
	}
	public String getSellingPrice4() {
		return sellingPrice4;
	}
	public void setSellingPrice4(String sellingPrice4) {
		this.sellingPrice4 = sellingPrice4;
	}
	public String getSellingPrice5() {
		return sellingPrice5;
	}
	public void setSellingPrice5(String sellingPrice5) {
		this.sellingPrice5 = sellingPrice5;
	}
	public String getUpdDate() {
		return updDate;
	}
	public void setUpdDate(String updDate) {
		this.updDate = updDate;
	}
	public String getDispUpdDate() {
		return dispUpdDate;
	}
	public void setDispUpdDate(String dispUpdDate) {
		this.dispUpdDate = dispUpdDate;
	}
	public boolean isUpdFlg() {
		return updFlg;
	}
	public void setUpdFlg(boolean updFlg) {
		this.updFlg = updFlg;
	}

	@Override
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		@SuppressWarnings("rawtypes")
		Map m = request.getParameterMap();
		if (m.size() == 0 || m.get("back") != null) {
			return new ActionErrors();
		}
		return super.validate(mapping, request);
	}

}
