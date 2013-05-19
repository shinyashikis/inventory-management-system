package master.item.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import master.item.dto.MItemClassDTO;

import fw.common.db.dao.AbstractCommonDAO;
import fw.common.db.dto.CommonDTO;

/**
 * <p>商品分類マスタDAO</p>
 */
public class MItemClassDAO extends AbstractCommonDAO {

	/** SELECT * FROM M_ITEM_CLASS ORDER BY CODE */
	public static final String SQLID001 = "M_ITEM_CLASS_001";
	/** SELECT * FROM M_ITEM_CLASS WHERE CODE = ? */
	public static final String SQLID002 = "M_ITEM_CLASS_002";
	/** INSERT INTO M_ITEM_CLASS (CODE, NAME, KANA) VALUES (?, ?, ?) */
	public static final String SQLID003 = "M_ITEM_CLASS_003";
	/** UPDATE M_ITEM_CLASS SET NAME = ?, KANA = ? WHERE CODE = ? AND UPDDATE = ? */
	public static final String SQLID004 = "M_ITEM_CLASS_004";
	/** DELETE FROM M_ITEM_CLASS WHERE CODE IN */
	public static final String SQLID005 = "M_ITEM_CLASS_005";

	public MItemClassDAO(Connection conn) {
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
		List<CommonDTO> list = new ArrayList<CommonDTO>();

		PreparedStatement stmt= conn.prepareStatement(sql);
		for (int i = 0; i < params.size(); i++) {
			stmt.setObject(i+1, params.get(i));
		}

		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			MItemClassDTO dto = new MItemClassDTO();
			dto.setCode(rs.getInt(MItemClassDTO.CODE));
			dto.setName(rs.getString(MItemClassDTO.NAME));
			dto.setKana(rs.getString(MItemClassDTO.KANA));
			dto.setUpdDate(rs.getTimestamp(MItemClassDTO.UPD_DATE));
			list.add(dto);
		}
		return list;
	}

	@Override
	public int update(String sql, List<Object> params) throws SQLException {
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
