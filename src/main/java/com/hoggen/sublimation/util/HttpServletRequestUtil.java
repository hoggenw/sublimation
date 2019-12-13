package com.hoggen.sublimation.util;

import javax.servlet.http.HttpServletRequest;

public class HttpServletRequestUtil {
	public static int getInt(HttpServletRequest request, String key) {
		try {
			return Integer.decode(request.getParameter(key));
		} catch (Exception e) {
			return -1;
			// TODO: handle exception
		}
	}

	public static long getLong(HttpServletRequest request, String name) {

		try {
			return Long.valueOf(request.getParameter(name));
		} catch (Exception e) {
			return -1;
		}
	}

	public static Double getDouble(HttpServletRequest request, String name) {

		try {
			return Double.valueOf(request.getParameter(name));
		} catch (Exception e) {
			return -1d;
		}
	}


	public static Float getFloat(HttpServletRequest request, String name) {

		try {
			return Float.valueOf(request.getParameter(name));
		} catch (Exception e) {
			return -1f;
		}
	}

	public static Boolean getBoolean(HttpServletRequest request, String name) {

		try {
			return Boolean.valueOf(request.getParameter(name));
		} catch (Exception e) {
			return false;
		}
	}

	public static String getString(HttpServletRequest request, String name) {
		try {
			String result = request.getParameter(name);
			if (result != null) {
				// 去掉左右两侧的空格
				result = result.trim();
			}
			if ("".equals(result)){
				result = null;
			}

			return result;
		} catch (Exception e) {
			return null;
		}

	}
}
