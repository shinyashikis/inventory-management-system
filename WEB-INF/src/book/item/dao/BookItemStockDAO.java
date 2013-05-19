package book.item.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import book.item.dto.BookItemStockDTO;

import fw.common.db.dto.CommonDTO;
import fw.common.db.dto.DTOUtil;

public class BookItemStockDAO extends BookItemCommonDAO {

	public static final String SQLID00101 = "BOOK_ITEM_STOCK_00101";
	public static final String SQLID00102 = "BOOK_ITEM_STOCK_00102";
	public static final String SQLID00103 = "BOOK_ITEM_STOCK_00103";
	public static final String SQLID00104 = "BOOK_ITEM_STOCK_00104";
	public static final String SQLID00105 = "BOOK_ITEM_STOCK_00105";
	public static final String SQLID00106 = "BOOK_ITEM_STOCK_00106";
	public static final String SQLID00107 = "BOOK_ITEM_STOCK_00107";
	public static final String SQLID00108 = "BOOK_ITEM_STOCK_00108";
	public static final String SQLID00109 = "BOOK_ITEM_STOCK_00109";
	public static final String SQLID00110 = "BOOK_ITEM_STOCK_00110";
	public static final String SQLID00111 = "BOOK_ITEM_STOCK_00111";
	public static final String SQLID00112 = "BOOK_ITEM_STOCK_00112";
	public static final String SQLID00113 = "BOOK_ITEM_STOCK_00113";
	public static final String SQLID00114 = "BOOK_ITEM_STOCK_00114";
	public static final String SQLID00115 = "BOOK_ITEM_STOCK_00115";
	public static final String SQLID00116 = "BOOK_ITEM_STOCK_00116";

	public BookItemStockDAO(Connection conn) {
		super(conn);
	}

	@Override
	public List<CommonDTO> select(String sql, List<Object> params)
			throws SQLException {
		PreparedStatement stmt= conn.prepareStatement(sql);
		for (int i = 0; i < params.size(); i++) {
			stmt.setObject(i+1, params.get(i));
		}
		return DTOUtil.makeDTOList(stmt.executeQuery(), BookItemStockDTO.class);
	}
}
