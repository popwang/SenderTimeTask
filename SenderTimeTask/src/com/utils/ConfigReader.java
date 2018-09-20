package com.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
/**
 * 读取配置文件
 * @author Administrator
 */
public class ConfigReader {
	private static Properties p = new Properties();

	public static Properties readConfigFile() {
		String path = System.getProperty("user.dir") + "/conf/conf.properties";
		try {
			FileInputStream in = new FileInputStream(new File(path));
			p.load(in);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return p;
	}
	
	/**
	 * 根据系统标识获取主机ip
	 * @param systemId
	 * @return
	 */
	public static String getHost(String systemId) {
		return p.getProperty(systemId+"_HOST").toString();
	}
	/**
	 * 根据系统标识获取主机端口
	 * @param systemId
	 * @return
	 */
	public static int getPort(String systemId){
		return Integer.parseInt(p.getProperty(systemId+"_PORT").toString());
	}
	/**
	 * 根据key，获取port列表
	 * @param systemId
	 * @return
	 */
	public static String getPorts(String systemId){
		return p.getProperty(systemId+"_PORT");
	}
	
	/**
	 * 读取郑州特比佳服务ip
	 * @return
	 */
	public static String getZZtbjIP() {
		return p.getProperty("ZZ_TBJ_HOST").toString();
	}
	
	/**
	 * 读取郑州特比佳服务port
	 * @return
	 */
	public static int getZZtbjPORT() {
		return Integer.parseInt(p.getProperty("ZZ_TBJ_PORT").toString());
	}
	
	/**
	 * 读取郑州交委服务ip
	 * @return
	 */
	public static String getZZtraIP() {
		return p.getProperty("ZZ_TRA_HOST").toString();
	}
	
	/**
	 * 读取郑州交委服务port
	 * @return
	 */
	public static int getZZtraPORT() {
		return Integer.parseInt(p.getProperty("ZZ_TRA_PORT").toString());
	}
	
	/**
	 * 读取山东济宁服务ip
	 * @return
	 */
	public static String getSdjnIP() {
		return p.getProperty("SD_JN_HOST").toString();
	}
	
	/**
	 * 读取山东济宁服务port
	 * @return
	 */
	public static int getSdjnPORT() {
		return Integer.parseInt(p.getProperty("SD_JN_PORT").toString());
	}
	
	/**
	 * 读取开封服务ip
	 * @return
	 */
	public static String getKfIP() {
		return p.getProperty("HA_KF_HOST").toString();
	}
	
	/**
	 * 读取开封服务port
	 * @return
	 */
	public static int getKfPORT() {
		return Integer.parseInt(p.getProperty("HA_KF_PORT").toString());
	}
	
	/**
	 * 读取周口服务ip
	 * @return
	 */
	public static String getZkIP() {
		System.out.println(p.getProperty("HA_ZK_HOST").toString());
		return p.getProperty("HA_ZK_HOST").toString();
	}
	
	/**
	 * 读取周口服务port
	 * @return
	 */
	public static int getZkPORT() {
		System.out.println(p.getProperty("HA_ZK_PORT").toString());
		return Integer.parseInt(p.getProperty("HA_ZK_PORT").toString());
	}
	
	/**
	 * 读取河北UDP服务ip
	 * @return
	 */
	public static String getHbIP() {
		return p.getProperty("HB_XX_HOST").toString();
	}
	
	/**
	 * 读取河北UDP服务port
	 * @return
	 */
	public static int getHbPORT() {
		return Integer.parseInt(p.getProperty("HB_XX_PORT").toString());
	}
	
	/**
	 * 读取西安服务ip
	 * @return
	 */
	public static String getXaIP() {
		return p.getProperty("SX_XA_HOST").toString();
	}
	
	/**
	 * 读取西安服务port
	 * @return
	 */
	public static int getXaPORT() {
		return Integer.parseInt(p.getProperty("SX_XA_PORT").toString());
	}
	
	/**
	 * 读取西安高新区服务ip
	 * @return
	 */
	public static String getXaGxqIP() {
		return p.getProperty("XA_GXQ_HOST").toString();
	}
	
	/**
	 * 读取西安高新区服务port
	 * @return
	 */
	public static int getXaGxqPORT() {
		return Integer.parseInt(p.getProperty("XA_GXQ_PORT").toString());
	}
	
	/**
	 * 读取山东济南服务ip
	 * @return
	 */
	public static String getJiNanIP() {
		return p.getProperty("SD_JINAN_HOST").toString();
	}
	
	/**
	 * 读取山东济南服务port
	 * @return
	 */
	public static int getJiNanPORT() {
		return Integer.parseInt(p.getProperty("SD_JINAN_PORT").toString());
	}
	
	/**
	 * 读取西安大兴区服务ip
	 * @return
	 */
	public static String getDaxingquIP() {
		return p.getProperty("XA_DAXINGQU_HOST").toString();
	}
	
	/**
	 * 读取西安大兴区服务port
	 * @return
	 */
	public static int getDaxingquPORT() {
		return Integer.parseInt(p.getProperty("XA_DAXINGQU_PORT").toString());
	}
	
	/**
	 * 读取湖南长沙服务ip
	 * @return
	 */
	public static String getHnChangshaIP() {
		return p.getProperty("HN_CS_HOST").toString();
	}
	
	/**
	 * 读取湖南长沙服务port
	 * @return
	 */
	public static int getHnChangshaPORT() {
		return Integer.parseInt(p.getProperty("HN_CS_PORT").toString());
	}
	
	/**
	 * 读取西安临潼服务ip
	 * @return
	 */
	public static String getXaLintongIP() {
		return p.getProperty("XA_LINTONG_HOST").toString();
	}
	
	/**
	 * 读取西安临潼服务port
	 * @return
	 */
	public static int getXaLintongPORT() {
		return Integer.parseInt(p.getProperty("XA_LINTONG_PORT").toString());
	}
	
	/**
	 * 读取洛阳伊川服务ip
	 * @return
	 */
	public static String getLyYcIP() {
		return p.getProperty("LY_YC_HOST").toString();
	}
	
	/**
	 * 读取洛阳伊川服务port
	 * @return
	 */
	public static int getLyYcPORT() {
		return Integer.parseInt(p.getProperty("LY_YC_PORT").toString());
	}

	static {
		readConfigFile();
	}
}
