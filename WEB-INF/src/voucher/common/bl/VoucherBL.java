package voucher.common.bl;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import common.CommonBL;
import common.MsgResourcesConstants;

import prop.ViewProperties;

import fw.common.date.DateFormatPattern;
import fw.common.date.DateUtility;
import fw.common.db.SQLProperties;
import fw.common.db.dto.CommonDTO;
import fw.common.util.CommonConstants;
import fw.common.util.CommonUtil;
import fw.core.base.TransactionInfo;

import voucher.common.bean.DetailBean;
import voucher.common.bean.VoucherBean;
import voucher.common.dao.VoucherDAO;
import voucher.common.dto.VoucherDTO;

/**
 * 伝票BL
 */
public class VoucherBL {

	/**
	 * <p>伝票BeanMap取得処理</p>
	 *
	 * @param sqlId
	 * @param voucherKind
	 * @param taxVal
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 * @throws ParseException
	 */
	public static Map<String, VoucherBean> getVoucherBeanMap(
			String sqlId, String voucherKind, BigDecimal taxVal) throws IOException, SQLException, ParseException {
		Map<String, VoucherBean> voucherBeanMap = new LinkedHashMap<String, VoucherBean>();

		VoucherDAO dao = new VoucherDAO(TransactionInfo.getConnection());
		String sql = SQLProperties.getInstance().getValue(sqlId);

		List<CommonDTO> retList = dao.select(sql, new ArrayList<Object>());
		for (CommonDTO ret : retList) {
			VoucherDTO dto = (VoucherDTO)ret;

			String voucherNo = CommonUtil.convString(dto.getVoucherNo(),false);

			VoucherBean voucherBean = (voucherBeanMap.isEmpty()) ? null : voucherBeanMap.get(voucherNo);
			if (voucherBean == null) {
				voucherBean = new VoucherBean();
				setBeanForVoucher(voucherBean, dto, voucherNo, voucherKind);
				voucherBeanMap.put(voucherNo, voucherBean);
			}
			setBeanForDeal(voucherBean, dto);
			setBeanForDetail(voucherBean, dto, voucherNo);
			setBeanForPrice(voucherBean, taxVal);
		}

		return voucherBeanMap;
	}

	/**
	 * <p>伝票Bean設定処理</p>
	 *
	 * @param voucherBean
	 * @param dto
	 * @param voucherNo
	 * @param voucherKind
	 */
	private static void setBeanForVoucher(
			VoucherBean voucherBean, VoucherDTO dto, String voucherNo, String voucherKind) {
		voucherBean.setVoucherNo(voucherNo);
		voucherBean.setVoucherKind(voucherKind);
		voucherBean.setVoucherDate((dto.getVoucherDate() == null) ? null :
			DateUtility.getDate(DateFormatPattern.PATTERN_YYYYMMDD, dto.getVoucherDate().getTime()));
		voucherBean.setVoucherName(dto.getVoucherName());
		voucherBean.setPaymentLimit(dto.getPaymentLimit());
		voucherBean.setPaymentPlace(dto.getPaymentPlace());
		voucherBean.setPaymentCondition(dto.getPaymentCondition());
		voucherBean.setExpirationDate(dto.getExpirationDate());
		voucherBean.setMemo(dto.getMemo());
		voucherBean.setEtc1(dto.getEtc1());
		voucherBean.setEtc2(dto.getEtc2());
		voucherBean.setReceiptNo(dto.getReceiptNo());
		voucherBean.setProviso(dto.getProviso());
		voucherBean.setReceiptDate((dto.getReceiptDate() == null) ? null :
			DateUtility.getDate(DateFormatPattern.PATTERN_YYYYMMDD, dto.getReceiptDate().getTime()));
		voucherBean.setDiscount(dto.getDiscount());
	}

