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
import book.voucher.bean.BookBillTotal;
import book.voucher.dao.BookBillTotalDAO;
import book.voucher.dto.BookBillTotalDTO;

import prop.ViewProperties;

import fw.common.db.SQLProperties;
import fw.common.db.dto.CommonDTO;
import fw.core.base.TransactionInfo;

/**
 * 合計請求書ビジネスロジック
 */
public class BookBillTotalBL {

	private static int DECIMAL_DIGIT = Integer.parseInt(
			ViewProperties.getInstance().getValue(ViewProperties.DECIMAL_DIGIT, ViewProperties.VALUE));

	/**
	 * 合計請求書リスト取得処理
	 * @param form
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public static List<BookBillTotal> getBookBillTotalList(BookVoucherListForm form)
		throws IOException, SQLException, IllegalAccessException, InvocationTargetException {

		BookBillTotalDAO dao = new BookBillTotalDAO(TransactionInfo.getConnection());
		List<BookBillTotal> bookBillTotalList = new ArrayList<BookBillTotal>();

		String sql = SQLProperties.getInstance().getValue(BookBillTotalDAO.SQLID00101);

		// TODO
		Integer from = Integer.parseInt("20120302");
		Integer to = Integer.parseInt("20130301");

		// TODO 締日、ORDER BYを可変に
		List<Object> params = new ArrayList<Object>();

		List<CommonDTO> retList = dao.select(sql.toString(), params);
		for (CommonDTO dto : retList) {
			BookBillTotalDTO bookBillTotalDTO = (BookBillTotalDTO)dto;

			BookBillTotal bookBillTotal = new BookBillTotal();
			bookBillTotal.setNendo(form.getNendo());
			bookBillTotal.setFrom(from.toString());
			bookBillTotal.setTo(to.toString());

			ConvertUtils.register(new BigDecimalConverter(null), BigDecimal.class);
			BeanUtils.copyProperties(bookBillTotal, SerializationUtils.clone(bookBillTotalDTO));

			bookBillTotal.setStaff(new StringBuilder().append(
					bookBillTotalDTO.getStaffSei()).append(
							bookBillTotalDTO.getStaffName()).toString());

			// TODO 消費税(セッションからとる)
			BigDecimal tax = bookBillTotal.getSumTaxExcludedAmount().multiply(
					BigDecimal.valueOf(0.05)).setScale(
							DECIMAL_DIGIT, RoundingMode.DOWN);
			bookBillTotal.setTax(tax);

			// 税込金額
			BigDecimal taxIncludedAmount = bookBillTotal.getSumTaxExcludedAmount().add(tax);
			bookBillTotal.setTaxIncludedAmount(taxIncludedAmount);

			// 請求金額(税込金額 + 調整額)
			bookBillTotal.setAllPrice(taxIncludedAmount.add(bookBillTotal.getDiscount()));
			bookBillTotalList.add(bookBillTotal);
		}

		return bookBillTotalList;
	}


}
