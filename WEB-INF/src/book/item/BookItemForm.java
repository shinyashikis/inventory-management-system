package book.item;

import book.common.BookCommonForm;

/**
 * <p>(帳簿)商品情報フォーム</p>
 */
public class BookItemForm extends BookCommonForm {
	private static final long serialVersionUID = -5827997092426686107L;

	private String itemCondition;
	private String itemClassCondition;

	public String getItemCondition() {
		return itemCondition;
	}

	public void setItemCondition(String itemCondition) {
		this.itemCondition = itemCondition;
	}

	public String getItemClassCondition() {
		return itemClassCondition;
	}

	public void setItemClassCondition(String itemClassCondition) {
		this.itemClassCondition = itemClassCondition;
	}

}
