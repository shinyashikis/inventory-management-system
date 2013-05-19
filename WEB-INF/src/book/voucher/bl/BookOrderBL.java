package book.voucher.bl;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.BigDecimalConverter;
import org.apache.commons.lang.SerializationUtils;

import book.voucher.BookVoucherListForm;
import book.voucher.bean.BookOrder;
import book.voucher.dao.BookOrderDAO;
import book.voucher.dto.BookOrderDTO;

import prop.ViewProperties;

import fw.common.db.SQLProperties;
import fw.common.db.dto.CommonDTO;
import fw.core.base.TransactionInfo;

/**
 * 注文書ビジネスロジック
 */
public class BookOrderBL {

	private static int DECIMAL_DIGIT = Integer.parseInt(
			ViewProperties.getInstance().getValue(ViewProperties.DECIMAL_DIGIT, ViewProperties.VALUE));

	/**
	 * 注文書リスト取得処理
	 * @param form
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public static List<BookOrder> getBookOrderList(BookVoucherListForm form)
		throws IOException, SQLException, IllegalAccessException, InvocationTargetException {

		BookOrderDAO dao = new BookOrderDAO(TransactionInfo.getConnection());
		List<BookOrder> bookOrderList = new ArrayList<BookOrder>();

		String sql = SQLProperties.getInstance().getValue(BookOrderDAO.SQLID00101);

		// TODO
		Integer from = Integer.parseInt("20120302");
		Integer to = Integer.parseInt("20130301");

		// TODO 締日、ORDER BYを可変に
		List<Object> params = new ArrayList<Object>();

		List<CommonDTO> retList = dao.select(sql.toString(), params);
		for (CommonDTO dto : retList) {
			BookOrderDTO bookOrderDTO = (BookOrderDTO)dto;

			BookOrder bookOrder = new BookOrder();
			bookOrder.setNendo(form.getNendo());
			bookOrder.setFrom(from.toString());
			bookOrder.setTo(to.toString());

			ConvertUtils.register(new BigDecimalConverter(null), BigDecimal.class);
			BeanUtils.copyProperties(bookOrder, SerializationUtils.clone(bookOrderDTO));

			bookOrder.setStaff(new StringBuilder().append(
					bookOrderDTO.getStaffSei()).append(
							bookOrderDTO.getStaffName()).toString());

			// TODO 消費税(セッションからとる)
			BigDecimal tax = bookOrder.getSumTaxExcludedAmount().multiply(
					BigDecimal.valueOf(0.05)).setScale(
							DECIMAL_DIGIT, RoundingMode.DOWN);
			bookOrder.setTax(tax);

			// 税込金額
			BigDecimal taxIncludedAmount = bookOrder.getSumTaxExcludedAmount().add(tax);
			bookOrder.setTaxIncludedAmount(taxIncludedAmount);

			// 注文金額(税込金額 + 調整額)
			bookOrder.setAllPrice(taxIncludedAmount.add(bookOrder.getDiscount()));
			bookOrderList.add(bookOrder);
		}

		return bookOrderList;
	}


}
