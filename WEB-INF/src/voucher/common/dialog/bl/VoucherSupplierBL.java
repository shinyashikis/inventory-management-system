package voucher.common.dialog.bl;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import prop.ViewProperties;

import voucher.common.bean.DealBean;
import voucher.common.dialog.dao.VoucherSupplierDAO;
import voucher.common.dialog.dto.VoucherSupplierDTO;

import common.CommonBL;

import fw.common.db.SQLProperties;
import fw.common.db.dto.CommonDTO;
import fw.common.util.CommonUtil;
import fw.core.base.TransactionInfo;

/**
 * <p>(伝票)仕入先ビジネスロジック</p>
 */
public class VoucherSupplierBL {

	/**
	 * 取引先BeanMap取得処理
	 *
	 * @param targetDealCode
	 * @param targetVoucherDate
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 * @throws ParseException
	 */
	public static Map<String, DealBean> getDealBeanMap(
			String targetDealCode, String targetVoucherDate) throws IOException, SQLException, ParseException {
		return createDealBeanMap(targetDealCode, targetVoucherDate, true);
	}

	/**
	 * 取引先BeanMap取得処理
	 *
	 * @param targetDealCode
	 * @param targetVoucherDate
	 * @param searchLike
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 * @throws ParseException
	 */
	public static Map<String, DealBean> getDealBeanMap(
			String targetDealCode, String targetVoucherDate, boolean searchLike) throws IOException, SQLException, ParseException {
		return createDealBeanMap(targetDealCode, targetVoucherDate, searchLike);
	}

	/**
	 * 取引先BeanMap取得処理
	 *
	 * @param targetDealCode
	 * @param searchLike
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 * @throws ParseException
	 */
	private static Map<String, DealBean> createDealBeanMap(
			String targetDealCode, String targetVoucherDate, boolean searchLike) throws IOException, SQLException, ParseException {

		Map<String, DealBean> dealBeanMap = new LinkedHashMap<String, DealBean>();

		VoucherSupplierDAO dao = new VoucherSupplierDAO(TransactionInfo.getConnection());
		String sql = SQLProperties.getInstance().getValue(VoucherSupplierDAO.SQLID001);

		List<Object> params = new ArrayList<Object>();
		if (!searchLike) {
			// 完全一致検索

			params.add(targetDealCode);

		} else {
			// 部分一致検索

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
			VoucherSupplierDTO dto = (VoucherSupplierDTO)ret;

			DealBean dealBean = new DealBean();
			convVoucherSupplierDTOtoDealBean(targetVoucherDate, dealBean, dto);

			dealBeanMap.put(dealBean.getDealCode(), dealBean);
		}

		return dealBeanMap;
	}

	/**
	 * (伝票)仕入先DTO⇒取引先Bean変換処理
	 *
	 * @param voucherDate
	 * @param dealBean
	 * @param dto
	 * @throws ParseException
	 */
	private static void convVoucherSupplierDTOtoDealBean(
			String voucherDate, DealBean dealBean, VoucherSupplierDTO dto) throws ParseException {
		dealBean.setDealKind(ViewProperties.getInstance().getValue(
				ViewProperties.DEAL_KIND_SUPPLIER, ViewProperties.DISP_VALUE));
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
			dealBean.setShimebi(
					CommonBL.calcShimebi(voucherDate,
							Integer.toString(dto.getShimebiMonthly()), Integer.toString(dto.getShimebiKessai())));

			// 決済日を設定
			dealBean.setKessai(
					CommonBL.calcKessai(voucherDate,
							Integer.toString(dto.getKessaiMonthly()), Integer.toString(dto.getShimebiKessai())));
		}

		dealBean.setDealDivisionCode(Integer.toString(dto.getDealDivision()));

		dealBean.setTax(
				CommonBL.makeCalcCalcHasu(dto.getTax(), dto.getCalc(), dto.getCalcHasu()));

		dealBean.setStaffCode(CommonUtil.convString(dto.getStaffCode(),true));
		dealBean.setStaff(CommonBL.makeName(dto.getStaffSei(), dto.getStaffName(), false));
		dealBean.setBusyo(dto.getStaffBusyo());
	}
}
