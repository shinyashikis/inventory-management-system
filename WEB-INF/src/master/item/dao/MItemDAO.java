package master.item.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import master.item.dto.MItemDTO;

import fw.common.db.dao.AbstractCommonDAO;
import fw.common.db.dto.CommonDTO;

/**
 * <p>商品マスタDAO</p>
 */
public class MItemDAO extends AbstractCommonDAO {

	/** SELECT * FROM M_ITEM ORDER BY CODE */
	public static final String SQLID001 = "M_ITEM_001";
	/** UPDATE M_ITEM SET NAME = ?, KANA = ?, KIKAU = ?, CLASSCODE = ?, STOCK = ?, PROPERSTOCK = ?, KISYUSTOCK = ?, KICYUSTOCKUPDOWN = ?, UNIT = ?, PRICE = ?, KISYUPRICE = ?, STANDARDPRICE = ?, SELLINGPRICE2 = ?, SELLINGPRICE3 = ?, SELLINGPRICE4 = ?, SELLINGPRICE5 = ? WHERE CODE = ? AND UPDDATE = ? */
	public static final String SQLID002 = "M_ITEM_002";
	/** INSERT INTO M_ITEM (CODE, NAME, KANA, KIKAU, CLASSCODE, STOCK, PROPERSTOCK, KISYUSTOCK, KICYUSTOCKUPDOWN, UNIT, PRICE, KISYUPRICE, STANDARDPRICE, SELLINGPRICE2, SELLINGPRICE3, SELLINGPRICE4, SELLINGPRICE5) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) */
	public static final String SQLID003 = "M_ITEM_003";
	/** SELECT * FROM M_ITEM WHERE CODE = ? */
	public static final String SQLID004 = "M_ITEM_004";
	/** DELETE FROM M_ITEM WHERE CODE IN */
	public static final String SQLID005 = "M_ITEM_005";
	/** UPDATE M_ITEM SET STOCK = ? WHERE CODE = ? */
	public static final String SQLID006 = "M_ITEM_006";

	public MItemDAO(Connection conn) {
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
			MItemDTO dto = new MItemDTO();
			dto.setClassCode(rs.getInt(MItemDTO.CLASS_CODE));
			dto.setCode(rs.getString(MItemDTO.CODE));
			dto.setKana(rs.getString(MItemDTO.KANA));
			dto.setKicyuStockUpDown(rs.getInt(MItemDTO.KICYU_STOCK_UPDOWN));
			dto.setKikaku(rs.getString(MItemDTO.KIKAU));
			dto.setKisyuPrice(rs.getBigDecimal(MItemDTO.KISYU_PRICE));
			dto.setKisyuStock(rs.getInt(MItemDTO.KISYU_STOCK));
			dto.setName(rs.getString(MItemDTO.NAME));
			dto.setPrice(rs.getBigDecimal(MItemDTO.PRICE));
			dto.setProperStock(rs.getInt(MItemDTO.PROPER_STOCK));
			dto.setSellingPrice2(rs.getBigDecimal(MItemDTO.SELLING_PRICE2));
			dto.setSellingPrice3(rs.getBigDecimal(MItemDTO.SELLING_PRICE3));
			dto.setSellingPrice4(rs.getBigDecimal(MItemDTO.SELLING_PRICE4));
			dto.setSellingPrice5(rs.getBigDecimal(MItemDTO.SELLING_PRICE5));
			dto.setStandardPrice(rs.getBigDecimal(MItemDTO.STANDARD_PRICE));
			dto.setStock(rs.getInt(MItemDTO.STOCK));
			dto.setUnit(rs.getString(MItemDTO.UNIT));
			dto.setUpdDate(rs.getTimestamp(MItemDTO.UPD_DATE));
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
