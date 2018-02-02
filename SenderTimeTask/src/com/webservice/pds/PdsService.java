package com.webservice.pds;

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

import com.mapper.CommonMapper;
import com.utils.SystemEnum;
import com.utils.WebserviceUtil;
import com.vo.EquipmentData;
import com.vo.EquipmentProjectVo;
import com.webservice.pds.ws.DataAccessException_Exception;
import com.webservice.pds.ws.SaveYCJCService;
import com.webservice.pds.ws.SaveYCJCServicePortType;

/**
 * 1.ƽ��ɽ���������ݶԽӳɹ���ʹ��webservice���ã����밲������ӦΪͬһ���ң�Э����ͬ��
 * 2.�Խ�ǰ��Ҫ��Ŀ�����йز��Ž�����Ŀ�Ǽǣ��������ǲ���Ǽǽ��գ�
 * 3.Ŀǰ����һ̨�豸��149��177û�еǼǽ��벻�ɹ���
 * 4.�����绰���ι�-17788188038
 * @author Administrator
 */
@Component
public class PdsService {
	public static Log log = LogFactory.getLog(PdsService.class);
	@Autowired
	private CommonMapper mapper;
	
	public static void main(String[] args){
		PdsService ps = new PdsService();
		SaveYCJCServicePortType port = ps.getPortTimeOut(10);
	    if(port==null){
	    	log.info("��ȡwebservice����ʱ�����η����쳣�˳���");
	    }else{
	    	log.info("��ȡ�ɹ���");
	    }
	}
	
	public String handler(){
	    List<EquipmentProjectVo> list = mapper.selectEquipmentListBySystemId(SystemEnum.PDS_SYSTEM.getId());
	    log.info("���ִ������豸��Ϊ��"+list.size());
	    SaveYCJCServicePortType port = this.getPortTimeOut(10);
	    if(port==null){
	    	log.info("��ȡwebservice����ʱ�����η����쳣�˳���");
	    	return "ERROR";
	    }
	    for (EquipmentProjectVo name : list)
	    {
	      String e = "00" + name.getV_equipment_name().substring(3);
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
	    return "SUCCESS";
	}
	
	/**
	 * ����ʱ��ȡwebservice����
	 * second ��
	 * @return
	 */
	public SaveYCJCServicePortType getPortTimeOut(int second){
		final ExecutorService exec = Executors.newFixedThreadPool(1);  
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
		} finally {
			exec.shutdown();
		}
		return port;
	}
}
