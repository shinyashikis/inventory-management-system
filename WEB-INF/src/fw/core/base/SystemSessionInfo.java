package fw.core.base;

import java.util.HashMap;
import java.util.Map;

public class SystemSessionInfo {

	private Map<String, Object> values = new HashMap<String, Object>();

	Object getValue(String key) {
		return values.get(key);
	}

	void setValue(String key, Object value) {
		values.put(key, value);
	}

	void removeValue(String key) {
		values.remove(key);
	}

	void clear() {
		values.clear();
	}
}
