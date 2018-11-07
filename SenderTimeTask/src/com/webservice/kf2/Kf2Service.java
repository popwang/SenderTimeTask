package com.webservice.kf2;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.ServerInterface;
import com.mapper.CommonMapper;
import com.utils.CommonUtil;
import com.utils.SystemEnum;
import com.utils.ThreadPoolUtil;
import com.utils.WebserviceUtil;
import com.vo.EquipmentData;
import com.vo.EquipmentProjectVo;
import com.webservice.kf2.ws.DataAccessException_Exception;
import com.webservice.kf2.ws.SaveYCJCService;
import com.webservice.kf2.ws.SaveYCJCServicePortType;
/**
 * 18903780430
 * ����2�Խӽӿڣ�ͳһ��ţ�621
 * �豸��ţ�235���а����
 * ����豸��Ҫ������Ϣ��΢�ſ����ﳾ2���
 * @author pactera
 */
@Component
public class Kf2Service implements ServerInterface {
	
	public static Log log = LogFactory.getLog(Kf2Service.class);
	@Autowired
	private CommonMapper mapper;
	
	@Override
	public void handler(){
	    List<EquipmentProjectVo> list = mapper.selectEquipmentListBySystemId(SystemEnum.HA_KF2_SYSTEM.getId());
	    log.info("���ִ������豸��Ϊ��"+list.size());
	    SaveYCJCServicePortType port = this.getPortTimeOut(WebserviceUtil.WEBSERVICE_TIMEOUT);
	    if(port==null){
	    	log.info("��ȡwebservice����ʱ�����η����쳣�˳���");
	    	return;
	    }
	    for (EquipmentProjectVo name : list){
	      String e = name.getV_real_equipment_name();
	      EquipmentData v = mapper.selectDataByName(e);
	      if(v==null){
	    	  log.info(name.getV_equipment_name()+"��ǰ�����ݡ�");
				continue;
		  }
	      v.setV_equipment_name(name.getV_equipment_name());
	      String dataStr = WebserviceUtil.getDataString(v);
	      log.info(dataStr);
	      try{
	        String result = port.saveYCJC(dataStr);
	        log.info("���������ؽ����" + result);
	      }catch (DataAccessException_Exception e1){
	        e1.printStackTrace();
	      }
	    }
	    log.info("�������ݷ�����ɣ�");
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
	
	public static void main(String[] args){
		Kf2Service kf = new Kf2Service();
		SaveYCJCServicePortType port = kf.getPortTimeOut(10);
	    if(port==null){
	    	log.info("��ȡwebservice����ʱ�����η����쳣�˳���");
	    }
	    
	    EquipmentData e = CommonUtil.getEquipmentDataInstance();
	    e.setV_equipment_name("621000235");
	    String dataStr = WebserviceUtil.getDataString(e);
		try{
	        String result = port.saveYCJC(dataStr);
	        log.info("���������ؽ����" + result);
	      }catch (DataAccessException_Exception e1){
	        e1.printStackTrace();
	      }
	}

}
