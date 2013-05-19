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

import voucher.common.bl.VoucherBL;

import master.item.ItemForm;
import master.item.ItemListForm;
import master.item.bean.MItemBean;
import master.item.dao.MItemDAO;
import master.item.dto.MItemDTO;
import fw.common.date.DateFormatPattern;
import fw.common.date.DateUtility;
import fw.common.db.SQLProperties;
import fw.common.db.dto.CommonDTO;
import fw.common.util.CommonConstants;
import fw.common.util.CommonUtil;
import common.MsgResourcesConstants;
import fw.core.base.TransactionInfo;

/**
 * <p>商品ビジネスロジック</p>
 */
public class ItemBL {

	public static boolean isExitItem() throws IOException, SQLException {
		return !getAllItem().isEmpty();
	}

	/**
	 * 商品リストフォーム取得
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 */
	public static ItemListForm getItemListForm() throws IOException, SQLException {
		ItemListForm itemListForm = new ItemListForm();

		for (CommonDTO ret : getAllItem()) {
			MItemDTO dto = (MItemDTO)ret;
			MItemBean item = new MItemBean();
			item.setCode(dto.getCode());
			item.setClassCode(CommonUtil.convString(dto.getClassCode(),true));
			item.setKana(dto.getKana());
			item.setKicyuStockUpDown(CommonUtil.convString(dto.getKicyuStockUpDown(),false));
			item.setKikaku(dto.getKikaku());
			item.setKisyuPrice((dto.getKisyuPrice() == null) ? null : dto.getKisyuPrice().toPlainString());
			item.setKisyuStock(CommonUtil.convString(dto.getKisyuStock(),false));
			item.setName(dto.getName());
			item.setPrice((dto.getPrice() == null) ? null : dto.getPrice().toPlainString());
			item.setProperStock(CommonUtil.convString(dto.getProperStock(),false));
			item.setSellingPrice2((dto.getSellingPrice2() == null) ? null : dto.getSellingPrice2().toPlainString());
			item.setSellingPrice3((dto.getSellingPrice3() == null) ? null : dto.getSellingPrice3().toPlainString());
			item.setSellingPrice4((dto.getSellingPrice4() == null) ? null : dto.getSellingPrice4().toPlainString());
			item.setSellingPrice5((dto.getSellingPrice5() == null) ? null : dto.getSellingPrice5().toPlainString());
			item.setStandardPrice((dto.getStandardPrice() == null) ? null : dto.getStandardPrice().toPlainString());
			item.setStock(CommonUtil.convString(dto.getStock(),false));
			item.setUnit(dto.getUnit());
			item.setUpdDate(String.valueOf(dto.getUpdDate().getTime()));
			item.setDispUpdDate(DateUtility.getDate(DateFormatPattern.PATTERN_YYYYMMDD_HYPHEN, dto.getUpdDate().getTime()));
			itemListForm.setItem(dto.getCode(),item);
		}

		return itemListForm;
	}

	/**
	 * 商品マスタBean⇒商品フォーム変換
	 * @param itemBean
	 * @return
	 */
	public static ItemForm convMItemBeanToItemForm(MItemBean itemBean) {
		ItemForm itemForm = new ItemForm();
		itemForm.setCode(itemBean.getCode());
		itemForm.setClassCode(itemBean.getClassCode());
		itemForm.setKana(itemBean.getKana());
		itemForm.setKicyuStockUpDown(itemBean.getKicyuStockUpDown());
		itemForm.setKikaku(itemBean.getKikaku());
		itemForm.setKisyuPrice(itemBean.getKisyuPrice());
		itemForm.setKisyuStock(itemBean.getKisyuStock());
		itemForm.setName(itemBean.getName());
		itemForm.setPrice(itemBean.getPrice());
		itemForm.setProperStock(itemBean.getProperStock());
		itemForm.setSellingPrice2(itemBean.getSellingPrice2());
		itemForm.setSellingPrice3(itemBean.getSellingPrice3());
		itemForm.setSellingPrice4(itemBean.getSellingPrice4());
		itemForm.setSellingPrice5(itemBean.getSellingPrice5());
		itemForm.setStandardPrice(itemBean.getStandardPrice());
		itemForm.setStock(itemBean.getStock());
		itemForm.setUnit(itemBean.getUnit());
		itemForm.setUpdDate(itemBean.getUpdDate());
		itemForm.setDispUpdDate(itemBean.getDispUpdDate());
		return itemForm;
	}

