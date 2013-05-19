package voucher.sales.bl;

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
import voucher.sales.SalesForm;
import voucher.sales.dao.TSalesDAO;
import voucher.sales.dao.TSalesDealDAO;
import voucher.sales.dao.TSalesDetailsDAO;
import voucher.sales.dto.TSalesDTO;

/**
 * 売上伝票BL
 */
public class SalesBL {

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
				VoucherDAO.SQLID003,
				ViewProperties.getInstance().getValue(ViewProperties.VOUCHER_KIND_SALES, ViewProperties.VALUE),
				taxVal);
	}

	/**
	 * <p>最新伝票番号(売上伝票)取得</p>
	 *
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 */
	public static String getNewVoucherNo() throws IOException, SQLException {
		String maxVoucherNo = null;

		TSalesDAO dao = new TSalesDAO(TransactionInfo.getConnection());
		String sql = SQLProperties.getInstance().getValue(TSalesDAO.SQLID001);
		List<CommonDTO> retList = dao.select(sql, new ArrayList<Object>());
		for (CommonDTO ret : retList) {
			TSalesDTO dto = (TSalesDTO)ret;
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
	 * <p>伝票(売上伝票)存在チェック</p>
	 *
	 * @param voucherNo
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 */
	public static boolean isExistVoucher(String voucherNo) throws IOException, SQLException {

		TSalesDAO dao = new TSalesDAO(TransactionInfo.getConnection());
		String sql = SQLProperties.getInstance().getValue(TSalesDAO.SQLID002);
		List<Object> params = new ArrayList<Object>();
		params.add(voucherNo);

		List<CommonDTO> retList = dao.select(sql, params);

		Integer val = null;
		for (CommonDTO ret : retList) {
			TSalesDTO dto = (TSalesDTO)ret;
			val = dto.getVoucherNo();
			break;
		}

		return (val != null);
	}

	/**
	 * <p>追加処理</p>
	 *
	 * @param salesForm
	 * @throws IOException
	 * @throws SQLException
	 */
	public static void insert(SalesForm salesForm) throws IOException, SQLException {
		// 売上伝票テーブル追加処理
		insertSales(salesForm);
		// 売上伝票取引テーブル追加処理
		insertSalesDeal(salesForm);
		// 売上伝票明細テーブル追加処理
		insertSalesDetails(salesForm);
	}

	/**
	 * <p>更新処理</p>
	 *
	 * @param salesForm
	 * @throws IOException
	 * @throws SQLException
	 */
	public static void update(SalesForm salesForm) throws IOException, SQLException {
		// 売上伝票テーブル更新処理
		updateSales(salesForm);
		// 売上伝票取引テーブル更新処理
		updateSalesDeal(salesForm);
		// 売上伝票明細テーブル削除処理
		deleteSalesDetails(salesForm);
		// 売上伝票明細テーブル追加処理
		insertSalesDetails(salesForm);
	}

	/**
	 * <p>更新処理</p>
	 *
	 * @param voucherNo
	 * @throws IOException
	 * @throws SQLException
	 */
	public static void delete(String[] voucherNo) throws IOException, SQLException {
		// 売上伝票テーブル削除処理
		deleteSales(voucherNo);
		// 売上伝票取引テーブル削除処理
		deleteSalesDeal(voucherNo);
		// 売上伝票明細テーブル削除処理
		deleteSalesDetails(voucherNo);
	}

	/**
	 * <p>売上伝票テーブル追加処理</p>
	 *
	 * @param salesForm
	 * @throws IOException
	 * @throws SQLException
	 */
	public static void insertSales(SalesForm salesForm) throws IOException, SQLException {
		TSalesDAO dao = new TSalesDAO(TransactionInfo.getConnection());
		String sql = SQLProperties.getInstance().getValue(TSalesDAO.SQLID003);

		List<Object> params = new ArrayList<Object>();
		params.add(salesForm.getVoucherNo());
		params.add(salesForm.getVoucherDate());
		params.add(salesForm.getDiscount());
		params.add(salesForm.getEtc1());
		params.add(salesForm.getEtc2());
		params.add(salesForm.getReceiptNo());
		params.add(salesForm.getProviso());
		params.add((salesForm.getReceiptDate() == null ||
				"".equals(salesForm.getReceiptDate())) ? null : salesForm.getReceiptDate());

		params.add(salesForm.getMemo());

		dao.update(sql, params);
	}

	/**
	 * <p>売上伝票取引テーブル追加処理</p>
	 *
	 * @param salesForm
	 * @throws IOException
	 * @throws SQLException
	 */
	public static void insertSalesDeal(SalesForm salesForm) throws IOException, SQLException {

		TSalesDealDAO dao = new TSalesDealDAO(TransactionInfo.getConnection());
		String sql = SQLProperties.getInstance().getValue(TSalesDealDAO.SQLID001);

		List<Object> params = new ArrayList<Object>();
		params.add(salesForm.getVoucherNo());
		params.add(salesForm.getDealKind());
		params.add(salesForm.getDealCode());
		dao.update(sql, params);
	}

	/**
	 * <p>売上伝票明細テーブル追加処理</p>
	 *
	 * @param salesForm
	 * @throws IOException
	 * @throws SQLException
	 */
	public static void insertSalesDetails(SalesForm salesForm) throws IOException, SQLException {

		TSalesDetailsDAO dao = new TSalesDetailsDAO(TransactionInfo.getConnection());
		String sql = SQLProperties.getInstance().getValue(TSalesDetailsDAO.SQLID001);
		List<Object> params = null;

		int seq = 0;
		for (Map<String, String> map : salesForm.getDetailList()) {
			params = new ArrayList<Object>();
			params.add(salesForm.getVoucherNo());
			params.add(seq++);
			params.add(map.get(SalesForm.DETAIL_KEY_ITEM_CODE));
			params.add(map.get(SalesForm.DETAIL_KEY_ITEM_UNIT_PRICE));
			params.add(map.get(SalesForm.DETAIL_KEY_ITEM_COUNT));
			params.add(map.get(SalesForm.DETAIL_KEY_ITEM_MEMO));
			dao.update(sql, params);
		}

	}

	/**
	 * <p>売上伝票テーブル更新処理</p>
	 *
	 * @param salesForm
	 * @throws IOException
	 * @throws SQLException
	 */
	public static void updateSales(SalesForm salesForm) throws IOException, SQLException {

		TSalesDAO dao = new TSalesDAO(TransactionInfo.getConnection());
		String sql = SQLProperties.getInstance().getValue(TSalesDAO.SQLID004);

		List<Object> params = new ArrayList<Object>();
		params.add(salesForm.getVoucherDate());
		params.add(salesForm.getDiscount());
		params.add(salesForm.getEtc1());
		params.add(salesForm.getEtc2());
		params.add(salesForm.getReceiptNo());
		params.add(salesForm.getProviso());
		params.add((salesForm.getReceiptDate() == null ||
				"".equals(salesForm.getReceiptDate())) ? null : salesForm.getReceiptDate());
		params.add(salesForm.getMemo());
		params.add(salesForm.getVoucherNo());
		dao.update(sql, params);

	}

	/**
	 * <p>売上伝票取引テーブル更新処理</p>
	 *
	 * @param salesForm
	 * @throws IOException
	 * @throws SQLException
	 */
	public static void updateSalesDeal(SalesForm salesForm) throws IOException, SQLException {

		TSalesDealDAO dao = new TSalesDealDAO(TransactionInfo.getConnection());
		String sql = SQLProperties.getInstance().getValue(TSalesDealDAO.SQLID002);

		List<Object> params = new ArrayList<Object>();
		params.add(salesForm.getDealKind());
		params.add(salesForm.getDealCode());
		params.add(salesForm.getVoucherNo());
		dao.update(sql, params);

	}

	/**
	 * <p>売上伝票テーブル削除処理</p>
	 *
	 * @param voucherNo
	 * @throws IOException
	 * @throws SQLException
	 */
	public static void deleteSales(String[] voucherNo) throws IOException, SQLException {

		List<Object> params = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder(SQLProperties.getInstance().getValue(TSalesDAO.SQLID005));
		sql.append(CommonConstants.HALF_SPACE);
		sql.append(CommonConstants.KAKKO_START);
		for (int i = 0; i < voucherNo.length; i++) {
			params.add(voucherNo[i]);
			sql.append(CommonConstants.CAMMA);
			sql.append(CommonConstants.QUESTION);
		}
		sql.append(CommonConstants.KAKKO_END);

		TSalesDAO dao = new TSalesDAO(TransactionInfo.getConnection());
		dao.delete(sql.toString().replaceFirst(CommonConstants.CAMMA, ""), params);
	}

	/**
	 * <p>売上伝票取引テーブル削除処理</p>
	 *
	 * @param voucherNo
	 * @throws IOException
	 * @throws SQLException
	 */
	public static void deleteSalesDeal(String[] voucherNo) throws IOException, SQLException {

		List<Object> params = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder(SQLProperties.getInstance().getValue(TSalesDealDAO.SQLID003));
		sql.append(CommonConstants.HALF_SPACE);
		sql.append(CommonConstants.KAKKO_START);
		for (int i = 0; i < voucherNo.length; i++) {
			params.add(voucherNo[i]);
			sql.append(CommonConstants.CAMMA);
			sql.append(CommonConstants.QUESTION);
		}
		sql.append(CommonConstants.KAKKO_END);

		TSalesDealDAO dao = new TSalesDealDAO(TransactionInfo.getConnection());
		dao.delete(sql.toString().replaceFirst(CommonConstants.CAMMA, ""), params);
	}

	/**
	 * <p>売上伝票明細テーブル削除処理</p>
	 *
	 * @param salesForm
	 * @throws IOException
	 * @throws SQLException
	 */
	public static void deleteSalesDetails(SalesForm salesForm) throws IOException, SQLException {
		deleteSalesDetails(new String[]{salesForm.getVoucherNo()});
	}

	/**
	 * <p>売上伝票明細テーブル削除処理</p>
	 *
	 * @param voucherNo
	 * @throws IOException
	 * @throws SQLException
	 */
	public static void deleteSalesDetails(String[] voucherNo) throws IOException, SQLException {

		List<Object> params = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder(SQLProperties.getInstance().getValue(TSalesDetailsDAO.SQLID002));
		sql.append(CommonConstants.HALF_SPACE);
		sql.append(CommonConstants.KAKKO_START);
		for (int i = 0; i < voucherNo.length; i++) {
			params.add(voucherNo[i]);
			sql.append(CommonConstants.CAMMA);
			sql.append(CommonConstants.QUESTION);
		}
		sql.append(CommonConstants.KAKKO_END);

		TSalesDetailsDAO dao = new TSalesDetailsDAO(TransactionInfo.getConnection());
		dao.delete(sql.toString().replaceFirst(CommonConstants.CAMMA, ""), params);
	}

	/**
	 * <p>伝票Bean反映</p>
	 * 伝票Beanの情報を売上伝票FormBeanへ反映
	 *
	 * @param salesForm
	 * @param voucherBean
	 * @throws Exception
	 */
	public static void reflectVoucherBeanToSalesForm(
			SalesForm salesForm, VoucherBean voucherBean) throws Exception {
		salesForm.setVoucherNo(voucherBean.getVoucherNo());
		salesForm.setVoucherDate(voucherBean.getVoucherDate());
		salesForm.setEtc1(voucherBean.getEtc1());
		salesForm.setEtc2(voucherBean.getEtc2());
		salesForm.setReceiptNo(voucherBean.getReceiptNo());
		salesForm.setProviso(voucherBean.getProviso());
		salesForm.setReceiptDate(voucherBean.getReceiptDate());
		salesForm.setMemo(voucherBean.getMemo());
		salesForm.setDiscount(
				(voucherBean.getDiscount() == null) ? null :
						voucherBean.getDiscount().toString());
		reflectDealBeanToSalesForm(salesForm, voucherBean.getDealBean());
		reflectDetailBeanToSalesForm(salesForm, voucherBean.getDetailList());
	}

	/**
	 * <p>取引先Bean反映</p>
	 * 取引先Beanの情報を売上伝票FormBeanへ反映
	 *
	 * @param salesForm
	 * @param dealBean
	 * @throws Exception
	 */
	public static void reflectDealBeanToSalesForm(
			SalesForm salesForm, DealBean dealBean) throws Exception {
		salesForm.setDealCode(dealBean.getDealCode());
		salesForm.setDealKind(dealBean.getDealKind());
	}

	/**
	 * <p>明細Bean反映</p>
	 * 明細Beanの情報を売上伝票FormBeanへ反映
	 *
	 * @param salesForm
	 * @param detailBeanList
	 * @throws Exception
	 */
	public static void reflectDetailBeanToSalesForm(
			SalesForm salesForm, List<DetailBean> detailBeanList) throws Exception {
		for (DetailBean detailBean : detailBeanList) {
			reflectDetailBeanToSalesForm(salesForm, detailBean);
		}
	}

	/**
	 * <p>明細Bean反映</p>
	 * 明細Beanの情報を売上伝票FormBeanへ反映
	 *
	 * @param salesForm
	 * @param detailBean
	 * @throws Exception
	 */
	public static void reflectDetailBeanToSalesForm(
			SalesForm salesForm, DetailBean detailBean) throws Exception {
		salesForm.setDetail(createDetailMap(detailBean));
	}

	/**
	 * <p>明細Bean反映</p>
	 * 明細Beanの情報を売上伝票FormBeanへ反映
	 *
	 * @param salesForm
	 * @param itemIndex
	 * @param detailBean
	 * @throws Exception
	 */
	public static void reflectDetailBeanToSalesForm(
			SalesForm salesForm, int itemIndex, DetailBean detailBean) throws Exception {
		salesForm.setDetail(itemIndex, createDetailMap(detailBean));
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
		detailMap.put(SalesForm.DETAIL_KEY_ITEM_CODE, detailBean.getItemCode());
		detailMap.put(SalesForm.DETAIL_KEY_ITEM_NAME, detailBean.getItemName());
		detailMap.put(SalesForm.DETAIL_KEY_ITEM_KIKAKU, detailBean.getItemKikaku());
		detailMap.put(SalesForm.DETAIL_KEY_ITEM_UNIT, detailBean.getItemUnit());
		detailMap.put(SalesForm.DETAIL_KEY_ITEM_PRICE, detailBean.getItemPrice());
		detailMap.put(SalesForm.DETAIL_KEY_ITEM_COUNT, detailBean.getItemCount());
		detailMap.put(SalesForm.DETAIL_KEY_ITEM_UNIT_PRICE, detailBean.getItemUnitPrice());
		detailMap.put(SalesForm.DETAIL_KEY_ITEM_MEMO, detailBean.getItemMemo());
		detailMap.put(SalesForm.DETAIL_KEY_ITEM_PURCHASE_PRICE, detailBean.getItemPurchasePrice());
		return detailMap;
	}

	/**
	 * <p>入力情報反映</p>
	 * 画面入力情報を伝票Beanへ反映
	 *
	 * @param voucherBean
	 * @param salesForm
	 * @throws Exception
	 */
	public static void reflectInputToVoucherBean(
			VoucherBean voucherBean, SalesForm salesForm) throws Exception {

		// 売上伝票FormBeanが保持する値を伝票Beanに設定
		voucherBean.setVoucherNo(salesForm.getVoucherNo());
		voucherBean.setVoucherDate(salesForm.getVoucherDate());
		voucherBean.setEtc1(salesForm.getEtc1());
		voucherBean.setEtc2(salesForm.getEtc2());
		voucherBean.setReceiptNo(salesForm.getReceiptNo());
		voucherBean.setProviso(salesForm.getProviso());
		voucherBean.setReceiptDate(salesForm.getReceiptDate());
		voucherBean.setMemo(salesForm.getMemo());
		voucherBean.setDiscount((salesForm.getDiscount() != null)
				? new BigDecimal(salesForm.getDiscount()) : new BigDecimal(0));

		// 売上伝票FormBeanが保持する値を取引先Beanに設定
		reflectInputToDealBean(voucherBean.getDealBean(), salesForm);

		// 売上伝票FormBeanが保持する値を明細Beanに設定
		reflectInputToDetailBean(voucherBean.getDetailList(), salesForm);
	}

	/**
	 * <p>入力情報反映</p>
	 * 画面入力情報を取引先Beanへ反映
	 *
	 * @param dealBean
	 * @param salesForm
	 * @throws Exception
	 */
	public static void reflectInputToDealBean(
			DealBean dealBean, SalesForm salesForm) throws Exception {
		// 売上伝票FormBeanが保持する値を取引先Beanに設定
		dealBean.setDealCode(salesForm.getDealCode());
		dealBean.setDealKind(salesForm.getDealKind());
	}

	/**
	 * <p>入力情報反映</p>
	 * 画面入力情報を明細Beanへ反映
	 *
	 * @param detailBeanList
	 * @param salesForm
	 */
	public static void reflectInputToDetailBean(
			List<DetailBean> detailBeanList, SalesForm salesForm) {
		int index = 0;
		for (Map<String, String> detailMap : salesForm.getDetailList()) {
			// 売上伝票FormBeanが保持する値を対象となる明細Beanに設定
			DetailBean detailBean = detailBeanList.get(index++);
			detailBean.setItemCode(detailMap.get(SalesForm.DETAIL_KEY_ITEM_CODE));
			detailBean.setItemCount(detailMap.get(SalesForm.DETAIL_KEY_ITEM_COUNT));
			detailBean.setItemUnitPrice(detailMap.get(SalesForm.DETAIL_KEY_ITEM_UNIT_PRICE));
			detailBean.setItemMemo(detailMap.get(SalesForm.DETAIL_KEY_ITEM_MEMO));
		}
	}

	/**
	 * 金額関連情報クリア
	 *
	 * @param voucherBean
	 * @param salesForm
	 */
	public static void clearPriceInfo(VoucherBean voucherBean, SalesForm salesForm) {
		voucherBean.setDiscount(new BigDecimal(0));
		voucherBean.setTaxExcludedAmount(new BigDecimal(0));
		voucherBean.setTax(new BigDecimal(0));
		voucherBean.setTaxIncludedAmount(new BigDecimal(0));
		voucherBean.setGrossProfit(new BigDecimal(0));
		voucherBean.setAmount(new BigDecimal(0));

		salesForm.setDiscount("0");
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
	 * @param salesForm
	 */
	public static void calcPrice(SalesForm salesForm) {
		for (Map<String, String> detail : salesForm.getDetailList()) {
			String itemPrice = VoucherBL.calcItemPrice(
					new BigDecimal(detail.get(SalesForm.DETAIL_KEY_ITEM_UNIT_PRICE)),
					Long.parseLong(detail.get(SalesForm.DETAIL_KEY_ITEM_COUNT))).toString();
			detail.put(SalesForm.DETAIL_KEY_ITEM_PRICE, itemPrice);
		}
	}
}
