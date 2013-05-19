package fw.common.db;

import java.io.BufferedInputStream;
import java.io.IOException;

import fw.common.prop.AbstractProperties;

public class SQLErrCodeProperties extends AbstractProperties {

	private static final String FILE = "sqlErrCode.properties";
	private static SQLErrCodeProperties me = new SQLErrCodeProperties();

	public static final String ERR_FK = "ERR.FK";

	private SQLErrCodeProperties() {}

	public static SQLErrCodeProperties getInstance() {
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
