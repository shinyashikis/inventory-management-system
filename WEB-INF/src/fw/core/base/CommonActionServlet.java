package fw.core.base;

import javax.servlet.ServletException;

import org.apache.struts.action.ActionServlet;

import fw.common.db.ConnectionFactory;

/**
 * <p>共通アクションサーブレット</p>
 */
public class CommonActionServlet extends ActionServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * <p>Webアプリケーション起動時処理</p>
	 * @see org.apache.struts.action.ActionServlet#init()
	 */
	@Override
	public void init() throws ServletException {
		super.init();
		try {
			ConnectionFactory.init();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
	}

	/**
	 * <p>Webアプリケーション終了時処理</p>
	 * @see org.apache.struts.action.ActionServlet#destroy()
	 */
	@Override
	public void destroy() {
		super.destroy();
	}
}
