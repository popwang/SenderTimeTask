package com.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

import com.vo.EquipmentData;
/**
 * 调用webservice接口的服务，协议相同，使用同一个工具类方法生成发送数据串
 * @author pactera
 */
public class WebserviceUtil {
	public static final int WEBSERVICE_TIMEOUT = 3;
	/**
	 * 河南省内某厂家webservice服务数据字符串
	 * @param v
	 * @return
	 */
	public static String getDataString(EquipmentData v){
		StringBuffer sb = new StringBuffer("DevID:|:");
	      sb.append(v.getV_equipment_name());
	      sb.append("#|#Time:|:");
	      sb.append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
	      sb.append("#|#HUMI:|:");
	      sb.append(v.getP007());
	      sb.append("#|#TEMP:|:");
	      sb.append(v.getP006());
	      sb.append("#|#PRE:|:");
	      sb.append(v.getP010());
	      sb.append("#|#WINDD:|:");
	      sb.append(CommonUtil.getWindString(v.getP005()));
	      sb.append("#|#WINDS:|:");
	      sb.append(v.getP004());
	      sb.append("#|#NOISE:|:");
	      sb.append(v.getP008());
	      sb.append("#|#PM25:|:");
	      sb.append((int)v.getP002());
	      sb.append("#|#PM10:|:");
	      sb.append((int)v.getP003());
	      sb.append("#|#TSP:|:");
	      sb.append((int)v.getP009());
	      
		return sb.toString();
	}
	
	public static JSONObject getContentObject(EquipmentData v) throws JSONException{
		JSONObject sub = new JSONObject();
		sub.put("unitno", CODE);
		sub.put("deviceno", v.getV_equipment_name());
		sub.put("temperature", v.getP006()+"");
		sub.put("humidity", v.getP007()+"");
		sub.put("windpower", v.getP004()+"");
		sub.put("winddirect", CommonUtil.getWindString3(v.getP005()));
		sub.put("pm10", v.getP003()+"");
		sub.put("pm2_5", v.getP002()+"");
		sub.put("noise", v.getP008()+"");
		sub.put("uploadtime", CRC.currentTimeStampString2());
		return sub;
	}
	
	public static String KEY = "B2BA2A96-C85D-438F-9D85-F490AFF9ECC6";
	public static String CODE = "ycjk";
	
	public static String getSignMD5(String content) {
		long timestamp = System.nanoTime();
		return MD(timestamp+KEY+content)+":"+timestamp;
	}
	
	public static String getDataString3(JSONObject sub) throws JSONException{
		JSONObject object = new JSONObject();
		object.put("sign", getSignMD5(sub.toString()));
		object.put("unitno", CODE);
		object.put("content", sub);
		return object.toString();
	}
	
	public static void main(String[] args) {
		System.out.println(MD5("111"));
		System.out.println(MD("111"));
		System.out.println(new Date().getTime());
	}
	
	//第一种实现
    public final static String MD5(String s) {
        char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};       
        try {
            byte[] btInput = s.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    //第二种实现
    public final static String MD(String str) {//项目中的
        try {
              MessageDigest md5 = MessageDigest.getInstance("MD5");
              md5.update(str.getBytes());
              byte b[] = md5.digest();

              StringBuffer sb = new StringBuffer("");
              for (int n = 0; n < b.length; n++) {
                int i = b[n];
                if (i < 0) i += 256;
                if (i < 16) sb.append("0");
                sb.append(Integer.toHexString(i));
              }
              return sb.toString();  //32位加密
            } catch (NoSuchAlgorithmException e) {
              e.printStackTrace();
              return null;
            }
    }
	
}
