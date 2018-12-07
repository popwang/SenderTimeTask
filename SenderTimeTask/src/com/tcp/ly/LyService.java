package com.tcp.ly;

import org.springframework.stereotype.Component;

import com.common.service.AbstractBaseService;
import com.utils.ByteUtil;
import com.utils.CommonUtil;
import com.utils.SocketUtil;
import com.utils.SystemEnum;
import com.vo.EquipmentData;
/**
 * �����м�����ƽ̨��Э��������һ��
 * �豸����ƽ̨ͳһ����
 * @author 27438
 */
@Component
public class LyService extends AbstractBaseService {
	@Override
	public void sendEquipmentData(EquipmentData v) {
		sendKfDataToRemote(ByteUtil.getSendDataBytes(LYUtil.getRegisterInfo(v), ByteUtil.CMD_R));
		sendKfDataToRemote(ByteUtil.getSendDataBytes(LYUtil.getVoiceInfo(v),ByteUtil.CMD_V));
		sendKfDataToRemote(ByteUtil.getSendDataBytes(LYUtil.getPmInfo(v), ByteUtil.CMD_P));
	}
	
	public static void main(String[] args){
		EquipmentData e = CommonUtil.getEquipmentDataInstance();
		e.setV_equipment_name("4103060028102");
		sendKfDataToRemote(ByteUtil.getSendDataBytes(LYUtil.getRegisterInfo(e), ByteUtil.CMD_R));
		sendKfDataToRemote(ByteUtil.getSendDataBytes(LYUtil.getVoiceInfo(e),ByteUtil.CMD_V));
		sendKfDataToRemote(ByteUtil.getSendDataBytes(LYUtil.getPmInfo(e), ByteUtil.CMD_P));
	}
	
	public static void sendKfDataToRemote(byte[] info){
		SocketUtil.init2(SystemEnum.HA_LY_SYSTEM.toString());
		SocketUtil.sendByteDataBySocket(SystemEnum.HA_LY_SYSTEM.toString(), 1,info, log);
	}
	
}
