package com.tcp.anhx;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.common.service.AbstractBaseService;
import com.utils.CommonUtil;
import com.utils.SystemEnum;
import com.vo.EquipmentData;
import com.vo.EquipmentProjectVo;
/**
 * ��������2ƽ̨��ʹ��tcpЭ�飬ԭƽ̨�����Ѿ�����
 * 218.28.94.157:60009
 * ΢��Ⱥ����豸
 * @author pactera
 */
@Component
public class Ayhx2Service extends AbstractBaseService {
	
	@Override
	public void handler(SystemEnum systemEnum) {
		List<EquipmentProjectVo> list = mapper.selectEquipmentListBySystemId(systemEnum.getId());
		log.info(systemEnum.getName()+"���ִ������豸��Ϊ��" + list.size());
		List<EquipmentData> datas = new ArrayList<>();
		for (EquipmentProjectVo vo : list) {
			EquipmentData v = mapper.selectDataByName(vo.getV_real_equipment_name());
			if(v==null){
				log.info(vo.getV_equipment_name()+"��ǰ�����ݡ�");
				continue;
			}
			v.setV_equipment_name(vo.getV_equipment_name());
			v.setV_project_name(vo.getV_project_name());
			datas.add(v);
		}
		String info = AyHxUtil.getDataString(datas);
		log.info("�������ݣ�"+info);
		byte[] bytes = AyHxUtil.getBytesArray(info);
		CommonUtil.sendByteDataToRemote2(systemEnum.toString(), bytes, log);
		log.info(systemEnum.getName()+"���ַ�����ɡ�");
	}
	
	public static void main(String[] args) {
//		Ayhx2Service service = new Ayhx2Service();
//		service.handler(SystemEnum.AY_HX2_SYSTEM);
		List<EquipmentData> datas = new ArrayList<>();
		for(int i=0;i<5;i++){
			datas.add(CommonUtil.getEquipmentDataInstance());
		}
		String info = AyHxUtil.getDataString(datas);
		log.info(info);
		byte[] bytes = AyHxUtil.getBytesArray(info);
		CommonUtil.sendByteDataToRemote2(SystemEnum.AY_HX2_SYSTEM.toString(), bytes, log);
	}

	@Override
	public void sendEquipmentData(EquipmentData v) {
		
	}
}
