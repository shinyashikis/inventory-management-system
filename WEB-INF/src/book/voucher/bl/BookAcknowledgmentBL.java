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
import book.voucher.bean.BookAcknowledgment;
import book.voucher.dao.BookAcknowledgmentDAO;
import book.voucher.dto.BookAcknowledgmentDTO;

import prop.ViewProperties;

import fw.common.db.SQLProperties;
import fw.common.db.dto.CommonDTO;
import fw.core.base.TransactionInfo;

/**
 * 領収書ビジネスロジック
 */
public class BookAcknowledgmentBL {

	private static int DECIMAL_DIGIT = Integer.parseInt(
			ViewProperties.getInstance().getValue(ViewProperties.DECIMAL_DIGIT, ViewProperties.VALUE));

	/**
	 * 領収書リスト取得処理
	 * @param form
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public static List<BookAcknowledgment> getBookAcknowledgmentList(BookVoucherListForm form)
		throws IOException, SQLException, IllegalAccessException, InvocationTargetException {

		BookAcknowledgmentDAO dao = new BookAcknowledgmentDAO(TransactionInfo.getConnection());
		List<BookAcknowledgment> bookAcknowledgmentList = new ArrayList<BookAcknowledgment>();

		String sql = SQLProperties.getInstance().getValue(BookAcknowledgmentDAO.SQLID00101);

		// TODO
		Integer from = Integer.parseInt("20120302");
		Integer to = Integer.parseInt("20130301");

		// TODO 締日、ORDER BYを可変に
		List<Object> params = new ArrayList<Object>();

		List<CommonDTO> retList = dao.select(sql.toString(), params);
		for (CommonDTO dto : retList) {
			BookAcknowledgmentDTO bookAcknowledgmentDTO = (BookAcknowledgmentDTO)dto;

			BookAcknowledgment bookAcknowledgment = new BookAcknowledgment();
			bookAcknowledgment.setNendo(form.getNendo());
			bookAcknowledgment.setFrom(from.toString());
			bookAcknowledgment.setTo(to.toString());

			ConvertUtils.register(new BigDecimalConverter(null), BigDecimal.class);
			BeanUtils.copyProperties(bookAcknowledgment, SerializationUtils.clone(bookAcknowledgmentDTO));

			bookAcknowledgment.setStaff(new StringBuilder().append(
					bookAcknowledgmentDTO.getStaffSei()).append(
							bookAcknowledgmentDTO.getStaffName()).toString());

			// TODO 消費税(セッションからとる)
			BigDecimal tax = bookAcknowledgment.getSumTaxExcludedAmount().multiply(
					BigDecimal.valueOf(0.05)).setScale(
							DECIMAL_DIGIT, RoundingMode.DOWN);
			bookAcknowledgment.setTax(tax);

			// 税込金額
			BigDecimal taxIncludedAmount = bookAcknowledgment.getSumTaxExcludedAmount().add(tax);
			bookAcknowledgment.setTaxIncludedAmount(taxIncludedAmount);

			// 領収金額(税込金額 + 調整額)
			bookAcknowledgment.setAllPrice(taxIncludedAmount.add(bookAcknowledgment.getDiscount()));
			bookAcknowledgmentList.add(bookAcknowledgment);
		}

		return bookAcknowledgmentList;
	}


}
