package master.staff.bl;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import master.staff.StaffForm;
import master.staff.StaffListForm;
import master.staff.bean.MStaffBean;
import master.staff.dao.MStaffDAO;
import master.staff.dto.MStaffDTO;

import fw.common.db.SQLProperties;
import fw.common.db.dto.CommonDTO;
import fw.common.util.CommonConstants;
import common.MsgResourcesConstants;
import fw.core.base.TransactionInfo;

/**
 * <p>担当者ビジネスロジック</p>
 */
public class StaffBL {

	/**
	 * 担当者一覧フォーム取得
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 */
	public static StaffListForm getStaffListForm() throws IOException, SQLException {
		StaffListForm staffListForm = new StaffListForm();
		for (CommonDTO ret : getAllStaff()) {
			MStaffBean staff = convMStaffDTOtoMStaffBean((MStaffDTO)ret);
			staffListForm.setStaff(staff.getCode(), staff);
		}
		return staffListForm;
	}

	/**
	 * @return
	 * @throws SQLException
	 * @throws IOException
	 */
	public static Map<String, MStaffBean> getMStaffBeanMap() throws IOException, SQLException {
		Map<String, MStaffBean> map = new LinkedHashMap<String, MStaffBean>();
		for (CommonDTO ret : getAllStaff()) {
			MStaffBean staff = convMStaffDTOtoMStaffBean((MStaffDTO)ret);
			map.put(staff.getCode(), staff);
		}
		return map;
	}

	/**
	 * 担当者マスタBean取得
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 */
	public static List<MStaffBean> getMStaffBeanList() throws IOException, SQLException {
		List<MStaffBean> staffBeanList = new ArrayList<MStaffBean>();
		for (CommonDTO ret : getAllStaff()) {
			MStaffBean staff = convMStaffDTOtoMStaffBean((MStaffDTO)ret);
			staffBeanList.add(staff);
		}
		return staffBeanList;
	}

	/**
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 */
	private static List<CommonDTO> getAllStaff() throws IOException, SQLException {
		MStaffDAO dao = new MStaffDAO(TransactionInfo.getConnection());
		String sql = SQLProperties.getInstance().getValue(MStaffDAO.SQLID001);
		return dao.select(sql, new ArrayList<Object>());
	}

	/**
	 * @param dto
	 * @return
	 */
	private static MStaffBean convMStaffDTOtoMStaffBean(MStaffDTO dto) {
		MStaffBean staff = new MStaffBean();
		staff.setCode(Integer.toString(dto.getCode()));
		staff.setSei(dto.getSei());
		staff.setName(dto.getName());
		staff.setSeiKana(dto.getSeiKana());
		staff.setNameKana(dto.getNameKana());
		staff.setBusyo(dto.getBusyo());
		staff.setUpdDate(String.valueOf(dto.getUpdDate().getTime()));
		return staff;
	}

	/**
	 * 担当者マスタBean⇒担当者フォーム変換
	 * @param staffBean
	 * @return
	 */
	public static StaffForm convMStaffBeanToStaffForm(MStaffBean staffBean) {
		StaffForm staffForm = new StaffForm();
		staffForm.setCode(staffBean.getCode());
		staffForm.setSei(staffBean.getSei());
		staffForm.setName(staffBean.getName());
		staffForm.setSeiKana(staffBean.getSeiKana());
		staffForm.setNameKana(staffBean.getNameKana());
		staffForm.setBusyo(staffBean.getBusyo());
		staffForm.setUpdDate(staffBean.getUpdDate());
		return staffForm;
	}

	/**
	 * 登録処理
	 * @param staffForm
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 */
	public static ActionMessages insert(StaffForm staffForm) throws IOException, SQLException {

		String sql = SQLProperties.getInstance().getValue(MStaffDAO.SQLID005);
		List<Object> params = new ArrayList<Object>();
		params.add(staffForm.getCode());
		MStaffDAO dao = new MStaffDAO(TransactionInfo.getConnection());
		List<CommonDTO> ret = dao.select(sql, params);
		if (ret.size() > 0) {
			// 担当者コード[{0}]は既に使用されています。
			ActionMessages errors = new ActionMessages();
			ActionMessage error = new ActionMessage(
					MsgResourcesConstants.STAFF_UPDATE_ERRMSG_DUPLICATE, staffForm.getCode());
			errors.add(ActionMessages.GLOBAL_MESSAGE, error);
			return errors;
		}

		sql = SQLProperties.getInstance().getValue(MStaffDAO.SQLID004);
		params = new ArrayList<Object>();
		params.add(staffForm.getCode());
		params.add(staffForm.getSei());
		params.add(staffForm.getName());
		params.add(staffForm.getSeiKana());
		params.add(staffForm.getNameKana());
		params.add(staffForm.getBusyo());
		dao.insert(sql, params);

		return null;
	}

	/**
	 * 更新処理
	 * @param staffForm
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 */
	public static ActionMessages update(StaffForm staffForm) throws IOException, SQLException {

		String sql = SQLProperties.getInstance().getValue(MStaffDAO.SQLID003);
		List<Object> params = new ArrayList<Object>();
		params.add(staffForm.getSei());
		params.add(staffForm.getName());
		params.add(staffForm.getSeiKana());
		params.add(staffForm.getNameKana());
		params.add(staffForm.getBusyo());
		params.add(staffForm.getCode());
		params.add(new Timestamp(Long.parseLong(staffForm.getUpdDate())));

		MStaffDAO dao = new MStaffDAO(TransactionInfo.getConnection());
		int ret = dao.update(sql, params);

		if (ret == 0) {
			// 担当者コード[{0}]は他のユーザに変更されています。
			ActionMessages errors = new ActionMessages();
			ActionMessage error = new ActionMessage(
					MsgResourcesConstants.STAFF_UPDATE_ERRMSG_LOCK, staffForm.getCode());
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
	public static void delete(StaffListForm listForm) throws IOException, SQLException {
		String[] codes = listForm.getSel();

		List<Object> params = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder(SQLProperties.getInstance().getValue(MStaffDAO.SQLID002));
		sql.append(CommonConstants.HALF_SPACE);
		sql.append(CommonConstants.KAKKO_START);
		for (int i = 0; i < codes.length; i++) {
			params.add(codes[i]);
			sql.append(CommonConstants.CAMMA);
			sql.append(CommonConstants.QUESTION);
		}
		sql.append(CommonConstants.KAKKO_END);

		MStaffDAO dao = new MStaffDAO(TransactionInfo.getConnection());
		dao.delete(sql.toString().replaceFirst(CommonConstants.CAMMA, ""), params);
	}
}
