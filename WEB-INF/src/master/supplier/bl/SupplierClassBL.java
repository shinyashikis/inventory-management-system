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

import master.supplier.SupplierClassForm;
import master.supplier.SupplierClassListForm;
import master.supplier.bean.MSupplierClassBean;
import master.supplier.dao.MSupplierClassDAO;
import master.supplier.dto.MSupplierClassDTO;
import fw.common.db.SQLErrCodeProperties;
import fw.common.db.SQLProperties;
import fw.common.db.dto.CommonDTO;
import fw.common.util.CommonConstants;
import fw.common.util.CommonUtil;
import common.MsgResourcesConstants;
import fw.core.base.TransactionInfo;

public class SupplierClassBL {

	/**
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 */
	public static Map<String, MSupplierClassBean> getMSupplierClassBeanMap() throws IOException, SQLException {
		Map<String, MSupplierClassBean> map = new LinkedHashMap<String, MSupplierClassBean>();
		for (CommonDTO ret : getAllSupplierClass()) {
			MSupplierClassBean supplierClass =
				convMSupplierClassDTOtoMSupplierClassBean((MSupplierClassDTO)ret);
			map.put(supplierClass.getCode(), supplierClass);
		}
		return map;
	}

	/**
	 * 仕入先分類一覧フォーム取得
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 */
	public static SupplierClassListForm getSupplierClassListForm() throws IOException, SQLException {
		SupplierClassListForm supplierClassListForm = new SupplierClassListForm();
		for (CommonDTO ret : getAllSupplierClass()) {
			MSupplierClassBean supplierClass =
				convMSupplierClassDTOtoMSupplierClassBean((MSupplierClassDTO)ret);
			supplierClassListForm.setSupplierClass(supplierClass.getCode(), supplierClass);
		}
		return supplierClassListForm;
	}

	/**
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 */
	private static List<CommonDTO> getAllSupplierClass() throws IOException, SQLException {
		MSupplierClassDAO dao = new MSupplierClassDAO(TransactionInfo.getConnection());
		String sql = SQLProperties.getInstance().getValue(MSupplierClassDAO.SQLID001);
		return dao.select(sql, new ArrayList<Object>());
	}

	/**
	 * @param dto
	 * @return
	 */
	private static MSupplierClassBean convMSupplierClassDTOtoMSupplierClassBean(MSupplierClassDTO dto) {
		MSupplierClassBean supplierClass = new MSupplierClassBean();
		String code = CommonUtil.convString(dto.getCode(),true);
		supplierClass.setCode(code);
		supplierClass.setKana(dto.getKana());
		supplierClass.setName(dto.getName());
		supplierClass.setUpdDate(String.valueOf(dto.getUpdDate().getTime()));
		return supplierClass;
	}

	/**
	 * 仕入先分類マスタBean⇒仕入先分類フォーム変換
	 * @param supplierClassBean
	 * @return
	 */
	public static SupplierClassForm convMSupplierClassBeanToSupplierClassForm(MSupplierClassBean supplierClassBean) {
		SupplierClassForm supplierClassForm = new SupplierClassForm();
		supplierClassForm.setCode(supplierClassBean.getCode());
		supplierClassForm.setKana(supplierClassBean.getKana());
		supplierClassForm.setName(supplierClassBean.getName());
		supplierClassForm.setUpdDate(supplierClassBean.getUpdDate());
		return supplierClassForm;
	}

	/**
	 * 登録処理
	 * @param supplierClassForm
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 */
	public static ActionMessages insert(SupplierClassForm supplierClassForm) throws IOException, SQLException {

		String sql = SQLProperties.getInstance().getValue(MSupplierClassDAO.SQLID002);
		List<Object> params = new ArrayList<Object>();
		params.add(supplierClassForm.getCode());
		MSupplierClassDAO dao = new MSupplierClassDAO(TransactionInfo.getConnection());
		List<CommonDTO> ret = dao.select(sql, params);
		if (ret.size() > 0) {
			// 仕入先分類コード[{0}]は既に使用されています。
			ActionMessages errors = new ActionMessages();
			ActionMessage error = new ActionMessage(
					MsgResourcesConstants.SUPPLIER_CLASS_UPDATE_ERRMSG_DUPLICATE, supplierClassForm.getCode());
			errors.add(ActionMessages.GLOBAL_MESSAGE, error);
			return errors;
		}

		sql = SQLProperties.getInstance().getValue(MSupplierClassDAO.SQLID003);
		params = new ArrayList<Object>();
		params.add(CommonUtil.convInteger(supplierClassForm.getCode()));
		params.add(supplierClassForm.getName());
		params.add(supplierClassForm.getKana());
		dao.insert(sql, params);

		return null;
	}

	/**
	 * 更新処理
	 * @param supplierClassForm
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 */
	public static ActionMessages update(SupplierClassForm supplierClassForm) throws IOException, SQLException {
		String sql = SQLProperties.getInstance().getValue(MSupplierClassDAO.SQLID004);
		List<Object> params = new ArrayList<Object>();
		params.add(supplierClassForm.getName());
		params.add(supplierClassForm.getKana());
		params.add(supplierClassForm.getCode());
		params.add(new Timestamp(Long.parseLong(supplierClassForm.getUpdDate())));

		MSupplierClassDAO dao = new MSupplierClassDAO(TransactionInfo.getConnection());
		int ret = dao.update(sql, params);

		if (ret == 0) {
			// 仕入先分類コード[{0}]は他のユーザに変更されています。
			ActionMessages errors = new ActionMessages();
			ActionMessage error = new ActionMessage(
					MsgResourcesConstants.SUPPLIER_CLASS_UPDATE_ERRMSG_LOCK, supplierClassForm.getCode());
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
	public static ActionMessages delete(SupplierClassListForm listForm) throws IOException, SQLException {
		String[] codes = listForm.getSel();

		List<Object> params = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder(SQLProperties.getInstance().getValue(MSupplierClassDAO.SQLID005));
		sql.append(CommonConstants.HALF_SPACE);
		sql.append(CommonConstants.KAKKO_START);
		for (int i = 0; i < codes.length; i++) {
			params.add(codes[i]);
			sql.append(CommonConstants.CAMMA);
			sql.append(CommonConstants.QUESTION);
		}
		sql.append(CommonConstants.KAKKO_END);

		MSupplierClassDAO dao = new MSupplierClassDAO(TransactionInfo.getConnection());

		try {
			dao.delete(sql.toString().replaceFirst(CommonConstants.CAMMA, ""), params);
		} catch (SQLException e) {
			int errCode = e.getErrorCode();
			if (Integer.toString(errCode).equals(
					SQLErrCodeProperties.getInstance().getValue(SQLErrCodeProperties.ERR_FK))) {
				// 外部キー制約エラー時

				// 使用中の仕入先分類は削除できません。
				ActionMessages errors = new ActionMessages();
				ActionMessage error = new ActionMessage(
						MsgResourcesConstants.SUPPLIER_CLASS_UPDATE_ERRMSG_FK);
				errors.add(ActionMessages.GLOBAL_MESSAGE, error);
				return errors;
			}
			throw e;
		}
		return null;
	}
}
