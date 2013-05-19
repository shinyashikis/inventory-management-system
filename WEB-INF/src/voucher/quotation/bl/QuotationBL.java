package voucher.quotation.bl;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fw.common.db.SQLProperties;
import fw.common.db.dto.CommonDTO;
import fw.common.util.CommonConstants;
import fw.core.base.TransactionInfo;

import prop.ViewProperties;

import voucher.common.bean.DealBean;
import voucher.common.bean.DetailBean;
import voucher.common.bean.VoucherBean;
import voucher.common.bl.VoucherBL;
import voucher.common.dao.VoucherDAO;
import voucher.quotation.QuotationForm;
import voucher.quotation.dao.TQuotationDAO;
import voucher.quotation.dao.TQuotationDealDAO;
import voucher.quotation.dao.TQuotationDetailsDAO;
import voucher.quotation.dto.TQuotationDTO;

/**
 * 見積書BL
 */
public class QuotationBL {

	/**
	 * <p>伝票BeanMap取得処理</p>
	 *
	 * @param taxVal
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 * @throws RuntimeException
	 * @throws ParseException
	 */
	public static Map<String, VoucherBean> getVoucherBeanMap(BigDecimal taxVal)
		throws IOException, SQLException, ParseException, RuntimeException {
		// DBより取得後、伝票BeanMapとして返却する
		return VoucherBL.getVoucherBeanMap(
				VoucherDAO.SQLID001,
				ViewProperties.getInstance().getValue(ViewProperties.VOUCHER_KIND_QUOTATION, ViewProperties.VALUE),
				taxVal);
	}

	/**
	 * <p>最新伝票番号(見積書)取得</p>
	 *
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 */
	public static String getNewVoucherNo() throws IOException, SQLException {
		String maxVoucherNo = null;

		TQuotationDAO dao = new TQuotationDAO(TransactionInfo.getConnection());
		String sql = SQLProperties.getInstance().getValue(TQuotationDAO.SQLID001);
		List<CommonDTO> retList = dao.select(sql, new ArrayList<Object>());
		for (CommonDTO ret : retList) {
			TQuotationDTO dto = (TQuotationDTO)ret;
			Integer voucherNo = dto.getVoucherNo();
			if (voucherNo == null || voucherNo == 0) {
				maxVoucherNo = "1";
			} else {
				maxVoucherNo = Integer.toString(voucherNo + 1);
			}
		}

		return maxVoucherNo;
	}

	/**
	 * <p>伝票(見積書)存在チェック</p>
	 *
	 * @param voucherNo
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 */
	public static boolean isExistVoucher(String voucherNo) throws IOException, SQLException {

		TQuotationDAO dao = new TQuotationDAO(TransactionInfo.getConnection());
		String sql = SQLProperties.getInstance().getValue(TQuotationDAO.SQLID002);
		List<Object> params = new ArrayList<Object>();
		params.add(voucherNo);

		List<CommonDTO> retList = dao.select(sql, params);

		Integer val = null;
		for (CommonDTO ret : retList) {
			TQuotationDTO dto = (TQuotationDTO)ret;
			val = dto.getVoucherNo();
			break;
		}

		return (val != null);
	}

	/**
	 * <p>追加処理</p>
	 *
	 * @param quotationForm
	 * @throws IOException
	 * @throws SQLException
	 */
	public static void insert(QuotationForm quotationForm) throws IOException, SQLException {
		// 見積書テーブル追加処理
		insertQuotation(quotationForm);
		// 見積書取引テーブル追加処理
		insertQuotationDeal(quotationForm);
		// 見積書明細テーブル追加処理
		insertQuotationDetails(quotationForm);
	}

	/**
	 * <p>更新処理</p>
	 *
	 * @param quotationForm
	 * @throws IOException
	 * @throws SQLException
	 */
	public static void update(QuotationForm quotationForm) throws IOException, SQLException {
		// 見積書テーブル更新処理
		updateQuotation(quotationForm);
		// 見積書取引テーブル更新処理
		updateQuotationDeal(quotationForm);
		// 見積書明細テーブル削除処理
		deleteQuotationDetails(quotationForm);
		// 見積書明細テーブル追加処理
		insertQuotationDetails(quotationForm);
	}

