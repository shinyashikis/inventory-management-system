package master.item.bl;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import master.item.ItemClassForm;
import master.item.ItemClassListForm;
import master.item.bean.MItemClassBean;
import master.item.dao.MItemClassDAO;
import master.item.dto.MItemClassDTO;
import fw.common.db.SQLErrCodeProperties;
import fw.common.db.SQLProperties;
import fw.common.db.dto.CommonDTO;
import fw.common.util.CommonConstants;
import fw.common.util.CommonUtil;
import common.MsgResourcesConstants;
import fw.core.base.TransactionInfo;

/**
 * <p>商品分類ビジネスロジック</p>
 */
public class ItemClassBL {

	/**
	 * 商品分類リストフォーム取得
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 */
	public static ItemClassListForm getItemClassListForm() throws IOException, SQLException {
		ItemClassListForm itemClassListForm = new ItemClassListForm();

		for (CommonDTO ret : getAllItemClass()) {
			MItemClassDTO dto = (MItemClassDTO)ret;
			MItemClassBean itemClass = new MItemClassBean();
			String code = CommonUtil.convString(dto.getCode(),true);
			itemClass.setCode(code);
			itemClass.setKana(dto.getKana());
			itemClass.setName(dto.getName());
			itemClass.setUpdDate(String.valueOf(dto.getUpdDate().getTime()));
			itemClassListForm.setItemClass(code,itemClass);
		}

		return itemClassListForm;
	}

	/**
	 * 商品分類マスタBean⇒商品分類フォーム変換
	 * @param itemClassBean
	 * @return
	 */
	public static ItemClassForm convMItemClassBeanToItemClassForm(MItemClassBean itemClassBean) {
		ItemClassForm itemClassForm = new ItemClassForm();
		itemClassForm.setCode(itemClassBean.getCode());
		itemClassForm.setKana(itemClassBean.getKana());
		itemClassForm.setName(itemClassBean.getName());
		itemClassForm.setUpdDate(itemClassBean.getUpdDate());
		return itemClassForm;
	}

	/**
	 * 商品分類マスタBeanマップ取得
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 */
	public static Map<String, MItemClassBean> getMItemClassBeanMap() throws IOException, SQLException {
		Map<String, MItemClassBean> map = new LinkedHashMap<String, MItemClassBean>();
		for (CommonDTO ret : getAllItemClass()) {
			MItemClassDTO dto = (MItemClassDTO)ret;
			MItemClassBean itemClass = new MItemClassBean();
			String code = CommonUtil.convString(dto.getCode(),true);
			itemClass.setCode(code);
			itemClass.setKana(dto.getKana());
			itemClass.setName(dto.getName());
			itemClass.setUpdDate(String.valueOf(dto.getUpdDate().getTime()));
			map.put(code,itemClass);
		}

		return map;
	}

	private static List<CommonDTO> getAllItemClass() throws IOException, SQLException {
		MItemClassDAO dao = new MItemClassDAO(TransactionInfo.getConnection());
		String sql = SQLProperties.getInstance().getValue(MItemClassDAO.SQLID001);
		return dao.select(sql, new ArrayList<Object>());
	}

	/**
	 * 登録処理
	 * @param itemClassForm
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 */
	public static ActionMessages insert(ItemClassForm itemClassForm) throws IOException, SQLException {

		String sql = SQLProperties.getInstance().getValue(MItemClassDAO.SQLID002);
		List<Object> params = new ArrayList<Object>();
		params.add(itemClassForm.getCode());
		MItemClassDAO dao = new MItemClassDAO(TransactionInfo.getConnection());
		List<CommonDTO> ret = dao.select(sql, params);
		if (ret.size() > 0) {
			// 商品分類コード[{0}]は既に使用されています。
			ActionMessages errors = new ActionMessages();
			ActionMessage error = new ActionMessage(
					MsgResourcesConstants.ITEM_CLASS_UPDATE_ERRMSG_DUPLICATE, itemClassForm.getCode());
			errors.add(ActionMessages.GLOBAL_MESSAGE, error);
			return errors;
		}

		sql = SQLProperties.getInstance().getValue(MItemClassDAO.SQLID003);
		params = new ArrayList<Object>();
		params.add(CommonUtil.convInteger(itemClassForm.getCode()));
		params.add(itemClassForm.getName());
		params.add(itemClassForm.getKana());
		dao.insert(sql, params);

		return null;
	}

	/**
	 * 更新処理
	 * @param itemClassForm
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 */
	public static ActionMessages update(ItemClassForm itemClassForm) throws IOException, SQLException {
		String sql = SQLProperties.getInstance().getValue(MItemClassDAO.SQLID004);
		List<Object> params = new ArrayList<Object>();
		params.add(itemClassForm.getName());
		params.add(itemClassForm.getKana());
		params.add(itemClassForm.getCode());
		params.add(new Timestamp(Long.parseLong(itemClassForm.getUpdDate())));

		MItemClassDAO dao = new MItemClassDAO(TransactionInfo.getConnection());
		int ret = dao.update(sql, params);

		if (ret == 0) {
			// 商品分類コード[{0}]は他のユーザに変更されています。
			ActionMessages errors = new ActionMessages();
			ActionMessage error = new ActionMessage(
					MsgResourcesConstants.ITEM_CLASS_UPDATE_ERRMSG_LOCK, itemClassForm.getCode());
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
	public static ActionMessages delete(ItemClassListForm listForm) throws IOException, SQLException {
		String[] codes = listForm.getSel();

		List<Object> params = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder(SQLProperties.getInstance().getValue(MItemClassDAO.SQLID005));
		sql.append(CommonConstants.HALF_SPACE);
		sql.append(CommonConstants.KAKKO_START);
		for (int i = 0; i < codes.length; i++) {
			params.add(codes[i]);
			sql.append(CommonConstants.CAMMA);
			sql.append(CommonConstants.QUESTION);
		}
		sql.append(CommonConstants.KAKKO_END);

		MItemClassDAO dao = new MItemClassDAO(TransactionInfo.getConnection());

		try {
			dao.delete(sql.toString().replaceFirst(CommonConstants.CAMMA, ""), params);
		} catch (SQLException e) {
			int errCode = e.getErrorCode();
			if (Integer.toString(errCode).equals(
					SQLErrCodeProperties.getInstance().getValue(SQLErrCodeProperties.ERR_FK))) {
				// 外部キー制約エラー時

				// 使用中の商品分類は削除できません。
				ActionMessages errors = new ActionMessages();
				ActionMessage error = new ActionMessage(
						MsgResourcesConstants.ITEM_CLASS_UPDATE_ERRMSG_FK);
				errors.add(ActionMessages.GLOBAL_MESSAGE, error);
				return errors;
			}
			throw e;
		}
		return null;
	}
}
