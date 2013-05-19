package voucher.quotation.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import voucher.quotation.dto.TQuotationDTO;

import fw.common.db.dao.AbstractCommonDAO;
import fw.common.db.dto.CommonDTO;
import fw.common.db.dto.DTOUtil;

/**
 * 見積書テーブルDAO
 */
public class TQuotationDAO extends AbstractCommonDAO {

	public static final String SQLID001 = "T_QUOTATION_001";
	public static final String SQLID002 = "T_QUOTATION_002";
	public static final String SQLID003 = "T_QUOTATION_003";
	public static final String SQLID004 = "T_QUOTATION_004";
	public static final String SQLID005 = "T_QUOTATION_005";

	public TQuotationDAO(Connection conn) {
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
		return DTOUtil.makeDTOList(stmt.executeQuery(), TQuotationDTO.class);
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
