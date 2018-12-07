package com.webservice.ayhx;

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
import com.webservice.ayhx.ws.DataAccessException_Exception;
import com.webservice.ayhx.ws.SaveYCJCService;
import com.webservice.ayhx.ws.SaveYCJCServicePortType;

/**
 * 1.安阳滑县数据对接，使用webservice调用；（与平顶山应为同一厂家，协议相同）
 * 2.技术电话：18625575498
 * 3.原技术黄工已经不干，暂时联系不到相关人员。
 * 4.已废弃
 */
@Component
public class AyhxService extends AbstractBaseService {
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

	/**
	 * 带超时获取webservice服务 second 秒
	 * @return
	 */
	public SaveYCJCServicePortType getPortTimeOut(int second) {
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

	@Override
	public void sendEquipmentData(EquipmentData v) {

	}

	public static void main(String[] args) {
		AyhxService gy = new AyhxService();
		SaveYCJCServicePortType port = gy.getPortTimeOut(10);
		if (port == null) {
			log.info("获取webservice服务超时，本次发送异常退出！");
		}
		EquipmentData v = CommonUtil.getEquipmentDataInstance();
		v.setV_equipment_name("811170056");
		String dataStr = WebserviceUtil.getDataString(v);
		log.info(dataStr);
		try {
			String result = port.saveYCJC(dataStr);
			log.info("服务器返回结果：" + result);
		} catch (DataAccessException_Exception e1) {
			e1.printStackTrace();
		}
	}
}
