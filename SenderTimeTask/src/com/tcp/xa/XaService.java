package com.tcp.xa;

import org.springframework.stereotype.Component;

import com.common.service.AbstractBaseService;
import com.tcp.xadx.DxUtil;
import com.utils.SocketUtil;
import com.utils.SystemEnum;
import com.vo.EquipmentData;
/**
 * ***************************
 * ԭЭ��������
 * �����������Խӽӿڣ�Э��ʹ��212
 * ����豸��Ҫ�����Ź���΢�ţ���˹�����Ϣ������MN�� 
 * AZ00000075��AZ000117110901
 * AZ00000109��AZ000117112903
 * ***************************
 * 2018-06-21 ��Э��ʹ�ô�����Э�飬����˲���
 * ***************************
 * @author pactera
 */
@Component
public class XaService extends AbstractBaseService {

	@Override
	public void sendEquipmentData(EquipmentData v) {
		DxUtil dx = new DxUtil(v);
		SocketUtil.init2(SystemEnum.SX_XA_SYSTEM.toString());
		SocketUtil.sendByteDataBySocket(SystemEnum.SX_XA_SYSTEM.toString(), 1,dx.getSequenceBytes(), log);
		SocketUtil.sendByteDataBySocket(SystemEnum.SX_XA_SYSTEM.toString(), 1,dx.getNoiseBytes(), log);
		SocketUtil.sendByteDataBySocket(SystemEnum.SX_XA_SYSTEM.toString(), 1,dx.getPM25Bytes(), log);
		SocketUtil.sendByteDataBySocket(SystemEnum.SX_XA_SYSTEM.toString(), 1,dx.getPM10Bytes(), log);
//		SocketUtil.sendByteDataBySocket(SystemEnum.SX_XA_SYSTEM.toString(), 1,dx.getGPSBytes(), log);
//		SocketUtil.sendByteDataBySocket(SystemEnum.SX_XA_SYSTEM.toString(), 1,dx.getDropRateBytes(), log);
	}
	
	public static void main(String[] args){
		EquipmentData e = new EquipmentData();
		e.setV_equipment_name("18490437173");
		e.setP001(0);
		e.setP002(68);
		e.setP003(83);
		e.setP004(0.5);
		e.setP005(3);
		e.setP006(32.0);
		e.setP007(45.3);
		e.setP008(52.1);
		e.setP009(0);
		e.setP010(0);
		
		DxUtil dx = new DxUtil(e);
		SocketUtil.init2(SystemEnum.SX_XA_SYSTEM.toString());
		SocketUtil.sendByteDataBySocket(SystemEnum.SX_XA_SYSTEM.toString(), 1,dx.getSequenceBytes(), log);
		SocketUtil.sendByteDataBySocket(SystemEnum.SX_XA_SYSTEM.toString(), 1,dx.getNoiseBytes(), log);
		SocketUtil.sendByteDataBySocket(SystemEnum.SX_XA_SYSTEM.toString(), 1,dx.getPM25Bytes(), log);
		SocketUtil.sendByteDataBySocket(SystemEnum.SX_XA_SYSTEM.toString(), 1,dx.getPM10Bytes(), log);
		SocketUtil.sendByteDataBySocket(SystemEnum.SX_XA_SYSTEM.toString(), 1,dx.getGPSBytes(), log);
		SocketUtil.sendByteDataBySocket(SystemEnum.SX_XA_SYSTEM.toString(), 1,dx.getDropRateBytes(), log);
	}
}