	/**
	 * <p>取引先Bean設定処理</p>
	 *
	 * @param bean
	 * @param dto
	 * @throws ParseException
	 */
	private static void setBeanForDeal(VoucherBean voucherBean, VoucherDTO dto) throws ParseException {
		String dealKind = CommonUtil.convString(dto.getDealKind(),false);

		voucherBean.getDealBean().setDealKind(dealKind);
		voucherBean.getDealBean().setDealCode(dto.getDealCode());
		voucherBean.getDealBean().setDealName(dto.getDealName());
		voucherBean.getDealBean().setDealClassCode(CommonUtil.convString(dto.getDealClassCode(),true));
		voucherBean.getDealBean().setDealClassName(dto.getDealClassName());
		voucherBean.getDealBean().setDealStaff(CommonBL.makeName(dto.getDealStaffSei(), dto.getDealStaffName(), false));

		voucherBean.getDealBean().setDealStaffKeisyo(
				CommonBL.convKeisyo(ViewProperties.DISP_VALUE, ViewProperties.VALUE, dto.getDealStaffKeisyo()));

		voucherBean.getDealBean().setDealBusyo(dto.getDealStaffBusyo());
		voucherBean.getDealBean().setTel(dto.getDealTel());
		voucherBean.getDealBean().setPostCode(CommonUtil.makePostCode(dto.getDealPostCode1(),dto.getDealPostCode2()));
		voucherBean.getDealBean().setAddr1(dto.getDealAddr1());
		voucherBean.getDealBean().setAddr2(dto.getDealAddr2());

		if (ViewProperties.getInstance().getValue(
				ViewProperties.DEAL_DIVISION_KAKE, ViewProperties.VALUE).equals(Integer.toString(dto.getDealDivision()))) {
			// 取引区分が「掛」の場合

			// 締日を設定
			voucherBean.getDealBean().setShimebi(
					CommonBL.calcShimebi(
							voucherBean.getVoucherDate(),
							Integer.toString(dto.getShimebiMonthly()),
							Integer.toString(dto.getShimebiKessai())));

			// 決済日を設定
			voucherBean.getDealBean().setKessai(
					CommonBL.calcKessai(
							voucherBean.getVoucherDate(),
							Integer.toString(dto.getKessaiMonthly()),
							Integer.toString(dto.getShimebiKessai())));
		}

		voucherBean.getDealBean().setDealDivision(
				CommonBL.convDealDivision(ViewProperties.DISP_VALUE, ViewProperties.VALUE, dto.getDealDivision()));
		voucherBean.getDealBean().setDealDivisionCode(Integer.toString(dto.getDealDivision()));

		voucherBean.getDealBean().setPriceDivision(CommonUtil.convString(dto.getPriceDivision(),false));

		voucherBean.getDealBean().setTax(
				CommonBL.makeCalcCalcHasu(dto.getTax(), dto.getCalc(), dto.getCalcHasu()));

		voucherBean.getDealBean().setStaffCode(CommonUtil.convString(dto.getStaffCode(),true));
		voucherBean.getDealBean().setStaff(CommonBL.makeName(dto.getStaffSei(), dto.getStaffName(), false));
		voucherBean.getDealBean().setBusyo(dto.getStaffBusyo());
	}

	/**
	 * <p>明細Bean設定処理</p>
	 *
	 * @param voucherBean
	 * @param dto
	 * @param voucherNo
	 */
	private static void setBeanForDetail(VoucherBean voucherBean, VoucherDTO dto, String voucherNo) {
		DetailBean detailBean = new DetailBean();
		detailBean.setVoucherNo(voucherNo);

		String seq = CommonUtil.convString(dto.getSeq(),false);
		detailBean.setSeq(seq);
		detailBean.setSalesNo(CommonUtil.convString(dto.getSalesNo(),false));
		detailBean.setSalesSeq(CommonUtil.convString(dto.getSalesSeq(),false));
		detailBean.setSalesDate((dto.getSalesDate() == null) ? null :
			DateUtility.getDate(DateFormatPattern.PATTERN_YYYYMMDD, dto.getSalesDate().getTime()));
		detailBean.setItemCode(dto.getItemCode());
		detailBean.setItemName(dto.getItemName());
		detailBean.setItemKikaku(dto.getItemKikaku());
		detailBean.setItemCount(CommonUtil.convString(dto.getItemCount(),false));
		detailBean.setItemUnit(dto.getItemUnit());
		detailBean.setItemUnitPrice(dto.getItemUnitPrice().toString());

		// 金額 = 単価 * 数量
		detailBean.setItemPrice(
				calcItemPrice(new BigDecimal(detailBean.getItemUnitPrice()),
						Long.valueOf(Integer.toString(dto.getItemCount()))).toString());

		detailBean.setItemMemo(dto.getItemMemo());
		detailBean.setItemPurchasePrice(dto.getItemPurchasePrice().toString());

		voucherBean.addDetail(detailBean);
	}

