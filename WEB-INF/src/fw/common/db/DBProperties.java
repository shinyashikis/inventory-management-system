package fw.common.db;

import java.io.BufferedInputStream;
import java.io.IOException;

import fw.common.prop.AbstractProperties;

public class DBProperties extends AbstractProperties {

	public static final String KEY_DS_NAME = "DS_NAME";

	public static final String COLUMN_TYPE = "COLUMN_TYPE";
	public static final String COLUMN_TYPE_BIGINT = "COLUMN_TYPE_BIGINT";
	public static final String COLUMN_TYPE_BIT = "COLUMN_TYPE_BIT";
	public static final String COLUMN_TYPE_BLOB = "COLUMN_TYPE_BLOB";
	public static final String COLUMN_TYPE_BOOL = "COLUMN_TYPE_BOOL";
	public static final String COLUMN_TYPE_CHAR = "COLUMN_TYPE_CHAR";
	public static final String COLUMN_TYPE_CHARACTER_VARYING = "COLUMN_TYPE_CHARACTER_VARYING";
	public static final String COLUMN_TYPE_DATE = "COLUMN_TYPE_DATE";
	public static final String COLUMN_TYPE_DATETIME = "COLUMN_TYPE_DATETIME";
	public static final String COLUMN_TYPE_DEC = "COLUMN_TYPE_DEC";
	public static final String COLUMN_TYPE_DECIMAL = "COLUMN_TYPE_DECIMAL";
	public static final String COLUMN_TYPE_DOUBLE = "COLUMN_TYPE_DOUBLE";
	public static final String COLUMN_TYPE_DOUBLE_PRECISION = "COLUMN_TYPE_DOUBLE_PRECISION";
	public static final String COLUMN_TYPE_FLOAT = "COLUMN_TYPE_FLOAT";
	public static final String COLUMN_TYPE_INT = "COLUMN_TYPE_INT";
	public static final String COLUMN_TYPE_INTEGER = "COLUMN_TYPE_INTEGER";
	public static final String COLUMN_TYPE_MEDIUMBLOB = "COLUMN_TYPE_MEDIUMBLOB";
	public static final String COLUMN_TYPE_MEDIUMINT = "COLUMN_TYPE_MEDIUMINT";
	public static final String COLUMN_TYPE_NCHAR = "COLUMN_TYPE_NCHAR";
	public static final String COLUMN_TYPE_NUMERIC = "COLUMN_TYPE_NUMERIC";
	public static final String COLUMN_TYPE_REAL = "COLUMN_TYPE_REAL";
	public static final String COLUMN_TYPE_SMALLINT = "COLUMN_TYPE_SMALLINT";
	public static final String COLUMN_TYPE_TEXT = "COLUMN_TYPE_TEXT";
	public static final String COLUMN_TYPE_TIME = "COLUMN_TYPE_TIME";
	public static final String COLUMN_TYPE_TIMESTAMP = "COLUMN_TYPE_TIMESTAMP";
	public static final String COLUMN_TYPE_TINYBLOB = "COLUMN_TYPE_TINYBLOB";
	public static final String COLUMN_TYPE_TINYINT = "COLUMN_TYPE_TINYINT";
	public static final String COLUMN_TYPE_TINYTEXT = "COLUMN_TYPE_TINYTEXT";
	public static final String COLUMN_TYPE_VARCHAR = "COLUMN_TYPE_VARCHAR";
	public static final String COLUMN_TYPE_YEAR = "COLUMN_TYPE_YEAR";

	private static final String FILE = "db.properties";
	private static DBProperties me = new DBProperties();

	private DBProperties() {}

	public static DBProperties getInstance() {
		return me;
	}

	/**
	 * @see common.prop.AbstractProperties#load()
	 */
	@Override
	protected void load() throws IOException {
		load(new BufferedInputStream(getClass().getResourceAsStream(FILE)));
	}
}
