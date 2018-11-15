package com.tcp.xanew;


import org.json.JSONException;
import org.springframework.stereotype.Component;

import com.common.service.AbstractBaseService;
import com.tcp.xabq.XabqUtil;
import com.utils.CommonUtil;
import com.utils.SystemEnum;
import com.vo.EquipmentData;
/**
 * �����������Խӽӿڣ�Э��ʹ��֣���رؼ�Э��
 * ��ϵ��΢�ţ����죬����豸��Ҫ�豸�ź͹�������
 * ip:61.185.220.176:8068
 * 2018-11-08���������2��Э����з���
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
