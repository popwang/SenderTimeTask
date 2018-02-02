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
 * 1.平顶山宝丰县数据对接成功，使用webservice调用；（与安阳滑县应为同一厂家，协议相同）
 * 2.对接前需要项目部到有关部门进行项目登记，否则他们不予登记接收；
 * 3.目前接入一台设备：149；177没有登记接入不成功；
 * 4.技术电话：任工-17788188038
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
	    	log.info("获取webservice服务超时，本次发送异常退出！");
	    }else{
	    	log.info("获取成功！");
	    }
	}
	
	public String handler(){
	    List<EquipmentProjectVo> list = mapper.selectEquipmentListBySystemId(SystemEnum.PDS_SYSTEM.getId());
	    log.info("本轮待发送设备数为："+list.size());
	    SaveYCJCServicePortType port = this.getPortTimeOut(10);
	    if(port==null){
	    	log.info("获取webservice服务超时，本次发送异常退出！");
	    	return "ERROR";
	    }
	    for (EquipmentProjectVo name : list)
	    {
	      String e = "00" + name.getV_equipment_name().substring(3);
	      EquipmentData v = mapper.selectDataByName(e);
	      if(v==null){
	    	  log.info(name.getV_equipment_name()+"当前无数据。");
				continue;
		  }
	      v.setV_equipment_name(name.getV_equipment_name());
	      String dataStr = WebserviceUtil.getDataString(v);
	      log.info(dataStr);
	      try{
	        String result = port.saveYCJC(dataStr);
	        log.info("服务器返回结果：" + result);
	      }catch (DataAccessException_Exception e1){
	        e1.printStackTrace();
	      }
	    }
	    log.info("本轮数据发送完成！");
	    return "SUCCESS";
	}
	
	/**
	 * 带超时获取webservice服务
	 * second 秒
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
