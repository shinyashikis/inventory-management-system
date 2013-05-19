package book.item.bl;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.BigDecimalConverter;
import org.apache.commons.lang.SerializationUtils;

import prop.ViewProperties;

import book.item.BookItemForm;
import book.item.bean.BookItemStock;
import book.item.dao.BookItemStockDAO;
import book.item.dto.BookItemStockDTO;
import fw.common.db.SQLProperties;
import fw.common.db.dto.CommonDTO;
import fw.common.util.CommonConstants;
import fw.core.base.TransactionInfo;

/**
 * 商品在庫表ビジネスロジック
 */
public class BookItemStockBL {

	/**
	 * 商品在庫表リスト取得処理
	 * @param form
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public static List<BookItemStock> getBookItemStockList(BookItemForm form)
		throws IOException, SQLException, IllegalAccessException, InvocationTargetException {

		BookItemStockDAO dao = new BookItemStockDAO(TransactionInfo.getConnection());
		List<BookItemStock> bookItemStockList = new ArrayList<BookItemStock>();

		List<Object> params = new ArrayList<Object>();

		StringBuilder sql = new StringBuilder();
		sql.append(choiceSQLSelect(form, params));
		sql.append(CommonConstants.HALF_SPACE);
		sql.append(choiceSQLSort(form));

		List<CommonDTO> retList = dao.select(sql.toString(), params);
		for (CommonDTO dto : retList) {
			BookItemStock bookItemStock = new BookItemStock();
			ConvertUtils.register(new BigDecimalConverter(null), BigDecimal.class);
			BeanUtils.copyProperties(bookItemStock, SerializationUtils.clone((BookItemStockDTO)dto));
			bookItemStock.setKabusoku(BookItemCommonBL.calcKabusoku(bookItemStock.getProperStock(), bookItemStock.getStock()));
			bookItemStockList.add(bookItemStock);
		}

		return bookItemStockList;
	}

	/**
	 * SELECT文選択
	 * @param form
	 * @param params
	 * @return
	 * @throws IOException
	 */
	private static String choiceSQLSelect(BookItemForm form, List<Object> params) throws IOException {

		if (BookItemCommonBL.isSelectedAllItemClass(form)) {
			// 商品分類指定「分類なし」

			if (BookItemCommonBL.isSelectedAllItem(form)) {
				// 商品指定「全商品」

				return choiceSQLSelect(form, 1);

			} else {
				// 商品指定 あり

				params.add(form.getItemCondition());
				return choiceSQLSelect(form, 3);
			}

		} else {
			// 商品分類指定 あり

			if (BookItemCommonBL.isSelectedAllItem(form)) {
				// 商品指定「全商品」

				params.add(form.getItemClassCondition());
				return choiceSQLSelect(form, 5);
			} else {
				// 商品指定 あり

				params.add(form.getItemCondition());
				params.add(form.getItemClassCondition());
				return choiceSQLSelect(form, 7);
			}
		}
	}

	/**
	 * SELECT文選択
	 * @param form
	 * @param index
	 * @return
	 * @throws IOException
	 */
	private static String choiceSQLSelect(BookItemForm form, int index) throws IOException {
		StringBuilder sb = new StringBuilder();

		String dispTargetDetail = form.getDispTargetDetail();
		if (ViewProperties.getInstance().getValue(
				ViewProperties.BOOK_DISP_DETAIL_COUNT, ViewProperties.VALUE).equals(dispTargetDetail)) {
			// 「数量表示」
			index += 0;

		} else if (ViewProperties.getInstance().getValue(
				ViewProperties.BOOK_DISP_DETAIL_PRICE, ViewProperties.VALUE).equals(dispTargetDetail)) {
			// 「金額表示」
			index += 1;

		} else {
			throw new IllegalArgumentException();
		}

		switch (index) {
		case 1:
			return sb.append(SQLProperties.getInstance().getValue(BookItemStockDAO.SQLID00101)).toString();
		case 2:
			return sb.append(SQLProperties.getInstance().getValue(BookItemStockDAO.SQLID00102)).toString();
		case 3:
			return sb.append(SQLProperties.getInstance().getValue(BookItemStockDAO.SQLID00103)).toString();
		case 4:
			return sb.append(SQLProperties.getInstance().getValue(BookItemStockDAO.SQLID00104)).toString();
		case 5:
			return sb.append(SQLProperties.getInstance().getValue(BookItemStockDAO.SQLID00105)).toString();
		case 6:
			return sb.append(SQLProperties.getInstance().getValue(BookItemStockDAO.SQLID00106)).toString();
		case 7:
			return sb.append(SQLProperties.getInstance().getValue(BookItemStockDAO.SQLID00107)).toString();
		case 8:
			return sb.append(SQLProperties.getInstance().getValue(BookItemStockDAO.SQLID00108)).toString();
		default:
			throw new IllegalArgumentException();
		}
	}

