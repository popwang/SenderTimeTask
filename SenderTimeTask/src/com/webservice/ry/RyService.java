package com.webservice.ry;

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
import com.utils.CommonUtil;
import com.utils.SystemEnum;
import com.utils.ThreadPoolUtil;
import com.utils.WebserviceUtil;
import com.vo.EquipmentData;
import com.vo.EquipmentProjectVo;
import com.webservice.ry.ws.DataAccessException_Exception;
import com.webservice.ry.ws.SaveYCJCService;
import com.webservice.ry.ws.SaveYCJCServicePortType;
/**
 * 1.�����������ݶԽӳɹ���ʹ��webservice���ã�ÿ2���ӷ���һ�Ρ����밲�����أ�ƽ��ɽӦΪͬһ���ң�Э����ͬ��
 * 2.�Խ�ǰ��Ҫ��ϵͳ�ϵǼǣ���½������ӹ�����Ϣ������Ӽ�����Ϣ������Ӽ���豸��Ϣ��ע��ѡ����ȷ�Ĺ�����Ϣ��
 * http://117.158.183.213:8081/rysanitate/b2c4jadmin/login.jsp
 * http://117.158.183.213:8081/rysanitate/services/SaveYCJCService?wsdl
 * �û��� ��15000000000
 * ����     ��123456��
 * 3.Ŀǰ����һ̨�豸��313,911170381
 * 4.�����绰��15136482008��
 */
@Component("ryService")
public class RyService {
	public static Log log = LogFactory.getLog(RyService.class);
	@Autowired
	private CommonMapper mapper;
	
	public String hanlder() {
		List<EquipmentProjectVo> list = mapper.selectEquipmentListBySystemId(SystemEnum.LY_RY_SYSTEM.getId());
	    log.info("���ִ������豸��Ϊ��"+list.size());
	    SaveYCJCServicePortType port = this.getPortTimeOut(WebserviceUtil.WEBSERVICE_TIMEOUT);
	    if(port==null){
	    	log.info("��ȡwebservice����ʱ�����η����쳣�˳���");
	    	return "ERROR";
	    }
	    for (EquipmentProjectVo name : list){
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
	
	public static void main(String[] args){
		RyService ry = new RyService();
		SaveYCJCServicePortType port = ry.getPortTimeOut(10);
	    if(port==null){
	    	log.info("��ȡwebservice����ʱ�����η����쳣�˳���");
	    }
	    String dataStr = WebserviceUtil.getDataString(CommonUtil.getEquipmentDataInstance());
	    try {
			String result = port.saveYCJC(dataStr);
			System.out.println(result);
		} catch (DataAccessException_Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
