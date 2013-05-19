package master.supplier.bl;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import master.supplier.SupplierForm;
import master.supplier.SupplierListForm;
import master.supplier.bean.MSupplierBean;
import master.supplier.dao.MSupplierDAO;
import master.supplier.dto.MSupplierDTO;

import fw.common.db.SQLProperties;
import fw.common.db.dto.CommonDTO;
import fw.common.util.CommonConstants;
import fw.common.util.CommonUtil;
import common.MsgResourcesConstants;
import fw.core.base.TransactionInfo;

public class SupplierBL {

	/**
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 */
	public static Map<String, MSupplierBean> getMSupplierBeanMap() throws IOException, SQLException {
		Map<String, MSupplierBean> map = new LinkedHashMap<String, MSupplierBean>();
		for (CommonDTO ret : getAllSupplier()) {
			MSupplierBean supplier = convMSupplierDTOtoMSupplierBean((MSupplierDTO)ret);
			map.put(supplier.getCode(), supplier);
		}
		return map;
	}

	/**
	 * 仕入先一覧フォーム取得
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 */
	public static SupplierListForm getSupplierListForm() throws IOException, SQLException {
		SupplierListForm supplierListForm = new SupplierListForm();

		for (CommonDTO ret : getAllSupplier()) {
			MSupplierBean supplier = convMSupplierDTOtoMSupplierBean((MSupplierDTO)ret);
			supplierListForm.setSupplier(supplier.getCode(), supplier);
		}

		return supplierListForm;
	}

	/**
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 */
	private static List<CommonDTO> getAllSupplier() throws IOException, SQLException {
		MSupplierDAO dao = new MSupplierDAO(TransactionInfo.getConnection());
		String sql = SQLProperties.getInstance().getValue(MSupplierDAO.SQLID001);
		return dao.select(sql, new ArrayList<Object>());
	}

	/**
	 * @param dto
	 * @return
	 */
	private static MSupplierBean convMSupplierDTOtoMSupplierBean(MSupplierDTO dto) {
		MSupplierBean supplier = new MSupplierBean();
		String code = dto.getCode();
		supplier.setAddr1(dto.getAddr1());
		supplier.setAddr2(dto.getAddr2());
		supplier.setBusyo(dto.getBusyo());
		supplier.setClassCode(CommonUtil.convString(dto.getClassCode(),true));
		supplier.setCode(code);
		supplier.setSupplierStaffName(dto.getSupplierStaffName());
		supplier.setSupplierStaffSei(dto.getSupplierStaffSei());
		supplier.setDealDivision(CommonUtil.convString(dto.getDealDivision(),false));
		supplier.setFax(dto.getFax());
		supplier.setKana(dto.getKana());
		supplier.setKeisyo(CommonUtil.convString(dto.getKeisyo(),false));
		supplier.setKessaiDivision(CommonUtil.convString(dto.getKessaiDivision(),false));
		supplier.setKessaiMonthly(CommonUtil.convString(dto.getKessaiMonthly(),false));
		supplier.setKisyuBalance(CommonUtil.convString(dto.getKisyuBalance(),false));
		supplier.setName(dto.getName());
		supplier.setPostCode1(dto.getPostCode1());
		supplier.setPostCode2(dto.getPostCode2());
		supplier.setShimebiKessai(CommonUtil.convString(dto.getShimebiKessai(),false));
		supplier.setShimebiMonthly(CommonUtil.convString(dto.getShimebiMonthly(),false));
		supplier.setStaffCode(CommonUtil.convString(dto.getStaffCode(),true));
		supplier.setTel(dto.getTel());
		supplier.setTax(Integer.toString(dto.getTax()));
		supplier.setCalc(Integer.toString(dto.getCalc()));
		supplier.setCalcHasu(Integer.toString(dto.getCalcHasu()));
		supplier.setUpdDate(String.valueOf(dto.getUpdDate().getTime()));
		return supplier;
	}

