package com.tcp.xainter;

import org.springframework.stereotype.Component;

import com.common.service.AbstractBaseService;
import com.tcp.xadx.DxUtil;
import com.utils.CommonUtil;
import com.utils.SocketUtil;
import com.utils.SystemEnum;
import com.vo.EquipmentData;
/**
 * 西安国际港务区对接，协议同西安其他区相同，先发设备信息后给ip+port
 * @author pactera
 *
 */
@Component
public class XaInterService extends AbstractBaseService {

	@Override
	public void sendEquipmentData(EquipmentData v) {
		DxUtil dx = new DxUtil(v);
		SocketUtil.init2(SystemEnum.XA_INTER_SYSTEM.toString());
		SocketUtil.sendByteDataBySocket(SystemEnum.XA_INTER_SYSTEM.toString(), 1,dx.getSequenceBytes(), log);
		SocketUtil.sendByteDataBySocket(SystemEnum.XA_INTER_SYSTEM.toString(), 1,dx.getNoiseBytes(), log);
		SocketUtil.sendByteDataBySocket(SystemEnum.XA_INTER_SYSTEM.toString(), 1,dx.getPM25Bytes(), log);
		SocketUtil.sendByteDataBySocket(SystemEnum.XA_INTER_SYSTEM.toString(), 1,dx.getPM10Bytes(), log);
	}
	
	public static void main(String[] args) {
		XaInterService service = new XaInterService();
		EquipmentData v = CommonUtil.getEquipmentDataInstance();
		v.setV_equipment_name("19941048354");
		service.sendEquipmentData(v);
	}

}
