package voucher.order.bl;

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
import voucher.order.OrderForm;
import voucher.order.dao.TOrderDAO;
import voucher.order.dao.TOrderDealDAO;
import voucher.order.dao.TOrderDetailsDAO;
import voucher.order.dto.TOrderDTO;

/**
 * 注文書BL
 */
public class OrderBL {

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
				VoucherDAO.SQLID002,
				ViewProperties.getInstance().getValue(ViewProperties.VOUCHER_KIND_ORDER, ViewProperties.VALUE),
				taxVal);
	}

	/**
	 * <p>最新伝票番号(注文書)取得</p>
	 *
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 */
	public static String getNewVoucherNo() throws IOException, SQLException {
		String maxVoucherNo = null;

		TOrderDAO dao = new TOrderDAO(TransactionInfo.getConnection());
		String sql = SQLProperties.getInstance().getValue(TOrderDAO.SQLID001);
		List<CommonDTO> retList = dao.select(sql, new ArrayList<Object>());
		for (CommonDTO ret : retList) {
			TOrderDTO dto = (TOrderDTO)ret;
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
	 * <p>伝票(注文書)存在チェック</p>
	 *
	 * @param voucherNo
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 */
	public static boolean isExistVoucher(String voucherNo) throws IOException, SQLException {

		TOrderDAO dao = new TOrderDAO(TransactionInfo.getConnection());
		String sql = SQLProperties.getInstance().getValue(TOrderDAO.SQLID002);
		List<Object> params = new ArrayList<Object>();
		params.add(voucherNo);

		List<CommonDTO> retList = dao.select(sql, params);

		Integer val = null;
		for (CommonDTO ret : retList) {
			TOrderDTO dto = (TOrderDTO)ret;
			val = dto.getVoucherNo();
			break;
		}

		return (val != null);
	}

	/**
	 * <p>追加処理</p>
	 *
	 * @param orderForm
	 * @throws IOException
	 * @throws SQLException
	 */
	public static void insert(OrderForm orderForm) throws IOException, SQLException {
		// 注文書テーブル追加処理
		insertOrder(orderForm);
		// 注文書取引テーブル追加処理
		insertOrderDeal(orderForm);
		// 注文書明細テーブル追加処理
		insertOrderDetails(orderForm);
	}

	/**
	 * <p>更新処理</p>
	 *
	 * @param orderForm
	 * @throws IOException
	 * @throws SQLException
	 */
	public static void update(OrderForm orderForm) throws IOException, SQLException {
		// 注文書テーブル更新処理
		updateOrder(orderForm);
		// 注文書取引テーブル更新処理
		updateOrderDeal(orderForm);
		// 注文書明細テーブル削除処理
		deleteOrderDetails(orderForm);
		// 注文書明細テーブル追加処理
		insertOrderDetails(orderForm);
	}

	/**
	 * <p>更新処理</p>
	 *
	 * @param voucherNo
	 * @throws IOException
	 * @throws SQLException
	 */
	public static void delete(String[] voucherNo) throws IOException, SQLException {
		// 注文書テーブル削除処理
		deleteOrder(voucherNo);
		// 注文書取引テーブル削除処理
		deleteOrderDeal(voucherNo);
		// 注文書明細テーブル削除処理
		deleteOrderDetails(voucherNo);
	}

	/**
	 * <p>注文書テーブル追加処理</p>
	 *
	 * @param orderForm
	 * @throws IOException
	 * @throws SQLException
	 */
	public static void insertOrder(OrderForm orderForm) throws IOException, SQLException {
		TOrderDAO dao = new TOrderDAO(TransactionInfo.getConnection());
		String sql = SQLProperties.getInstance().getValue(TOrderDAO.SQLID003);

		List<Object> params = new ArrayList<Object>();
		params.add(orderForm.getVoucherNo());
		params.add(orderForm.getVoucherDate());
		params.add(orderForm.getDiscount());
		params.add(orderForm.getVoucherName());
		params.add(orderForm.getPaymentLimit());
		params.add(orderForm.getPaymentPlace());
		params.add(orderForm.getPaymentCondition());
		params.add(orderForm.getExpirationDate());
		params.add(orderForm.getMemo());

		dao.update(sql, params);
	}

	/**
	 * <p>注文書取引テーブル追加処理</p>
	 *
	 * @param orderForm
	 * @throws IOException
	 * @throws SQLException
	 */
	public static void insertOrderDeal(OrderForm orderForm) throws IOException, SQLException {

		TOrderDealDAO dao = new TOrderDealDAO(TransactionInfo.getConnection());
		String sql = SQLProperties.getInstance().getValue(TOrderDealDAO.SQLID001);

		List<Object> params = new ArrayList<Object>();
		params.add(orderForm.getVoucherNo());
		params.add(orderForm.getDealKind());
		params.add(orderForm.getDealCode());
		dao.update(sql, params);
	}

	/**
	 * <p>注文書明細テーブル追加処理</p>
	 *
	 * @param orderForm
	 * @throws IOException
	 * @throws SQLException
	 */
	public static void insertOrderDetails(OrderForm orderForm) throws IOException, SQLException {

		TOrderDetailsDAO dao = new TOrderDetailsDAO(TransactionInfo.getConnection());
		String sql = SQLProperties.getInstance().getValue(TOrderDetailsDAO.SQLID001);
		List<Object> params = null;

		int seq = 0;
		for (Map<String, String> map : orderForm.getDetailList()) {
			params = new ArrayList<Object>();
			params.add(orderForm.getVoucherNo());
			params.add(seq++);
			params.add(map.get(OrderForm.DETAIL_KEY_ITEM_CODE));
			params.add(map.get(OrderForm.DETAIL_KEY_ITEM_UNIT_PRICE));
			params.add(map.get(OrderForm.DETAIL_KEY_ITEM_COUNT));
			params.add(map.get(OrderForm.DETAIL_KEY_ITEM_MEMO));
			dao.update(sql, params);
		}

	}

	/**
	 * <p>注文書テーブル更新処理</p>
	 *
	 * @param orderForm
	 * @throws IOException
	 * @throws SQLException
	 */
	public static void updateOrder(OrderForm orderForm) throws IOException, SQLException {

		TOrderDAO dao = new TOrderDAO(TransactionInfo.getConnection());
		String sql = SQLProperties.getInstance().getValue(TOrderDAO.SQLID004);

		List<Object> params = new ArrayList<Object>();
		params.add(orderForm.getVoucherDate());
		params.add(orderForm.getDiscount());
		params.add(orderForm.getVoucherName());
		params.add(orderForm.getPaymentLimit());
		params.add(orderForm.getPaymentPlace());
		params.add(orderForm.getPaymentCondition());
		params.add(orderForm.getExpirationDate());
		params.add(orderForm.getMemo());
		params.add(orderForm.getVoucherNo());
		dao.update(sql, params);

	}

	/**
	 * <p>注文書取引テーブル更新処理</p>
	 *
	 * @param orderForm
	 * @throws IOException
	 * @throws SQLException
	 */
	public static void updateOrderDeal(OrderForm orderForm) throws IOException, SQLException {

		TOrderDealDAO dao = new TOrderDealDAO(TransactionInfo.getConnection());
		String sql = SQLProperties.getInstance().getValue(TOrderDealDAO.SQLID002);

		List<Object> params = new ArrayList<Object>();
		params.add(orderForm.getDealKind());
		params.add(orderForm.getDealCode());
		params.add(orderForm.getVoucherNo());
		dao.update(sql, params);

	}

	/**
	 * <p>注文書テーブル削除処理</p>
	 *
	 * @param voucherNo
	 * @throws IOException
	 * @throws SQLException
	 */
	public static void deleteOrder(String[] voucherNo) throws IOException, SQLException {

		List<Object> params = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder(SQLProperties.getInstance().getValue(TOrderDAO.SQLID005));
		sql.append(CommonConstants.HALF_SPACE);
		sql.append(CommonConstants.KAKKO_START);
		for (int i = 0; i < voucherNo.length; i++) {
			params.add(voucherNo[i]);
			sql.append(CommonConstants.CAMMA);
			sql.append(CommonConstants.QUESTION);
		}
		sql.append(CommonConstants.KAKKO_END);

		TOrderDAO dao = new TOrderDAO(TransactionInfo.getConnection());
		dao.delete(sql.toString().replaceFirst(CommonConstants.CAMMA, ""), params);
	}

	/**
	 * <p>注文書取引テーブル削除処理</p>
	 *
	 * @param voucherNo
	 * @throws IOException
	 * @throws SQLException
	 */
	public static void deleteOrderDeal(String[] voucherNo) throws IOException, SQLException {

		List<Object> params = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder(SQLProperties.getInstance().getValue(TOrderDealDAO.SQLID003));
		sql.append(CommonConstants.HALF_SPACE);
		sql.append(CommonConstants.KAKKO_START);
		for (int i = 0; i < voucherNo.length; i++) {
			params.add(voucherNo[i]);
			sql.append(CommonConstants.CAMMA);
			sql.append(CommonConstants.QUESTION);
		}
		sql.append(CommonConstants.KAKKO_END);

		TOrderDealDAO dao = new TOrderDealDAO(TransactionInfo.getConnection());
		dao.delete(sql.toString().replaceFirst(CommonConstants.CAMMA, ""), params);
	}

	/**
	 * <p>注文書明細テーブル削除処理</p>
	 *
	 * @param orderForm
	 * @throws IOException
	 * @throws SQLException
	 */
	public static void deleteOrderDetails(OrderForm orderForm) throws IOException, SQLException {
		deleteOrderDetails(new String[]{orderForm.getVoucherNo()});
	}

	/**
	 * <p>注文書明細テーブル削除処理</p>
	 *
	 * @param voucherNo
	 * @throws IOException
	 * @throws SQLException
	 */
	public static void deleteOrderDetails(String[] voucherNo) throws IOException, SQLException {

		List<Object> params = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder(SQLProperties.getInstance().getValue(TOrderDetailsDAO.SQLID002));
		sql.append(CommonConstants.HALF_SPACE);
		sql.append(CommonConstants.KAKKO_START);
		for (int i = 0; i < voucherNo.length; i++) {
			params.add(voucherNo[i]);
			sql.append(CommonConstants.CAMMA);
			sql.append(CommonConstants.QUESTION);
		}
		sql.append(CommonConstants.KAKKO_END);

		TOrderDetailsDAO dao = new TOrderDetailsDAO(TransactionInfo.getConnection());
		dao.delete(sql.toString().replaceFirst(CommonConstants.CAMMA, ""), params);
	}

	/**
	 * <p>伝票Bean反映</p>
	 * 伝票Beanの情報を注文書FormBeanへ反映
	 *
	 * @param orderForm
	 * @param voucherBean
	 * @throws Exception
	 */
	public static void reflectVoucherBeanToOrderForm(
			OrderForm orderForm, VoucherBean voucherBean) throws Exception {
		orderForm.setVoucherNo(voucherBean.getVoucherNo());
		orderForm.setVoucherDate(voucherBean.getVoucherDate());
		orderForm.setVoucherName(voucherBean.getVoucherName());
		orderForm.setPaymentLimit(voucherBean.getPaymentLimit());
		orderForm.setPaymentPlace(voucherBean.getPaymentPlace());
		orderForm.setPaymentCondition(voucherBean.getPaymentCondition());
		orderForm.setExpirationDate(voucherBean.getExpirationDate());
		orderForm.setMemo(voucherBean.getMemo());
		orderForm.setDiscount(
				(voucherBean.getDiscount() == null) ? null :
						voucherBean.getDiscount().toString());
		reflectDealBeanToOrderForm(orderForm, voucherBean.getDealBean());
		reflectDetailBeanToOrderForm(orderForm, voucherBean.getDetailList());
	}

	/**
	 * <p>取引先Bean反映</p>
	 * 取引先Beanの情報を注文書FormBeanへ反映
	 *
	 * @param orderForm
	 * @param dealBean
	 * @throws Exception
	 */
	public static void reflectDealBeanToOrderForm(
			OrderForm orderForm, DealBean dealBean) throws Exception {
		orderForm.setDealCode(dealBean.getDealCode());
		orderForm.setDealKind(dealBean.getDealKind());
	}

	/**
	 * <p>明細Bean反映</p>
	 * 明細Beanの情報を注文書FormBeanへ反映
	 *
	 * @param orderForm
	 * @param detailBeanList
	 * @throws Exception
	 */
	public static void reflectDetailBeanToOrderForm(
			OrderForm orderForm, List<DetailBean> detailBeanList) throws Exception {
		for (DetailBean detailBean : detailBeanList) {
			reflectDetailBeanToOrderForm(orderForm, detailBean);
		}
	}

	/**
	 * <p>明細Bean反映</p>
	 * 明細Beanの情報を注文書FormBeanへ反映
	 *
	 * @param orderForm
	 * @param detailBean
	 * @throws Exception
	 */
	public static void reflectDetailBeanToOrderForm(
			OrderForm orderForm, DetailBean detailBean) throws Exception {
		orderForm.setDetail(createDetailMap(detailBean));
	}

	/**
	 * <p>明細Bean反映</p>
	 * 明細Beanの情報を注文書FormBeanへ反映
	 *
	 * @param orderForm
	 * @param itemIndex
	 * @param detailBean
	 * @throws Exception
	 */
	public static void reflectDetailBeanToOrderForm(
			OrderForm orderForm, int itemIndex, DetailBean detailBean) throws Exception {
		orderForm.setDetail(itemIndex, createDetailMap(detailBean));
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
		detailMap.put(OrderForm.DETAIL_KEY_ITEM_CODE, detailBean.getItemCode());
		detailMap.put(OrderForm.DETAIL_KEY_ITEM_NAME, detailBean.getItemName());
		detailMap.put(OrderForm.DETAIL_KEY_ITEM_KIKAKU, detailBean.getItemKikaku());
		detailMap.put(OrderForm.DETAIL_KEY_ITEM_UNIT, detailBean.getItemUnit());
		detailMap.put(OrderForm.DETAIL_KEY_ITEM_PRICE, detailBean.getItemPrice());
		detailMap.put(OrderForm.DETAIL_KEY_ITEM_COUNT, detailBean.getItemCount());
		detailMap.put(OrderForm.DETAIL_KEY_ITEM_UNIT_PRICE, detailBean.getItemUnitPrice());
		detailMap.put(OrderForm.DETAIL_KEY_ITEM_MEMO, detailBean.getItemMemo());
		detailMap.put(OrderForm.DETAIL_KEY_ITEM_PURCHASE_PRICE, detailBean.getItemPurchasePrice());
		return detailMap;
	}

	/**
	 * <p>入力情報反映</p>
	 * 画面入力情報を伝票Beanへ反映
	 *
	 * @param voucherBean
	 * @param orderForm
	 * @throws Exception
	 */
	public static void reflectInputToVoucherBean(
			VoucherBean voucherBean, OrderForm orderForm) throws Exception {

		// 注文書FormBeanが保持する値を伝票Beanに設定
		voucherBean.setVoucherNo(orderForm.getVoucherNo());
		voucherBean.setVoucherDate(orderForm.getVoucherDate());
		voucherBean.setVoucherName(orderForm.getVoucherName());
		voucherBean.setPaymentLimit(orderForm.getPaymentLimit());
		voucherBean.setPaymentPlace(orderForm.getPaymentPlace());
		voucherBean.setPaymentCondition(orderForm.getPaymentCondition());
		voucherBean.setExpirationDate(orderForm.getExpirationDate());
		voucherBean.setMemo(orderForm.getMemo());
		voucherBean.setDiscount((orderForm.getDiscount() != null)
				? new BigDecimal(orderForm.getDiscount()) : new BigDecimal(0));

		// 注文書FormBeanが保持する値を取引先Beanに設定
		reflectInputToDealBean(voucherBean.getDealBean(), orderForm);

		// 注文書FormBeanが保持する値を明細Beanに設定
		reflectInputToDetailBean(voucherBean.getDetailList(), orderForm);
	}

	/**
	 * <p>入力情報反映</p>
	 * 画面入力情報を取引先Beanへ反映
	 *
	 * @param dealBean
	 * @param orderForm
	 * @throws Exception
	 */
	public static void reflectInputToDealBean(
			DealBean dealBean, OrderForm orderForm) throws Exception {
		// 注文書FormBeanが保持する値を取引先Beanに設定
		dealBean.setDealCode(orderForm.getDealCode());
		dealBean.setDealKind(orderForm.getDealKind());
	}

	/**
	 * <p>入力情報反映</p>
	 * 画面入力情報を明細Beanへ反映
	 *
	 * @param detailBeanList
	 * @param orderForm
	 */
	public static void reflectInputToDetailBean(
			List<DetailBean> detailBeanList, OrderForm orderForm) {
		int index = 0;
		for (Map<String, String> detailMap : orderForm.getDetailList()) {
			// 注文書FormBeanが保持する値を対象となる明細Beanに設定
			DetailBean detailBean = detailBeanList.get(index++);
			detailBean.setItemCode(detailMap.get(OrderForm.DETAIL_KEY_ITEM_CODE));
			detailBean.setItemCount(detailMap.get(OrderForm.DETAIL_KEY_ITEM_COUNT));
			detailBean.setItemUnitPrice(detailMap.get(OrderForm.DETAIL_KEY_ITEM_UNIT_PRICE));
			detailBean.setItemMemo(detailMap.get(OrderForm.DETAIL_KEY_ITEM_MEMO));
		}
	}

	/**
	 * 金額関連情報クリア
	 *
	 * @param voucherBean
	 * @param orderForm
	 */
	public static void clearPriceInfo(VoucherBean voucherBean, OrderForm orderForm) {
		voucherBean.setDiscount(new BigDecimal(0));
		voucherBean.setTaxExcludedAmount(new BigDecimal(0));
		voucherBean.setTax(new BigDecimal(0));
		voucherBean.setTaxIncludedAmount(new BigDecimal(0));
		voucherBean.setGrossProfit(new BigDecimal(0));
		voucherBean.setAmount(new BigDecimal(0));

		orderForm.setDiscount("0");
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
	 * @param orderForm
	 */
	public static void calcPrice(OrderForm orderForm) {
		for (Map<String, String> detail : orderForm.getDetailList()) {
			String itemPrice = VoucherBL.calcItemPrice(
					new BigDecimal(detail.get(OrderForm.DETAIL_KEY_ITEM_UNIT_PRICE)),
					Long.parseLong(detail.get(OrderForm.DETAIL_KEY_ITEM_COUNT))).toString();
			detail.put(OrderForm.DETAIL_KEY_ITEM_PRICE, itemPrice);
		}
	}
}
