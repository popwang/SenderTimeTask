package com.webservice.xxxq;

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
import com.webservice.xxxq.ws.YCJKService;
import com.webservice.xxxq.ws.YCJKServiceSoap;
/**
 * 陕西西咸新区对接接口，微信联系人
 * @author 27438
 *
 */
@Component
public class XxxqService extends AbstractBaseService {
	/**
	 * 重写父类handler方法
	 */
	@Override
	public void handler(SystemEnum systemEnum) {
		List<EquipmentProjectVo> list = mapper.selectEquipmentListBySystemId(systemEnum.getId());
		log.info(systemEnum.getName() + "本轮待发送设备数为：" + list.size());
		YCJKServiceSoap port = this.getPortTimeOut(WebserviceUtil.WEBSERVICE_TIMEOUT);
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
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		log.info(systemEnum.getName() + "本轮数据发送完成！");
	}

	/**
	 * 带超时获取webservice服务 second 秒
	 * @return
	 */
	public YCJKServiceSoap getPortTimeOut(int second) {
		ExecutorService exec = ThreadPoolUtil.getExecutorService();
		Callable<YCJKServiceSoap> call = new Callable<YCJKServiceSoap>() {
			public YCJKServiceSoap call() throws Exception {
				YCJKService ss = new YCJKService();
				YCJKServiceSoap port = ss.getYCJKServiceSoap();
				return port;
			}
		};
		Future<YCJKServiceSoap> future = exec.submit(call);
		YCJKServiceSoap port = null;
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
		XxxqService gy = new XxxqService();
		YCJKServiceSoap port = gy.getPortTimeOut(10);
		if (port == null) {
			log.info("获取webservice服务超时，本次发送异常退出！");
		}
		EquipmentData v = CommonUtil.getEquipmentDataInstance();
		v.setV_equipment_name("621001031");
		String dataStr = WebserviceUtil.getDataString(v);
		log.info(dataStr);
		try {
			String result = port.saveYCJC(dataStr);
			log.info("服务器返回结果：" + result);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
}
