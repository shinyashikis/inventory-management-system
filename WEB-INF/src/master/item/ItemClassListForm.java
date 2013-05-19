package master.item;

import java.util.LinkedHashMap;
import java.util.Map;

import master.item.bean.MItemClassBean;

import org.apache.struts.action.ActionForm;

/**
 * <p>商品分類リストフォーム</p>
 */
public class ItemClassListForm extends ActionForm {
	private static final long serialVersionUID = -6379226685607572322L;

	private String[] sel;
	private Map<String,MItemClassBean> itemClassMap = new LinkedHashMap<String,MItemClassBean>();

	public String[] getSel() {
		return sel;
	}

	public void setSel(String[] sel) {
		this.sel = sel;
	}

	public Map<String, MItemClassBean> getItemClassMap() {
		return itemClassMap;
	}

	public void setItemClassMap(Map<String, MItemClassBean> itemClassMap) {
		this.itemClassMap = itemClassMap;
	}

	public void setItemClass(String code, MItemClassBean itemClass) {
		itemClassMap.put(code, itemClass);
	}

}