	/**
	 * 商品マスタBeanマップ取得
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 */
	public static Map<String, MItemBean> getMItemBeanList() throws IOException, SQLException {
		Map<String, MItemBean> map = new LinkedHashMap<String, MItemBean>();

		for (CommonDTO ret : getAllItem()) {
			MItemDTO dto = (MItemDTO)ret;
			MItemBean item = new MItemBean();
			item.setCode(dto.getCode());
			item.setClassCode(CommonUtil.convString(dto.getClassCode(),true));
			item.setKana(dto.getKana());
			item.setKicyuStockUpDown(CommonUtil.convString(dto.getKicyuStockUpDown(),false));
			item.setKikaku(dto.getKikaku());
			item.setKisyuPrice((dto.getKisyuPrice() == null) ? null : dto.getKisyuPrice().toPlainString());
			item.setKisyuStock(CommonUtil.convString(dto.getKisyuStock(),false));
			item.setName(dto.getName());
			item.setPrice((dto.getPrice() == null) ? null : dto.getPrice().toPlainString());
			item.setProperStock(CommonUtil.convString(dto.getProperStock(),false));
			item.setSellingPrice2((dto.getSellingPrice2() == null) ? null : dto.getSellingPrice2().toPlainString());
			item.setSellingPrice3((dto.getSellingPrice3() == null) ? null : dto.getSellingPrice3().toPlainString());
			item.setSellingPrice4((dto.getSellingPrice4() == null) ? null : dto.getSellingPrice4().toPlainString());
			item.setSellingPrice5((dto.getSellingPrice5() == null) ? null : dto.getSellingPrice5().toPlainString());
			item.setStandardPrice((dto.getStandardPrice() == null) ? null : dto.getStandardPrice().toPlainString());
			item.setStock(CommonUtil.convString(dto.getStock(),false));
			item.setUnit(dto.getUnit());
			item.setUpdDate(String.valueOf(dto.getUpdDate().getTime()));
			map.put(dto.getCode(), item);
		}

		return map;
	}

	private static List<CommonDTO> getAllItem() throws IOException, SQLException {
		MItemDAO dao = new MItemDAO(TransactionInfo.getConnection());
		String sql = SQLProperties.getInstance().getValue(MItemDAO.SQLID001);
		return dao.select(sql, new ArrayList<Object>());
	}

	/**
	 * 登録処理
	 * @param itemForm
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 */
	public static ActionMessages insert(ItemForm itemForm) throws IOException, SQLException {

		String sql = SQLProperties.getInstance().getValue(MItemDAO.SQLID004);
		List<Object> params = new ArrayList<Object>();
		params.add(itemForm.getCode());
		MItemDAO dao = new MItemDAO(TransactionInfo.getConnection());
		List<CommonDTO> ret = dao.select(sql, params);
		if (ret.size() > 0) {
			// 商品コード[{0}]は既に使用されています。
			ActionMessages errors = new ActionMessages();
			ActionMessage error = new ActionMessage(
					MsgResourcesConstants.ITEM_UPDATE_ERRMSG_DUPLICATE, itemForm.getCode());
			errors.add(ActionMessages.GLOBAL_MESSAGE, error);
			return errors;
		}

		sql = SQLProperties.getInstance().getValue(MItemDAO.SQLID003);
		params = new ArrayList<Object>();
		params.add(itemForm.getCode());
		params.add(itemForm.getName());
		params.add(itemForm.getKana());
		params.add(itemForm.getKikaku());
		params.add(CommonUtil.convInteger(itemForm.getClassCode()));
		params.add(CommonUtil.convInteger(itemForm.getStock()));
		params.add(CommonUtil.convInteger(itemForm.getProperStock()));
		params.add(CommonUtil.convInteger(itemForm.getKisyuStock()));
		params.add(CommonUtil.convInteger(itemForm.getKicyuStockUpDown()));
		params.add(itemForm.getUnit());
		params.add(CommonUtil.convDouble(itemForm.getPrice()));
		params.add(CommonUtil.convDouble(itemForm.getKisyuPrice()));
		params.add(CommonUtil.convDouble(itemForm.getStandardPrice()));
		params.add(CommonUtil.convDouble(itemForm.getSellingPrice2()));
		params.add(CommonUtil.convDouble(itemForm.getSellingPrice3()));
		params.add(CommonUtil.convDouble(itemForm.getSellingPrice4()));
		params.add(CommonUtil.convDouble(itemForm.getSellingPrice5()));
		dao.insert(sql, params);

		return null;
	}

