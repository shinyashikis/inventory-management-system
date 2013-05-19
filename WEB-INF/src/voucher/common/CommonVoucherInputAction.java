package voucher.common;

import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import fw.core.base.AbstractCommonEventDispatchAction;

/**
 * <p>帳票共通入力アクション</p>
 */
public class CommonVoucherInputAction extends AbstractCommonEventDispatchAction {

	/**
	 * <p>プレビュー表示</p>
	 * @param response
	 * @param byteAry
	 * @throws Exception
	 */
	protected void preview(HttpServletResponse response, byte[] byteAry) throws Exception {
		OutputStream out = null;
		try {
			response.setHeader("Cache-Control", "");
			response.setHeader("Pragma", "");

			response.setContentType("application/pdf");
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
