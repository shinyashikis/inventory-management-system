package fw.core.base;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.EventDispatchAction;

import fw.common.util.CommonUtil;
import fw.core.error.RequestInvalidException;


/**
 * <p>共通アクション抽象クラス</p>
 */
abstract public class AbstractCommonEventDispatchAction extends EventDispatchAction {

	/**
	 * <p>存在しないメソッド呼び出し時</p>
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward invalideMethod(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		throw new RequestInvalidException("存在しないメソッド呼び出し");
	}

	/**
	 * <p>振分処理</p>
	 * @see org.apache.struts.actions.DispatchAction#dispatchMethod(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.String)
	 */
	@Override
	final protected ActionForward dispatchMethod(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response, String name)
			throws Exception {
		String token = CommonUtil.getPropertyValue(
				mapping.getProperty(BaseCheckLogic.CONFIG_PROP_TOKEN_KEY), name);
		if (!BaseCheckLogic.checkToken(request, this, token)) {
			throw new RequestInvalidException("トークンチェックエラー");
		}
		return super.dispatchMethod(mapping, form, request, response, name);
	}

	/**
	 * @see org.apache.struts.action.Action#saveToken(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	protected void saveToken(HttpServletRequest request) {
		super.saveToken(request);
	}

	/**
	 * @see org.apache.struts.action.Action#resetToken(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	protected void resetToken(HttpServletRequest request) {
		super.resetToken(request);
	}

	/**
	 * @see org.apache.struts.action.Action#isTokenValid(javax.servlet.http.HttpServletRequest, boolean)
	 */
	@Override
	protected boolean isTokenValid(HttpServletRequest request, boolean reset) {
		return super.isTokenValid(request, reset);
	}
}
