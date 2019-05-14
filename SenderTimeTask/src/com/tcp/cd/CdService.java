package com.tcp.cd;

import org.springframework.stereotype.Component;

import com.common.service.AbstractBaseService;
import com.utils.ByteUtil;
import com.utils.CommonUtil;
import com.utils.SystemEnum;
import com.vo.EquipmentData;
/**
 *   �ɶ�ƽ̨�ӿ�
 *   ΢����ϵ��
 * @author 27438
 *
 */
@Component
public class CdService extends AbstractBaseService {

	@Override
	public void sendEquipmentData(EquipmentData v) {
		DevUpMsg dev = CdUtil.getDevUpMsg(v);
		byte[] info = CdUtil.getDataInfo(dev);
		log.info(ByteUtil.bytesToHexString(info));
		CommonUtil.sendByteDataToRemote2(SystemEnum.SC_CD_SYSTEM.toString(), info, log);
	}
	
	public static void main(String[] args) {
		DevUpMsg dev = CdUtil.getDevUpMsg();
		byte[] info = CdUtil.getDataInfo(dev);
		log.info(ByteUtil.bytesToHexString(info));
		CommonUtil.sendByteDataToRemote2(SystemEnum.SC_CD_SYSTEM.toString(), info, log);
	}

}
