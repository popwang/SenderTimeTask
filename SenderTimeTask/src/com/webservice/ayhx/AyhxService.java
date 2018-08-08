package com.webservice.ayhx;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mapper.CommonMapper;
import com.utils.SystemEnum;
import com.utils.ThreadPoolUtil;
import com.utils.WebserviceUtil;
import com.vo.EquipmentData;
import com.vo.EquipmentProjectVo;
import com.webservice.ayhx.ws.DataAccessException_Exception;
import com.webservice.ayhx.ws.SaveYCJCService;
import com.webservice.ayhx.ws.SaveYCJCServicePortType;

/**
 * 1.�����������ݶԽӣ�ʹ��webservice���ã�����ƽ��ɽӦΪͬһ���ң�Э����ͬ��
 * 2.�����绰��18625575498
 * 3.ԭ�����ƹ��Ѿ����ɣ���ʱ��ϵ���������Ա��
 * 4.�ѷ���
 */
@Component
public class AyhxService {
	public static Log log = LogFactory.getLog(AyhxService.class);
	@Autowired
	private CommonMapper mapper;

	public String handler() {
		List<EquipmentProjectVo> list = mapper.selectEquipmentListBySystemId(SystemEnum.AY_HX_SYSTEM.getId());
		log.info("���ִ������豸��Ϊ��"+list.size());
		SaveYCJCServicePortType port = this.getPortTimeOut(WebserviceUtil.WEBSERVICE_TIMEOUT);
	    if(port==null){
	    	log.info("��ȡwebservice����ʱ�����η����쳣�˳���");
	    	return "ERROR";
	    }
		for (EquipmentProjectVo name : list) {
			String e = "0000" + name.getV_equipment_name().substring(5);
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
			} catch (DataAccessException_Exception e1) {
				e1.printStackTrace();
			}
		}
		log.info("�������ݷ�����ɣ�");
		return "SUCCESS";
	}
	
	public SaveYCJCServicePortType getPortTimeOu2(int second){
		Callable<SaveYCJCServicePortType> call = new Callable<SaveYCJCServicePortType>() {  
	        public SaveYCJCServicePortType call() throws Exception {
	        	SaveYCJCService ss = new SaveYCJCService();
	    	    SaveYCJCServicePortType port = ss.getSaveYCJCServiceHttpPort();
	            return port;
	        }
	    };
	    FutureTask<SaveYCJCServicePortType> funtrueTask = new FutureTask<SaveYCJCServicePortType>(call);
	    Thread thread = new Thread(funtrueTask);
	    thread.start();
	    SaveYCJCServicePortType port = null;
	    while(funtrueTask.isDone()){
	    	try {
				port = (SaveYCJCServicePortType)funtrueTask.get();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
	    }
		return port;
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
		} finally {
			exec.shutdown();
		}
		return port;
	}
}
