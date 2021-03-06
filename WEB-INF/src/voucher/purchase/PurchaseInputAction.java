package voucher.purchase;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedHashMap;
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
import voucher.common.bl.VoucherBL;
import voucher.common.dialog.bl.VoucherItemBL;
import voucher.common.dialog.bl.VoucherSupplierBL;
import voucher.common.docwriter.VoucherDocData;
import voucher.common.docwriter.VoucherDocDataGenerator;
import voucher.purchase.bl.PurchaseBL;
import voucher.purchase.docwriter.PurchaseDocInfo;
import voucher.purchase.docwriter.PurchaseDocWriter;

import common.BizCommonConstants;
import common.MsgResourcesConstants;

import fw.common.docwriter.AbstractDocWriter;
import fw.common.docwriter.DocInfo;
import fw.common.docwriter.PDFWirter;
import fw.common.util.CommonConstants;
import fw.common.util.SystemSessionKey;
import fw.core.base.SystemSessionManager;

/**
 * <p>仕入伝票入力アクション</p>
 */
public class PurchaseInputAction extends CommonVoucherInputAction {

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

		PurchaseForm purchaseForm = (PurchaseForm)form;

		// セッションより伝票Bean取得
		VoucherBean voucherBean = (VoucherBean)
			request.getSession(false).getAttribute(BizCommonConstants.BEAN_NAME_VOUCHER_BEAN);

		// 入力情報反映
		PurchaseBL.reflectInputToVoucherBean(voucherBean, purchaseForm);

		// セッションより、消費税率を取得
		BigDecimal taxValue = ((BasicBean)SystemSessionManager.getValue(
				request.getSession(false), SystemSessionKey.BASIC_INFO)).getTaxVal();

		// 金額計算
		PurchaseBL.calcPrice(voucherBean, taxValue);
		PurchaseBL.calcPrice((PurchaseForm)form);

		if (PurchaseBL.isExistVoucher(purchaseForm.getVoucherNo())) {
			// 更新時
			PurchaseBL.update(purchaseForm);

		} else {
			// 新規登録時
			PurchaseBL.insert(purchaseForm);
		}

		// 伝票BeanMap再取得後、セッションに格納する
		request.getSession(false).setAttribute(
				BizCommonConstants.BEAN_NAME_VOUCHER_BEAN_MAP, PurchaseBL.getVoucherBeanMap(taxValue));

		// 不要となるセッション情報を破棄
		request.getSession(false).removeAttribute(BizCommonConstants.FORM_BEAN_NAME_PURCHASE_FORM);
		request.getSession(false).removeAttribute(BizCommonConstants.BEAN_NAME_VOUCHER_BEAN);
		request.getSession(false).removeAttribute(BizCommonConstants.BEAN_NAME_DEAL_BEAN_MAP);
		request.getSession(false).removeAttribute(BizCommonConstants.BEAN_NAME_DETAIL_BEAN_MAP);

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
		PurchaseBL.reflectInputToVoucherBean(voucherBean, (PurchaseForm)form);

		// セッションより、消費税率を取得
		BigDecimal taxValue = ((BasicBean)SystemSessionManager.getValue(
				request.getSession(false), SystemSessionKey.BASIC_INFO)).getTaxVal();

		// 金額計算
		PurchaseBL.calcPrice(voucherBean, taxValue);
		PurchaseBL.calcPrice((PurchaseForm)form);

		String fileName = new StringBuilder("purchase").append(Calendar.getInstance().getTimeInMillis()).toString();
		byte[] byteAry = outputFile(request.getSession(false), (PurchaseForm)form, fileName);

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
		request.getSession(false).removeAttribute(BizCommonConstants.FORM_BEAN_NAME_PURCHASE_FORM);
		request.getSession(false).removeAttribute(BizCommonConstants.BEAN_NAME_VOUCHER_BEAN);
		request.getSession(false).removeAttribute(BizCommonConstants.BEAN_NAME_DEAL_BEAN_MAP);
		request.getSession(false).removeAttribute(BizCommonConstants.BEAN_NAME_DETAIL_BEAN_MAP);
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
		String targetDealKind = (String)request.getParameter("targetDealKind");
		String targetVoucherDate = (String)request.getParameter("targetVoucherDate");

		if (voucherBean.getDealBean().getDealCode() != null &&
				targetDealCode.equals(voucherBean.getDealBean().getDealCode())) {
			// 取引先コードが変更されていない場合
			return mapping.findForward(BizCommonConstants.FWD_REDISP);
		}

