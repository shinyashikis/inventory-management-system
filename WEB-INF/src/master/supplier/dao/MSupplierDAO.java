package master.supplier.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import master.supplier.dto.MSupplierDTO;

import fw.common.db.dao.AbstractCommonDAO;
import fw.common.db.dto.CommonDTO;

/**
 * <p>仕入先マスタDAO</p>
 */
public class MSupplierDAO extends AbstractCommonDAO {

	/** SELECT * FROM M_SUPPLIER ORDER BY CODE */
	public static final String SQLID001 = "M_SUPPLIER_001";
	/** SELECT * FROM M_SUPPLIER WHERE CODE = ? */
	public static final String SQLID002 = "M_SUPPLIER_002";
	/** INSERT INTO M_SUPPLIER (CODE, NAME, KANA, CLASSCODE, ADDR1, ADDR2, POSTCODE1, POSTCODE2, TEL, FAX, SUPPLIERSTAFFSEI, SUPPLIERSTAFFNAME, KEISYO, BUSYO, DEALDIVISION, KESSAIDIVISION, KISYUBALANCE, SHIMEBIMONTHLY, SHIMEBIKESSAI, KESSAIMONTHLY, STAFFCODE, TAX, CALC, CALCHASU) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) */
	public static final String SQLID003 = "M_SUPPLIER_003";
	/** UPDATE M_SUPPLIER SET NAME = ?, KANA = ?, CLASSCODE = ?, ADDR1 = ?, ADDR2 = ?, POSTCODE1 = ?, POSTCODE2 = ?, TEL = ?, FAX = ?, SUPPLIERSTAFFSEI = ?, SUPPLIERSTAFFNAME = ?, KEISYO = ?, BUSYO = ?, DEALDIVISION = ?, KESSAIDIVISION = ?, KISYUBALANCE = ?, SHIMEBIMONTHLY = ?, SHIMEBIKESSAI = ?, KESSAIMONTHLY = ?, STAFFCODE = ?, TAX = ?, CALC = ?, CALCHASU = ? WHERE CODE = ? AND UPDDATE = ? */
	public static final String SQLID004 = "M_SUPPLIER_004";
	/** DELETE FROM M_SUPPLIER WHERE CODE IN */
	public static final String SQLID005 = "M_SUPPLIER_005";

	public MSupplierDAO(Connection conn) {
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
			MSupplierDTO dto = new MSupplierDTO();
			dto.setAddr1(rs.getString(MSupplierDTO.ADDR1));
			dto.setAddr2(rs.getString(MSupplierDTO.ADDR2));
			dto.setBusyo(rs.getString(MSupplierDTO.BUSYO));
			dto.setClassCode(rs.getInt(MSupplierDTO.CLACC_CODE));
			dto.setCode(rs.getString(MSupplierDTO.CODE));
			dto.setSupplierStaffName(rs.getString(MSupplierDTO.SUPPLIER_STAFF_NAME));
			dto.setSupplierStaffSei(rs.getString(MSupplierDTO.SUPPLIER_STAFF_SEI));
			dto.setDealDivision(rs.getInt(MSupplierDTO.DEAL_DIVISION));
			dto.setFax(rs.getString(MSupplierDTO.FAX));
			dto.setKana(rs.getString(MSupplierDTO.KANE));
			dto.setKeisyo(rs.getInt(MSupplierDTO.KEISYO));
			dto.setKessaiDivision(rs.getInt(MSupplierDTO.KESSAI_DIVISION));
			dto.setKessaiMonthly(rs.getInt(MSupplierDTO.KESSAI_MONTHLY));
			dto.setKisyuBalance(rs.getDouble(MSupplierDTO.KISYU_BALANCE));
			dto.setName(rs.getString(MSupplierDTO.NAME));
			dto.setPostCode1(rs.getString(MSupplierDTO.POSTCODE1));
			dto.setPostCode2(rs.getString(MSupplierDTO.POSTCODE2));
			dto.setShimebiKessai(rs.getInt(MSupplierDTO.SHIMEBI_KESSAI));
			dto.setShimebiMonthly(rs.getInt(MSupplierDTO.SHIMEBI_MONTHLY));
			dto.setStaffCode(rs.getInt(MSupplierDTO.STAFF_CODE));
			dto.setTel(rs.getString(MSupplierDTO.TEL));
			dto.setTax(rs.getInt(MSupplierDTO.TAX));
			dto.setCalc(rs.getInt(MSupplierDTO.CALC));
			dto.setCalcHasu(rs.getInt(MSupplierDTO.CALC_HASU));
			dto.setUpdDate(rs.getTimestamp(MSupplierDTO.UPDDATE));
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
