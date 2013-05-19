package voucher.bill.dialog.bl;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import prop.ViewProperties;

import voucher.bill.dialog.bean.BillCustomerBean;
import voucher.bill.dialog.dao.BillCustomerDAO;
import voucher.bill.dialog.dto.BillCustomerDTO;
import voucher.common.bean.DealBean;
import voucher.common.bean.DetailBean;

import common.CommonBL;

import fw.common.date.DateFormatPattern;
import fw.common.date.DateUtility;
import fw.common.db.SQLProperties;
import fw.common.db.dto.CommonDTO;
import fw.common.util.CommonUtil;
import fw.core.base.TransactionInfo;

/**
 * <p>(請求書)得意先ビジネスロジック</p>
 */
public class BillCustomerBL {

	/**
	 * 請求書得意先BeanMap取得処理
	 *
	 * @param targetDealCode
	 * @param voucherDate
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 * @throws ParseException
	 */
	public static Map<String, BillCustomerBean> getBillCustomerBeanMap(
			String targetDealCode, String voucherDate) throws IOException, SQLException, ParseException {
		return createBillCustomerBeanMap(targetDealCode, voucherDate, true);
	}

	/**
	 * 請求書得意先BeanMap取得処理
	 *
	 * @param targetDealCode
	 * @param voucherDate
	 * @param searchLike
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 * @throws ParseException
	 */
	public static Map<String, BillCustomerBean> getBillCustomerBeanMap(
			String targetDealCode, String voucherDate, boolean searchLike) throws IOException, SQLException, ParseException {
		return createBillCustomerBeanMap(targetDealCode, voucherDate, searchLike);
	}

	/**
	 * 請求書得意先BeanMap取得処理
	 *
	 * @param targetDealCode
	 * @param voucherDate
	 * @param searchLike
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 * @throws ParseException
	 */
	private static Map<String, BillCustomerBean> createBillCustomerBeanMap(
			String targetDealCode, String voucherDate, boolean searchLike) throws IOException, SQLException, ParseException {

		Map<String, BillCustomerBean> billCustomerBeanMap = new LinkedHashMap<String, BillCustomerBean>();

		BillCustomerDAO dao = new BillCustomerDAO(TransactionInfo.getConnection());
		String sql = SQLProperties.getInstance().getValue(BillCustomerDAO.SQLID001);

		List<Object> params = new ArrayList<Object>();
		if (!searchLike) {
			// 取引先コード完全一致検索

			params.add(targetDealCode);

		} else {
			// 取引先コード部分一致検索

			if (targetDealCode == null) {
				params.add("%%");
			} else {
				params.add(
						new StringBuilder().append("%")
						.append(targetDealCode)
						.append("%").toString()
						);
			}
		}

		List<CommonDTO> retList = dao.select(sql, params);

		for (CommonDTO ret : retList) {
			BillCustomerDTO dto = (BillCustomerDTO)ret;

			// 表示対象チェック
			if (!isTarget(voucherDate,
					DateUtility.date2String(dto.getSalesDate(), DateFormatPattern.PATTERN_YYYYMMDD),
					Integer.toString(dto.getShimebiMonthly()), Integer.toString(dto.getShimebiKessai()))) {
				continue;
			}

			BillCustomerBean billCustomerBean =
				(billCustomerBeanMap.isEmpty()) ? null : billCustomerBeanMap.get(dto.getDealCode());

			if (billCustomerBean == null) {
				billCustomerBean = new BillCustomerBean();

				// 取引先Bean設定
				DealBean dealBean = new DealBean();
				convVoucherCustomerDTOtoDealBean(dealBean, dto, voucherDate);
				billCustomerBean.setDealBean(dealBean);

				// 請求書得意先Bean設定
				billCustomerBeanMap.put(billCustomerBean.getDealBean().getDealCode(), billCustomerBean);
			}

			// 明細Bean設定
			convBillCustomerDTOtoDetailBean(billCustomerBean, dto);
		}

		return billCustomerBeanMap;
	}

	/**
	 * 表示対象チェック
	 *
	 * @param voucherDate
	 * @param salesDate
	 * @param shimebiMonthly
	 * @param shimebiKessai
	 * @return
	 * @throws ParseException
	 */
	private static boolean isTarget(
			String voucherDate, String salesDate, String shimebiMonthly, String shimebiKessai) throws ParseException {
		// 伝票日付
		Calendar voucherDateCal = Calendar.getInstance();
		voucherDateCal.setTime(DateUtility.string2Date(voucherDate, DateFormatPattern.PATTERN_YYYYMMDD));

		// 締日
		Calendar shimebi = Calendar.getInstance();
		shimebi.setTime(DateUtility.string2Date(
				CommonBL.calcShimebi(salesDate, shimebiMonthly, shimebiKessai), DateFormatPattern.PATTERN_YYYYMMDD));

		// 決済日
		Calendar kessai = Calendar.getInstance();
		kessai.setTime(DateUtility.string2Date(
				CommonBL.calcKessai(salesDate, shimebiMonthly, shimebiKessai), DateFormatPattern.PATTERN_YYYYMMDD));

		// 締日≦伝票日付＜決済日
		return (shimebi.compareTo(voucherDateCal) <= 0 && voucherDateCal.compareTo(kessai) < 0);
	}