	/**
	 * <p>更新処理</p>
	 *
	 * @param voucherNo
	 * @throws IOException
	 * @throws SQLException
	 */
	public static void delete(String[] voucherNo) throws IOException, SQLException {
		// 見積書テーブル削除処理
		deleteQuotation(voucherNo);
		// 見積書取引テーブル削除処理
		deleteQuotationDeal(voucherNo);
		// 見積書明細テーブル削除処理
		deleteQuotationDetails(voucherNo);
	}

	/**
	 * <p>見積書テーブル追加処理</p>
	 *
	 * @param quotationForm
	 * @throws IOException
	 * @throws SQLException
	 */
	public static void insertQuotation(QuotationForm quotationForm) throws IOException, SQLException {
		TQuotationDAO dao = new TQuotationDAO(TransactionInfo.getConnection());
		String sql = SQLProperties.getInstance().getValue(TQuotationDAO.SQLID003);

		List<Object> params = new ArrayList<Object>();
		params.add(quotationForm.getVoucherNo());
		params.add(quotationForm.getVoucherDate());
		params.add(quotationForm.getDiscount());
		params.add(quotationForm.getVoucherName());
		params.add(quotationForm.getPaymentLimit());
		params.add(quotationForm.getPaymentPlace());
		params.add(quotationForm.getPaymentCondition());
		params.add(quotationForm.getExpirationDate());
		params.add(quotationForm.getMemo());

		dao.update(sql, params);
	}

	/**
	 * <p>見積書取引テーブル追加処理</p>
	 *
	 * @param quotationForm
	 * @throws IOException
	 * @throws SQLException
	 */
	public static void insertQuotationDeal(QuotationForm quotationForm) throws IOException, SQLException {

		TQuotationDealDAO dao = new TQuotationDealDAO(TransactionInfo.getConnection());
		String sql = SQLProperties.getInstance().getValue(TQuotationDealDAO.SQLID001);

		List<Object> params = new ArrayList<Object>();
		params.add(quotationForm.getVoucherNo());
		params.add(quotationForm.getDealKind());
		params.add(quotationForm.getDealCode());
		dao.update(sql, params);
	}

	/**
	 * <p>見積書明細テーブル追加処理</p>
	 *
	 * @param quotationForm
	 * @throws IOException
	 * @throws SQLException
	 */
	public static void insertQuotationDetails(QuotationForm quotationForm) throws IOException, SQLException {

		TQuotationDetailsDAO dao = new TQuotationDetailsDAO(TransactionInfo.getConnection());
		String sql = SQLProperties.getInstance().getValue(TQuotationDetailsDAO.SQLID001);
		List<Object> params = null;

		int seq = 0;
		for (Map<String, String> map : quotationForm.getDetailList()) {
			params = new ArrayList<Object>();
			params.add(quotationForm.getVoucherNo());
			params.add(seq++);
			params.add(map.get(QuotationForm.DETAIL_KEY_ITEM_CODE));
			params.add(map.get(QuotationForm.DETAIL_KEY_ITEM_UNIT_PRICE));
			params.add(map.get(QuotationForm.DETAIL_KEY_ITEM_COUNT));
			params.add(map.get(QuotationForm.DETAIL_KEY_ITEM_MEMO));
			dao.update(sql, params);
		}

	}

	/**
	 * <p>見積書テーブル更新処理</p>
	 *
	 * @param quotationForm
	 * @throws IOException
	 * @throws SQLException
	 */
	public static void updateQuotation(QuotationForm quotationForm) throws IOException, SQLException {

		TQuotationDAO dao = new TQuotationDAO(TransactionInfo.getConnection());
		String sql = SQLProperties.getInstance().getValue(TQuotationDAO.SQLID004);

		List<Object> params = new ArrayList<Object>();
		params.add(quotationForm.getVoucherDate());
		params.add(quotationForm.getDiscount());
		params.add(quotationForm.getVoucherName());
		params.add(quotationForm.getPaymentLimit());
		params.add(quotationForm.getPaymentPlace());
		params.add(quotationForm.getPaymentCondition());
		params.add(quotationForm.getExpirationDate());
		params.add(quotationForm.getMemo());
		params.add(quotationForm.getVoucherNo());
		dao.update(sql, params);

	}

