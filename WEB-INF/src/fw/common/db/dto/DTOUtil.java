package fw.common.db.dto;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fw.common.db.DBProperties;
import fw.common.util.CommonUtil;

public class DTOUtil {

	/**
	 * 取得結果DTO生成処理
	 * @param rs
	 * @param clazzDTO
	 * @param dto
	 * @return
	 * @throws SQLException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List<CommonDTO> makeDTOList(ResultSet rs, Class clazzDTO) throws SQLException {
		List<CommonDTO> list = new ArrayList<CommonDTO>();

		try {
			while (rs.next()) {
				CommonDTO dto = (CommonDTO)clazzDTO.newInstance();

				ResultSetMetaData metaData = rs.getMetaData();
				int columnCnt = metaData.getColumnCount();
				for (int i = 1; i <= columnCnt; i++) {

						String columnName = metaData.getColumnLabel(i);
						String columnTypeName = metaData.getColumnTypeName(i);

						Class parameterTypes = null;
						String rsMethodName = null;
						Field[] fields = DBProperties.class.getFields();
						for (int fIndex = 0; fIndex < fields.length; fIndex++) {
							Field f = fields[fIndex];
							if (f.getName().startsWith(DBProperties.COLUMN_TYPE + "_")) {
								String value = DBProperties.getInstance().getValue((String)f.get(DBProperties.getInstance()));
								String dbtype = value.substring(0, value.indexOf(","));

								if (dbtype.equals(columnTypeName)) {
									String str = value.substring(dbtype.length() + 1,value.length());
									String javatype = str.substring(0,str.indexOf(","));
									parameterTypes = Class.forName(javatype);
									rsMethodName = str.substring(javatype.length() + 1,str.length());
									break;
								}
							}
						}

						Method dtoMethod = clazzDTO.getMethod(CommonUtil.generateSetterName(columnName), parameterTypes);
						Method rsMethod = ResultSet.class.getMethod(rsMethodName, String.class);
						dtoMethod.invoke(dto, rsMethod.invoke(rs, columnName));
				}
				list.add(dto);
			}
		} catch (SecurityException e) {
			throw new RuntimeException(e);
		} catch (NoSuchMethodException e) {
			throw new RuntimeException(e);
		} catch (IllegalArgumentException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		} catch (InvocationTargetException e) {
			throw new RuntimeException(e);
		} catch (InstantiationException e) {
			throw new RuntimeException(e);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		return list;
	}
}
