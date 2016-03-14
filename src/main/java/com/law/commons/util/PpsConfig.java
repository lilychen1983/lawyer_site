package com.law.commons.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 版权所有: 版权所有(C) 2014，lenovo<br/>
 * 内容摘要: 用于读取配置文件<br/>
 * 创建日期: 2014-10-9<br/>
 * 作　　者: caoyong1<br/>
 * 修改记录：
 */
public class PpsConfig {

	private   Properties properties = new Properties();
	// 配置文件名称
	private static final String conifgName = "/app.properties";
	private static PpsConfig instance = new PpsConfig();

	/**
	 * 构造方法 加载配置文件
	 */
	private PpsConfig() {
		InputStream is = null;
		try {
			is = getClass().getResourceAsStream(conifgName);
			properties.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
					is = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

//	/**
//	 * 获取单例对象
//	 * 
//	 * @return EmpConfig
//	 */
//	private static PpsConfig getInstance() {
//		return instance;
//	}

	/**
	 * 将属性值获取为int型
	 * 
	 * @param str
	 *            属性名
	 * @return
	 */
	public static int getint(String str) {
		try {
			return Integer.parseInt(instance.properties.getProperty(str));
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	/**
	 * 将属性值获取为long型
	 * 
	 * @param str
	 *            属性名
	 * @return
	 */
	public static long getlong(String str) {
		try {
			return Long.parseLong(instance.properties.getProperty(str));
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	/**
	 * 将属性值获取为double型
	 * 
	 * @param str
	 *            属性名
	 * @return
	 */
	public static double getdouble(String str) {
		try {
			return Double.parseDouble(instance.properties.getProperty(str));

		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	/**
	 * 将属性值获取为String型
	 * 
	 * @param str
	 *            属性名
	 * @return
	 */
	public static String getString(String str) {
		try {
			return instance.properties.getProperty(str);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * 将属性值获取为boolean型
	 * 
	 * @param str
	 *            属性名
	 * @return
	 */
	public static boolean getBoolean(String str) {
		try {
			return Boolean.parseBoolean(instance.properties.getProperty(str));
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
