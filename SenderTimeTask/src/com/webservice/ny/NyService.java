package com.webservice.ny;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.springframework.stereotype.Component;

import com.common.service.AbstractBaseService;
import com.utils.CommonUtil;
import com.utils.SystemEnum;
import com.utils.ThreadPoolUtil;
import com.utils.WebserviceUtil;
import com.vo.EquipmentData;
import com.vo.EquipmentProjectVo;
import com.webservice.ny.ws.DataAccessException_Exception;
import com.webservice.ny.ws.SaveYCJCService;
import com.webservice.ny.ws.SaveYCJCServicePortType;
/**
 * �����ӿڶԽ�
 * 9λ���룬ǰ3λ��ƽ̨�ṩ����6λ����뱣�ֲ��ظ�����
 * ������ϵ�ˣ�13903776396����Ҫ����֤�飬��δ���䳧�ұ���
 * �Խ��豸��374
 * @author pactera
 *
 */
@Component
public class NyService extends AbstractBaseService {
	
	/**
	 * ��д����handler����
	 */
	@Override
	public void handler(SystemEnum systemEnum) {
		List<EquipmentProjectVo> list = mapper.selectEquipmentListBySystemId(systemEnum.getId());
		log.info(systemEnum.getName() + "���ִ������豸��Ϊ��" + list.size());
		SaveYCJCServicePortType port = this.getPortTimeOut(WebserviceUtil.WEBSERVICE_TIMEOUT);
		if (port == null) {
			log.info("��ȡwebservice����ʱ�����η����쳣�˳���");
			return;
		}
		for (EquipmentProjectVo name : list) {
			String e = name.getV_real_equipment_name();
			EquipmentData v = mapper.selectDataByName(e);
			if (v == null) {
				log.info(name.getV_equipment_name() + "��ǰ�����ݡ�");
				continue;
			}
			v.setV_equipment_name(name.getV_equipment_name());
			String dataStr = WebserviceUtil.getDataString(v);
			log.info(dataStr);
			try {
				String result = port.saveYCJC(dataStr);
				log.info("���������ؽ����" + result);
			} catch (DataAccessException_Exception e1) {
				e1.printStackTrace();
			}
		}
		log.info(systemEnum.getName() + "�������ݷ�����ɣ�");
	}
	
	@Override
	public void sendEquipmentData(EquipmentData v) {
		
	}
	
	/**
	 * ����ʱ��ȡwebservice����
	 * second ��
	 * @return
	 */
	public SaveYCJCServicePortType getPortTimeOut(int second){
		ExecutorService exec = ThreadPoolUtil.getExecutorService();
	    Callable<SaveYCJCServicePortType> call = new Callable<SaveYCJCServicePortType>() {  
	        public SaveYCJCServicePortType call() throws Exception {
	        	SaveYCJCService ss = new SaveYCJCService();
	    	    SaveYCJCServicePortType port = ss.getSaveYCJCServiceHttpPort();
	            return port;
	        }
	    };
	    Future<SaveYCJCServicePortType> future = exec.submit(call);
	    SaveYCJCServicePortType port = null;
		try {
			port = future.get(1000 * second, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e2) {
			e2.printStackTrace();
		} catch (ExecutionException e2) {
			e2.printStackTrace();
		} catch (TimeoutException e2) {
			e2.printStackTrace();
		} 
		return port;
	}
	
	public static void main(String[] args) {
		NyService ry = new NyService();
		SaveYCJCServicePortType port = ry.getPortTimeOut(3);
	    if(port==null){
	    	log.info("��ȡwebservice����ʱ�����η����쳣�˳���");
	    }
	    EquipmentData vo = CommonUtil.getEquipmentDataInstance();
	    vo.setV_equipment_name("621000677");
	    String dataStr = WebserviceUtil.getDataString(vo);
	    try {
			String result = port.saveYCJC(dataStr);
			System.out.println(result);
		} catch (DataAccessException_Exception e) {
			e.printStackTrace();
		}
	}
}
