package book.item.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import fw.common.db.dao.AbstractCommonDAO;

public abstract class BookItemCommonDAO extends AbstractCommonDAO {

	public static final String SQLID001 = "BOOK_ITEM_COMMON_001";
	public static final String SQLID002 = "BOOK_ITEM_COMMON_002";
	public static final String SQLID003 = "BOOK_ITEM_COMMON_003";
	public static final String SQLID004 = "BOOK_ITEM_COMMON_004";
	public static final String SQLID005 = "BOOK_ITEM_COMMON_005";
	public static final String SQLID006 = "BOOK_ITEM_COMMON_006";

	public BookItemCommonDAO(Connection conn) {
		super(conn);
	}

	@Override
	public int delete(String sql, List<Object> params) throws SQLException {
		return doUpdate(sql, params);
	}

	@Override
	public int insert(String sql, List<Object> params) throws SQLException {
		return doUpdate(sql, params);
	}

	@Override
	public int update(String sql, List<Object> params) throws SQLException {
		return doUpdate(sql, params);
	}

	/**
	 * @param sql
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	private int doUpdate(String sql, List<Object> params) throws SQLException {
		PreparedStatement stmt= conn.prepareStatement(sql);
		for (int i = 0; i < params.size(); i++) {
			stmt.setObject(i+1, params.get(i));
		}
		return stmt.executeUpdate();
	}

}
