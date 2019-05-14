package com.tcp.hbxa2;

import org.json.JSONException;
import org.springframework.stereotype.Component;

import com.common.service.AbstractBaseService;
import com.http.hbxa.HbxaUtil;
import com.utils.CommonUtil;
import com.utils.SocketUtil;
import com.utils.SystemEnum;
import com.vo.EquipmentData;

@Component
public class Hbxa2Service extends AbstractBaseService{

	@Override
	public void sendEquipmentData(EquipmentData v) {
		try {
			String info = HbxaUtil.getJsonInfo(v);
			SocketUtil.init2(SystemEnum.HB_XA2_SYSTEM.toString());
			SocketUtil.sendDataBySocket(SystemEnum.HB_XA2_SYSTEM.toString(), 1,info, log);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		EquipmentData v = CommonUtil.getEquipmentDataInstance();
		try {
			String info = HbxaUtil.getJsonInfo(v);
//			SocketUtil.init2(SystemEnum.HB_XA2_SYSTEM.toString());
			SocketUtil.init(SystemEnum.HB_XA2_SYSTEM.toString(),"115.28.103.66",65531);
			SocketUtil.sendDataBySocket(SystemEnum.HB_XA2_SYSTEM.toString(), 1,info, log);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

}
