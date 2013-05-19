package master.customer.bl;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import master.customer.CustomerForm;
import master.customer.CustomerListForm;
import master.customer.bean.MCustomerBean;
import master.customer.dao.MCustomerDAO;
import master.customer.dto.MCustomerDTO;

import fw.common.db.SQLProperties;
import fw.common.db.dto.CommonDTO;
import fw.common.util.CommonConstants;
import fw.common.util.CommonUtil;
import common.MsgResourcesConstants;
import fw.core.base.TransactionInfo;

/**
 * <p>得意先ビジネスロジック</p>
 */
public class CustomerBL {

	public static Map<String, MCustomerBean> getMCustomerBeanMap() throws IOException, SQLException {
		Map<String, MCustomerBean> map = new LinkedHashMap<String, MCustomerBean>();
		for (CommonDTO ret : getAllCustomer()) {
			MCustomerDTO dto = (MCustomerDTO)ret;
			MCustomerBean customer = convMCustomerDTOtoMCustomerBean(dto);
			map.put(customer.getCode(), customer);
		}
		return map;
	}

	/**
	 * 得意先一覧フォーム取得
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 */
	public static CustomerListForm getCustomerListForm() throws IOException, SQLException {
		CustomerListForm customerListForm = new CustomerListForm();

		for (CommonDTO ret : getAllCustomer()) {
			MCustomerDTO dto = (MCustomerDTO)ret;
			MCustomerBean customer = convMCustomerDTOtoMCustomerBean(dto);
			customerListForm.setCustomer(customer.getCode(), customer);
		}

		return customerListForm;
	}

	/**
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 */
	private static List<CommonDTO> getAllCustomer() throws IOException, SQLException {
		MCustomerDAO dao = new MCustomerDAO(TransactionInfo.getConnection());
		String sql = SQLProperties.getInstance().getValue(MCustomerDAO.SQLID001);
		return dao.select(sql, new ArrayList<Object>());
	}

	/**
	 * @param dto
	 * @return
	 */
	private static MCustomerBean convMCustomerDTOtoMCustomerBean(MCustomerDTO dto) {
		MCustomerBean customer = new MCustomerBean();
		String code = dto.getCode();
		customer.setAddr1(dto.getAddr1());
		customer.setAddr2(dto.getAddr2());
		customer.setBusyo(dto.getBusyo());
		customer.setClassCode(CommonUtil.convString(dto.getClassCode(),true));
		customer.setCode(code);
		customer.setCustomerStaffName(dto.getCustomerStaffName());
		customer.setCustomerStaffSei(dto.getCustomerStaffSei());
		customer.setDealDivision(CommonUtil.convString(dto.getDealDivision(),false));
		customer.setFax(dto.getFax());
		customer.setKana(dto.getKana());
		customer.setKeisyo(CommonUtil.convString(dto.getKeisyo(),false));
		customer.setKessaiDivision(CommonUtil.convString(dto.getKessaiDivision(),false));
		customer.setKessaiMonthly(CommonUtil.convString(dto.getKessaiMonthly(),false));
		customer.setKisyuBalance(dto.getKisyuBalance() == null ? null : dto.getKisyuBalance().toString());
		customer.setName(dto.getName());
		customer.setPostCode1(dto.getPostCode1());
		customer.setPostCode2(dto.getPostCode2());
		customer.setSalePriceDivision(CommonUtil.convString(dto.getSalePriceDivision(),false));
		customer.setShimebiKessai(CommonUtil.convString(dto.getShimebiKessai(),false));
		customer.setShimebiMonthly(CommonUtil.convString(dto.getShimebiMonthly(),false));
		customer.setStaffCode(CommonUtil.convString(dto.getStaffCode(),true));
		customer.setTel(dto.getTel());
		customer.setUpdDate(String.valueOf(dto.getUpdDate().getTime()));
		return customer;
	}

	/**
	 * 得意先マスタBean⇒得意先フォーム変換
	 * @param customerBean
	 * @return
	 */
	public static CustomerForm convMCustomerBeanToCusotmerForm(MCustomerBean customerBean) {
		CustomerForm customerForm = new CustomerForm();
		customerForm.setAddr1(customerBean.getAddr1());
		customerForm.setAddr2(customerBean.getAddr2());
		customerForm.setBusyo(customerBean.getBusyo());
		customerForm.setClassCode(customerBean.getClassCode());
		customerForm.setCode(customerBean.getCode());
		customerForm.setCustomerStaffName(customerBean.getCustomerStaffName());
		customerForm.setCustomerStaffSei(customerBean.getCustomerStaffSei());
		customerForm.setDealDivision(customerBean.getDealDivision());
		customerForm.setFax(customerBean.getFax());
		customerForm.setKana(customerBean.getKana());
		customerForm.setKeisyo(customerBean.getKeisyo());
		customerForm.setKessaiDivision(customerBean.getKessaiDivision());
		customerForm.setKessaiMonthly(customerBean.getKessaiMonthly());
		customerForm.setKisyuBalance(customerBean.getKisyuBalance());
		customerForm.setName(customerBean.getName());
		customerForm.setPostCode1(customerBean.getPostCode1());
		customerForm.setPostCode2(customerBean.getPostCode2());
		customerForm.setSalePriceDivision(customerBean.getSalePriceDivision());
		customerForm.setShimebiKessai(customerBean.getShimebiKessai());
		customerForm.setShimebiMonthly(customerBean.getShimebiMonthly());
		customerForm.setStaffCode(customerBean.getStaffCode());
		customerForm.setTel(customerBean.getTel());
		customerForm.setUpdDate(customerBean.getUpdDate());
		return customerForm;
	}