	/**
	 * <p>見積書取引テーブル更新処理</p>
	 *
	 * @param quotationForm
	 * @throws IOException
	 * @throws SQLException
	 */
	public static void updateQuotationDeal(QuotationForm quotationForm) throws IOException, SQLException {

		TQuotationDealDAO dao = new TQuotationDealDAO(TransactionInfo.getConnection());
		String sql = SQLProperties.getInstance().getValue(TQuotationDealDAO.SQLID002);

		List<Object> params = new ArrayList<Object>();
		params.add(quotationForm.getDealKind());
		params.add(quotationForm.getDealCode());
		params.add(quotationForm.getVoucherNo());
		dao.update(sql, params);

	}

	/**
	 * <p>見積書テーブル削除処理</p>
	 *
	 * @param voucherNo
	 * @throws IOException
	 * @throws SQLException
	 */
	public static void deleteQuotation(String[] voucherNo) throws IOException, SQLException {

		List<Object> params = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder(SQLProperties.getInstance().getValue(TQuotationDAO.SQLID005));
		sql.append(CommonConstants.HALF_SPACE);
		sql.append(CommonConstants.KAKKO_START);
		for (int i = 0; i < voucherNo.length; i++) {
			params.add(voucherNo[i]);
			sql.append(CommonConstants.CAMMA);
			sql.append(CommonConstants.QUESTION);
		}
		sql.append(CommonConstants.KAKKO_END);

		TQuotationDAO dao = new TQuotationDAO(TransactionInfo.getConnection());
		dao.delete(sql.toString().replaceFirst(CommonConstants.CAMMA, ""), params);
	}

	/**
	 * <p>見積書取引テーブル削除処理</p>
	 *
	 * @param voucherNo
	 * @throws IOException
	 * @throws SQLException
	 */
	public static void deleteQuotationDeal(String[] voucherNo) throws IOException, SQLException {

		List<Object> params = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder(SQLProperties.getInstance().getValue(TQuotationDealDAO.SQLID003));
		sql.append(CommonConstants.HALF_SPACE);
		sql.append(CommonConstants.KAKKO_START);
		for (int i = 0; i < voucherNo.length; i++) {
			params.add(voucherNo[i]);
			sql.append(CommonConstants.CAMMA);
			sql.append(CommonConstants.QUESTION);
		}
		sql.append(CommonConstants.KAKKO_END);

		TQuotationDealDAO dao = new TQuotationDealDAO(TransactionInfo.getConnection());
		dao.delete(sql.toString().replaceFirst(CommonConstants.CAMMA, ""), params);
	}

	/**
	 * <p>見積書明細テーブル削除処理</p>
	 *
	 * @param quotationForm
	 * @throws IOException
	 * @throws SQLException
	 */
	public static void deleteQuotationDetails(QuotationForm quotationForm) throws IOException, SQLException {
		deleteQuotationDetails(new String[]{quotationForm.getVoucherNo()});
	}

	/**
	 * <p>見積書明細テーブル削除処理</p>
	 *
	 * @param voucherNo
	 * @throws IOException
	 * @throws SQLException
	 */
	public static void deleteQuotationDetails(String[] voucherNo) throws IOException, SQLException {

		List<Object> params = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder(SQLProperties.getInstance().getValue(TQuotationDetailsDAO.SQLID002));
		sql.append(CommonConstants.HALF_SPACE);
		sql.append(CommonConstants.KAKKO_START);
		for (int i = 0; i < voucherNo.length; i++) {
			params.add(voucherNo[i]);
			sql.append(CommonConstants.CAMMA);
			sql.append(CommonConstants.QUESTION);
		}
		sql.append(CommonConstants.KAKKO_END);

		TQuotationDetailsDAO dao = new TQuotationDetailsDAO(TransactionInfo.getConnection());
		dao.delete(sql.toString().replaceFirst(CommonConstants.CAMMA, ""), params);
	}

	/**
	 * <p>伝票Bean反映</p>
	 * 伝票Beanの情報を見積書FormBeanへ反映
	 *
	 * @param quotationForm
	 * @param voucherBean
	 * @throws Exception
	 */
	public static void reflectVoucherBeanToQuotationForm(
			QuotationForm quotationForm, VoucherBean voucherBean) throws Exception {
		quotationForm.setVoucherNo(voucherBean.getVoucherNo());
		quotationForm.setVoucherDate(voucherBean.getVoucherDate());
		quotationForm.setVoucherName(voucherBean.getVoucherName());
		quotationForm.setPaymentLimit(voucherBean.getPaymentLimit());
		quotationForm.setPaymentPlace(voucherBean.getPaymentPlace());
		quotationForm.setPaymentCondition(voucherBean.getPaymentCondition());
		quotationForm.setExpirationDate(voucherBean.getExpirationDate());
		quotationForm.setMemo(voucherBean.getMemo());
		quotationForm.setDiscount(
				(voucherBean.getDiscount() == null) ? null :
						voucherBean.getDiscount().toString());
		reflectDealBeanToQuotationForm(quotationForm, voucherBean.getDealBean());
		reflectDetailBeanToQuotationForm(quotationForm, voucherBean.getDetailList());
	}

