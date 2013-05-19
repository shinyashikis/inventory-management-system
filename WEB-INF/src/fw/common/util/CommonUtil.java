package fw.common.util;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * <p>共通ユーティリティクラス</p>
 */
public class CommonUtil {

	/**
	 * @param property
	 * @param name
	 * @return
	 */
	public static String getPropertyValue(String property, String name) {
		if (property == null) {
			return null;
		}

		StringTokenizer camma = new StringTokenizer(property, CommonConstants.CAMMA);
		while (camma.hasMoreElements()) {
			// login=no
			String value = camma.nextToken();
			String method = value.substring(0, value.indexOf(CommonConstants.EQUAL));
			if (method.equals(name)) {
				return value.substring(
						value.indexOf(CommonConstants.EQUAL) + 1, value.length());
			}
		}
		return null;
	}

	/**
	 * @param value
	 * @param empty
	 * @return
	 */
	public static String convString(Integer value, boolean empty) {
		if (value == null) {
			return null;
		}
		return (value == 0 && empty) ? "" : Integer.toString(value);
	}

	/**
	 * @param value
	 * @param empty
	 * @return
	 */
	public static String convString(Double value, boolean empty) {
		if (value == null) {
			return null;
		}
		return (value == 0 && empty) ? "" : Double.toString(value);
	}

	/**
	 * @param value
	 * @return
	 */
	public static Integer convInteger(String value) {
		return (value == null || "".equals(value)) ? null : Integer.valueOf(value);
	}

	/**
	 * @param value
	 * @return
	 */
	public static Integer convInteger(String value, boolean zero) {
		if (value == null || "".equals(value)) {
			return (zero) ? 0 : null;
		} else {
			return Integer.valueOf(value);
		}
	}

	/**
	 * @param value
	 * @return
	 */
	public static Double convDouble(String value) {
		return (value == null || "".equals(value)) ? null : Double.valueOf(value);
	}

	/**
	 * @param fieldName
	 * @return
	 */
	public static String generateSetterName(String fieldName) {
		return new StringBuilder("set").append(
				fieldName.substring(0, 1).toUpperCase()).append(fieldName.substring(1,fieldName.length())).toString();

	}

	/**
	 * @param clazz
	 * @return
	 */
	public static List<Method> getGetterMethod(Class<?> clazz) {
		List<Method> list = new ArrayList<Method>();
		Method[] methods = clazz.getDeclaredMethods();
		for (int index = 0; index < methods.length; index++) {
			Method m = methods[index];
			if (m.getName().startsWith("get")) {
				list.add(m);
			}
		}
		return list;
	}

	/**
	 * @param postCode1
	 * @param postCode2
	 * @return
	 */
	public static String makePostCode(String postCode1, String postCode2) {
		if ((postCode1 == null || postCode2 == null) ||
				("".equals(postCode1) || "".equals(postCode1))) {
			return "";
		}
		return new StringBuilder(postCode1).append(CommonConstants.HYPHEN).append(postCode2).toString();
	}
}
