package com.tcp.traffic;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mapper.CommonMapper;
import com.tcp.sd.SdService;
import com.utils.CRC;
import com.utils.CommonUtil;
import com.utils.ConfigReader;
import com.utils.SystemEnum;
import com.vo.EquipmentData;
import com.vo.EquipmentProjectVo;

/**
 * 1.֣�ݽ�ί���ݽ���ƽ̨����˾Ϊ���ڹ�˾��������ɽ������Ϊͬһ��˾��
 * 2.Ŀǰ�ѽ��������ɹ��������������Ҫ�����豸����Ҫ���йز��Ž��б�������д�Ƚ��걸����Ϣ��
 * 3.��ǰ��Ҫ�����豸Ϊ144�����ڹ�˾δ���������ݲ������գ���Э����豸���������Ҫ�� 
 * 4.�����绰��13826513167��QQ:120014831
 * 5.Socket socket = new Socket("203.110.178.83", 9005);//���Ի���
 *	 Socket socket = new Socket("139.224.57.175", 9005);// ��ʽ����
 * @author Administrator
 */
@Component
public class TrafficService {
	public static Log log = LogFactory.getLog(SdService.class);
	@Autowired
	private CommonMapper mapper;

	public void handler() {
		List<EquipmentProjectVo> list = mapper
				.selectEquipmentListBySystemId(SystemEnum.ZZ_TRA_SYSTEM.getId());
		log.info("���ִ������豸��Ϊ��"+list.size());
		for (EquipmentProjectVo vo : list) {
			EquipmentData e = mapper.selectDataByName(vo.getV_equipment_name());
			if(e==null){
				log.info(vo.getV_equipment_name()+"��ǰ�����ݡ�");
				continue;
			}
			String info = CRC.getDataString2(e);
			log.info("��������:" + info);
			CommonUtil.sendDataToRemote(ConfigReader.getZZtraIP(),
					ConfigReader.getZZtraPORT(),info,log);
		}
	}
}