	/**
	 * <p>金額関連情報設定</p>
	 *
	 * @param voucherBean
	 * @param taxVal
	 */
	public static void setBeanForPrice(VoucherBean voucherBean, BigDecimal taxVal) {
		int decimalDigit = Integer.parseInt(ViewProperties.getInstance().getValue(
				ViewProperties.DECIMAL_DIGIT, ViewProperties.VALUE));

		// 税抜金額(全商品の金額の合計)
		BigDecimal taxExcludedAmount = new BigDecimal(0);
		// 仕入金額(全商品の仕入金額の合計)
		BigDecimal itemPurchasePriceAmount = new BigDecimal(0);

		for (DetailBean detailBean : voucherBean.getDetailList()) {
			// 税抜金額
			taxExcludedAmount = taxExcludedAmount.add(new BigDecimal(detailBean.getItemPrice()));
			// 仕入金額の合計
			itemPurchasePriceAmount = itemPurchasePriceAmount.add(
					calcItemPurchasePrice(new BigDecimal(detailBean.getItemPurchasePrice()),
							Long.valueOf(detailBean.getItemCount())));
		}

		// 消費税額(金額の合計 * 消費税率)
		BigDecimal tax = taxExcludedAmount.multiply(taxVal);

		// 税込金額(税抜金額 + 消費税額)
		BigDecimal taxIncludedAmount = calcTaxExcludedAmount(taxExcludedAmount, tax);

		// 粗利益(税抜金額 - 仕入金額の合計)
		BigDecimal grossProfit = calcGrossProfit(taxExcludedAmount, itemPurchasePriceAmount);

		// 調整額
		BigDecimal discount = voucherBean.getDiscount();

		// 合計金額(税込金額 + 調整額)
		BigDecimal amount = calcAmount(taxIncludedAmount, discount);

		voucherBean.setTaxExcludedAmount(taxExcludedAmount.setScale(decimalDigit, RoundingMode.DOWN));
		voucherBean.setTax(tax.setScale(decimalDigit, RoundingMode.DOWN));
		voucherBean.setTaxIncludedAmount(taxIncludedAmount.setScale(decimalDigit, RoundingMode.DOWN));
		voucherBean.setGrossProfit(grossProfit.setScale(decimalDigit, RoundingMode.DOWN));
		voucherBean.setAmount(amount.setScale(decimalDigit, RoundingMode.DOWN));

	}

	/**
	 * <p>金額計算</p>
	 *
	 * @param itemUnitPrice
	 * @param itemCount
	 * @return
	 */
	public static BigDecimal calcItemPrice(BigDecimal itemUnitPrice, long itemCount) {
		// 金額 = 単価 * 数量
		return itemUnitPrice.multiply(
				BigDecimal.valueOf(Long.valueOf(itemCount)));
	}

	/**
	 * <p>仕入金額計算</p>
	 *
	 * @param itemPurchasePrice
	 * @param itemCount
	 * @return
	 */
	public static BigDecimal calcItemPurchasePrice(BigDecimal itemPurchasePrice, long itemCount) {
		// 仕入金額 = 仕入単価 * 数量
		return itemPurchasePrice.multiply(
				BigDecimal.valueOf(Long.valueOf(itemCount)));
	}

	/**
	 * <p>税込金額計算</p>
	 *
	 * @return
	 */
	public static BigDecimal calcTaxExcludedAmount(
			BigDecimal taxExcludedAmount, BigDecimal tax) {
		// 税込金額 = 税抜金額 + 消費税額
		return taxExcludedAmount.add(tax);
	}

	/**
	 * <p>粗利益計算</p>
	 *
	 * @param taxExcludedAmount
	 * @param itemPurchasePriceAmount
	 * @return
	 */
	public static BigDecimal calcGrossProfit(
			BigDecimal taxExcludedAmount, BigDecimal itemPurchasePriceAmount) {
		// 粗利益 = 税抜金額 - 仕入金額の合計
		return taxExcludedAmount.subtract(itemPurchasePriceAmount);
	}

