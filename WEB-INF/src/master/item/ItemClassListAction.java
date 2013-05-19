package master.item;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import master.item.bl.ItemClassBL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import common.MsgResourcesConstants;
import fw.core.base.AbstractCommonEventDispatchAction;

/**
 * <p>商品分類一覧アクション</p>
 */
public class ItemClassListAction extends AbstractCommonEventDispatchAction {

	private static final String FWD_INIT = "init";
	private static final String FWD_ADD = "add";
	private static final String FWD_UPDATE = "update";
	private static final String FWD_DELETE = "delete";
	private static final String FWD_FK_ERROR = "fkError";

	/**
	 * 初期表示時
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward init(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// チェックボックスの選択状態を解除
		ItemClassListForm itemClassListForm = (ItemClassListForm)request.getSession(false).getAttribute("ItemClassListForm");
		itemClassListForm.setSel(null);
		return mapping.findForward(FWD_INIT);
	}

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
		request.setAttribute("ItemClassForm", new ItemClassForm());
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
		ItemClassListForm listForm = (ItemClassListForm)form;
		String code = listForm.getSel()[0];
		request.setAttribute("ItemClassForm",
				ItemClassBL.convMItemClassBeanToItemClassForm(listForm.getItemClassMap().get(code)));
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
		ActionMessages errors = ItemClassBL.delete((ItemClassListForm)form);
		if (errors != null) {
			saveErrors(request, errors);
			return mapping.findForward(FWD_FK_ERROR);
		}

		ActionMessages messages = new ActionMessages();
		ActionMessage message = new ActionMessage(
				MsgResourcesConstants.COMMON_MSG_DELETE, "商品分類");
		messages.add(ActionMessages.GLOBAL_MESSAGE, message);
		saveMessages(request, messages);

		request.getSession(false).setAttribute("ItemClassListForm", ItemClassBL.getItemClassListForm());
		return mapping.findForward(FWD_DELETE);
	}

}
