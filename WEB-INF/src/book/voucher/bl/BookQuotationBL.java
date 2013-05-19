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
import book.voucher.bean.BookQuotation;
import book.voucher.dao.BookQuotationDAO;
import book.voucher.dto.BookQuotationDTO;

import prop.ViewProperties;

import fw.common.db.SQLProperties;
import fw.common.db.dto.CommonDTO;
import fw.core.base.TransactionInfo;

/**
 * 見積書ビジネスロジック
 */
public class BookQuotationBL {

	private static int DECIMAL_DIGIT = Integer.parseInt(
			ViewProperties.getInstance().getValue(ViewProperties.DECIMAL_DIGIT, ViewProperties.VALUE));

	/**
	 * 見積書リスト取得処理
	 * @param form
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public static List<BookQuotation> getBookQuotationList(BookVoucherListForm form)
		throws IOException, SQLException, IllegalAccessException, InvocationTargetException {

		BookQuotationDAO dao = new BookQuotationDAO(TransactionInfo.getConnection());
		List<BookQuotation> bookQuotationList = new ArrayList<BookQuotation>();

		String sql = SQLProperties.getInstance().getValue(BookQuotationDAO.SQLID00101);

		// TODO
		Integer from = Integer.parseInt("20120302");
		Integer to = Integer.parseInt("20130301");

		// TODO 締日、ORDER BYを可変に
		List<Object> params = new ArrayList<Object>();

		List<CommonDTO> retList = dao.select(sql.toString(), params);
		for (CommonDTO dto : retList) {
			BookQuotationDTO bookQuotationDTO = (BookQuotationDTO)dto;

			BookQuotation bookQuotation = new BookQuotation();
			bookQuotation.setNendo(form.getNendo());
			bookQuotation.setFrom(from.toString());
			bookQuotation.setTo(to.toString());

			ConvertUtils.register(new BigDecimalConverter(null), BigDecimal.class);
			BeanUtils.copyProperties(bookQuotation, SerializationUtils.clone(bookQuotationDTO));

			bookQuotation.setStaff(new StringBuilder().append(
					bookQuotationDTO.getStaffSei()).append(
							bookQuotationDTO.getStaffName()).toString());

			// TODO 消費税(セッションからとる)
			BigDecimal tax = bookQuotation.getSumTaxExcludedAmount().multiply(
					BigDecimal.valueOf(0.05)).setScale(
							DECIMAL_DIGIT, RoundingMode.DOWN);
			bookQuotation.setTax(tax);

			// 税込金額
			BigDecimal taxIncludedAmount = bookQuotation.getSumTaxExcludedAmount().add(tax);
			bookQuotation.setTaxIncludedAmount(taxIncludedAmount);

			// 見積金額(税込金額 + 調整額)
			bookQuotation.setAllPrice(taxIncludedAmount.add(bookQuotation.getDiscount()));
			bookQuotationList.add(bookQuotation);
		}

		return bookQuotationList;
	}


}