	/**
	 * <p>取引先Bean反映</p>
	 * 取引先Beanの情報を見積書FormBeanへ反映
	 *
	 * @param quotationForm
	 * @param dealBean
	 * @throws Exception
	 */
	public static void reflectDealBeanToQuotationForm(
			QuotationForm quotationForm, DealBean dealBean) throws Exception {
		quotationForm.setDealCode(dealBean.getDealCode());
		quotationForm.setDealKind(dealBean.getDealKind());
	}

	/**
	 * <p>明細Bean反映</p>
	 * 明細Beanの情報を見積書FormBeanへ反映
	 *
	 * @param quotationForm
	 * @param detailBeanList
	 * @throws Exception
	 */
	public static void reflectDetailBeanToQuotationForm(
			QuotationForm quotationForm, List<DetailBean> detailBeanList) throws Exception {
		for (DetailBean detailBean : detailBeanList) {
			reflectDetailBeanToQuotationForm(quotationForm, detailBean);
		}
	}

	/**
	 * <p>明細Bean反映</p>
	 * 明細Beanの情報を見積書FormBeanへ反映
	 *
	 * @param quotationForm
	 * @param detailBean
	 * @throws Exception
	 */
	public static void reflectDetailBeanToQuotationForm(
			QuotationForm quotationForm, DetailBean detailBean) throws Exception {
		quotationForm.setDetail(createDetailMap(detailBean));
	}

	/**
	 * <p>明細Bean反映</p>
	 * 明細Beanの情報を見積書FormBeanへ反映
	 *
	 * @param quotationForm
	 * @param itemIndex
	 * @param detailBean
	 * @throws Exception
	 */
	public static void reflectDetailBeanToQuotationForm(
			QuotationForm quotationForm, int itemIndex, DetailBean detailBean) throws Exception {
		quotationForm.setDetail(itemIndex, createDetailMap(detailBean));
	}

	/**
	 * <p>明細Map作成</p>
	 * 明細Beanの情報を元に明細Mapを作成
	 *
	 * @param detailBean
	 * @return
	 */
	private static Map<String, String> createDetailMap(DetailBean detailBean) {
		Map<String, String> detailMap = new HashMap<String, String>();
		detailMap.put(QuotationForm.DETAIL_KEY_ITEM_CODE, detailBean.getItemCode());
		detailMap.put(QuotationForm.DETAIL_KEY_ITEM_NAME, detailBean.getItemName());
		detailMap.put(QuotationForm.DETAIL_KEY_ITEM_KIKAKU, detailBean.getItemKikaku());
		detailMap.put(QuotationForm.DETAIL_KEY_ITEM_UNIT, detailBean.getItemUnit());
		detailMap.put(QuotationForm.DETAIL_KEY_ITEM_PRICE, detailBean.getItemPrice());
		detailMap.put(QuotationForm.DETAIL_KEY_ITEM_COUNT, detailBean.getItemCount());
		detailMap.put(QuotationForm.DETAIL_KEY_ITEM_UNIT_PRICE, detailBean.getItemUnitPrice());
		detailMap.put(QuotationForm.DETAIL_KEY_ITEM_MEMO, detailBean.getItemMemo());
		detailMap.put(QuotationForm.DETAIL_KEY_ITEM_PURCHASE_PRICE, detailBean.getItemPurchasePrice());
		return detailMap;
	}

