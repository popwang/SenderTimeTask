package com.webservice.lyln;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.common.service.AbstractBaseService;
import com.utils.CommonUtil;
import com.utils.SystemEnum;
import com.utils.ThreadPoolUtil;
import com.utils.WebserviceUtil;
import com.vo.EquipmentData;
import com.vo.EquipmentProjectVo;
import com.webservice.ayhx.AyhxService;
import com.webservice.ayhx.ws.DataAccessException_Exception;
import com.webservice.ayhx.ws.SaveYCJCService;
import com.webservice.ayhx.ws.SaveYCJCServicePortType;
import com.webservice.lyln.ws.DustSurveillance;
import com.webservice.lyln.ws.DustSurveillanceSoap;

/**
 * 洛阳洛宁平台
 * @author 27438
 *
 */
@Component
public class LylnService extends AbstractBaseService {

	/**
	 * 重写父类handler方法
	 */
	@Override
	public void handler(SystemEnum systemEnum) {
		List<EquipmentProjectVo> list = mapper.selectEquipmentListBySystemId(systemEnum.getId());
		log.info(systemEnum.getName() + "本轮待发送设备数为：" + list.size());
		DustSurveillanceSoap port = this.getPortTimeOut(WebserviceUtil.WEBSERVICE_TIMEOUT);
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
				String result = port.uploadDustInfo("04391a005ac27298bbe09a0d83f0769a:1473431447", "qsmp", dataStr);
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
	public DustSurveillanceSoap getPortTimeOut(int second) {
		ExecutorService exec = ThreadPoolUtil.getExecutorService();
		Callable<DustSurveillanceSoap> call = new Callable<DustSurveillanceSoap>() {
			public DustSurveillanceSoap call() throws Exception {
				DustSurveillance ss = new DustSurveillance();
				DustSurveillanceSoap port = ss.getDustSurveillanceSoap();
				return port;
			}
		};
		Future<DustSurveillanceSoap> future = exec.submit(call);
		DustSurveillanceSoap port = null;
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

	public static void main(String[] args) throws JSONException {
		LylnService gy = new LylnService();
		DustSurveillanceSoap port = gy.getPortTimeOut(10);
		if (port == null) {
			log.info("获取webservice服务超时，本次发送异常退出！");
		}
		EquipmentData v = CommonUtil.getEquipmentDataInstance();
		v.setV_equipment_name("16051171");
		JSONObject object = WebserviceUtil.getContentObject(v);
		String content = object.toString();
		System.out.println(content);
		try {
			
			String str = WebserviceUtil.getDataString3(object);
			System.out.println(str);
			String sign = WebserviceUtil.getSignMD5(str);
			String result = port.uploadDustInfo(sign, WebserviceUtil.CODE, str);
			log.info("服务器返回结果：" + result);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

}
