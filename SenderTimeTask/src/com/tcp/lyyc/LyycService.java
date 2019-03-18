package com.tcp.lyyc;

import org.springframework.stereotype.Component;

import com.common.service.AbstractBaseService;
import com.tcp.kf.KFUtil;
import com.utils.ByteUtil;
import com.utils.CommonUtil;
import com.utils.ConfigReader;
import com.utils.SocketUtil;
import com.utils.SystemEnum;
import com.vo.EquipmentData;
/**
 * 洛阳伊川平台，协议与最初开封一样
 * 不需要添加
 * @author 27438
 *
 */
@Component
public class LyycService extends AbstractBaseService {
	@Override
	public void sendEquipmentData(EquipmentData v) {
		sendKfDataToRemote(ByteUtil.getSendDataBytes(KFUtil.getRegisterInfo(v), ByteUtil.CMD_R));
		sendKfDataToRemote(ByteUtil.getSendDataBytes(KFUtil.getVoiceInfo(v),ByteUtil.CMD_V));
		sendKfDataToRemote(ByteUtil.getSendDataBytes(KFUtil.getPmInfo(v), ByteUtil.CMD_P));
	}
	
	public static void main(String[] args){
		EquipmentData e = CommonUtil.getEquipmentDataInstance();
		e.setV_equipment_name("20170005150");
		sendKfDataToRemote(ByteUtil.getSendDataBytes(KFUtil.getRegisterInfo(e), ByteUtil.CMD_R));
		sendKfDataToRemote(ByteUtil.getSendDataBytes(KFUtil.getVoiceInfo(e),ByteUtil.CMD_V));
		sendKfDataToRemote(ByteUtil.getSendDataBytes(KFUtil.getPmInfo(e), ByteUtil.CMD_P));
	}
	
	public static void sendKfDataToRemote(byte[] info){
		SocketUtil.init(SystemEnum.LY_YC_SYSTEM.toString(), ConfigReader.getLyYcIP(),
				ConfigReader.getLyYcPORT());
		SocketUtil.sendByteDataBySocket(SystemEnum.LY_YC_SYSTEM.toString(), 1,info, log);
	}
	
}