	/**
	 * <p>合計金額計算</p>
	 *
	 * @param taxIncludedAmount
	 * @param discount
	 * @return
	 */
	public static BigDecimal calcAmount(
			BigDecimal taxIncludedAmount, BigDecimal discount) {
		// 合計金額 = 税込金額 + 調整額
		return taxIncludedAmount.add(discount);
	}

	/**
	 * <p>商品コード変更有無</p>
	 *
	 * @param voucherBean
	 * @param targetItemCode
	 * @param targetItemIndex
	 * @return
	 */
	public static boolean isChangeItem(
			VoucherBean voucherBean, String targetItemCode, String targetItemIndex) {
		int index = Integer.parseInt(targetItemIndex);
		String beforeItemCode = voucherBean.getDetailList().get(index).getItemCode();

		return
			(beforeItemCode == null && targetItemCode != null) ||
			(beforeItemCode != null && targetItemCode == null) ||
			!(beforeItemCode.equals(targetItemCode));
	}

	/**
	 * <p>商品使用チェック</p>
	 * 伝票明細で該当商品が既に使用されているか確認。
	 *
	 * @param itemCodes
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 */
	public static ActionMessages chkExistItem(Object[] itemCodes) throws IOException, SQLException {

		ActionMessages errors = null;

		List<Object> allParams = new ArrayList<Object>();

		VoucherDAO dao = new VoucherDAO(TransactionInfo.getConnection());

		// 可変部分作成(itemCode IN)
		List<Object> params = new ArrayList<Object>();
		String sqlIn = null;
		StringBuilder sb = new StringBuilder();
		sb.append(CommonConstants.HALF_SPACE);
		sb.append(CommonConstants.KAKKO_START);
		for (int i = 0; i < itemCodes.length; i++) {
			params.add(itemCodes[i]);
			sb.append(CommonConstants.CAMMA);
			sb.append(CommonConstants.QUESTION);
		}
		sb.append(CommonConstants.KAKKO_END);
		sqlIn = sb.toString().replaceFirst(CommonConstants.CAMMA, "");

		// SQL文を統合
		StringBuilder sql = new StringBuilder();
		sql.append(new StringBuilder(SQLProperties.getInstance().getValue(VoucherDAO.SQLID00601)).append(sqlIn));
		sql.append(CommonConstants.HALF_SPACE);
		allParams.addAll(params);
		sql.append("UNION");
		sql.append(CommonConstants.HALF_SPACE);
		sql.append(new StringBuilder(SQLProperties.getInstance().getValue(VoucherDAO.SQLID00602)).append(sqlIn));
		sql.append(CommonConstants.HALF_SPACE);
		allParams.addAll(params);
		sql.append("UNION");
		sql.append(CommonConstants.HALF_SPACE);
		sql.append(new StringBuilder(SQLProperties.getInstance().getValue(VoucherDAO.SQLID00603)).append(sqlIn));
		sql.append(CommonConstants.HALF_SPACE);
		allParams.addAll(params);
		sql.append("UNION");
		sql.append(CommonConstants.HALF_SPACE);
		sql.append(new StringBuilder(SQLProperties.getInstance().getValue(VoucherDAO.SQLID00604)).append(sqlIn));
		sql.append(CommonConstants.HALF_SPACE);
		sql.append(SQLProperties.getInstance().getValue(VoucherDAO.SQLID00605));
		allParams.addAll(params);

		// SQL実行
		StringBuilder items = new StringBuilder();
		for (CommonDTO dto : dao.select(sql.toString(), allParams)) {
			items.append(((VoucherDTO)dto).getItemCode());
			items.append(CommonConstants.CAMMA);
		}

		if (items.length() > 0) {
			// 伝票で使用されている商品[商品コード:{0}]は削除できません。
			errors = new ActionMessages();
			ActionMessage error = new ActionMessage(
					MsgResourcesConstants.VOUCHER_ITEM_ERRMSG_NON_DELETE_ALREADY_USE_ITEM,
					items.substring(0, items.lastIndexOf(CommonConstants.CAMMA)));
			errors.add(ActionMessages.GLOBAL_MESSAGE, error);
		}
		return errors;

	}
}
