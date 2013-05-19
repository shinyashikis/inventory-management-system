package fw.core.base;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import fw.core.error.RequestInvalidException;

/**
 * <p>共通アクション抽象クラス</p>
 */
abstract public class AbstractCommonAction extends Action {

	/**
	 * <p>実行処理</p>
	 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	final public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String token = mapping.getProperty(BaseCheckLogic.CONFIG_PROP_TOKEN_KEY);
		if (!BaseCheckLogic.checkToken(request, this, token)) {
			throw new RequestInvalidException();
		}
		return doExecute(mapping, form, request, response);
	}

	/**
	 * <p>子クラス固有実行処理</p>
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	abstract protected ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception;

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
