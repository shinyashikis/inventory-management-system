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

import master.customer.CustomerClassForm;
import master.customer.CustomerClassListForm;
import master.customer.bean.MCustomerClassBean;
import master.customer.dao.MCustomerClassDAO;
import master.customer.dto.MCustomerClassDTO;
import fw.common.db.SQLErrCodeProperties;
import fw.common.db.SQLProperties;
import fw.common.db.dto.CommonDTO;
import fw.common.util.CommonConstants;
import fw.common.util.CommonUtil;
import common.MsgResourcesConstants;
import fw.core.base.TransactionInfo;

/**
 * <p>得意先分類ビジネスロジック</p>
 */
public class CustomerClassBL {

	/**
	 * @return
	 * @throws SQLException
	 * @throws IOException
	 */
	public static Map<String, MCustomerClassBean> getMCustomerClassBeanMap() throws IOException, SQLException {
		Map<String, MCustomerClassBean> map = new LinkedHashMap<String, MCustomerClassBean>();
		for (CommonDTO ret : getAllCustomerClassList()) {
			MCustomerClassBean customerClass =
				convMCustomerClassDTOtoMCustomerClassBean((MCustomerClassDTO)ret);
			map.put(customerClass.getCode(), customerClass);
		}
		return map;
	}

	/**
	 * 得意先分類一覧フォーム取得
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 */
	public static CustomerClassListForm getCustomerClassListForm() throws IOException, SQLException {
		CustomerClassListForm customerClassListForm = new CustomerClassListForm();

		for (CommonDTO ret : getAllCustomerClassList()) {
			MCustomerClassBean customerClass =
				convMCustomerClassDTOtoMCustomerClassBean((MCustomerClassDTO)ret);
			customerClassListForm.setCustomerClass(customerClass.getCode(), customerClass);
		}

		return customerClassListForm;
	}

	/**
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 */
	private static List<CommonDTO> getAllCustomerClassList() throws IOException, SQLException {
		MCustomerClassDAO dao = new MCustomerClassDAO(TransactionInfo.getConnection());
		String sql = SQLProperties.getInstance().getValue(MCustomerClassDAO.SQLID001);
		return dao.select(sql, new ArrayList<Object>());
	}

	/**
	 * @param dto
	 * @return
	 */
	private static MCustomerClassBean convMCustomerClassDTOtoMCustomerClassBean(MCustomerClassDTO dto) {
		MCustomerClassBean customerClass = new MCustomerClassBean();
		String code = CommonUtil.convString(dto.getCode(),true);
		customerClass.setCode(code);
		customerClass.setKana(dto.getKana());
		customerClass.setName(dto.getName());
		customerClass.setUpdDate(String.valueOf(dto.getUpdDate().getTime()));
		return customerClass;
	}

	/**
	 * 得意先分類マスタBean⇒得意先分類フォーム変換
	 * @param customerClassBean
	 * @return
	 */
	public static CustomerClassForm convMCustomerClassBeanToCustomerClassForm(MCustomerClassBean customerClassBean) {
		CustomerClassForm customerClassForm = new CustomerClassForm();
		customerClassForm.setCode(customerClassBean.getCode());
		customerClassForm.setKana(customerClassBean.getKana());
		customerClassForm.setName(customerClassBean.getName());
		customerClassForm.setUpdDate(customerClassBean.getUpdDate());
		return customerClassForm;
	}

	/**
	 * 登録処理
	 * @param customerClassForm
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 */
	public static ActionMessages insert(CustomerClassForm customerClassForm) throws IOException, SQLException {

		String sql = SQLProperties.getInstance().getValue(MCustomerClassDAO.SQLID002);
		List<Object> params = new ArrayList<Object>();
		params.add(customerClassForm.getCode());
		MCustomerClassDAO dao = new MCustomerClassDAO(TransactionInfo.getConnection());
		List<CommonDTO> ret = dao.select(sql, params);
		if (ret.size() > 0) {
			// 得意先分類コード[{0}]は既に使用されています。
			ActionMessages errors = new ActionMessages();
			ActionMessage error = new ActionMessage(
					MsgResourcesConstants.CUSTOMER_CLASS_UPDATE_ERRMSG_DUPLICATE, customerClassForm.getCode());
			errors.add(ActionMessages.GLOBAL_MESSAGE, error);
			return errors;
		}

		sql = SQLProperties.getInstance().getValue(MCustomerClassDAO.SQLID003);
		params = new ArrayList<Object>();
		params.add(CommonUtil.convInteger(customerClassForm.getCode()));
		params.add(customerClassForm.getName());
		params.add(customerClassForm.getKana());
		dao.insert(sql, params);

		return null;
	}

	/**
	 * 更新処理
	 * @param customerClassForm
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 */
	public static ActionMessages update(CustomerClassForm customerClassForm) throws IOException, SQLException {
		String sql = SQLProperties.getInstance().getValue(MCustomerClassDAO.SQLID004);
		List<Object> params = new ArrayList<Object>();
		params.add(customerClassForm.getName());
		params.add(customerClassForm.getKana());
		params.add(customerClassForm.getCode());
		params.add(new Timestamp(Long.parseLong(customerClassForm.getUpdDate())));

		MCustomerClassDAO dao = new MCustomerClassDAO(TransactionInfo.getConnection());
		int ret = dao.update(sql, params);

		if (ret == 0) {
			// 得意先分類コード[{0}]は他のユーザに変更されています。
			ActionMessages errors = new ActionMessages();
			ActionMessage error = new ActionMessage(
					MsgResourcesConstants.CUSTOMER_CLASS_UPDATE_ERRMSG_LOCK, customerClassForm.getCode());
			errors.add(ActionMessages.GLOBAL_MESSAGE, error);
			return errors;
		}

		return null;
	}

	/**
	 * 削除処理
	 * @param listForm
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 */
	public static ActionMessages delete(CustomerClassListForm listForm) throws IOException, SQLException {
		String[] codes = listForm.getSel();

		List<Object> params = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder(SQLProperties.getInstance().getValue(MCustomerClassDAO.SQLID005));
		sql.append(CommonConstants.HALF_SPACE);
		sql.append(CommonConstants.KAKKO_START);
		for (int i = 0; i < codes.length; i++) {
			params.add(codes[i]);
			sql.append(CommonConstants.CAMMA);
			sql.append(CommonConstants.QUESTION);
		}
		sql.append(CommonConstants.KAKKO_END);

		MCustomerClassDAO dao = new MCustomerClassDAO(TransactionInfo.getConnection());

		try {
			dao.delete(sql.toString().replaceFirst(CommonConstants.CAMMA, ""), params);
		} catch (SQLException e) {
			int errCode = e.getErrorCode();
			if (Integer.toString(errCode).equals(
					SQLErrCodeProperties.getInstance().getValue(SQLErrCodeProperties.ERR_FK))) {
				// 外部キー制約エラー時

				// 使用中の得意先分類は削除できません。
				ActionMessages errors = new ActionMessages();
				ActionMessage error = new ActionMessage(
						MsgResourcesConstants.CUSTOMER_CLASS_UPDATE_ERRMSG_FK);
				errors.add(ActionMessages.GLOBAL_MESSAGE, error);
				return errors;
			}
			throw e;
		}

		return null;
	}
}
