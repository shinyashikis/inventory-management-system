package book.item.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import book.item.dto.BookPurchasePriceAveDTO;

import fw.common.db.dto.CommonDTO;
import fw.common.db.dto.DTOUtil;

public class BookPurchasePriceAveDAO extends BookItemCommonDAO {

	public static final String SQLID00101 = "BOOK_PURCHASE_PRICE_AVE_00101";
	public static final String SQLID00102 = "BOOK_PURCHASE_PRICE_AVE_00102";

	public BookPurchasePriceAveDAO(Connection conn) {
		super(conn);
	}

	@Override
	public List<CommonDTO> select(String sql, List<Object> params)
			throws SQLException {
		PreparedStatement stmt= conn.prepareStatement(sql);
		for (int i = 0; i < params.size(); i++) {
			stmt.setObject(i+1, params.get(i));
		}
		return DTOUtil.makeDTOList(stmt.executeQuery(), BookPurchasePriceAveDTO.class);
	}
}
