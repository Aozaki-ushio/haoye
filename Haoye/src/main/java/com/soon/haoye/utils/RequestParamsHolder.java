package dmzsmos.utils;

import java.util.HashMap;
import java.util.Map;

public class RequestParamsHolder {

	private static final ThreadLocal<Map<String, String>> paramsHolder = new ThreadLocal<Map<String, String>>();

	public static void setParameter(String key, String value) {
		Map<String, String> map = getParams();
		if (map == null) {
			map = new HashMap<>();
			map.put(key, value);
			setParams(map);
		} else {
			map.put(key, value);
		}
	}

	public static String getParameter(String key) {
		Map<String, String> params = getParams();
		return params == null ? null : params.get(key);
	}

	public static void setParams(Map<String, String> params) {
		paramsHolder.set(params);
	}

	public static Map<String, String> getParams() {
		return paramsHolder.get();
	}

	public static void removeParams() {
		paramsHolder.remove();
	}

}