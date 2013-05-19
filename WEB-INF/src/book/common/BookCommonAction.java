package book.common;

import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.BizCommonConstants;

import fw.core.base.AbstractCommonEventDispatchAction;

/**
 * 帳簿共通アクション
 */
public class BookCommonAction extends AbstractCommonEventDispatchAction {

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
		request.getSession(false).removeAttribute(BizCommonConstants.FORM_BEAN_NAME_BOOK_ITEM_FORM);
		request.getSession(false).removeAttribute(BizCommonConstants.BEAN_NAME_BOOK_BEAN);
		return mapping.findForward(BizCommonConstants.FWD_BACK);
	}

	/**
	 * エクセルダウンロード
	 * @param response
	 * @param byteAry
	 * @throws Exception
	 */
	protected void download(HttpServletResponse response, byte[] byteAry) throws Exception {
		OutputStream out = null;
		try {
			response.setHeader("Cache-Control", "");
			response.setHeader("Pragma", "");
			response.setContentType("application/vnd.ms-excel");
			response.setContentLength(byteAry.length);
			out = response.getOutputStream();
			out.write(byteAry);
		} catch (Exception e) {
			throw e;
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}
}
