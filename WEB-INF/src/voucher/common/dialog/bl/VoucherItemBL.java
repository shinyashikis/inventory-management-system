package voucher.common.dialog.bl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import prop.ViewProperties;

import fw.common.db.SQLProperties;
import fw.common.db.dto.CommonDTO;
import fw.core.base.TransactionInfo;

import voucher.common.bean.DetailBean;
import voucher.common.bean.VoucherBean;
import voucher.common.dialog.dao.VoucherItemDAO;
import voucher.common.dialog.dto.VoucherItemDTO;

/**
 * <p>(伝票)商品ビジネスロジック</p>
 */
public class VoucherItemBL {

	/**
	 * 明細BeanMap取得処理
	 *
	 * @param voucherBean
	 * @param targetItemCode
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 */
	public static Map<String, DetailBean> getDetailBeanMap(
			VoucherBean voucherBean, String targetItemCode) throws IOException, SQLException {
		return createDetailBeanMap(voucherBean, targetItemCode, true);
	}

	/**
	 * 明細BeanMap取得処理
	 *
	 * @param voucherBean
	 * @param targetItemCode
	 * @param searchLike
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 */
	public static Map<String, DetailBean> getDetailBeanMap(
			VoucherBean voucherBean, String targetItemCode, boolean searchLike) throws IOException, SQLException {
		return createDetailBeanMap(voucherBean, targetItemCode, searchLike);
	}

	/**
	 * 明細BeanMap生成処理
	 *
	 * @param voucherBean
	 * @param targetItemCode
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 */
	private static Map<String, DetailBean> createDetailBeanMap(
			VoucherBean voucherBean, String targetItemCode, boolean searchLike) throws IOException, SQLException {

		Map<String, DetailBean> detailBeanMap = new LinkedHashMap<String, DetailBean>();

		VoucherItemDAO dao = new VoucherItemDAO(TransactionInfo.getConnection());
		String sql = SQLProperties.getInstance().getValue(VoucherItemDAO.SQLID001);

		List<Object> params = new ArrayList<Object>();
		if (!searchLike) {
			// 完全一致検索

			params.add(targetItemCode);

		} else {
			// 部分一致検索

			if (targetItemCode == null) {
				params.add("%%");
			} else {
				params.add(
						new StringBuilder().append("%")
						.append(targetItemCode)
						.append("%").toString()
						);
			}
		}

		List<CommonDTO> retList = dao.select(sql, params);

		for (CommonDTO ret : retList) {
			VoucherItemDTO dto = (VoucherItemDTO)ret;

			String itemCode = dto.getItemCode();

			DetailBean detailBean = new DetailBean();
			detailBean.setVoucherNo(voucherBean.getVoucherNo());
			detailBean.setItemCode(dto.getItemCode());
			detailBean.setItemName(dto.getItemName());
			detailBean.setItemKikaku(dto.getItemKikaku());
			detailBean.setItemUnit(dto.getItemUnit());

			ViewProperties viewProp = ViewProperties.getInstance();

			String dealKind = voucherBean.getDealBean().getDealKind();
			if (viewProp.getValue(ViewProperties.DEAL_KIND_CUSTOMER, ViewProperties.VALUE).equals(dealKind)) {
				// 取引先区分が「得意先」の場合

				// 取引先価格区分によって、使用する販売単価を決める
				String priceDivision = voucherBean.getDealBean().getPriceDivision();
				if (priceDivision == null ||
						viewProp.getValue(ViewProperties.SALE_PRICE_DIVISION_1, ViewProperties.VALUE).equals(priceDivision)) {
					detailBean.setItemUnitPrice(dto.getItemUnitPrice1().toString());
				} else if (viewProp.getValue(ViewProperties.SALE_PRICE_DIVISION_2, ViewProperties.VALUE).equals(priceDivision)) {
					detailBean.setItemUnitPrice(dto.getItemUnitPrice2().toString());
				} else if (viewProp.getValue(ViewProperties.SALE_PRICE_DIVISION_3, ViewProperties.VALUE).equals(priceDivision)) {
					detailBean.setItemUnitPrice(dto.getItemUnitPrice3().toString());
				} else if (viewProp.getValue(ViewProperties.SALE_PRICE_DIVISION_4, ViewProperties.VALUE).equals(priceDivision)) {
					detailBean.setItemUnitPrice(dto.getItemUnitPrice4().toString());
				} else if (viewProp.getValue(ViewProperties.SALE_PRICE_DIVISION_5, ViewProperties.VALUE).equals(priceDivision)) {
					detailBean.setItemUnitPrice(dto.getItemUnitPrice5().toString());
				} else {
					throw new IllegalArgumentException();
				}

			} else if (viewProp.getValue(ViewProperties.DEAL_KIND_SUPPLIER, ViewProperties.VALUE).equals(dealKind)) {
				// 取引先区分が「仕入先」の場合

				// 単価には仕入金額を設定する
				detailBean.setItemUnitPrice(dto.getItemPurchasePrice().toString());
			} else {
				throw new IllegalArgumentException();
			}

			detailBean.setItemPurchasePrice(dto.getItemPurchasePrice().toString());

			detailBeanMap.put(itemCode, detailBean);
		}

		return detailBeanMap;
	}

}
