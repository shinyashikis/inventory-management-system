package fw.core.error;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ExceptionHandler;
import org.apache.struts.config.ExceptionConfig;

/**
 * <p>共通エラーハンドラ</p>
 */
public class CommonErrorHandler extends ExceptionHandler {

	/**
	 * エラー処理
	 * @see org.apache.struts.action.ExceptionHandler#execute(java.lang.Exception, org.apache.struts.config.ExceptionConfig, org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public ActionForward execute(Exception ex, ExceptionConfig ae,
			ActionMapping mapping, ActionForm formInstance,
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException {

		ActionForward forward;
		String property = null;
		ActionMessage error = null;

		if (ae.getPath() != null) {
			forward = new ActionForward(ae.getPath());
		} else {
			forward = mapping.getInputForward();
		}

		if (ex instanceof RequestInvalidException) {
			// 処理要求が無効になりました。
            error = new ActionMessage(ae.getKey());
            property = error.getKey();
            request.getSession(false).invalidate();

		} else if (ex instanceof SQLException) {
			// SQLエラーが発生しました。
            error = new ActionMessage(ae.getKey());
            property = error.getKey();
            request.getSession(false).invalidate();

		} else if (ex instanceof RuntimeException || ex instanceof Exception) {
			// システムエラーが発生しました。
            error = new ActionMessage(ae.getKey());
            property = error.getKey();
            request.getSession(false).invalidate();
		}

        storeException(request, property, error, forward, ae.getScope());
		return forward;
	}

	/**
	 * スタックトレース取得
	 * @param ex
	 * @return
	 */
	@SuppressWarnings("unused")
	private String getStackTrace(Exception ex) {
		StringWriter sw = null;
		PrintWriter pw = null;
		try {
			sw = new StringWriter();
			pw = new PrintWriter(sw);
			ex.printStackTrace(pw);
			return sw.toString();
		} finally {
			try {
				if (sw != null) {
					sw.close();
					sw = null;
				}
				if (pw != null) {
					pw.close();
					pw = null;
				}
			} catch (IOException e) {}
		}
	}
}
