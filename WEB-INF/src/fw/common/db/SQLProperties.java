package fw.common.db;

import java.io.BufferedInputStream;
import java.io.IOException;

import fw.common.prop.AbstractProperties;

public class SQLProperties extends AbstractProperties {

	private static final String FILE = "sql.properties";
	private static SQLProperties me = new SQLProperties();

	private SQLProperties() {}

	public static SQLProperties getInstance() {
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