	/**
	 * 仕入先マスタBean⇒仕入先フォーム変換
	 * @param supplierBean
	 * @return
	 */
	public static SupplierForm convMSupplierBeanToCusotmerForm(MSupplierBean supplierBean) {
		SupplierForm supplierForm = new SupplierForm();
		supplierForm.setAddr1(supplierBean.getAddr1());
		supplierForm.setAddr2(supplierBean.getAddr2());
		supplierForm.setBusyo(supplierBean.getBusyo());
		supplierForm.setClassCode(supplierBean.getClassCode());
		supplierForm.setCode(supplierBean.getCode());
		supplierForm.setSupplierStaffName(supplierBean.getSupplierStaffName());
		supplierForm.setSupplierStaffSei(supplierBean.getSupplierStaffSei());
		supplierForm.setDealDivision(supplierBean.getDealDivision());
		supplierForm.setFax(supplierBean.getFax());
		supplierForm.setKana(supplierBean.getKana());
		supplierForm.setKeisyo(supplierBean.getKeisyo());
		supplierForm.setKessaiDivision(supplierBean.getKessaiDivision());
		supplierForm.setKessaiMonthly(supplierBean.getKessaiMonthly());
		supplierForm.setKisyuBalance(supplierBean.getKisyuBalance());
		supplierForm.setName(supplierBean.getName());
		supplierForm.setPostCode1(supplierBean.getPostCode1());
		supplierForm.setPostCode2(supplierBean.getPostCode2());
		supplierForm.setShimebiKessai(supplierBean.getShimebiKessai());
		supplierForm.setShimebiMonthly(supplierBean.getShimebiMonthly());
		supplierForm.setStaffCode(supplierBean.getStaffCode());
		supplierForm.setTel(supplierBean.getTel());
		supplierForm.setTax(supplierBean.getTax());
		supplierForm.setCalc(supplierBean.getCalc());
		supplierForm.setCalcHasu(supplierBean.getCalcHasu());
		supplierForm.setUpdDate(supplierBean.getUpdDate());
		return supplierForm;
	}

	/**
	 * 登録処理
	 * @param supplierForm
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 */
	public static ActionMessages insert(SupplierForm supplierForm) throws IOException, SQLException {

		String sql = SQLProperties.getInstance().getValue(MSupplierDAO.SQLID002);
		List<Object> params = new ArrayList<Object>();
		params.add(supplierForm.getCode());
		MSupplierDAO dao = new MSupplierDAO(TransactionInfo.getConnection());
		List<CommonDTO> ret = dao.select(sql, params);
		if (ret.size() > 0) {
			// 仕入先コード[{0}]は既に使用されています。
			ActionMessages errors = new ActionMessages();
			ActionMessage error = new ActionMessage(
					MsgResourcesConstants.SUPPLIER_UPDATE_ERRMSG_DUPLICATE, supplierForm.getCode());
			errors.add(ActionMessages.GLOBAL_MESSAGE, error);
			return errors;
		}

		sql = SQLProperties.getInstance().getValue(MSupplierDAO.SQLID003);
		params = new ArrayList<Object>();
		params.add(supplierForm.getCode());
		params.add(supplierForm.getName());
		params.add(supplierForm.getKana());
		params.add(CommonUtil.convInteger(supplierForm.getClassCode()));
		params.add(supplierForm.getAddr1());
		params.add(supplierForm.getAddr2());
		params.add(supplierForm.getPostCode1());
		params.add(supplierForm.getPostCode2());
		params.add(supplierForm.getTel());
		params.add(supplierForm.getFax());
		params.add(supplierForm.getSupplierStaffSei());
		params.add(supplierForm.getSupplierStaffName());
		params.add(CommonUtil.convInteger(supplierForm.getKeisyo()));
		params.add(supplierForm.getBusyo());
		params.add(CommonUtil.convInteger(supplierForm.getDealDivision()));
		params.add(CommonUtil.convInteger(supplierForm.getKessaiDivision()));
		params.add(CommonUtil.convDouble(supplierForm.getKisyuBalance()));
		params.add(CommonUtil.convInteger(supplierForm.getShimebiMonthly()));
		params.add(CommonUtil.convInteger(supplierForm.getShimebiKessai()));
		params.add(CommonUtil.convInteger(supplierForm.getKessaiMonthly()));
		params.add(CommonUtil.convInteger(supplierForm.getStaffCode()));
		params.add(CommonUtil.convInteger(supplierForm.getTax()));
		params.add(CommonUtil.convInteger(supplierForm.getCalc()));
		params.add(CommonUtil.convInteger(supplierForm.getCalcHasu()));
		dao.insert(sql, params);

		return null;
	}

