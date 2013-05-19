package voucher.purchase.bl;

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
import voucher.purchase.PurchaseForm;
import voucher.purchase.dao.TPurchaseDAO;
import voucher.purchase.dao.TPurchaseDealDAO;
import voucher.purchase.dao.TPurchaseDetailsDAO;
import voucher.purchase.dto.TPurchaseDTO;

/**
 * 仕入伝票BL
 */
public class PurchaseBL {

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
				VoucherDAO.SQLID004,
				ViewProperties.getInstance().getValue(ViewProperties.VOUCHER_KIND_PURCHASE, ViewProperties.VALUE),
				taxVal);
	}

	/**
	 * <p>最新伝票番号(仕入伝票)取得</p>
	 *
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 */
	public static String getNewVoucherNo() throws IOException, SQLException {
		String maxVoucherNo = null;

		TPurchaseDAO dao = new TPurchaseDAO(TransactionInfo.getConnection());
		String sql = SQLProperties.getInstance().getValue(TPurchaseDAO.SQLID001);
		List<CommonDTO> retList = dao.select(sql, new ArrayList<Object>());
		for (CommonDTO ret : retList) {
			TPurchaseDTO dto = (TPurchaseDTO)ret;
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
	 * <p>伝票(仕入伝票)存在チェック</p>
	 *
	 * @param voucherNo
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 */
	public static boolean isExistVoucher(String voucherNo) throws IOException, SQLException {

		TPurchaseDAO dao = new TPurchaseDAO(TransactionInfo.getConnection());
		String sql = SQLProperties.getInstance().getValue(TPurchaseDAO.SQLID002);
		List<Object> params = new ArrayList<Object>();
		params.add(voucherNo);

		List<CommonDTO> retList = dao.select(sql, params);

		Integer val = null;
		for (CommonDTO ret : retList) {
			TPurchaseDTO dto = (TPurchaseDTO)ret;
			val = dto.getVoucherNo();
			break;
		}

		return (val != null);
	}

	/**
	 * <p>追加処理</p>
	 *
	 * @param purchaseForm
	 * @throws IOException
	 * @throws SQLException
	 */
	public static void insert(PurchaseForm purchaseForm) throws IOException, SQLException {
		// 仕入伝票テーブル追加処理
		insertPurchase(purchaseForm);
		// 仕入伝票取引テーブル追加処理
		insertPurchaseDeal(purchaseForm);
		// 仕入伝票明細テーブル追加処理
		insertPurchaseDetails(purchaseForm);
	}

	/**
	 * <p>更新処理</p>
	 *
	 * @param purchaseForm
	 * @throws IOException
	 * @throws SQLException
	 */
	public static void update(PurchaseForm purchaseForm) throws IOException, SQLException {
		// 仕入伝票テーブル更新処理
		updatePurchase(purchaseForm);
		// 仕入伝票取引テーブル更新処理
		updatePurchaseDeal(purchaseForm);
		// 仕入伝票明細テーブル削除処理
		deletePurchaseDetails(purchaseForm);
		// 仕入伝票明細テーブル追加処理
		insertPurchaseDetails(purchaseForm);
	}

	/**
	 * <p>更新処理</p>
	 *
	 * @param voucherNo
	 * @throws IOException
	 * @throws SQLException
	 */
	public static void delete(String[] voucherNo) throws IOException, SQLException {
		// 仕入伝票テーブル削除処理
		deletePurchase(voucherNo);
		// 仕入伝票取引テーブル削除処理
		deletePurchaseDeal(voucherNo);
		// 仕入伝票明細テーブル削除処理
		deletePurchaseDetails(voucherNo);
	}

	/**
	 * <p>仕入伝票テーブル追加処理</p>
	 *
	 * @param purchaseForm
	 * @throws IOException
	 * @throws SQLException
	 */
	public static void insertPurchase(PurchaseForm purchaseForm) throws IOException, SQLException {
		TPurchaseDAO dao = new TPurchaseDAO(TransactionInfo.getConnection());
		String sql = SQLProperties.getInstance().getValue(TPurchaseDAO.SQLID003);

		List<Object> params = new ArrayList<Object>();
		params.add(purchaseForm.getVoucherNo());
		params.add(purchaseForm.getVoucherDate());
		params.add(purchaseForm.getDiscount());
		params.add(purchaseForm.getEtc1());
		params.add(purchaseForm.getEtc2());
		params.add(purchaseForm.getMemo());

		dao.update(sql, params);
	}

	/**
	 * <p>仕入伝票取引テーブル追加処理</p>
	 *
	 * @param purchaseForm
	 * @throws IOException
	 * @throws SQLException
	 */
	public static void insertPurchaseDeal(PurchaseForm purchaseForm) throws IOException, SQLException {

		TPurchaseDealDAO dao = new TPurchaseDealDAO(TransactionInfo.getConnection());
		String sql = SQLProperties.getInstance().getValue(TPurchaseDealDAO.SQLID001);

		List<Object> params = new ArrayList<Object>();
		params.add(purchaseForm.getVoucherNo());
		params.add(purchaseForm.getDealKind());
		params.add(purchaseForm.getDealCode());
		dao.update(sql, params);
	}

	/**
	 * <p>仕入伝票明細テーブル追加処理</p>
	 *
	 * @param purchaseForm
	 * @throws IOException
	 * @throws SQLException
	 */
	public static void insertPurchaseDetails(PurchaseForm purchaseForm) throws IOException, SQLException {

		TPurchaseDetailsDAO dao = new TPurchaseDetailsDAO(TransactionInfo.getConnection());
		String sql = SQLProperties.getInstance().getValue(TPurchaseDetailsDAO.SQLID001);
		List<Object> params = null;

		int seq = 0;
		for (Map<String, String> map : purchaseForm.getDetailList()) {
			params = new ArrayList<Object>();
			params.add(purchaseForm.getVoucherNo());
			params.add(seq++);
			params.add(map.get(PurchaseForm.DETAIL_KEY_ITEM_CODE));
			params.add(map.get(PurchaseForm.DETAIL_KEY_ITEM_UNIT_PRICE));
			params.add(map.get(PurchaseForm.DETAIL_KEY_ITEM_COUNT));
			params.add(map.get(PurchaseForm.DETAIL_KEY_ITEM_MEMO));
			dao.update(sql, params);
		}

	}

	/**
	 * <p>仕入伝票テーブル更新処理</p>
	 *
	 * @param purchaseForm
	 * @throws IOException
	 * @throws SQLException
	 */
	public static void updatePurchase(PurchaseForm purchaseForm) throws IOException, SQLException {

		TPurchaseDAO dao = new TPurchaseDAO(TransactionInfo.getConnection());
		String sql = SQLProperties.getInstance().getValue(TPurchaseDAO.SQLID004);

		List<Object> params = new ArrayList<Object>();
		params.add(purchaseForm.getVoucherDate());
		params.add(purchaseForm.getDiscount());
		params.add(purchaseForm.getEtc1());
		params.add(purchaseForm.getEtc2());
		params.add(purchaseForm.getMemo());
		params.add(purchaseForm.getVoucherNo());
		dao.update(sql, params);

	}

	/**
	 * <p>仕入伝票取引テーブル更新処理</p>
	 *
	 * @param purchaseForm
	 * @throws IOException
	 * @throws SQLException
	 */
	public static void updatePurchaseDeal(PurchaseForm purchaseForm) throws IOException, SQLException {

		TPurchaseDealDAO dao = new TPurchaseDealDAO(TransactionInfo.getConnection());
		String sql = SQLProperties.getInstance().getValue(TPurchaseDealDAO.SQLID002);

		List<Object> params = new ArrayList<Object>();
		params.add(purchaseForm.getDealKind());
		params.add(purchaseForm.getDealCode());
		params.add(purchaseForm.getVoucherNo());
		dao.update(sql, params);

	}

	/**
	 * <p>仕入伝票テーブル削除処理</p>
	 *
	 * @param voucherNo
	 * @throws IOException
	 * @throws SQLException
	 */
	public static void deletePurchase(String[] voucherNo) throws IOException, SQLException {

		List<Object> params = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder(SQLProperties.getInstance().getValue(TPurchaseDAO.SQLID005));
		sql.append(CommonConstants.HALF_SPACE);
		sql.append(CommonConstants.KAKKO_START);
		for (int i = 0; i < voucherNo.length; i++) {
			params.add(voucherNo[i]);
			sql.append(CommonConstants.CAMMA);
			sql.append(CommonConstants.QUESTION);
		}
		sql.append(CommonConstants.KAKKO_END);

		TPurchaseDAO dao = new TPurchaseDAO(TransactionInfo.getConnection());
		dao.delete(sql.toString().replaceFirst(CommonConstants.CAMMA, ""), params);
	}

	/**
	 * <p>仕入伝票取引テーブル削除処理</p>
	 *
	 * @param voucherNo
	 * @throws IOException
	 * @throws SQLException
	 */
	public static void deletePurchaseDeal(String[] voucherNo) throws IOException, SQLException {

		List<Object> params = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder(SQLProperties.getInstance().getValue(TPurchaseDealDAO.SQLID003));
		sql.append(CommonConstants.HALF_SPACE);
		sql.append(CommonConstants.KAKKO_START);
		for (int i = 0; i < voucherNo.length; i++) {
			params.add(voucherNo[i]);
			sql.append(CommonConstants.CAMMA);
			sql.append(CommonConstants.QUESTION);
		}
		sql.append(CommonConstants.KAKKO_END);

		TPurchaseDealDAO dao = new TPurchaseDealDAO(TransactionInfo.getConnection());
		dao.delete(sql.toString().replaceFirst(CommonConstants.CAMMA, ""), params);
	}

	/**
	 * <p>仕入伝票明細テーブル削除処理</p>
	 *
	 * @param purchaseForm
	 * @throws IOException
	 * @throws SQLException
	 */
	public static void deletePurchaseDetails(PurchaseForm purchaseForm) throws IOException, SQLException {
		deletePurchaseDetails(new String[]{purchaseForm.getVoucherNo()});
	}

	/**
	 * <p>仕入伝票明細テーブル削除処理</p>
	 *
	 * @param voucherNo
	 * @throws IOException
	 * @throws SQLException
	 */
	public static void deletePurchaseDetails(String[] voucherNo) throws IOException, SQLException {

		List<Object> params = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder(SQLProperties.getInstance().getValue(TPurchaseDetailsDAO.SQLID002));
		sql.append(CommonConstants.HALF_SPACE);
		sql.append(CommonConstants.KAKKO_START);
		for (int i = 0; i < voucherNo.length; i++) {
			params.add(voucherNo[i]);
			sql.append(CommonConstants.CAMMA);
			sql.append(CommonConstants.QUESTION);
		}
		sql.append(CommonConstants.KAKKO_END);

		TPurchaseDetailsDAO dao = new TPurchaseDetailsDAO(TransactionInfo.getConnection());
		dao.delete(sql.toString().replaceFirst(CommonConstants.CAMMA, ""), params);
	}

	/**
	 * <p>伝票Bean反映</p>
	 * 伝票Beanの情報を仕入伝票FormBeanへ反映
	 *
	 * @param purchaseForm
	 * @param voucherBean
	 * @throws Exception
	 */
	public static void reflectVoucherBeanToPurchaseForm(
			PurchaseForm purchaseForm, VoucherBean voucherBean) throws Exception {
		purchaseForm.setVoucherNo(voucherBean.getVoucherNo());
		purchaseForm.setVoucherDate(voucherBean.getVoucherDate());
		purchaseForm.setEtc1(voucherBean.getEtc1());
		purchaseForm.setEtc2(voucherBean.getEtc2());
		purchaseForm.setMemo(voucherBean.getMemo());
		purchaseForm.setDiscount(
				(voucherBean.getDiscount() == null) ? null :
						voucherBean.getDiscount().toString());
		reflectDealBeanToPurchaseForm(purchaseForm, voucherBean.getDealBean());
		reflectDetailBeanToPurchaseForm(purchaseForm, voucherBean.getDetailList());
	}

	/**
	 * <p>取引先Bean反映</p>
	 * 取引先Beanの情報を仕入伝票FormBeanへ反映
	 *
	 * @param purchaseForm
	 * @param dealBean
	 * @throws Exception
	 */
	public static void reflectDealBeanToPurchaseForm(
			PurchaseForm purchaseForm, DealBean dealBean) throws Exception {
		purchaseForm.setDealCode(dealBean.getDealCode());
		purchaseForm.setDealKind(dealBean.getDealKind());
	}

	/**
	 * <p>明細Bean反映</p>
	 * 明細Beanの情報を仕入伝票FormBeanへ反映
	 *
	 * @param purchaseForm
	 * @param detailBeanList
	 * @throws Exception
	 */
	public static void reflectDetailBeanToPurchaseForm(
			PurchaseForm purchaseForm, List<DetailBean> detailBeanList) throws Exception {
		for (DetailBean detailBean : detailBeanList) {
			reflectDetailBeanToPurchaseForm(purchaseForm, detailBean);
		}
	}

	/**
	 * <p>明細Bean反映</p>
	 * 明細Beanの情報を仕入伝票FormBeanへ反映
	 *
	 * @param purchaseForm
	 * @param detailBean
	 * @throws Exception
	 */
	public static void reflectDetailBeanToPurchaseForm(
			PurchaseForm purchaseForm, DetailBean detailBean) throws Exception {
		purchaseForm.setDetail(createDetailMap(detailBean));
	}

	/**
	 * <p>明細Bean反映</p>
	 * 明細Beanの情報を仕入伝票FormBeanへ反映
	 *
	 * @param purchaseForm
	 * @param itemIndex
	 * @param detailBean
	 * @throws Exception
	 */
	public static void reflectDetailBeanToPurchaseForm(
			PurchaseForm purchaseForm, int itemIndex, DetailBean detailBean) throws Exception {
		purchaseForm.setDetail(itemIndex, createDetailMap(detailBean));
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
		detailMap.put(PurchaseForm.DETAIL_KEY_ITEM_CODE, detailBean.getItemCode());
		detailMap.put(PurchaseForm.DETAIL_KEY_ITEM_NAME, detailBean.getItemName());
		detailMap.put(PurchaseForm.DETAIL_KEY_ITEM_KIKAKU, detailBean.getItemKikaku());
		detailMap.put(PurchaseForm.DETAIL_KEY_ITEM_UNIT, detailBean.getItemUnit());
		detailMap.put(PurchaseForm.DETAIL_KEY_ITEM_PRICE, detailBean.getItemPrice());
		detailMap.put(PurchaseForm.DETAIL_KEY_ITEM_COUNT, detailBean.getItemCount());
		detailMap.put(PurchaseForm.DETAIL_KEY_ITEM_UNIT_PRICE, detailBean.getItemUnitPrice());
		detailMap.put(PurchaseForm.DETAIL_KEY_ITEM_MEMO, detailBean.getItemMemo());
		detailMap.put(PurchaseForm.DETAIL_KEY_ITEM_PURCHASE_PRICE, detailBean.getItemPurchasePrice());
		return detailMap;
	}

	/**
	 * <p>入力情報反映</p>
	 * 画面入力情報を伝票Beanへ反映
	 *
	 * @param voucherBean
	 * @param purchaseForm
	 * @throws Exception
	 */
	public static void reflectInputToVoucherBean(
			VoucherBean voucherBean, PurchaseForm purchaseForm) throws Exception {

		// 仕入伝票FormBeanが保持する値を伝票Beanに設定
		voucherBean.setVoucherNo(purchaseForm.getVoucherNo());
		voucherBean.setVoucherDate(purchaseForm.getVoucherDate());
		voucherBean.setEtc1(purchaseForm.getEtc1());
		voucherBean.setEtc2(purchaseForm.getEtc2());
		voucherBean.setMemo(purchaseForm.getMemo());
		voucherBean.setDiscount((purchaseForm.getDiscount() != null)
				? new BigDecimal(purchaseForm.getDiscount()) : new BigDecimal(0));

		// 仕入伝票FormBeanが保持する値を取引先Beanに設定
		reflectInputToDealBean(voucherBean.getDealBean(), purchaseForm);

		// 仕入伝票FormBeanが保持する値を明細Beanに設定
		reflectInputToDetailBean(voucherBean.getDetailList(), purchaseForm);
	}

	/**
	 * <p>入力情報反映</p>
	 * 画面入力情報を取引先Beanへ反映
	 *
	 * @param dealBean
	 * @param purchaseForm
	 * @throws Exception
	 */
	public static void reflectInputToDealBean(
			DealBean dealBean, PurchaseForm purchaseForm) throws Exception {
		// 仕入伝票FormBeanが保持する値を取引先Beanに設定
		dealBean.setDealCode(purchaseForm.getDealCode());
		dealBean.setDealKind(purchaseForm.getDealKind());
	}

	/**
	 * <p>入力情報反映</p>
	 * 画面入力情報を明細Beanへ反映
	 *
	 * @param detailBeanList
	 * @param purchaseForm
	 */
	public static void reflectInputToDetailBean(
			List<DetailBean> detailBeanList, PurchaseForm purchaseForm) {
		int index = 0;
		for (Map<String, String> detailMap : purchaseForm.getDetailList()) {
			// 仕入伝票FormBeanが保持する値を対象となる明細Beanに設定
			DetailBean detailBean = detailBeanList.get(index++);
			detailBean.setItemCode(detailMap.get(PurchaseForm.DETAIL_KEY_ITEM_CODE));
			detailBean.setItemCount(detailMap.get(PurchaseForm.DETAIL_KEY_ITEM_COUNT));
			detailBean.setItemUnitPrice(detailMap.get(PurchaseForm.DETAIL_KEY_ITEM_UNIT_PRICE));
			detailBean.setItemMemo(detailMap.get(PurchaseForm.DETAIL_KEY_ITEM_MEMO));
		}
	}

	/**
	 * 金額関連情報クリア
	 *
	 * @param voucherBean
	 * @param purchaseForm
	 */
	public static void clearPriceInfo(VoucherBean voucherBean, PurchaseForm purchaseForm) {
		voucherBean.setDiscount(new BigDecimal(0));
		voucherBean.setTaxExcludedAmount(new BigDecimal(0));
		voucherBean.setTax(new BigDecimal(0));
		voucherBean.setTaxIncludedAmount(new BigDecimal(0));
		voucherBean.setGrossProfit(new BigDecimal(0));
		voucherBean.setAmount(new BigDecimal(0));

		purchaseForm.setDiscount("0");
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
	 * @param purchaseForm
	 */
	public static void calcPrice(PurchaseForm purchaseForm) {
		for (Map<String, String> detail : purchaseForm.getDetailList()) {
			String itemPrice = VoucherBL.calcItemPrice(
					new BigDecimal(detail.get(PurchaseForm.DETAIL_KEY_ITEM_UNIT_PRICE)),
					Long.parseLong(detail.get(PurchaseForm.DETAIL_KEY_ITEM_COUNT))).toString();
			detail.put(PurchaseForm.DETAIL_KEY_ITEM_PRICE, itemPrice);
		}
	}
}
