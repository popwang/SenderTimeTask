package com.tcp.xabq2;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mapper.CommonMapper;
import com.tcp.xabq.XabqService;
import com.tcp.xabq.XabqUtil;
import com.utils.CommonUtil;
import com.utils.ConfigReader;
import com.utils.SocketUtil;
import com.utils.SystemEnum;
import com.utils.TBJUtil;
import com.vo.EquipmentData;
import com.vo.EquipmentProjectVo;
/**
 * ���������������ƽ̨��Э����Ҫ�Լ��ṩ���ݶ�ʹ�������Э��
 * �绰΢�ţ�18161830955
 * ����ŷ������ң�������ƽ̨���еǼǣ��ݶ���Ȼʹ����ŷ�����豸����
 * SDYKAZ00001000
 * ���ڼ����Ϊ80-100��֮�䣬��90�룬cron�Ķ�ʱ�����޷���ɣ���ʹ��schedule��ʱ���ܣ�����main��������
 * @author pactera
 *
 */
@Component
public class Xabq2Service implements Runnable {
	public static Log log = LogFactory.getLog(Xabq2Service.class);
	@Autowired
	private CommonMapper mapper;
	@Override
	public void run() {
		List<EquipmentProjectVo> list = mapper.selectEquipmentListBySystemId(SystemEnum.XA_BQ2_SYSTEM.getId());
		log.info(SystemEnum.XA_BQ2_SYSTEM.getName()+"���ִ������豸��Ϊ��"+list.size());
		for(EquipmentProjectVo vo : list){
			EquipmentData e = mapper.selectDataByName(vo.getV_equipment_name().substring(6));
			if(e==null){
				log.info(vo.getV_equipment_name()+"��ǰ�����ݡ�");
				continue;
			}
			e.setV_equipment_name(vo.getV_equipment_name());
			String info = XabqUtil.getAirString(e);
			SocketUtil.init2(SystemEnum.XA_BQ2_SYSTEM.toString());
			SocketUtil.sendDataBySocket(SystemEnum.XA_BQ2_SYSTEM.toString(), 1,info, log);
		}
		log.info(SystemEnum.XA_BQ2_SYSTEM.getName()+"���ִ����ݷ�����ɣ�");
	}
		
	public static void main(String[] args) {
		EquipmentData e = CommonUtil.getEquipmentDataInstance();
		e.setV_equipment_name("SDYKAZ00001000");
		String info = TBJUtil.getDataString2(e);
		log.info("��������:" + info);
		CommonUtil.sendDataToRemote(ConfigReader.getHost(SystemEnum.XA_BQ2_SYSTEM.toString()),
				ConfigReader.getPort(SystemEnum.XA_BQ2_SYSTEM.toString()),info,log);
	}
}
