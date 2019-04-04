package com.tcp.cd;

import org.springframework.stereotype.Component;

import com.common.service.AbstractBaseService;
import com.utils.ByteUtil;
import com.utils.SocketUtil;
import com.utils.SystemEnum;
import com.vo.EquipmentData;
/**
 *   成都平台接口
 *   微信联系人
 * @author 27438
 *
 */
@Component
public class CdService extends AbstractBaseService {

	@Override
	public void sendEquipmentData(EquipmentData v) {
		
	}
	
	public static void main(String[] args) {
		DevUpMsg dev = CdUtil.getDevUpMsg();
		byte[] info = CdUtil.getDataInfo(dev);
		log.info(ByteUtil.bytesToHexString(info));
		SocketUtil.init(SystemEnum.SC_CD_SYSTEM.toString(),"xsd.watchmen.cn",10030);
		SocketUtil.sendByteDataBySocket(SystemEnum.SC_CD_SYSTEM.toString(), 1,info, log);
	}

}