	/**
	 * 更新処理
	 * @param supplierForm
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 */
	public static ActionMessages update(SupplierForm supplierForm) throws IOException, SQLException {
		String sql = SQLProperties.getInstance().getValue(MSupplierDAO.SQLID004);
		List<Object> params = new ArrayList<Object>();
		params.add(supplierForm.getName());
		params.add(supplierForm.getKana());
		params.add(CommonUtil.convInteger(supplierForm.getClassCode()));
		params.add(supplierForm.getAddr1());
		params.add(supplierForm.getAddr2());
		params.add(supplierForm.getPostCode1());
		params.add(supplierForm.getPostCode2());
		params.add(supplierForm.getTel());
		params.add(supplierForm.getFax());
		params.add(supplierForm.getSupplierStaffSei());
		params.add(supplierForm.getSupplierStaffName());
		params.add(CommonUtil.convInteger(supplierForm.getKeisyo()));
		params.add(supplierForm.getBusyo());
		params.add(CommonUtil.convInteger(supplierForm.getDealDivision()));
		params.add(CommonUtil.convInteger(supplierForm.getKessaiDivision()));
		params.add(CommonUtil.convDouble(supplierForm.getKisyuBalance()));
		params.add(CommonUtil.convInteger(supplierForm.getShimebiMonthly()));
		params.add(CommonUtil.convInteger(supplierForm.getShimebiKessai()));
		params.add(CommonUtil.convInteger(supplierForm.getKessaiMonthly()));
		params.add(CommonUtil.convInteger(supplierForm.getStaffCode()));
		params.add(CommonUtil.convInteger(supplierForm.getTax()));
		params.add(CommonUtil.convInteger(supplierForm.getCalc()));
		params.add(CommonUtil.convInteger(supplierForm.getCalcHasu()));
		params.add(supplierForm.getCode());
		params.add(new Timestamp(Long.parseLong(supplierForm.getUpdDate())));

		MSupplierDAO dao = new MSupplierDAO(TransactionInfo.getConnection());
		int ret = dao.update(sql, params);

		if (ret == 0) {
			// 仕入先コード[{0}]は他のユーザに変更されています。
			ActionMessages errors = new ActionMessages();
			ActionMessage error = new ActionMessage(
					MsgResourcesConstants.SUPPLIER_UPDATE_ERRMSG_LOCK, supplierForm.getCode());
			errors.add(ActionMessages.GLOBAL_MESSAGE, error);
			return errors;
		}

		return null;
	}

	/**
	 * 削除処理
	 * @param listForm
	 * @throws IOException
	 * @throws SQLException
	 */
	public static void delete(SupplierListForm listForm) throws IOException, SQLException {
		String[] codes = listForm.getSel();

		List<Object> params = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder(SQLProperties.getInstance().getValue(MSupplierDAO.SQLID005));
		sql.append(CommonConstants.HALF_SPACE);
		sql.append(CommonConstants.KAKKO_START);
		for (int i = 0; i < codes.length; i++) {
			params.add(codes[i]);
			sql.append(CommonConstants.CAMMA);
			sql.append(CommonConstants.QUESTION);
		}
		sql.append(CommonConstants.KAKKO_END);

		MSupplierDAO dao = new MSupplierDAO(TransactionInfo.getConnection());
		dao.delete(sql.toString().replaceFirst(CommonConstants.CAMMA, ""), params);
	}
}
