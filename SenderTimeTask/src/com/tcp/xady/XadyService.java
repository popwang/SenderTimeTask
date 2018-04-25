package com.tcp.xady;

import org.springframework.stereotype.Component;

import com.common.service.AbstractBaseService;
import com.tcp.xadx.DxUtil;
import com.utils.CommonUtil;
import com.utils.SocketUtil;
import com.utils.SystemEnum;
import com.vo.EquipmentData;
/**
 * �����������Խ�Э�飬����ͬ����������ַ��ͬ��404
 * ��������ϢEXCEL��д��ɣ��뷢��������325802777@qq.com��΢�ŷ��ͻ�����Ϣ��
 * @author pactera
 */
@Component
public class XadyService extends AbstractBaseService {
	
	@Override
	public void sendEquipmentData(EquipmentData v) {
		DxUtil dx = new DxUtil(v);
		SocketUtil.init2(SystemEnum.XA_DY_SYSTEM.toString());
		SocketUtil.sendByteDataBySocket(SystemEnum.XA_DY_SYSTEM.toString(), 1,dx.getSequenceBytes(), log);
		SocketUtil.sendByteDataBySocket(SystemEnum.XA_DY_SYSTEM.toString(), 1,dx.getNoiseBytes(), log);
		SocketUtil.sendByteDataBySocket(SystemEnum.XA_DY_SYSTEM.toString(), 1,dx.getPM25Bytes(), log);
		SocketUtil.sendByteDataBySocket(SystemEnum.XA_DY_SYSTEM.toString(), 1,dx.getPM10Bytes(), log);
		SocketUtil.sendByteDataBySocket(SystemEnum.XA_DY_SYSTEM.toString(), 1,dx.getGPSBytes(), log);
		SocketUtil.sendByteDataBySocket(SystemEnum.XA_DY_SYSTEM.toString(), 1,dx.getDropRateBytes(), log);
	}
	
	public static void main(String[] args) {
		XadyService service = new XadyService();
		EquipmentData v = CommonUtil.getEquipmentDataInstance();
		v.setV_equipment_name("17484756014");
		service.sendEquipmentData(v);
	}

}
