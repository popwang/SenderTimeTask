package com.http.cq;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.json.JSONException;
import org.json.JSONObject;

import com.utils.CRC;
import com.utils.CommonUtil;
import com.vo.EquipmentData;

public class CqUtil {
	
	public static String PUBLIC = "3aAde902-24dkdfAd-2vNGd88dc-j28pqEq6";
	public static String PRIVATE = "asd23s9df9quijfsoudghf387vhtruhvw4tf";
	
	public static Map<String,String> getHeaderMap(){
		Map<String,String> header = new HashMap<>();
		try {
			String rd = getStringRandom(10);
			String ts = CRC.currentTimeString("yyMMddHHmmss");
//			String time = Date2TimeStamp(ts,"yyMMddHHmmss");
			String tmp = rd+"_"+ts+"_"+PRIVATE;
			header.put("keyId", PUBLIC);
			header.put("ts", ts);
			header.put("rCode", rd);
			header.put("sign", sha1(tmp));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return header;
	}
	
	public static JSONObject toJsonObject(EquipmentData v) throws JSONException{
		JSONObject object = new JSONObject();
		object.put("deviceID", v.getV_equipment_name());
		object.put("recordTime", CRC.currentTimeString("yyMMddHHmmss"));
		object.put("temperature", v.getP006()+"");
		object.put("humidity", v.getP007()+"");
		object.put("pm2p5", v.getP002()+"");
		object.put("pm10", v.getP003()+"");
		object.put("noise", v.getP008()+"");
		object.put("windSpeed", v.getP004()+"");
		object.put("windDirection", CommonUtil.getWindString(v.getP005()));
		object.put("sessionID", getStringRandom(8));
		return object;
	}
	
	public static String sha1(String data) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA1");
		md.update(data.getBytes());
		StringBuffer buf = new StringBuffer();
		byte[] bits = md.digest();
		for(int i=0;i<bits.length;i++){
			int a = bits[i];
			if(a<0) a+=256;
			if(a<16) buf.append("0");
			buf.append(Integer.toHexString(a));
		}
		return buf.toString();
	}
	
	
	//生成随机数字和字母,  
    public static String getStringRandom(int length) {  
        String val = "";  
        Random random = new Random();        
        //length为几位密码 
        for(int i = 0; i < length; i++) {          
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";  
            //输出字母还是数字  
            if( "char".equalsIgnoreCase(charOrNum) ) {  
                //输出是大写字母还是小写字母  
                int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;  
                val += (char)(random.nextInt(26) + temp);  
            } else if( "num".equalsIgnoreCase(charOrNum) ) {  
                val += String.valueOf(random.nextInt(10));  
            }  
        }  
        return val;  
    }
    
    public static String getNowTimeStamp() {
        long time = System.currentTimeMillis();
        String nowTimeStamp = String.valueOf(time / 1000);
        return nowTimeStamp;
    }
    
    public static String Date2TimeStamp(String dateStr, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return String.valueOf(sdf.parse(dateStr).getTime() / 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
	
	public static void main(String[] args) throws NoSuchAlgorithmException {
		String rd = getStringRandom(10);
		String time = CRC.currentTimeString("yyMMddHHmmss");
		String tmp = rd+"_"+time+"_"+PRIVATE;
		System.out.println(rd);
		System.out.println(time);
		System.out.println(sha1(tmp));
		String sha = "w06004GM28_180707111810_asd23s9df9quijfsoudghf387vhtruhvw4tf";
		System.out.println(sha1(sha));
	}
}
