package master.basic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fw.common.db.dao.AbstractCommonDAO;
import fw.common.db.dto.CommonDTO;

import master.basic.dto.MBasicDTO;

/**
 * <p>基本設定マスタDAO</p>
 */
public class MBasicDAO extends AbstractCommonDAO {

	/** SELECT * FROM M_BASIC */
	public static final String SQLID001 = "M_BASIC_001";
	/** UPDATE M_BASIC SET NAME = ?, POSTCODE1 = ?, POSTCODE2 = ?, ADDR1 = ?, ADDR2 = ?, TEL = ?, FAX = ?, BANK = ?, BRANCH = ?, ACCOUNTNO = ?, ACCOUNTNAME = ?, KISYUYEAR = ?, KISYUMONTH = ?, KIMATSUYEAR = ?, KIMATSUMONTH = ?, KIMATSUDATE = ?, KESSAN = ?, TAX = ?, TAXVAL = ?, CALC = ?, CALCHASU = ? WHERE CODE =? AND UPDDATE = ? */
	public static final String SQLID002 = "M_BASIC_002";

	public MBasicDAO(Connection conn) {
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
			MBasicDTO dto = new MBasicDTO();
			dto.setAccountName(rs.getString(MBasicDTO.COLUMN_ACCOUNT_NAME));
			dto.setAccountNo(rs.getString(MBasicDTO.COLUMN_ACCOUNT_NO));
			dto.setAddr1(rs.getString(MBasicDTO.COLUMN_ADDR_1));
			dto.setAddr2(rs.getString(MBasicDTO.COLUMN_ADDR_2));
			dto.setBank(rs.getString(MBasicDTO.COLUMN_BANK));
			dto.setBranch(rs.getString(MBasicDTO.COLUMN_BRANCH));
			dto.setCalc(rs.getInt(MBasicDTO.COLUMN_CALC));
			dto.setCalcHasu(rs.getInt(MBasicDTO.COLUMN_CALC_HASU));
			dto.setFax(rs.getString(MBasicDTO.COLUMN_FAX));
			dto.setKessan(rs.getInt(MBasicDTO.COLUMN_KESSAN));
			dto.setKimatsuYear(rs.getInt(MBasicDTO.COLUMN_KIMATSU_YEAR));
			dto.setKimatsuMonth(rs.getInt(MBasicDTO.COLUMN_KIMATSU_MONTH));
			dto.setKimatsuDate(rs.getInt(MBasicDTO.COLUMN_KIMATSU_DATE));
			dto.setKisyuYear(rs.getInt(MBasicDTO.COLUMN_KISYU_YEAR));
			dto.setKisyuDate(rs.getInt(MBasicDTO.COLUMN_KISYU_DATE));
			dto.setKisyuMonth(rs.getInt(MBasicDTO.COLUMN_KISYU_MONTH));
			dto.setName(rs.getString(MBasicDTO.COLUMN_NAME));
			dto.setPostCode1(rs.getString(MBasicDTO.COLUMN_POST_CODE_1));
			dto.setPostCode2(rs.getString(MBasicDTO.COLUMN_POST_CODE_2));
			dto.setTax(rs.getInt(MBasicDTO.COLUMN_TAX));
			dto.setTaxVal(rs.getBigDecimal(MBasicDTO.COLUMN_TAX_VAL));
			dto.setTel(rs.getString(MBasicDTO.COLUMN_TEL));
			dto.setUpdDate(rs.getTimestamp(MBasicDTO.COLUMN_UPD_DATE));
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
