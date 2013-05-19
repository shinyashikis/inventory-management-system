package voucher.common.dialog.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import voucher.common.dialog.dto.VoucherItemDTO;

import fw.common.db.dao.AbstractCommonDAO;
import fw.common.db.dto.CommonDTO;
import fw.common.db.dto.DTOUtil;

/**
 * <p>(伝票)商品DAO</p>
 */
public class VoucherItemDAO extends AbstractCommonDAO {

	public static final String SQLID001 = "VOUCHER_ITEM_001";

	public VoucherItemDAO(Connection conn) {
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
		return DTOUtil.makeDTOList(stmt.executeQuery(), VoucherItemDTO.class);
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
