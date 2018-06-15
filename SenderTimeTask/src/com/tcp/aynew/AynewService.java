package com.tcp.aynew;

import org.springframework.stereotype.Component;

import com.common.service.AbstractBaseService;
import com.utils.ByteUtil;
import com.utils.CommonUtil;
import com.utils.SystemEnum;
import com.vo.EquipmentData;
/**
 * 安阳新平台，原http协议平台已经废弃
 * ip:222.223.239.126:9540
 * 域名:em.home-m2m.com:9540
 * @author pactera
 */
@Component
public class AynewService extends AbstractBaseService {

	@Override
	public void sendEquipmentData(EquipmentData v) {
		v.setV_equipment_name(v.getV_equipment_name().substring(4));
		AynewUtil util = new AynewUtil(v);
		byte[] info = util.getContentBytes();
		log.info(ByteUtil.bytesToHexString(info));
		CommonUtil.sendByteDataToRemote2(SystemEnum.AY_NEW_SYSTEM.toString(), info, log);
	}
	
	public static void main(String[] args) {
		AynewService ay = new AynewService();
		EquipmentData v = CommonUtil.getEquipmentDataInstance();
		v.setV_equipment_name("00000801");//313038d15a0e
		ay.sendEquipmentData(v);
	}
}
