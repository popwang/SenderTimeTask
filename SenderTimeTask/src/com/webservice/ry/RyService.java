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
 * 1.洛阳汝阳数据对接成功，使用webservice调用；每2分钟发送一次。（与安阳滑县，平顶山应为同一厂家，协议相同）
 * 2.对接前需要在系统上登记：登陆后先添加工地信息，再添加监测点信息，再添加监测设备信息，注意选择正确的工地信息。
 * http://117.158.183.213:8081/rysanitate/b2c4jadmin/login.jsp
 * http://117.158.183.213:8081/rysanitate/services/SaveYCJCService?wsdl
 * 用户名 ：15000000000
 * 密码     ：123456；
 * 3.目前接入一台设备：313,911170381
 * 4.技术电话：15136482008。
 */
@Component("ryService")
public class RyService {
	public static Log log = LogFactory.getLog(RyService.class);
	@Autowired
	private CommonMapper mapper;
	
	public String hanlder() {
		List<EquipmentProjectVo> list = mapper.selectEquipmentListBySystemId(SystemEnum.LY_RY_SYSTEM.getId());
	    log.info("本轮待发送设备数为："+list.size());
	    SaveYCJCServicePortType port = this.getPortTimeOut(WebserviceUtil.WEBSERVICE_TIMEOUT);
	    if(port==null){
	    	log.info("获取webservice服务超时，本次发送异常退出！");
	    	return "ERROR";
	    }
	    for (EquipmentProjectVo name : list){
	      String e = "0000" + name.getV_equipment_name().substring(5);
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
	    	log.info("获取webservice服务超时，本次发送异常退出！");
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