	/**
	 * Sort句選択
	 * @param form
	 * @return
	 * @throws IOException
	 */
	private static String choiceSQLSort(BookItemForm form) throws IOException {

		String selectDispSort = form.getSelectDispSort();
		String selectSortKind = form.getSelectSortKind();
		String selectSort = form.getSelectSort();

		int index = 1;
		ViewProperties viewProp = ViewProperties.getInstance();

		if (viewProp.getValue(
				ViewProperties.BOOK_SELECT_DISP_SORT_ITEM, ViewProperties.VALUE).equals(selectDispSort)) {
			// 商品
			index += 0;
		} else if (viewProp.getValue(
				ViewProperties.BOOK_SELECT_DISP_SORT_ITEM_CLASS, ViewProperties.VALUE).equals(selectDispSort)) {
			// 商品分類
			index += 4;
		} else {
			throw new IllegalArgumentException();
		}

		if (viewProp.getValue(
				ViewProperties.BOOK_SELECT_SORT_KIND_KANA, ViewProperties.VALUE).equals(selectSortKind)) {
			// ふりがな
			index += 0;
		} else if (viewProp.getValue(
				ViewProperties.BOOK_SELECT_SORT_KIND_CODE, ViewProperties.VALUE).equals(selectSortKind)) {
			// コード
			index += 2;
		} else {
			throw new IllegalArgumentException();
		}

		if (viewProp.getValue(
				ViewProperties.BOOK_SELECT_SORT_ASC, ViewProperties.VALUE).equals(selectSort)) {
			// 昇順
			index += 0;
		} else if (viewProp.getValue(
				ViewProperties.BOOK_SELECT_SORT_DSC, ViewProperties.VALUE).equals(selectSort)) {
			// 降順
			index += 1;
		} else {
			throw new IllegalArgumentException();
		}

		StringBuilder sb = new StringBuilder();

		switch (index) {
		case 1:
			sb.append(SQLProperties.getInstance().getValue(BookItemStockDAO.SQLID00109));
			break;
		case 2:
			sb.append(SQLProperties.getInstance().getValue(BookItemStockDAO.SQLID00110));
			break;
		case 3:
			sb.append(SQLProperties.getInstance().getValue(BookItemStockDAO.SQLID00111));
			break;
		case 4:
			sb.append(SQLProperties.getInstance().getValue(BookItemStockDAO.SQLID00112));
			break;
		case 5:
			sb.append(SQLProperties.getInstance().getValue(BookItemStockDAO.SQLID00113));
			break;
		case 6:
			sb.append(SQLProperties.getInstance().getValue(BookItemStockDAO.SQLID00114));
			break;
		case 7:
			sb.append(SQLProperties.getInstance().getValue(BookItemStockDAO.SQLID00115));
			break;
		case 8:
			sb.append(SQLProperties.getInstance().getValue(BookItemStockDAO.SQLID00116));
			break;
		default:
			throw new IllegalArgumentException();
		}

		return sb.toString();
	}

}
