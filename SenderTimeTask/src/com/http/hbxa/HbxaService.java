package com.http.hbxa;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;
import org.springframework.stereotype.Component;

import com.common.service.AbstractBaseService;
import com.tcp.xabq.XabqUtil;
import com.utils.CommonUtil;
import com.vo.EquipmentData;

/**
 * 河北雄安平台，协议使用国标212
 * 微信联系人
 * @author 27438
 */
@Component
public class HbxaService extends AbstractBaseService {

	@Override
	public void sendEquipmentData(EquipmentData v) {
		String info;
		try {
			info = HbxaUtil.getJsonInfo(v);
			CommonUtil.doHttpPost(v.getV_url(),info,log);
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) throws ClientProtocolException, JSONException, IOException {
		EquipmentData v = CommonUtil.getEquipmentDataInstance();
		String info = HbxaUtil.getJsonInfo(v);
		log.info(info);
		String url = "http://218.60.3.82:8080/api.do?uploadEnvInfo";
		CommonUtil.doHttpPost(url,info,log);
	}
}