	/**
	 * 更新処理
	 * @param itemForm
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 */
	public static ActionMessages update(ItemForm itemForm) throws IOException, SQLException {
		String sql = SQLProperties.getInstance().getValue(MItemDAO.SQLID002);
		List<Object> params = new ArrayList<Object>();
		params.add(itemForm.getName());
		params.add(itemForm.getKana());
		params.add(itemForm.getKikaku());
		params.add(CommonUtil.convInteger(itemForm.getClassCode()));
		params.add(CommonUtil.convInteger(itemForm.getStock()));
		params.add(CommonUtil.convInteger(itemForm.getProperStock()));
		params.add(CommonUtil.convInteger(itemForm.getKisyuStock()));
		params.add(CommonUtil.convInteger(itemForm.getKicyuStockUpDown()));
		params.add(itemForm.getUnit());
		params.add(CommonUtil.convDouble(itemForm.getPrice()));
		params.add(CommonUtil.convDouble(itemForm.getKisyuPrice()));
		params.add(CommonUtil.convDouble(itemForm.getStandardPrice()));
		params.add(CommonUtil.convDouble(itemForm.getSellingPrice2()));
		params.add(CommonUtil.convDouble(itemForm.getSellingPrice3()));
		params.add(CommonUtil.convDouble(itemForm.getSellingPrice4()));
		params.add(CommonUtil.convDouble(itemForm.getSellingPrice5()));
		params.add(itemForm.getCode());
		params.add(new Timestamp(Long.parseLong(itemForm.getUpdDate())));

		MItemDAO dao = new MItemDAO(TransactionInfo.getConnection());
		int ret = dao.update(sql, params);

		if (ret == 0) {
			// 商品コード[{0}]は他のユーザに変更されています。
			ActionMessages errors = new ActionMessages();
			ActionMessage error = new ActionMessage(
					MsgResourcesConstants.ITEM_UPDATE_ERRMSG_LOCK, itemForm.getCode());
			errors.add(ActionMessages.GLOBAL_MESSAGE, error);
			return errors;
		}

		return null;
	}

	/**
	 * 更新処理
	 * @param code
	 * @param sqlId
	 * @param params
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 */
	public static ActionMessages update(String code, String sqlId, List<Object> params) throws IOException, SQLException {
		String sql = SQLProperties.getInstance().getValue(sqlId);
		MItemDAO dao = new MItemDAO(TransactionInfo.getConnection());

		int ret = dao.update(sql, params);
		if (ret == 0) {
			// 商品コード[{0}]は他のユーザに変更されています。
			ActionMessages errors = new ActionMessages();
			ActionMessage error = new ActionMessage(
					MsgResourcesConstants.ITEM_UPDATE_ERRMSG_LOCK, code);
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
	public static ActionMessages delete(ItemListForm listForm) throws IOException, SQLException {
		ActionMessages errors = null;

		String[] codes = listForm.getSel();

		if ((errors = VoucherBL.chkExistItem(codes)) != null) {
			return errors;
		}

		List<Object> params = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder(SQLProperties.getInstance().getValue(MItemDAO.SQLID005));
		sql.append(CommonConstants.HALF_SPACE);
		sql.append(CommonConstants.KAKKO_START);
		for (int i = 0; i < codes.length; i++) {
			params.add(codes[i]);
			sql.append(CommonConstants.CAMMA);
			sql.append(CommonConstants.QUESTION);
		}
		sql.append(CommonConstants.KAKKO_END);

		MItemDAO dao = new MItemDAO(TransactionInfo.getConnection());
		dao.delete(sql.toString().replaceFirst(CommonConstants.CAMMA, ""), params);

		return errors;
	}
}
