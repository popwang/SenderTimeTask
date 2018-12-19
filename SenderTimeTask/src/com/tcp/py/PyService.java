package com.tcp.py;

import org.springframework.stereotype.Component;

import com.common.service.AbstractBaseService;
import com.utils.CommonUtil;
import com.utils.ConfigReader;
import com.utils.SocketUtil;
import com.utils.SystemEnum;
import com.vo.EquipmentData;
/**
 * 濮阳对接接口
 * @author 27438
 *
 */
@Component
public class PyService extends AbstractBaseService {
	@Override
	public void sendEquipmentData(EquipmentData v) {
		PyUtil py = new PyUtil(v);
		byte[] infos = py.getInfos();
		SocketUtil.init(SystemEnum.HA_PY_SYSTEM.toString()+"-"+v.getV_equipment_name(),ConfigReader.getHost(SystemEnum.HA_PY_SYSTEM.toString()),
				ConfigReader.getPort(SystemEnum.HA_PY_SYSTEM.toString()));
		SocketUtil.sendByteDataBySocket(SystemEnum.HA_PY_SYSTEM.toString(), 0, infos, log);
	}
	
	public static void main(String[] args){
		EquipmentData e = CommonUtil.getEquipmentDataInstance();
		e.setV_equipment_name("4500-0028-sclw-5001");
		PyUtil py = new PyUtil(e);
		byte[] infos = py.getInfos();
		SocketUtil.init2(SystemEnum.HA_PY_SYSTEM.toString());
		SocketUtil.sendByteDataBySocket(SystemEnum.HA_PY_SYSTEM.toString(), 0, infos, log);
	}
	
}
