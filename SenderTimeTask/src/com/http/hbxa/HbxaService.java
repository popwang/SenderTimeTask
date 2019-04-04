package com.http.hbxa;

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
		String info = XabqUtil.getAirString(v);
		CommonUtil.doHttpGet(v.getV_url()+"?info="+info,log);
	}
	
	public static void main(String[] args) {
		EquipmentData v = CommonUtil.getEquipmentDataInstance();
		String info = XabqUtil.getAirString(v);
		String url = "";
		CommonUtil.doHttpGet(url+info,log);
	}
}
