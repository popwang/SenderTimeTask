package com.webservice.yc;

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
import com.webservice.yc.ws.JKInfo;
import com.webservice.yc.ws.JKInfoSoap;
import com.webservice.yc.ws.JKInfoSoap_JKInfoSoap_Client;

@Component
public class YcService {
	public static Log log = LogFactory.getLog(YcService.class);
	private static String mKeyPwd = "3C083F98B4539DF73E01EF367533E597";
	@Autowired
	private CommonMapper mapper;
	
	public String hanlder() {
		List<EquipmentProjectVo> list = mapper.selectEquipmentListBySystemId(SystemEnum.NX_YC_SYSTEM.getId());
	    log.info(SystemEnum.NX_YC_SYSTEM.getName()+"本轮待发送设备数为："+list.size());
	    JKInfoSoap port = this.getPortTimeOut(WebserviceUtil.WEBSERVICE_TIMEOUT);
	    if(port==null){
	    	log.info("获取webservice服务超时，本次发送异常退出！");
	    	return "ERROR";
	    }
	    for (EquipmentProjectVo project : list){
	      String e =  project.getV_real_equipment_name();
	      EquipmentData v = mapper.selectDataByName(e);
	      if(v==null){
	    	  	log.info(project.getV_equipment_name()+"当前无数据。");
				continue;
		  }
	      v.setV_equipment_name(project.getV_equipment_name());

	      String dataStr = YcUtil.switchEquipmentToData(v);
	      log.info(dataStr);
	      try{
	        String result = port.xhtYCDateRec(mKeyPwd, dataStr);
	        log.info("服务器返回结果：" + result);
	      }catch (Exception e1){
	        e1.printStackTrace();
	      }
	    }
	    log.info(SystemEnum.NX_YC_SYSTEM.getName()+"本轮数据发送完成！");
	    return "SUCCESS";
	}
	
	/**
	 * 带超时获取webservice服务
	 * second 秒
	 * @return
	 */
	public JKInfoSoap getPortTimeOut(int second){
		ExecutorService exec = ThreadPoolUtil.getExecutorService();
	    Callable<JKInfoSoap> call = new Callable<JKInfoSoap>() {  
	        public JKInfoSoap call() throws Exception {
	        	JKInfo ss = new JKInfo(JKInfo.WSDL_LOCATION, JKInfoSoap_JKInfoSoap_Client.SERVICE_NAME);
	            JKInfoSoap port = ss.getJKInfoSoap();
	            return port;
	        }
	    };
	    Future<JKInfoSoap> future = exec.submit(call);  
	    JKInfoSoap port = null;
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
		YcService ry = new YcService();
		JKInfoSoap port = ry.getPortTimeOut(10);
	    if(port==null){
	    	log.info("获取webservice服务超时，本次发送异常退出！");
	    }
	    EquipmentData vo = CommonUtil.getEquipmentDataInstance();
	    vo.setV_equipment_name("ZB00000955");
	    String dataStr = YcUtil.switchEquipmentToData(vo);
	    try {
	    	String result = port.xhtYCDateRec(mKeyPwd, dataStr);
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
