package master.item;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import master.item.bl.ItemClassBL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import common.MsgResourcesConstants;
import fw.core.base.AbstractCommonEventDispatchAction;

/**
 * <p>商品分類登録アクション</p>
 */
public class ItemClassUpdateAction extends AbstractCommonEventDispatchAction {

	private static final String FWD_UPDATE = "update";
	private static final String FWD_BACK = "back";
	private static final String FWD_DUPLICATE_ERROR = "duplicateError";
	private static final String FWD_LOCK_ERROR = "lockError";

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
		HttpSession session = request.getSession(false);
		ItemClassListForm itemClassListForm = (ItemClassListForm)session.getAttribute("ItemClassListForm");

		ActionMessages errors = null;
		ItemClassForm itemClassForm = (ItemClassForm)form;
		String[] sel = itemClassListForm.getSel();
		if (sel == null || sel.length == 0) {
			// 登録処理

			if ((errors = ItemClassBL.insert(itemClassForm)) != null) {
				// 重複エラー時

				saveErrors(request, errors);
				return mapping.findForward(FWD_DUPLICATE_ERROR);
			}
		} else {
			// 更新処理

			if ((errors = ItemClassBL.update(itemClassForm)) != null) {
				// 排他エラー時

				// 最新状態のリストを再取得
				session.setAttribute("ItemClassListForm", ItemClassBL.getItemClassListForm());
				saveErrors(request, errors);
				return mapping.findForward(FWD_LOCK_ERROR);
			}
		}

		ActionMessages messages = new ActionMessages();
		ActionMessage message = new ActionMessage(
				MsgResourcesConstants.COMMON_MSG_UPDATE, "商品分類");
		messages.add(ActionMessages.GLOBAL_MESSAGE, message);
		saveMessages(request, messages);

		session.setAttribute("ItemClassListForm", ItemClassBL.getItemClassListForm());
		return mapping.findForward(FWD_UPDATE);
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
		// チェックボックスの選択状態を解除
		HttpSession session = request.getSession(false);
		ItemClassListForm itemClassListForm = (ItemClassListForm)session.getAttribute("ItemClassListForm");
		itemClassListForm.setSel(null);
		return mapping.findForward(FWD_BACK);
	}

}
