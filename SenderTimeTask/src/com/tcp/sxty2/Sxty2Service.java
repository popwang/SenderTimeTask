package com.tcp.sxty2;

import org.springframework.stereotype.Component;

import com.common.service.AbstractBaseService;
import com.tcp.kf.KFUtil;
import com.utils.ByteUtil;
import com.utils.CommonUtil;
import com.utils.SystemEnum;
import com.vo.EquipmentData;
/**
 * 山西太原平台2
 * 协议与开封一致，服务地址不同
 * @author 27438
 *
 */
@Component
public class Sxty2Service extends AbstractBaseService{
	
	@Override
	public void sendEquipmentData(EquipmentData v) {
		sendKfDataToRemote(ByteUtil.getSendDataBytes(KFUtil.getRegisterInfo(v), ByteUtil.CMD_R));
		sendKfDataToRemote(ByteUtil.getSendDataBytes(KFUtil.getVoiceInfo(v),ByteUtil.CMD_V));
		sendKfDataToRemote(ByteUtil.getSendDataBytes(KFUtil.getPmInfo(v), ByteUtil.CMD_P));
	}
	
	
	public static void sendKfDataToRemote(byte[] info){
		CommonUtil.sendByteDataToRemote2(SystemEnum.SX_TY2_SYSTEM.toString(),info,log);
	}
	
	public static void main(String[] args) {
		Sxty2Service server = new Sxty2Service();
		EquipmentData e = CommonUtil.getEquipmentDataInstance();
		e.setV_equipment_name("AZ00000417");
		server.sendEquipmentData(e);
	}

}
