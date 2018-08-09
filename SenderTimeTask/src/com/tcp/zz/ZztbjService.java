package com.tcp.zz;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.ServerInterface;
import com.mapper.CommonMapper;
import com.utils.CommonUtil;
import com.utils.SocketUtil;
import com.utils.SystemEnum;
import com.utils.TBJUtil;
import com.utils.ThreadPoolUtil;
import com.vo.EquipmentData;

/**
 * 1.֣���رؼѹ�˾�ӿڣ�Ŀǰ����5̨�豸�� 
 * 2.�豸�б�ֱ����Ӽ��ɣ� 
 * 3.�����绰����-177 3718 3234
 * 4.20180808,�޸����ݲ�ѯ��ʽ��һ���Թ�����ѯȫ�����ݣ������̳߳ز����������ݣ�
 * 
 * @author Administrator
 */
@Component
public class ZztbjService implements ServerInterface {

	public static Log log = LogFactory.getLog(ZztbjService.class);
	@Autowired
	private CommonMapper mapper;
	
	public void handler() {
		List<EquipmentData> list = mapper.selectEquipmentDataListBySystemId(SystemEnum.ZZ_TBJ_SYSTEM.getId());
		log.info("���ִ������豸��Ϊ��" + list.size());
		ThreadPoolUtil.printExecutorStatus();
		for (EquipmentData v : list) {
			if(v.getV_real_equipment_name()==null){
				log.info(v.getV_equipment_name()+"��ǰ�����ݡ�");
				continue;
			}
			SendRunnable runner = new SendRunnable(v,log);
			ThreadPoolUtil.getExecutorService().execute(runner);
		}
	}
	
	public static void main(String[] args){
		EquipmentData e = CommonUtil.getEquipmentDataInstance();
		e.setV_equipment_name("ZZ01000604");
		String info = TBJUtil.getDataString(e);
		SocketUtil.init(SystemEnum.ZZ_TBJ_SYSTEM.toString(), "123.15.58.210", 9123);
		SocketUtil.sendDataBySocket(SystemEnum.ZZ_TBJ_SYSTEM.toString(), 1,info, log);	
	}
}

