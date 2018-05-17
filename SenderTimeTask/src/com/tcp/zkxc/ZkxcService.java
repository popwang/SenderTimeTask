package com.tcp.zkxc;

import org.springframework.stereotype.Component;

import com.common.service.AbstractBaseService;
import com.tcp.kf.KFUtil;
import com.utils.ByteUtil;
import com.utils.CommonUtil;
import com.utils.SocketUtil;
import com.utils.SystemEnum;
import com.vo.EquipmentData;

/**
 * 周口项城对接，协议与开封，洛阳伊川相同 
 * 联系人：白鹏，bp18695858852 
 * ip:61.163.187.196:9080
 * 设备编号：4116810034101
 * @author pactera
 */
@Component
public class ZkxcService extends AbstractBaseService {

	@Override
	public void sendEquipmentData(EquipmentData e) {
		
		SocketUtil.init2(SystemEnum.ZK_XC_SYSTEM.toString());
		
		SocketUtil.sendByteDataBySocket(SystemEnum.ZK_XC_SYSTEM.toString(), 1,
				ByteUtil.getSendDataBytes(KFUtil.getRegisterInfo(e), ByteUtil.CMD_R), log);
		SocketUtil.sendByteDataBySocket(SystemEnum.ZK_XC_SYSTEM.toString(), 1,
				ByteUtil.getSendDataBytes(KFUtil.getVoiceInfo(e), ByteUtil.CMD_V), log);
		SocketUtil.sendByteDataBySocket(SystemEnum.ZK_XC_SYSTEM.toString(), 1,
				ByteUtil.getSendDataBytes(KFUtil.getPmInfo(e), ByteUtil.CMD_P), log);
	}
	
	public static void main(String[] args) {
		ZkxcService service = new ZkxcService();
		EquipmentData e = CommonUtil.getEquipmentDataInstance();
		e.setV_equipment_name("4116810034101");
		service.sendEquipmentData(e);
	}

}
