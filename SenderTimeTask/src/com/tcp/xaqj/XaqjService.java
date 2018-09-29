package com.tcp.xaqj;

import org.springframework.stereotype.Component;

import com.common.service.AbstractBaseService;
import com.tcp.xadx.DxUtil;
import com.utils.CommonUtil;
import com.utils.ConfigReader;
import com.utils.SocketUtil;
import com.utils.SystemEnum;
import com.vo.EquipmentData;

/**
 * 西安曲江对接协议，内容同大兴区，地址不同，414 将基本信息EXCEL填写完成，请发送至邮箱325802777@qq.com或微信发送基本信息表。
 * @author pactera
 */
@Component
public class XaqjService extends AbstractBaseService {

	@Override
	public void sendEquipmentData(EquipmentData v) {
		DxUtil dx = new DxUtil(v);
		// SocketUtil.init2(SystemEnum.XA_QJ_SYSTEM.toString()+"-"+v.getV_equipment_name());
		SocketUtil.init(SystemEnum.XA_QJ_SYSTEM.toString() + "-" + v.getV_equipment_name(),
				ConfigReader.getHost(SystemEnum.XA_QJ_SYSTEM.toString()),
				ConfigReader.getPort(SystemEnum.XA_QJ_SYSTEM.toString()));
		SocketUtil.sendByteDataBySocket(SystemEnum.XA_QJ_SYSTEM.toString() + "-" + v.getV_equipment_name(), 3,
				dx.getSequenceBytes(), log);
		SocketUtil.sendByteDataBySocket(SystemEnum.XA_QJ_SYSTEM.toString() + "-" + v.getV_equipment_name(), 3,
				dx.getNoiseBytes(), log);
		SocketUtil.sendByteDataBySocket(SystemEnum.XA_QJ_SYSTEM.toString() + "-" + v.getV_equipment_name(), 3,
				dx.getPM25Bytes(), log);
		SocketUtil.sendByteDataBySocket(SystemEnum.XA_QJ_SYSTEM.toString() + "-" + v.getV_equipment_name(), 3,
				dx.getPM10Bytes(), log);
		// 关闭连接下次重新申请
		 SocketUtil.deleteCacheByKey(SystemEnum.XA_QJ_SYSTEM.toString()+ "-" + v.getV_equipment_name());
		// CommonUtil.sendByteDataToRemote2(SystemEnum.XA_QJ_SYSTEM.toString(),dx.getSequenceBytes(),log);
		// CommonUtil.sendByteDataToRemote2(SystemEnum.XA_QJ_SYSTEM.toString(),dx.getNoiseBytes(),log);
		// CommonUtil.sendByteDataToRemote2(SystemEnum.XA_QJ_SYSTEM.toString(),dx.getPM25Bytes(),log);
		// CommonUtil.sendByteDataToRemote2(SystemEnum.XA_QJ_SYSTEM.toString(),dx.getPM10Bytes(),log);
	}

	public static void main(String[] args) {
		XaqjService service = new XaqjService();
		EquipmentData v = CommonUtil.getEquipmentDataInstance();
		v.setV_equipment_name("19984746944");
		service.sendEquipmentData(v);
	}

}