		Map<String, DealBean> dealBeanMap = new LinkedHashMap<String, DealBean>();
		if (targetDealKind.equals(
				ViewProperties.getInstance().getValue(ViewProperties.DEAL_KIND_SUPPLIER, ViewProperties.VALUE))) {
			// 取引区分が仕入先の場合

			// 対象となる取引先Beanを取得する
			dealBeanMap = VoucherSupplierBL.getDealBeanMap(targetDealCode, targetVoucherDate, false);
		} else {
			throw new IllegalArgumentException();
		}

		DealBean newDealBean = dealBeanMap.get(targetDealCode);

		if (dealBeanMap.size() == 0) {
			ActionMessages messages = new ActionMessages();
			ActionMessage message = new ActionMessage(
					MsgResourcesConstants.VOUCHER_DEAL_ERRMSG_NOEXIST, targetDealCode);
			messages.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, messages);

			newDealBean = new DealBean();
			newDealBean.setDealKind(ViewProperties.getInstance().getValue(
					ViewProperties.DEAL_KIND_SUPPLIER, ViewProperties.DISP_VALUE));
		}

		// セッションの取引先Beanを差し替える
		voucherBean.setDealBean(newDealBean);

		// 取引先Beanの内容を仕入伝票FormBeanへ反映
		PurchaseBL.reflectDealBeanToPurchaseForm((PurchaseForm)form, newDealBean);

		// セッションの明細Beanをクリア
		voucherBean.getDetailList().clear();

		// フォームの明細をクリア
		((PurchaseForm)form).getDetailList().clear();

		// 金額関連をクリア
		PurchaseBL.clearPriceInfo(voucherBean, (PurchaseForm)form);

		return mapping.findForward(BizCommonConstants.FWD_REDISP);
	}

	/**
	 * 「商品コード」フォーカスアウト時
	 *
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward searchItem(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		String targetItemIndex = (String)request.getParameter("targetItemIndex");
		String targetItemCode = (String)request.getParameter("targetItemCode");

		// セッションより伝票Bean取得
		VoucherBean voucherBean = (VoucherBean)
			request.getSession(false).getAttribute(BizCommonConstants.BEAN_NAME_VOUCHER_BEAN);

		// 入力情報反映
		PurchaseBL.reflectInputToVoucherBean(voucherBean, (PurchaseForm)form);

		// セッションより、消費税率を取得
		BigDecimal taxValue = ((BasicBean)SystemSessionManager.getValue(
				request.getSession(false), SystemSessionKey.BASIC_INFO)).getTaxVal();

		if (!voucherBean.getDetailList().isEmpty() &&
				!VoucherBL.isChangeItem(voucherBean, targetItemCode, targetItemIndex)) {
			// 商品コードが変更されていない場合

			// 金額再計算
			PurchaseBL.calcPrice(voucherBean, taxValue);
			PurchaseBL.calcPrice((PurchaseForm)form);

			return mapping.findForward(BizCommonConstants.FWD_REDISP);
		}

		// 対象商品コードの明細Beanを取得
		Map<String, DetailBean> detailBeanMap = VoucherItemBL.getDetailBeanMap(voucherBean, targetItemCode, false);
		DetailBean newDetailBean = detailBeanMap.get(targetItemCode);

		if (detailBeanMap.size() == 0) {
			ActionMessages messages = new ActionMessages();
			ActionMessage message = new ActionMessage(
					MsgResourcesConstants.VOUCHER_ITEM_ERRMSG_NOEXIST, targetItemCode);
			messages.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, messages);

			newDetailBean = new DetailBean();
		}

		// 対象となる明細Beanを変更する
		voucherBean.getDetailList().set(Integer.parseInt(targetItemIndex), newDetailBean);
		// 明細Beanの内容を仕入伝票FormBeanへ反映
		PurchaseBL.reflectDetailBeanToPurchaseForm(
				(PurchaseForm)form, Integer.parseInt(targetItemIndex), newDetailBean);

		// 金額再計算
		PurchaseBL.calcPrice(voucherBean, taxValue);
		PurchaseBL.calcPrice((PurchaseForm)form);

		return mapping.findForward(BizCommonConstants.FWD_REDISP);
	}

	/**
	 * 「削除」ボタン押下時
	 *
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward deleteItem(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		PurchaseForm purchaseForm = (PurchaseForm)form;
		List<String> delItemIndexList = new ArrayList<String>();
		for (Map<String, String> detail : purchaseForm.getDetailList()) {
			delItemIndexList.add(detail.get("itemIndex"));
		}

		// セッションより伝票Bean取得
		VoucherBean voucherBean = (VoucherBean)
			request.getSession(false).getAttribute(BizCommonConstants.BEAN_NAME_VOUCHER_BEAN);

		// 入力情報反映
		PurchaseBL.reflectInputToVoucherBean(voucherBean, (PurchaseForm)form);

		// セッションの明細Beanより選択した商品を削除する
		int index = 0;
		Iterator<DetailBean> itrSession = voucherBean.getDetailList().iterator();
		while (itrSession.hasNext()) {
			itrSession.next();
			if (delItemIndexList.contains(Integer.toString(index++))) {
				itrSession.remove();
			}
		}

		// 仕入伝票FormBeanより選択した商品を削除する
		index = 0;
		Iterator<Map<String, String>> itrForm = purchaseForm.getDetailList().iterator();
		while (itrForm.hasNext()) {
			itrForm.next();
			if (delItemIndexList.contains(Integer.toString(index++))) {
				itrForm.remove();
			}
		}

		// セッションより、消費税率を取得
		BigDecimal taxValue = ((BasicBean)SystemSessionManager.getValue(
				request.getSession(false), SystemSessionKey.BASIC_INFO)).getTaxVal();

		// 金額再計算
		PurchaseBL.calcPrice(voucherBean, taxValue);
		PurchaseBL.calcPrice((PurchaseForm)form);

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

		// セッションの取引先Beanの値を仕入伝票FormBeanに反映
		PurchaseBL.reflectDealBeanToPurchaseForm((PurchaseForm)form, voucherBean.getDealBean());

		// セッションの取引先Beanをクリア
		voucherBean.getDetailList().clear();

		// フォームの明細をクリア
		((PurchaseForm)form).getDetailList().clear();

		// 金額関連をクリア
		PurchaseBL.clearPriceInfo(voucherBean, (PurchaseForm)form);

		return mapping.findForward(BizCommonConstants.FWD_REDISP);
	}

	/**
	 * 再表示時(明細)
	 *
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward redispDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		// セッションより伝票Bean取得
		VoucherBean voucherBean = (VoucherBean)
			request.getSession(false).getAttribute(BizCommonConstants.BEAN_NAME_VOUCHER_BEAN);

		// 入力情報反映
		PurchaseBL.reflectInputToVoucherBean(voucherBean, (PurchaseForm)form);

		// 商品ダイアログで選択した商品(明細Bean)を、仕入伝票FormBeanに追加
		int beforeSize = ((PurchaseForm)form).getDetailList().size();
		int nowSize = voucherBean.getDetailList().size();
		for (int i = beforeSize; i < nowSize; i++) {
			PurchaseBL.reflectDetailBeanToPurchaseForm(
					(PurchaseForm)form, voucherBean.getDetailList().get(i));
		}

		// セッションより、消費税率を取得
		BigDecimal taxValue = ((BasicBean)SystemSessionManager.getValue(
				request.getSession(false), SystemSessionKey.BASIC_INFO)).getTaxVal();

		// 金額再計算
		PurchaseBL.calcPrice(voucherBean, taxValue);
		PurchaseBL.calcPrice((PurchaseForm)form);

		return mapping.findForward(BizCommonConstants.FWD_REDISP);
	}

	/**
	 * 帳票ファイル作成
	 *
	 * @param session
	 * @param purchaseForm
	 * @param fileName
	 * @return
	 * @throws Exception
	 */
	private byte[] outputFile(HttpSession session, PurchaseForm purchaseForm, String fileName) throws Exception {

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

			// 仕入伝票文書ファイルデータ作成
			VoucherDocData excelData = VoucherDocDataGenerator.generate(basicBean, voucherBean);

			ViewProperties viewProp = ViewProperties.getInstance();
			String printKind = purchaseForm.getPrintKind();
			if (viewProp.getValue(
					ViewProperties.PRINT_KIND_PURCHASE, ViewProperties.VALUE).equals(printKind)) {
				excelInfo = new PurchaseDocInfo();
				excelWriter = new PurchaseDocWriter(excelInfo);
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
