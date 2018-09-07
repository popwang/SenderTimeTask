package com.tcp.xabl;

import org.springframework.stereotype.Component;

import com.common.service.AbstractBaseService;
import com.tcp.xadx.DxUtil;
import com.utils.CommonUtil;
import com.utils.SocketUtil;
import com.utils.SystemEnum;
import com.vo.EquipmentData;
/**
 * 西安碑林区对接
 * @author pactera
 */
@Component
public class XablService extends AbstractBaseService {

	@Override
	public void sendEquipmentData(EquipmentData v) {
		DxUtil dx = new DxUtil(v);
		SocketUtil.init2(SystemEnum.XA_BL_SYSTEM.toString());
		SocketUtil.sendByteDataBySocket(SystemEnum.XA_BL_SYSTEM.toString(), 1,dx.getSequenceBytes(), log);
		SocketUtil.sendByteDataBySocket(SystemEnum.XA_BL_SYSTEM.toString(), 1,dx.getNoiseBytes(), log);
		SocketUtil.sendByteDataBySocket(SystemEnum.XA_BL_SYSTEM.toString(), 1,dx.getPM25Bytes(), log);
		SocketUtil.sendByteDataBySocket(SystemEnum.XA_BL_SYSTEM.toString(), 1,dx.getPM10Bytes(), log);
	}
	
	public static void main(String[] args) {
		XablService xa = new XablService();
		EquipmentData v = CommonUtil.getEquipmentDataInstance();
		v.setV_equipment_name("15617543474");
		xa.sendEquipmentData(v);
	}
}
