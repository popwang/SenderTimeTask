package com.http.nj;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;
import org.springframework.stereotype.Component;

import com.common.service.AbstractBaseService;
import com.utils.CommonUtil;
import com.vo.EquipmentData;
/**
 * 南京市平台对接接口
 * @author 27438
 *
 */
@Component
public class NjService extends AbstractBaseService {

	@Override
	public void sendEquipmentData(EquipmentData v) {
		try {
			String info = "["+NjUtil.toJsonObject(v).toString()+"]";
			log.info(info);
			Map<String,String> headers = new HashMap<>(); 
			headers.put("Content-Type", "application/json");
			headers.put("Accept", "application/json");
			CommonUtil.doHttpPost2(v.getV_url(), info, headers, log);
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) throws JSONException, ClientProtocolException, IOException {
		
		EquipmentData v = CommonUtil.getEquipmentDataInstance();
		v.setV_equipment_name("HYTDB10101");
		String info = "["+NjUtil.toJsonObject(v)+"]";
		log.info(info);
		String url = "http://hytd.bim001.cn/bims/rest/environmentInfo/saveEnvironmentInfo.jo";
		Map<String,String> headers = new HashMap<>(); 
		headers.put("Content-Type", "application/json");
		headers.put("Accept", "application/json");
		CommonUtil.doHttpPost2(url, info, headers, log);
	}
}
