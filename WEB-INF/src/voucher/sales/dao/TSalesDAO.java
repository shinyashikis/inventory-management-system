package voucher.sales.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import voucher.sales.dto.TSalesDTO;

import fw.common.db.dao.AbstractCommonDAO;
import fw.common.db.dto.CommonDTO;
import fw.common.db.dto.DTOUtil;

/**
 * 売上伝票テーブルDAO
 */
public class TSalesDAO extends AbstractCommonDAO {

	public static final String SQLID001 = "T_SALES_001";
	public static final String SQLID002 = "T_SALES_002";
	public static final String SQLID003 = "T_SALES_003";
	public static final String SQLID004 = "T_SALES_004";
	public static final String SQLID005 = "T_SALES_005";

	public TSalesDAO(Connection conn) {
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
	public List<CommonDTO> select(String sql, List<Object> params)
			throws SQLException {
		PreparedStatement stmt= conn.prepareStatement(sql);
		for (int i = 0; i < params.size(); i++) {
			stmt.setObject(i+1, params.get(i));
		}
		return DTOUtil.makeDTOList(stmt.executeQuery(), TSalesDTO.class);
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
