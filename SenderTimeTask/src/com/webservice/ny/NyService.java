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
 * 南阳接口对接
 * 9位编码，前3位由平台提供，后6位随机码保持不重复即可
 * 技术联系人：13903776396，需要资质证书，尚未分配厂家编码
 * 对接设备：374
 * @author pactera
 *
 */
@Component
public class NyService extends AbstractBaseService {
	
	/**
	 * 重写父类handler方法
	 */
	@Override
	public void handler(SystemEnum systemEnum) {
		List<EquipmentProjectVo> list = mapper.selectEquipmentListBySystemId(systemEnum.getId());
		log.info(systemEnum.getName() + "本轮待发送设备数为：" + list.size());
		SaveYCJCServicePortType port = this.getPortTimeOut(WebserviceUtil.WEBSERVICE_TIMEOUT);
		if (port == null) {
			log.info("获取webservice服务超时，本次发送异常退出！");
			return;
		}
		for (EquipmentProjectVo name : list) {
			String e = name.getV_real_equipment_name();
			EquipmentData v = mapper.selectDataByName(e);
			if (v == null) {
				log.info(name.getV_equipment_name() + "当前无数据。");
				continue;
			}
			v.setV_equipment_name(name.getV_equipment_name());
			String dataStr = WebserviceUtil.getDataString(v);
			log.info(dataStr);
			try {
				String result = port.saveYCJC(dataStr);
				log.info("服务器返回结果：" + result);
			} catch (DataAccessException_Exception e1) {
				e1.printStackTrace();
			}
		}
		log.info(systemEnum.getName() + "本轮数据发送完成！");
	}
	
	@Override
	public void sendEquipmentData(EquipmentData v) {
		
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
		} 
		return port;
	}
	
	public static void main(String[] args) {
		NyService ry = new NyService();
		SaveYCJCServicePortType port = ry.getPortTimeOut(3);
	    if(port==null){
	    	log.info("获取webservice服务超时，本次发送异常退出！");
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