	/**
	 * (請求書)得意先DTO⇒取引先Bean変換処理
	 *
	 * @param dealBean
	 * @param dto
	 * @param voucherDate
	 * @throws ParseException
	 */
	private static void convVoucherCustomerDTOtoDealBean(
			DealBean dealBean, BillCustomerDTO dto, String voucherDate) throws ParseException {
		dealBean.setDealKind(ViewProperties.getInstance().getValue(
				ViewProperties.DEAL_KIND_CUSTOMER, ViewProperties.DISP_VALUE));
		dealBean.setDealCode(dto.getDealCode());
		dealBean.setDealName(dto.getDealName());
		dealBean.setDealClassCode(CommonUtil.convString(dto.getDealClassCode(),true));
		dealBean.setDealClassName(dto.getDealClassName());
		dealBean.setDealStaff(CommonBL.makeName(dto.getDealStaffSei(), dto.getDealStaffName(), false));
		dealBean.setDealStaffKeisyo(
				CommonBL.convKeisyo(ViewProperties.DISP_VALUE, ViewProperties.VALUE, dto.getDealStaffKeisyo()));
		dealBean.setDealBusyo(dto.getDealStaffBusyo());
		dealBean.setTel(dto.getDealTel());
		dealBean.setPostCode(CommonUtil.makePostCode(dto.getDealPostCode1(),dto.getDealPostCode2()));
		dealBean.setAddr1(dto.getDealAddr1());
		dealBean.setAddr2(dto.getDealAddr2());
		dealBean.setDealDivision(
				CommonBL.convDealDivision(ViewProperties.DISP_VALUE, ViewProperties.VALUE, dto.getDealDivision()));
		dealBean.setDealDivisionCode(Integer.toString(dto.getDealDivision()));

		if (ViewProperties.getInstance().getValue(
				ViewProperties.DEAL_DIVISION_KAKE, ViewProperties.VALUE).equals(Integer.toString(dto.getDealDivision()))) {
			// 取引区分が「掛」の場合

			// 締日を設定
			dealBean.setShimebi(CommonBL.calcShimebi(
					voucherDate, Integer.toString(dto.getShimebiMonthly()), Integer.toString(dto.getShimebiKessai())));
//					CommonBL.makeShimebiKessaiDate(
//					CommonBL.SHIMEBI, Integer.toString(dto.getShimebiMonthly()), Integer.toString(dto.getShimebiKessai())));

			// 決済日を設定
			dealBean.setKessai(CommonBL.calcKessai(
					voucherDate, Integer.toString(dto.getKessaiMonthly()), Integer.toString(dto.getShimebiKessai())));
//			dealBean.setKessai(CommonBL.makeShimebiKessaiDate(
//					CommonBL.KESSAI, Integer.toString(dto.getKessaiMonthly()), Integer.toString(dto.getShimebiKessai())));
		}

		dealBean.setPriceDivision(CommonUtil.convString(dto.getPriceDivision(),false));
		dealBean.setStaffCode(CommonUtil.convString(dto.getStaffCode(),true));
		dealBean.setStaff(CommonBL.makeName(dto.getStaffSei(), dto.getStaffName(), false));
		dealBean.setBusyo(dto.getStaffBusyo());
	}

	/**
	 * (請求書)得意先DTO⇒明細Bean変換処理
	 *
	 * @param billCustomerBean
	 * @param dto
	 */
	private static void convBillCustomerDTOtoDetailBean(BillCustomerBean billCustomerBean, BillCustomerDTO dto) {
		DetailBean detailBean = new DetailBean();

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
		BigDecimal itemUnitPrice = new BigDecimal(detailBean.getItemUnitPrice());
		long itemCount = Long.valueOf(Integer.toString(dto.getItemCount()));
		detailBean.setItemPrice(
				itemUnitPrice.multiply(
						BigDecimal.valueOf(Long.valueOf(itemCount))).toString());

		detailBean.setItemMemo(dto.getItemMemo());
		detailBean.setItemPurchasePrice(dto.getItemPurchasePrice().toString());

		billCustomerBean.addDetail(detailBean);
	}
}
