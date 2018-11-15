package com.tcp.xanew;


import org.json.JSONException;
import org.springframework.stereotype.Component;

import com.common.service.AbstractBaseService;
import com.tcp.xabq.XabqUtil;
import com.utils.CommonUtil;
import com.utils.SystemEnum;
import com.vo.EquipmentData;
/**
 * 西安高新区对接接口，协议使用郑州特必佳协议
 * 联系人微信：蓝天，添加设备需要设备号和工地名称
 * ip:61.185.220.176:8068
 * 2018-11-08按照灞桥区2的协议进行发送
 * @author pactera
 */
@Component
public class XanewService extends AbstractBaseService{

	@Override
	public void sendEquipmentData(EquipmentData v) {
		String info = "";
		try {
			info = XabqUtil.toJsonObject(v);
		} catch (JSONException e1) {
			e1.printStackTrace();
		}
		CommonUtil.sendDataToRemote2(SystemEnum.XA_GXQ_SYSTEM.toString(), info, log);
	}
	
	public static void main(String[] args) {
		XanewService service = new XanewService();
		EquipmentData v = CommonUtil.getEquipmentDataInstance();
		v.setV_equipment_name("AZ00000729");
		service.sendEquipmentData(v);
	}
}
