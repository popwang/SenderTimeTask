package com.tcp.lyyc;

import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import org.springframework.stereotype.Component;

import com.common.service.AbstractBaseService;
import com.tcp.kf.KFUtil;
import com.utils.ByteUtil;
import com.utils.CommonUtil;
import com.utils.SystemEnum;
import com.vo.EquipmentData;
import com.vo.EquipmentProjectVo;
/**
 * ��������ƽ̨��Э�����������һ��
 * ����Ҫ���
 * @author 27438
 *
 */
@Component
public class LyycService extends AbstractBaseService {
	
	@Override
	public void handler(SystemEnum systemEnum) {
		List<EquipmentProjectVo> list = mapper.selectEquipmentListBySystemId(systemEnum.getId());
		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));
		int minute = calendar.get(Calendar.MINUTE)%10;
		for (EquipmentProjectVo vo : list) {
			if(Integer.parseInt(vo.getV_equipment_name().substring(vo.getV_equipment_name().length()-1))==minute) {
				EquipmentData v = mapper.selectDataByName(vo.getV_real_equipment_name());
				if(v==null){
					log.info(vo.getV_equipment_name()+"��ǰ�����ݡ�");
					continue;
				}
				v.setV_equipment_name(vo.getV_equipment_name());
				log.info("��ǰ�����豸�ţ�"+v.getV_equipment_name());
				sendKfDataToRemote(ByteUtil.getSendDataBytes(KFUtil.getRegisterInfo(v), ByteUtil.CMD_R));
				sendKfDataToRemote(ByteUtil.getSendDataBytes(KFUtil.getVoiceInfo(v),ByteUtil.CMD_V));
				sendKfDataToRemote(ByteUtil.getSendDataBytes(KFUtil.getPmInfo(v), ByteUtil.CMD_P));
			}
		}
		log.info(systemEnum.getName()+"���ַ�����ɡ�");
	}
	
	@Override
	public void sendEquipmentData(EquipmentData v) {
		
	}
	
	public static void sendKfDataToRemote(byte[] info){
		CommonUtil.sendByteDataToRemote2(SystemEnum.LY_YC_SYSTEM.toString(), info, log);
	}
	
}
