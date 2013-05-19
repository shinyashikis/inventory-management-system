package voucher.common.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import voucher.common.dto.VoucherDTO;

import fw.common.db.dao.AbstractCommonDAO;
import fw.common.db.dto.CommonDTO;
import fw.common.db.dto.DTOUtil;

/**
 * 伝票DAO
 */
public class VoucherDAO extends AbstractCommonDAO {

	public static final String SQLID001 = "VOUCHER_001";
	public static final String SQLID002 = "VOUCHER_002";
	public static final String SQLID003 = "VOUCHER_003";
	public static final String SQLID004 = "VOUCHER_004";
	public static final String SQLID005 = "VOUCHER_005";
	public static final String SQLID00601 = "VOUCHER_00601";
	public static final String SQLID00602 = "VOUCHER_00602";
	public static final String SQLID00603 = "VOUCHER_00603";
	public static final String SQLID00604 = "VOUCHER_00604";
	public static final String SQLID00605 = "VOUCHER_00605";

	public VoucherDAO(Connection conn) {
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
		return DTOUtil.makeDTOList(stmt.executeQuery(), VoucherDTO.class);
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
