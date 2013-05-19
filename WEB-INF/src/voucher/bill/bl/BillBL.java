package voucher.bill.bl;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
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
import voucher.bill.BillForm;
import voucher.bill.dao.TBillDAO;
import voucher.bill.dao.TBillDealDAO;
import voucher.bill.dao.TBillDetailsDAO;
import voucher.bill.dto.TBillDTO;

/**
 * 請求書BL
 */
public class BillBL {

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
				VoucherDAO.SQLID005,
				ViewProperties.getInstance().getValue(ViewProperties.VOUCHER_KIND_BILL, ViewProperties.VALUE),
				taxVal);
	}

	/**
	 * <p>最新伝票番号(請求書)取得</p>
	 *
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 */
	public static String getNewVoucherNo() throws IOException, SQLException {
		String maxVoucherNo = null;

		TBillDAO dao = new TBillDAO(TransactionInfo.getConnection());
		String sql = SQLProperties.getInstance().getValue(TBillDAO.SQLID001);
		List<CommonDTO> retList = dao.select(sql, new ArrayList<Object>());
		for (CommonDTO ret : retList) {
			TBillDTO dto = (TBillDTO)ret;
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
	 * <p>伝票(請求書)存在チェック</p>
	 *
	 * @param voucherNo
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 */
	public static boolean isExistVoucher(String voucherNo) throws IOException, SQLException {

		TBillDAO dao = new TBillDAO(TransactionInfo.getConnection());
		String sql = SQLProperties.getInstance().getValue(TBillDAO.SQLID002);
		List<Object> params = new ArrayList<Object>();
		params.add(voucherNo);

		List<CommonDTO> retList = dao.select(sql, params);

		Integer val = null;
		for (CommonDTO ret : retList) {
			TBillDTO dto = (TBillDTO)ret;
			val = dto.getVoucherNo();
			break;
		}

		return (val != null);
	}

	/**
	 * <p>追加処理</p>
	 *
	 * @param billForm
	 * @param detailList
	 * @throws IOException
	 * @throws SQLException
	 */
	public static void insert(BillForm billForm, List<DetailBean> detailList) throws IOException, SQLException {
		// 請求書テーブル追加処理
		insertBill(billForm);
		// 請求書取引テーブル追加処理
		insertBillDeal(billForm);
		// 請求書明細テーブル追加処理
		insertBillDetails(billForm, detailList);
	}

	/**
	 * <p>更新処理</p>
	 *
	 * @param billForm
	 * @param detailList
	 * @throws IOException
	 * @throws SQLException
	 */
	public static void update(BillForm billForm, List<DetailBean> detailList) throws IOException, SQLException {
		// 請求書テーブル更新処理
		updateBill(billForm);
		// 請求書取引テーブル更新処理
		updateBillDeal(billForm);
		// 請求書明細テーブル削除処理
		deleteBillDetails(billForm);
		// 請求書明細テーブル追加処理
		insertBillDetails(billForm, detailList);
	}

	/**
	 * <p>更新処理</p>
	 *
	 * @param voucherNo
	 * @throws IOException
	 * @throws SQLException
	 */
	public static void delete(String[] voucherNo) throws IOException, SQLException {
		// 請求書テーブル削除処理
		deleteBill(voucherNo);
		// 請求書取引テーブル削除処理
		deleteBillDeal(voucherNo);
		// 請求書明細テーブル削除処理
		deleteBillDetails(voucherNo);
	}

	/**
	 * <p>請求書テーブル追加処理</p>
	 *
	 * @param billForm
	 * @throws IOException
	 * @throws SQLException
	 */
	public static void insertBill(BillForm billForm) throws IOException, SQLException {
		TBillDAO dao = new TBillDAO(TransactionInfo.getConnection());
		String sql = SQLProperties.getInstance().getValue(TBillDAO.SQLID003);

		List<Object> params = new ArrayList<Object>();
		params.add(billForm.getVoucherNo());
		params.add(billForm.getVoucherDate());
		params.add(billForm.getDiscount());
		params.add(billForm.getMemo());

		dao.update(sql, params);
	}

	/**
	 * <p>請求書取引テーブル追加処理</p>
	 *
	 * @param billForm
	 * @throws IOException
	 * @throws SQLException
	 */
	public static void insertBillDeal(BillForm billForm) throws IOException, SQLException {

		TBillDealDAO dao = new TBillDealDAO(TransactionInfo.getConnection());
		String sql = SQLProperties.getInstance().getValue(TBillDealDAO.SQLID001);

		List<Object> params = new ArrayList<Object>();
		params.add(billForm.getVoucherNo());
		params.add(billForm.getDealKind());
		params.add(billForm.getDealCode());
		dao.update(sql, params);
	}

	/**
	 * <p>請求書明細テーブル追加処理</p>
	 *
	 * @param billForm
	 * @param detailList
	 * @throws IOException
	 * @throws SQLException
	 */
	public static void insertBillDetails(BillForm billForm, List<DetailBean> detailList)
		throws IOException, SQLException {

		TBillDetailsDAO dao = new TBillDetailsDAO(TransactionInfo.getConnection());
		String sql = SQLProperties.getInstance().getValue(TBillDetailsDAO.SQLID001);
		List<Object> params = null;

		int seq = 0;
		for (DetailBean detailBean : detailList) {
			params = new ArrayList<Object>();
			params.add(billForm.getVoucherNo());
			params.add(seq++);
			params.add(detailBean.getSalesNo());
			params.add(detailBean.getSalesSeq());
			params.add(detailBean.getSalesDate());
			params.add(detailBean.getItemCode());
			params.add(detailBean.getItemName());
			params.add(detailBean.getItemKikaku());
			params.add(detailBean.getItemCount());
			params.add(detailBean.getItemUnit());
			params.add(detailBean.getItemUnitPrice());
			params.add(detailBean.getItemMemo());
			params.add(detailBean.getItemPurchasePrice());
			dao.update(sql, params);
		}

	}

	/**
	 * <p>請求書テーブル更新処理</p>
	 *
	 * @param billForm
	 * @throws IOException
	 * @throws SQLException
	 */
	public static void updateBill(BillForm billForm) throws IOException, SQLException {

		TBillDAO dao = new TBillDAO(TransactionInfo.getConnection());
		String sql = SQLProperties.getInstance().getValue(TBillDAO.SQLID004);

		List<Object> params = new ArrayList<Object>();
		params.add(billForm.getVoucherDate());
		params.add(billForm.getDiscount());
		params.add(billForm.getMemo());
		params.add(billForm.getVoucherNo());
		dao.update(sql, params);

	}

	/**
	 * <p>請求書取引テーブル更新処理</p>
	 *
	 * @param billForm
	 * @throws IOException
	 * @throws SQLException
	 */
	public static void updateBillDeal(BillForm billForm) throws IOException, SQLException {

		TBillDealDAO dao = new TBillDealDAO(TransactionInfo.getConnection());
		String sql = SQLProperties.getInstance().getValue(TBillDealDAO.SQLID002);

		List<Object> params = new ArrayList<Object>();
		params.add(billForm.getDealKind());
		params.add(billForm.getDealCode());
		params.add(billForm.getVoucherNo());
		dao.update(sql, params);

	}

	/**
	 * <p>請求書テーブル削除処理</p>
	 *
	 * @param voucherNo
	 * @throws IOException
	 * @throws SQLException
	 */
	public static void deleteBill(String[] voucherNo) throws IOException, SQLException {

		List<Object> params = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder(SQLProperties.getInstance().getValue(TBillDAO.SQLID005));
		sql.append(CommonConstants.HALF_SPACE);
		sql.append(CommonConstants.KAKKO_START);
		for (int i = 0; i < voucherNo.length; i++) {
			params.add(voucherNo[i]);
			sql.append(CommonConstants.CAMMA);
			sql.append(CommonConstants.QUESTION);
		}
		sql.append(CommonConstants.KAKKO_END);

		TBillDAO dao = new TBillDAO(TransactionInfo.getConnection());
		dao.delete(sql.toString().replaceFirst(CommonConstants.CAMMA, ""), params);
	}

	/**
	 * <p>請求書取引テーブル削除処理</p>
	 *
	 * @param voucherNo
	 * @throws IOException
	 * @throws SQLException
	 */
	public static void deleteBillDeal(String[] voucherNo) throws IOException, SQLException {

		List<Object> params = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder(SQLProperties.getInstance().getValue(TBillDealDAO.SQLID003));
		sql.append(CommonConstants.HALF_SPACE);
		sql.append(CommonConstants.KAKKO_START);
		for (int i = 0; i < voucherNo.length; i++) {
			params.add(voucherNo[i]);
			sql.append(CommonConstants.CAMMA);
			sql.append(CommonConstants.QUESTION);
		}
		sql.append(CommonConstants.KAKKO_END);

		TBillDealDAO dao = new TBillDealDAO(TransactionInfo.getConnection());
		dao.delete(sql.toString().replaceFirst(CommonConstants.CAMMA, ""), params);
	}

	/**
	 * <p>請求書明細テーブル削除処理</p>
	 *
	 * @param billForm
	 * @throws IOException
	 * @throws SQLException
	 */
	public static void deleteBillDetails(BillForm billForm) throws IOException, SQLException {
		deleteBillDetails(new String[]{billForm.getVoucherNo()});
	}

	/**
	 * <p>請求書明細テーブル削除処理</p>
	 *
	 * @param voucherNo
	 * @throws IOException
	 * @throws SQLException
	 */
	public static void deleteBillDetails(String[] voucherNo) throws IOException, SQLException {

		List<Object> params = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder(SQLProperties.getInstance().getValue(TBillDetailsDAO.SQLID002));
		sql.append(CommonConstants.HALF_SPACE);
		sql.append(CommonConstants.KAKKO_START);
		for (int i = 0; i < voucherNo.length; i++) {
			params.add(voucherNo[i]);
			sql.append(CommonConstants.CAMMA);
			sql.append(CommonConstants.QUESTION);
		}
		sql.append(CommonConstants.KAKKO_END);

		TBillDetailsDAO dao = new TBillDetailsDAO(TransactionInfo.getConnection());
		dao.delete(sql.toString().replaceFirst(CommonConstants.CAMMA, ""), params);
	}

	/**
	 * <p>伝票Bean反映</p>
	 * 伝票Beanの情報を請求書FormBeanへ反映
	 *
	 * @param billForm
	 * @param voucherBean
	 * @throws Exception
	 */
	public static void reflectVoucherBeanToBillForm(
			BillForm billForm, VoucherBean voucherBean) throws Exception {
		billForm.setVoucherNo(voucherBean.getVoucherNo());
		billForm.setVoucherDate(voucherBean.getVoucherDate());
		billForm.setMemo(voucherBean.getMemo());
		billForm.setDiscount(
				(voucherBean.getDiscount() == null) ? null :
						voucherBean.getDiscount().toString());
		reflectDealBeanToBillForm(billForm, voucherBean.getDealBean());
	}

	/**
	 * <p>取引先Bean反映</p>
	 * 取引先Beanの情報を請求書FormBeanへ反映
	 *
	 * @param billForm
	 * @param dealBean
	 * @throws Exception
	 */
	public static void reflectDealBeanToBillForm(
			BillForm billForm, DealBean dealBean) throws Exception {
		billForm.setDealCode(dealBean.getDealCode());
		billForm.setDealKind(dealBean.getDealKind());
	}

	/**
	 * <p>入力情報反映</p>
	 * 画面入力情報を伝票Beanへ反映
	 *
	 * @param voucherBean
	 * @param billForm
	 * @throws Exception
	 */
	public static void reflectInputToVoucherBean(
			VoucherBean voucherBean, BillForm billForm) throws Exception {

		// 請求書FormBeanが保持する値を伝票Beanに設定
		voucherBean.setVoucherNo(billForm.getVoucherNo());
		voucherBean.setVoucherDate(billForm.getVoucherDate());
		voucherBean.setMemo(billForm.getMemo());
		voucherBean.setDiscount((billForm.getDiscount() != null)
				? new BigDecimal(billForm.getDiscount()) : new BigDecimal(0));

		// 請求書FormBeanが保持する値を取引先Beanに設定
		reflectInputToDealBean(voucherBean.getDealBean(), billForm);
	}

	/**
	 * <p>入力情報反映</p>
	 * 画面入力情報を取引先Beanへ反映
	 *
	 * @param dealBean
	 * @param billForm
	 * @throws Exception
	 */
	public static void reflectInputToDealBean(
			DealBean dealBean, BillForm billForm) throws Exception {
		// 請求書FormBeanが保持する値を取引先Beanに設定
		dealBean.setDealCode(billForm.getDealCode());
		dealBean.setDealKind(billForm.getDealKind());
	}

	/**
	 * 金額関連情報クリア
	 *
	 * @param voucherBean
	 * @param billForm
	 */
	public static void clearPriceInfo(VoucherBean voucherBean, BillForm billForm) {
		voucherBean.setDiscount(new BigDecimal(0));
		voucherBean.setTaxExcludedAmount(new BigDecimal(0));
		voucherBean.setTax(new BigDecimal(0));
		voucherBean.setTaxIncludedAmount(new BigDecimal(0));
		voucherBean.setGrossProfit(new BigDecimal(0));
		voucherBean.setAmount(new BigDecimal(0));

		billForm.setDiscount("0");
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
}
