package com.tcp.xabq2;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mapper.CommonMapper;
import com.tcp.xabq.XabqUtil;
import com.utils.CommonUtil;
import com.utils.ConfigReader;
import com.utils.SystemEnum;
import com.vo.EquipmentData;
import com.vo.EquipmentProjectVo;
/**
 * ���������������ƽ̨��Э����Ҫ�Լ��ṩ���ݶ�ʹ���رؼѸ�����Э��
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
			EquipmentData e = mapper.selectDataByName(vo.getV_real_equipment_name());
			if(e==null){
				log.info(vo.getV_equipment_name()+"��ǰ�����ݡ�");
				continue;
			}
			e.setV_equipment_name(vo.getV_equipment_name());
			String info = "";
			try {
				info = XabqUtil.toJsonObject(e);
			} catch (JSONException e1) {
				e1.printStackTrace();
			}
			CommonUtil.sendDataToRemote2(SystemEnum.XA_BQ2_SYSTEM.toString(), info, log);
		}
		log.info(SystemEnum.XA_BQ2_SYSTEM.getName()+"���ִ����ݷ�����ɣ�");
	}
	
	public void handler(String name){
		List<EquipmentData> list = mapper.selectDataListByName(name);
		for(EquipmentData e : list){
			String info = "";
			try {
				info = XabqUtil.toJsonObject(e);
				System.out.println(info);
			} catch (JSONException e1) {
				e1.printStackTrace();
			}
		}
	}
		
	public static void main(String[] args) throws JSONException {
		EquipmentData e = CommonUtil.getEquipmentDataInstance();
		e.setV_equipment_name("4100371002900001000");
		String info = XabqUtil.toJsonObject(e);
		log.info("��������:" + info);
		CommonUtil.sendDataToRemote2(SystemEnum.XA_BQ2_SYSTEM.toString(),info,log);
	}
}