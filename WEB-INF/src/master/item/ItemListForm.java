package master.item;

import java.util.LinkedHashMap;
import java.util.Map;

import master.item.bean.MItemBean;

import org.apache.struts.action.ActionForm;

/**
 * <p>商品リストフォーム</p>
 */
public class ItemListForm extends ActionForm {
	private static final long serialVersionUID = -3334106300513784470L;

	private String[] sel;
	private Map<String,MItemBean> itemMap = new LinkedHashMap<String,MItemBean>();

	public String[] getSel() {
		return sel;
	}

	public void setSel(String[] sel) {
		this.sel = sel;
	}

	public Map<String, MItemBean> getItemMap() {
		return itemMap;
	}

	public void setItemMap(Map<String, MItemBean> itemMap) {
		this.itemMap = itemMap;
	}

	public void setItem(String code, MItemBean item) {
		itemMap.put(code, item);
	}

}
