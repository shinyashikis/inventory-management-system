package fw.common.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fw.common.db.dto.CLoginStatusDTO;
import fw.common.db.dto.CommonDTO;

public class CLoginStatusDAO extends AbstractCommonDAO {

	/** SELECT * FROM C_LOGIN_STATUS WHERE USER = ? */
	public static final String SQLID001 = "C_LOGIN_STATUS_001";

	/** INSERT INTO C_LOGIN_STATUS (USER, SID) VALUES (?, ?) */
	public static final String SQLID002 = "C_LOGIN_STATUS_002";

	/** UPDATE C_LOGIN_STATUS SET SID = ? WHERE USER = ? */
	public static final String SQLID003 = "C_LOGIN_STATUS_003";

	/** DELETE FROM C_LOGIN_STATUS WHERE USER = ? */
	public static final String SQLID004 = "C_LOGIN_STATUS_004";

	public CLoginStatusDAO(Connection conn) {
		super(conn);
	}

	@Override
	public List<CommonDTO> select(String sql, List<Object> params) throws SQLException {
		List<CommonDTO> list = new ArrayList<CommonDTO>();

		PreparedStatement stmt= conn.prepareStatement(sql);
		for (int i = 0; i < params.size(); i++) {
			stmt.setObject(i+1, params.get(i));
		}

		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			CLoginStatusDTO dto = new CLoginStatusDTO();
			dto.setUser(rs.getString(CLoginStatusDTO.COLUMN_USER));
			dto.setSid(rs.getString(CLoginStatusDTO.COLUMN_SID));
			list.add(dto);
		}
		return list;
	}

	@Override
	public int insert(String sql, List<Object> params) throws SQLException {
		return doUpdate(sql, params);
	}

	@Override
	public int update(String sql, List<Object> params) throws SQLException {
		return doUpdate(sql, params);
	}

	@Override
	public int delete(String sql, List<Object> params) throws SQLException {
		return doUpdate(sql, params);
	}

	private int doUpdate(String sql, List<Object> params) throws SQLException {
		PreparedStatement stmt= conn.prepareStatement(sql);
		for (int i = 0; i < params.size(); i++) {
			stmt.setObject(i+1, params.get(i));
		}
		return stmt.executeUpdate();
	}

}
