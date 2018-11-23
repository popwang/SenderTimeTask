package com.test.cq;

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

public class Cq2Util2Test {
	
	public static String supplierKeyId = "b80348fa-3b8e-461d-a508-64ac4df2bf13";
	public static String supplierSecretKey = "CWHXNtrootfW2ht11dgiGPJRI2CF0voSteRs";
	
	public static String projectKeyId = "ebd99521-93f1-4834-9c6c-ca8aa5432536";
	public static String projectSecretKey = "O46C6Nv9JygA477mm69RzCTzqUzOoGYSGiyC";

	public static Map<String,String> getHeaderMap(){
		Map<String,String> header = new HashMap<>();
		SignatureDTO dto = SignatureDemo.createSignature(supplierKeyId + "_" + projectKeyId, supplierSecretKey, projectSecretKey);
		
		System.out.println("keyId:"+dto.getKeyId());
		System.out.println("ts:"+dto.getTs());
		System.out.println("rCode:"+dto.getrCode());
		System.out.println("signature:"+dto.getSignature());
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
	
	/**
	 * 生成随机数字和字母,  
	 * @param length
	 * @return
	 */
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
