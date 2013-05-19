package voucher.bill;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import master.basic.bean.BasicBean;

import org.apache.commons.io.FileUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import prop.CommonProperties;
import prop.ViewProperties;

import voucher.common.CommonVoucherInputAction;
import voucher.common.bean.DealBean;
import voucher.common.bean.DetailBean;
import voucher.common.bean.VoucherBean;
import voucher.common.docwriter.VoucherDocData;
import voucher.common.docwriter.VoucherDocDataGenerator;
import voucher.bill.bl.BillBL;
import voucher.bill.dialog.bean.BillCustomerBean;
import voucher.bill.dialog.bl.BillCustomerBL;
import voucher.bill.docwriter.BillDetailDocInfo;
import voucher.bill.docwriter.BillDetailDocWriter;

import common.BizCommonConstants;
import common.MsgResourcesConstants;

import fw.common.docwriter.AbstractDocWriter;
import fw.common.docwriter.DocInfo;
import fw.common.docwriter.PDFWirter;
import fw.common.util.CommonConstants;
import fw.common.util.SystemSessionKey;
import fw.core.base.SystemSessionManager;

/**
 * <p>請求書入力アクション</p>
 */
public class BillInputAction extends CommonVoucherInputAction {

	/**
	 * 「更新」ボタン押下時
	 *
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		BillForm billForm = (BillForm)form;

		// セッションより伝票Bean取得
		VoucherBean voucherBean = (VoucherBean)
			request.getSession(false).getAttribute(BizCommonConstants.BEAN_NAME_VOUCHER_BEAN);

		// 入力情報反映
		BillBL.reflectInputToVoucherBean(voucherBean, billForm);

		// セッションより、消費税率を取得
		BigDecimal taxValue = ((BasicBean)SystemSessionManager.getValue(
				request.getSession(false), SystemSessionKey.BASIC_INFO)).getTaxVal();

		// 金額計算
		BillBL.calcPrice(voucherBean, taxValue);

		if (BillBL.isExistVoucher(billForm.getVoucherNo())) {
			// 更新時
			BillBL.update(billForm, voucherBean.getDetailList());

		} else {
			// 新規登録時
			BillBL.insert(billForm, voucherBean.getDetailList());
		}

		// 伝票BeanMap再取得後、セッションに格納する
		request.getSession(false).setAttribute(
				BizCommonConstants.BEAN_NAME_VOUCHER_BEAN_MAP, BillBL.getVoucherBeanMap(taxValue));

		// 不要となるセッション情報を破棄
		request.getSession(false).removeAttribute(BizCommonConstants.FORM_BEAN_NAME_BILL_FORM);
		request.getSession(false).removeAttribute(BizCommonConstants.BEAN_NAME_VOUCHER_BEAN);
		request.getSession(false).removeAttribute(BizCommonConstants.BEAN_NAME_BILL_CUSTOMER_BEAN_MAP);

		return mapping.findForward(BizCommonConstants.FWD_UPDATE);
	}

	/**
	 * 「印刷プレビュー」ボタン押下時
	 *
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward print(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		// セッションより伝票Bean取得
		VoucherBean voucherBean = (VoucherBean)
			request.getSession(false).getAttribute(BizCommonConstants.BEAN_NAME_VOUCHER_BEAN);

		// 入力情報反映
		BillBL.reflectInputToVoucherBean(voucherBean, (BillForm)form);

		// セッションより、消費税率を取得
		BigDecimal taxValue = ((BasicBean)SystemSessionManager.getValue(
				request.getSession(false), SystemSessionKey.BASIC_INFO)).getTaxVal();

		// 金額計算
		BillBL.calcPrice(voucherBean, taxValue);

		String fileName = new StringBuilder("bill").append(Calendar.getInstance().getTimeInMillis()).toString();
		byte[] byteAry = outputFile(request.getSession(false), (BillForm)form, fileName);

		// 帳票プレビュー表示
		preview(response, byteAry);

		return null;
	}

	/**
	 * 「戻る」ボタン押下時
	 *
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward back(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 不要となるセッション情報を破棄
		request.getSession(false).removeAttribute(BizCommonConstants.FORM_BEAN_NAME_BILL_FORM);
		request.getSession(false).removeAttribute(BizCommonConstants.BEAN_NAME_VOUCHER_BEAN);
		request.getSession(false).removeAttribute(BizCommonConstants.BEAN_NAME_BILL_CUSTOMER_BEAN_MAP);
		return mapping.findForward(BizCommonConstants.FWD_BACK);
	}

	/**
	 * 「取引先コード」フォーカスアウト時
	 *
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward searchDeal(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		// セッションより伝票Bean取得
		VoucherBean voucherBean = (VoucherBean)
			request.getSession(false).getAttribute(BizCommonConstants.BEAN_NAME_VOUCHER_BEAN);

		String targetDealCode = (String)request.getParameter("targetDealCode");
		String targetVoucherDate = (String)request.getParameter("targetVoucherDate");

		if (voucherBean.getDealBean().getDealCode() != null &&
				targetDealCode.equals(voucherBean.getDealBean().getDealCode())) {
			// 取引先コードが変更されていない場合
			return mapping.findForward(BizCommonConstants.FWD_REDISP);
		}

		Map<String, BillCustomerBean> billCustomerBeanMap =
			BillCustomerBL.getBillCustomerBeanMap(targetDealCode, targetVoucherDate, false);

		DealBean newDealBean = null;
		List<DetailBean> newDetailList = null;

		if (billCustomerBeanMap.size() == 0) {
			ActionMessages messages = new ActionMessages();
			ActionMessage message = new ActionMessage(
					MsgResourcesConstants.VOUCHER_DEAL_ERRMSG_NOEXIST, targetDealCode);
			messages.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, messages);

			newDealBean = new DealBean();
			newDealBean.setDealKind(ViewProperties.getInstance().getValue(
					ViewProperties.DEAL_KIND_CUSTOMER, ViewProperties.DISP_VALUE));

			newDetailList = new ArrayList<DetailBean>();
		} else {
			newDealBean = billCustomerBeanMap.get(targetDealCode).getDealBean();
			newDetailList = billCustomerBeanMap.get(targetDealCode).getDetailList();
		}

		// セッションの取引先Beanを差し替える
		voucherBean.setDealBean(newDealBean);

		// 取引先Beanの内容を請求書FormBeanへ反映
		BillBL.reflectDealBeanToBillForm((BillForm)form, newDealBean);

		// セッションの明細Beanを差し替える
		voucherBean.setDetailList(newDetailList);

		// 金額再計算
		BigDecimal taxVal = ((BasicBean)SystemSessionManager.getValue(
				request.getSession(false), SystemSessionKey.BASIC_INFO)).getTaxVal();
		BillBL.calcPrice(voucherBean, taxVal);

		return mapping.findForward(BizCommonConstants.FWD_REDISP);
	}

	/**
	 * 再表示時(取引先)
	 *
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward redispDeal(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		// セッションより伝票Bean取得
		VoucherBean voucherBean = (VoucherBean)
			request.getSession(false).getAttribute(BizCommonConstants.BEAN_NAME_VOUCHER_BEAN);

		// セッションの取引先Beanの値を請求書FormBeanに反映
		BillBL.reflectDealBeanToBillForm((BillForm)form, voucherBean.getDealBean());

		// 金額再計算
		BigDecimal taxVal = ((BasicBean)SystemSessionManager.getValue(
				request.getSession(false), SystemSessionKey.BASIC_INFO)).getTaxVal();
		BillBL.calcPrice(voucherBean, taxVal);

		return mapping.findForward(BizCommonConstants.FWD_REDISP);
	}

	/**
	 * 帳票ファイル作成
	 *
	 * @param session
	 * @param billForm
	 * @param fileName
	 * @return
	 * @throws Exception
	 */
	private byte[] outputFile(HttpSession session, BillForm billForm, String fileName) throws Exception {

		// エクセルファイル
		String excelFileName = new StringBuilder(
				CommonProperties.getInstance().getValue(CommonProperties.TMP_DIR))
					.append(File.separator)
					.append(fileName)
					.append(CommonConstants.PERIOD)
					.append(CommonConstants.EXTENTIONS_XLS).toString();

		// PDFファイル
		String pdfFileName =
				new StringBuilder(CommonProperties.getInstance().getValue(CommonProperties.TMP_DIR))
					.append(File.separator)
					.append(fileName)
					.append(CommonConstants.PERIOD)
					.append(CommonConstants.EXTENTIONS_PDF).toString();

		AbstractDocWriter excelWriter = null;
		DocInfo excelInfo = null;
		PDFWirter pdfWriter = null;

		try {
			/*
			 * エクセルファイル作成
			 */

			// 各種セッション情報取得
			BasicBean basicBean = (BasicBean)SystemSessionManager.getValue(session, SystemSessionKey.BASIC_INFO);
			VoucherBean voucherBean = (VoucherBean)session.getAttribute(BizCommonConstants.BEAN_NAME_VOUCHER_BEAN);

			// 請求書文書ファイルデータ作成
			VoucherDocData excelData = VoucherDocDataGenerator.generate(basicBean, voucherBean);

			ViewProperties viewProp = ViewProperties.getInstance();
			String printKind = billForm.getPrintKind();
			if (viewProp.getValue(
					ViewProperties.PRINT_KIND_BILL_DETAIL, ViewProperties.VALUE).equals(printKind)) {
				excelInfo = new BillDetailDocInfo();
				excelWriter = new BillDetailDocWriter(excelInfo);
			} else {
				throw new IllegalArgumentException("printKind = " + printKind);
			}

			excelInfo.setDelFlg(true);
			excelInfo.setOutFileName(excelFileName);
			excelInfo.setDocData(excelData);
			excelWriter.write();

			/*
			 * PDFファイル作成
			 */

			DocInfo pdfInfo = new DocInfo();
			pdfInfo.setDelFlg(true);
			pdfInfo.setInFileName(excelFileName);
			pdfInfo.setOutFileName(pdfFileName);

			// PDFファイル出力処理
			pdfWriter = new PDFWirter(pdfInfo);
			pdfWriter.write();

			byte[] byteAry = FileUtils.readFileToByteArray(new File(pdfFileName));
			return byteAry;

		} catch (Exception e) {
			throw e;
		} finally {
			// 後処理
			if (excelWriter != null) excelWriter.terminate();
			if (pdfWriter != null) pdfWriter.terminate();
		}
	}

}
