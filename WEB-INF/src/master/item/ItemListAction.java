package master.item;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import master.item.bl.ItemBL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import common.MsgResourcesConstants;
import fw.core.base.AbstractCommonEventDispatchAction;

/**
 * <p>商品一覧アクション</p>
 */
public class ItemListAction extends AbstractCommonEventDispatchAction {

	private static final String FWD_ADD = "add";
	private static final String FWD_UPDATE = "update";
	private static final String FWD_DELETE = "delete";
	private static final String FWD_DELETE_ERROR = "deleteError";
	private static final String FWD_BACK = "back";

	/**
	 * 「登録」ボタン押下時
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setAttribute("ItemForm", new ItemForm());
		return mapping.findForward(FWD_ADD);
	}

	/**
	 * 「更新」ボタン押下時
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		ItemListForm listForm = (ItemListForm)form;
		String code = listForm.getSel()[0];
		ItemForm itemForm = ItemBL.convMItemBeanToItemForm(listForm.getItemMap().get(code));
		itemForm.setUpdFlg(true);
		request.setAttribute("ItemForm", itemForm);
		return mapping.findForward(FWD_UPDATE);
	}

	/**
	 * 「削除」ボタン押下時
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionMessages errors = null;
		if ((errors = ItemBL.delete((ItemListForm)form)) != null) {
			saveErrors(request, errors);
			return mapping.findForward(FWD_DELETE_ERROR);
		}

		ActionMessages messages = new ActionMessages();
		ActionMessage message = new ActionMessage(
				MsgResourcesConstants.COMMON_MSG_DELETE, "商品");
		messages.add(ActionMessages.GLOBAL_MESSAGE, message);
		saveMessages(request, messages);

		request.getSession(false).setAttribute("ItemListForm", ItemBL.getItemListForm());
		return mapping.findForward(FWD_DELETE);
	}

	/**
	 * 「戻る」ボタン押下時
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward back(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.getSession(false).removeAttribute("ItemListForm");
		request.getSession(false).removeAttribute("ItemClassListForm");
		return mapping.findForward(FWD_BACK);
	}

}
