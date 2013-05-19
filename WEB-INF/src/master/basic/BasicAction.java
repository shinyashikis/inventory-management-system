package master.basic;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import master.basic.bl.BasicBL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import common.MsgResourcesConstants;
import fw.common.util.SystemSessionKey;
import fw.core.base.AbstractCommonEventDispatchAction;
import fw.core.base.SystemSessionManager;

/**
 * <p>基本設定アクション</p>
 */
public class BasicAction extends AbstractCommonEventDispatchAction {

	private static final String FWD_REGIST = "regist";
	private static final String FWD_BACK = "back";
	private static final String FWD_LOCK_ERROR = "lockError";
	private static final String FORM = "BasicForm";

	/**
	 * 「登録」ボタン押下時
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward regist(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		BasicForm basicForm = (BasicForm)form;

		ActionMessages errors = BasicBL.update(basicForm);
		if (errors != null) {
			// 排他エラー時
			saveErrors(request, errors);
			return mapping.findForward(FWD_LOCK_ERROR);
		}

		Calendar kimatsu = BasicBL.calcKimatsu(
				basicForm.getKisyuYear(), basicForm.getKisyuMonth(), basicForm.getKisyuDate());
		basicForm.setKimatsuYear(Integer.toString(kimatsu.get(Calendar.YEAR)));
		basicForm.setKimatsuMonth(Integer.toString(kimatsu.get(Calendar.MONTH)+1));
		basicForm.setKimatsuDate(Integer.toString(kimatsu.get(Calendar.DATE)));

		ActionMessages messages = new ActionMessages();
		ActionMessage message = new ActionMessage(
				MsgResourcesConstants.COMMON_MSG_UPDATE, "基本設定");
		messages.add(ActionMessages.GLOBAL_MESSAGE, message);
		saveMessages(request, messages);

		basicForm = BasicBL.getBasicForm();

		SystemSessionManager.setValue(request.getSession(false),
				SystemSessionKey.BASIC_INFO, BasicBL.convBasicFormToBasicBean(basicForm));

		request.setAttribute(FORM, basicForm);
		return mapping.findForward(FWD_REGIST);
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
		return mapping.findForward(FWD_BACK);
	}

}