	/**
	 * 登録処理
	 * @param customerForm
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 */
	public static ActionMessages insert(CustomerForm customerForm) throws IOException, SQLException {

		String sql = SQLProperties.getInstance().getValue(MCustomerDAO.SQLID002);
		List<Object> params = new ArrayList<Object>();
		params.add(customerForm.getCode());
		MCustomerDAO dao = new MCustomerDAO(TransactionInfo.getConnection());
		List<CommonDTO> ret = dao.select(sql, params);
		if (ret.size() > 0) {
			// 得意先コード[{0}]は既に使用されています。
			ActionMessages errors = new ActionMessages();
			ActionMessage error = new ActionMessage(
					MsgResourcesConstants.CUSTOMER_UPDATE_ERRMSG_DUPLICATE, customerForm.getCode());
			errors.add(ActionMessages.GLOBAL_MESSAGE, error);
			return errors;
		}

		sql = SQLProperties.getInstance().getValue(MCustomerDAO.SQLID003);
		params = new ArrayList<Object>();
		params.add(customerForm.getCode());
		params.add(customerForm.getName());
		params.add(customerForm.getKana());
		params.add(CommonUtil.convInteger(customerForm.getClassCode()));
		params.add(customerForm.getAddr1());
		params.add(customerForm.getAddr2());
		params.add(customerForm.getPostCode1());
		params.add(customerForm.getPostCode2());
		params.add(customerForm.getTel());
		params.add(customerForm.getFax());
		params.add(customerForm.getCustomerStaffSei());
		params.add(customerForm.getCustomerStaffName());
		params.add(CommonUtil.convInteger(customerForm.getKeisyo()));
		params.add(customerForm.getBusyo());
		params.add(CommonUtil.convInteger(customerForm.getDealDivision()));
		params.add(CommonUtil.convInteger(customerForm.getKessaiDivision()));
		params.add(CommonUtil.convInteger(customerForm.getSalePriceDivision()));
		params.add(CommonUtil.convDouble(customerForm.getKisyuBalance()));
		params.add(CommonUtil.convInteger(customerForm.getShimebiMonthly()));
		params.add(CommonUtil.convInteger(customerForm.getShimebiKessai()));
		params.add(CommonUtil.convInteger(customerForm.getKessaiMonthly()));
		params.add(CommonUtil.convInteger(customerForm.getStaffCode()));
		dao.insert(sql, params);

		return null;
	}

	/**
	 * 更新処理
	 * @param customerForm
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 */
	public static ActionMessages update(CustomerForm customerForm) throws IOException, SQLException {
		String sql = SQLProperties.getInstance().getValue(MCustomerDAO.SQLID004);
		List<Object> params = new ArrayList<Object>();
		params.add(customerForm.getName());
		params.add(customerForm.getKana());
		params.add(CommonUtil.convInteger(customerForm.getClassCode()));
		params.add(customerForm.getAddr1());
		params.add(customerForm.getAddr2());
		params.add(customerForm.getPostCode1());
		params.add(customerForm.getPostCode2());
		params.add(customerForm.getTel());
		params.add(customerForm.getFax());
		params.add(customerForm.getCustomerStaffSei());
		params.add(customerForm.getCustomerStaffName());
		params.add(CommonUtil.convInteger(customerForm.getKeisyo()));
		params.add(customerForm.getBusyo());
		params.add(CommonUtil.convInteger(customerForm.getDealDivision()));
		params.add(CommonUtil.convInteger(customerForm.getKessaiDivision()));
		params.add(CommonUtil.convInteger(customerForm.getSalePriceDivision()));
		params.add(CommonUtil.convDouble(customerForm.getKisyuBalance()));
		params.add(CommonUtil.convInteger(customerForm.getShimebiMonthly()));
		params.add(CommonUtil.convInteger(customerForm.getShimebiKessai()));
		params.add(CommonUtil.convInteger(customerForm.getKessaiMonthly()));
		params.add(CommonUtil.convInteger(customerForm.getStaffCode()));
		params.add(customerForm.getCode());
		params.add(new Timestamp(Long.parseLong(customerForm.getUpdDate())));

		MCustomerDAO dao = new MCustomerDAO(TransactionInfo.getConnection());
		int ret = dao.update(sql, params);

		if (ret == 0) {
			// 得意先コード[{0}]は他のユーザに変更されています。
			ActionMessages errors = new ActionMessages();
			ActionMessage error = new ActionMessage(
					MsgResourcesConstants.CUSTOMER_UPDATE_ERRMSG_LOCK, customerForm.getCode());
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
	public static void delete(CustomerListForm listForm) throws IOException, SQLException {
		String[] codes = listForm.getSel();

		List<Object> params = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder(SQLProperties.getInstance().getValue(MCustomerDAO.SQLID005));
		sql.append(CommonConstants.HALF_SPACE);
		sql.append(CommonConstants.KAKKO_START);
		for (int i = 0; i < codes.length; i++) {
			params.add(codes[i]);
			sql.append(CommonConstants.CAMMA);
			sql.append(CommonConstants.QUESTION);
		}
		sql.append(CommonConstants.KAKKO_END);

		MCustomerDAO dao = new MCustomerDAO(TransactionInfo.getConnection());
		dao.delete(sql.toString().replaceFirst(CommonConstants.CAMMA, ""), params);
	}
}
