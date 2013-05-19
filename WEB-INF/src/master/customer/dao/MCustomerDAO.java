package master.customer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import master.customer.dto.MCustomerDTO;

import fw.common.db.dao.AbstractCommonDAO;
import fw.common.db.dto.CommonDTO;

/**
 * <p>得意先マスタDAO</p>
 */
public class MCustomerDAO extends AbstractCommonDAO {

	/** SELECT * FROM M_CUSTOMER ORDER BY CODE */
	public static final String SQLID001 = "M_CUSTOMER_001";
	/** SELECT * FROM M_CUSTOMER WHERE CODE = ? */
	public static final String SQLID002 = "M_CUSTOMER_002";
	/** INSERT INTO M_CUSTOMER (CODE, NAME, KANA, CLASSCODE, ADDR1, ADDR2, POSTCODE1, POSTCODE2, TEL, FAX, CUSTOMERSTAFFSEI, CUSTOMERSTAFFNAME, KEISYO, BUSYO, DEALDIVISION, KESSAIDIVISION, SALEPRICEDIVISION, KISYUBALANCE, SHIMEBIMONTHLY, SHIMEBIKESSAI, KESSAIMONTHLY, STAFFCODE) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) */
	public static final String SQLID003 = "M_CUSTOMER_003";
	/** UPDATE M_CUSTOMER SET NAME = ?, KANA = ?, CLASSCODE = ?, ADDR1 = ?, ADDR2 = ?, POSTCODE1 = ?, POSTCODE2 = ?, TEL = ?, FAX = ?, CUSTOMERSTAFFSEI = ?, CUSTOMERSTAFFNAME = ?, KEISYO = ?, BUSYO = ?, DEALDIVISION = ?, KESSAIDIVISION = ?, SALEPRICEDIVISION = ?, KISYUBALANCE = ?, SHIMEBIMONTHLY = ?, SHIMEBIKESSAI = ?, KESSAIMONTHLY = ?, STAFFCODE = ? WHERE CODE = ? AND UPDDATE = ? */
	public static final String SQLID004 = "M_CUSTOMER_004";
	/** DELETE FROM M_CUSTOMER WHERE CODE IN */
	public static final String SQLID005 = "M_CUSTOMER_005";
	/** SELECT M.CODE,M.NAME,M.KANA,M.CLASSCODE,M_CLASS.NAME AS CLASSNAME,M_CLASS.KANA AS CLASSNAMEKANA,M.ADDR1,M.ADDR2,M.POSTCODE1,M.POSTCODE2,M.TEL,M.FAX,M.CUSTOMERSTAFFSEI,M.CUSTOMERSTAFFNAME,M.KEISYO,M.BUSYO,M.DEALDIVISION,M.KESSAIDIVISION,M.SALEPRICEDIVISION,M.KISYUBALANCE,M.SHIMEBIMONTHLY,M.SHIMEBIKESSAI,M.KESSAIMONTHLY,M.STAFFCODE,M.UPDDATE,M_CLASS.UPDDATE AS CLASSUPDDATE FROM M_CUSTOMER M LEFT JOIN M_CUSTOMER_CLASS M_CLASS ON M.CLASSCODE = M_CLASS.CODE ORDER BY M.CODE */
	public static final String SQLID006 = "M_CUSTOMER_006";

	public MCustomerDAO(Connection conn) {
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
			MCustomerDTO dto = new MCustomerDTO();
			dto.setAddr1(rs.getString(MCustomerDTO.ADDR1));
			dto.setAddr2(rs.getString(MCustomerDTO.ADDR2));
			dto.setBusyo(rs.getString(MCustomerDTO.BUSYO));
			dto.setClassCode(rs.getInt(MCustomerDTO.CLACC_CODE));
			dto.setCode(rs.getString(MCustomerDTO.CODE));
			dto.setCustomerStaffName(rs.getString(MCustomerDTO.CUSTOMER_STAFF_NAME));
			dto.setCustomerStaffSei(rs.getString(MCustomerDTO.CUSTOMER_STAFF_SEI));
			dto.setDealDivision(rs.getInt(MCustomerDTO.DEAL_DIVISION));
			dto.setFax(rs.getString(MCustomerDTO.FAX));
			dto.setKana(rs.getString(MCustomerDTO.KANE));
			dto.setKeisyo(rs.getInt(MCustomerDTO.KEISYO));
			dto.setKessaiDivision(rs.getInt(MCustomerDTO.KESSAI_DIVISION));
			dto.setKessaiMonthly(rs.getInt(MCustomerDTO.KESSAI_MONTHLY));
			dto.setKisyuBalance(rs.getBigDecimal(MCustomerDTO.KISYU_BALANCE));
			dto.setName(rs.getString(MCustomerDTO.NAME));
			dto.setPostCode1(rs.getString(MCustomerDTO.POSTCODE1));
			dto.setPostCode2(rs.getString(MCustomerDTO.POSTCODE2));
			dto.setSalePriceDivision(rs.getInt(MCustomerDTO.SALE_PRICE_DIVISION));
			dto.setShimebiKessai(rs.getInt(MCustomerDTO.SHIMEBI_KESSAI));
			dto.setShimebiMonthly(rs.getInt(MCustomerDTO.SHIMEBI_MONTHLY));
			dto.setStaffCode(rs.getInt(MCustomerDTO.STAFF_CODE));
			dto.setTel(rs.getString(MCustomerDTO.TEL));
			dto.setUpdDate(rs.getTimestamp(MCustomerDTO.UPDDATE));
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