	/**
	 * <p>入力情報反映</p>
	 * 画面入力情報を伝票Beanへ反映
	 *
	 * @param voucherBean
	 * @param quotationForm
	 * @throws Exception
	 */
	public static void reflectInputToVoucherBean(
			VoucherBean voucherBean, QuotationForm quotationForm) throws Exception {

		// 見積書FormBeanが保持する値を伝票Beanに設定
		voucherBean.setVoucherNo(quotationForm.getVoucherNo());
		voucherBean.setVoucherDate(quotationForm.getVoucherDate());
		voucherBean.setVoucherName(quotationForm.getVoucherName());
		voucherBean.setPaymentLimit(quotationForm.getPaymentLimit());
		voucherBean.setPaymentPlace(quotationForm.getPaymentPlace());
		voucherBean.setPaymentCondition(quotationForm.getPaymentCondition());
		voucherBean.setExpirationDate(quotationForm.getExpirationDate());
		voucherBean.setMemo(quotationForm.getMemo());
		voucherBean.setDiscount((quotationForm.getDiscount() != null)
				? new BigDecimal(quotationForm.getDiscount()) : new BigDecimal(0));

		// 見積書FormBeanが保持する値を取引先Beanに設定
		reflectInputToDealBean(voucherBean.getDealBean(), quotationForm);

		// 見積書FormBeanが保持する値を明細Beanに設定
		reflectInputToDetailBean(voucherBean.getDetailList(), quotationForm);
	}

	/**
	 * <p>入力情報反映</p>
	 * 画面入力情報を取引先Beanへ反映
	 *
	 * @param dealBean
	 * @param quotationForm
	 * @throws Exception
	 */
	public static void reflectInputToDealBean(
			DealBean dealBean, QuotationForm quotationForm) throws Exception {
		// 見積書FormBeanが保持する値を取引先Beanに設定
		dealBean.setDealCode(quotationForm.getDealCode());
		dealBean.setDealKind(quotationForm.getDealKind());
	}

	/**
	 * <p>入力情報反映</p>
	 * 画面入力情報を明細Beanへ反映
	 *
	 * @param detailBeanList
	 * @param quotationForm
	 */
	public static void reflectInputToDetailBean(
			List<DetailBean> detailBeanList, QuotationForm quotationForm) {
		int index = 0;
		for (Map<String, String> detailMap : quotationForm.getDetailList()) {
			// 見積書FormBeanが保持する値を対象となる明細Beanに設定
			DetailBean detailBean = detailBeanList.get(index++);
			detailBean.setItemCode(detailMap.get(QuotationForm.DETAIL_KEY_ITEM_CODE));
			detailBean.setItemCount(detailMap.get(QuotationForm.DETAIL_KEY_ITEM_COUNT));
			detailBean.setItemUnitPrice(detailMap.get(QuotationForm.DETAIL_KEY_ITEM_UNIT_PRICE));
			detailBean.setItemMemo(detailMap.get(QuotationForm.DETAIL_KEY_ITEM_MEMO));
		}
	}

	/**
	 * 金額関連情報クリア
	 *
	 * @param voucherBean
	 * @param quotationForm
	 */
	public static void clearPriceInfo(VoucherBean voucherBean, QuotationForm quotationForm) {
		voucherBean.setDiscount(new BigDecimal(0));
		voucherBean.setTaxExcludedAmount(new BigDecimal(0));
		voucherBean.setTax(new BigDecimal(0));
		voucherBean.setTaxIncludedAmount(new BigDecimal(0));
		voucherBean.setGrossProfit(new BigDecimal(0));
		voucherBean.setAmount(new BigDecimal(0));

		quotationForm.setDiscount("0");
	}

	/**
	 * <p>金額計算</p>
	 *
	 * @param voucherBean
	 * @param taxVal
	 */
	public static void calcPrice(VoucherBean voucherBean, BigDecimal taxVal) {
		// 金額計算
		for (DetailBean detailBean : voucherBean.getDetailList()) {
			detailBean.setItemPrice(VoucherBL.calcItemPrice(
					new BigDecimal(detailBean.getItemUnitPrice()),
						Long.parseLong(detailBean.getItemCount())).toString());
		}

		// 金額情報設定
		VoucherBL.setBeanForPrice(voucherBean, taxVal);
	}

	/**
	 * <p>金額計算</p>
	 *
	 * @param quotationForm
	 */
	public static void calcPrice(QuotationForm quotationForm) {
		for (Map<String, String> detail : quotationForm.getDetailList()) {
			String itemPrice = VoucherBL.calcItemPrice(
					new BigDecimal(detail.get(QuotationForm.DETAIL_KEY_ITEM_UNIT_PRICE)),
					Long.parseLong(detail.get(QuotationForm.DETAIL_KEY_ITEM_COUNT))).toString();
			detail.put(QuotationForm.DETAIL_KEY_ITEM_PRICE, itemPrice);
		}
	}
}
