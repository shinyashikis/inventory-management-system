package master.staff.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fw.common.db.dao.AbstractCommonDAO;
import fw.common.db.dto.CommonDTO;

import master.staff.dto.MStaffDTO;

/**
 * <p>担当者マスタDAO</p>
 */
public class MStaffDAO extends AbstractCommonDAO {

	/** SELECT * FROM M_STAFF */
	public static final String SQLID001 = "M_STAFF_001";
	/** DELETE FROM M_STAFF WHERE CODE IN */
	public static final String SQLID002 = "M_STAFF_002";
	/** UPDATE M_STAFF SET SEI = ?, NAME = ?, SEIKANA = ?, NAMEKANA = ?, BUSYO = ? WHERE CODE = ? AND UPDDATE = ? */
	public static final String SQLID003 = "M_STAFF_003";
	/** INSERT INTO M_STAFF (CODE, SEI, NAME, SEIKANA, NAMEKANA, BUSYO) VALUES (?, ?, ?, ?, ?, ?) */
	public static final String SQLID004 = "M_STAFF_004";
	/** SELECT * FROM M_STAFF WHERE CODE = ? */
	public static final String SQLID005 = "M_STAFF_005";

	public MStaffDAO(Connection conn) {
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
			MStaffDTO dto = new MStaffDTO();
			dto.setCode(rs.getInt(MStaffDTO.COLUMN_CODE));
			dto.setSei(rs.getString(MStaffDTO.COLUMN_SEI));
			dto.setName(rs.getString(MStaffDTO.COLUMN_NAME));
			dto.setSeiKana(rs.getString(MStaffDTO.COLUMN_SEI_KANA));
			dto.setNameKana(rs.getString(MStaffDTO.COLUMN_NAME_KANA));
			dto.setBusyo(rs.getString(MStaffDTO.COLUMN_BUSYO));
			dto.setUpdDate(rs.getTimestamp(MStaffDTO.COLUMN_UPD_DATE));
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
