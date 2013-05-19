package fw.common.prop;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.Properties;

/**
 * <p>プロパティ抽象クラス</p>
 */
public abstract class AbstractProperties {

	/**
	 * <p>プロパティ</p>
	 */
	private Properties p = new Properties();

	/**
	 * <p>プロパティ値取得処理</p>
	 * @param key
	 * @return
	 * @throws IOException
	 */
	public String getValue(String key) throws IOException {
		if (p.isEmpty()) {
			load();
		}
		return p.getProperty(key);
	}

	/**
	 * <p>プロパティファイル読込処理</p>
	 * @throws IOException
	 */
	abstract protected void load() throws IOException;

	/**
	 * <p>プロパティファイル読込処理</p>
	 * @param inStream
	 * @throws IOException
	 */
	protected void load(InputStream inStream) throws IOException {
		try {
			p.load(inStream);
		} finally {
			inStream.close();
		}
	}

	/**
	 * <p>プロパティファイル読込処理</p>
	 * @param reader
	 * @throws IOException
	 */
	protected void load(Reader reader) throws IOException {
		try {
			p.load(reader);
		} finally {
			reader.close();
		}
	}
}
