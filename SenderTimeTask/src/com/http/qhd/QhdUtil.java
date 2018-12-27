package com.http.qhd;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.json.JSONException;
import org.json.JSONObject;

import com.http.cq2.SignatureDTO;
import com.http.cq2.SignatureDemo;
import com.utils.CRC;
import com.utils.CommonUtil;
import com.vo.EquipmentData;

public class QhdUtil {
	public static String supplierKeyId = "1e15e202-087d-4c60-9556-084f7a8a833a";
	public static String supplierSecretKey = "phtf9LZ661sAl0G1CdCJtzL6J5K5Ad5ID6Mv";
	
	public static String projectKeyId = "d44799b2-380b-4bc0-9d0d-426b41cd2318";
	public static String projectSecretKey = "CRngCqLflNuCYgFOCEZaW191RNFZM6111U35";
	
	public static Map<String,String> getHeaderMap(){
		Map<String,String> header = new HashMap<>();
		
		SignatureDTO dto = SignatureDemo.createSignature(supplierKeyId + "_" + projectKeyId, supplierSecretKey, projectSecretKey);
		header.put("keyId", dto.getKeyId());
		header.put("ts", dto.getTs()+"");
		header.put("rCode", dto.getrCode());
		header.put("signature", dto.getSignature());
		
		return header;
	}
	
	public static JSONObject toJsonObject(EquipmentData v) throws JSONException{
		JSONObject object = new JSONObject();
		object.put("sourceId", v.getV_equipment_name());
		object.put("serialNo", v.getV_equipment_name());
		object.put("recordTime", CRC.currentTimeString("yyyy-MM-dd HH:mm:ss"));
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
	
	
	//生成随机数字和字母
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
	
}
