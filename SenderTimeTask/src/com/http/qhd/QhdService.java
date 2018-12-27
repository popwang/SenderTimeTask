package com.http.qhd;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.common.service.AbstractBaseService;
import com.utils.CommonUtil;
import com.utils.SystemEnum;
import com.vo.EquipmentData;
import com.vo.EquipmentProjectVo;
/**
 * 秦皇岛接口对接，协议与重庆类似
 * @author pactera
 */
@Component
public class QhdService extends AbstractBaseService {
	
	@Override
	public void handler(SystemEnum systemEnum) {
		try {
			List<EquipmentProjectVo> list = mapper.selectEquipmentListBySystemId(systemEnum.getId());
			log.info(systemEnum.getName()+"本轮待发送设备数为：" + list.size());
			for (EquipmentProjectVo project : list) {
				EquipmentData v = mapper.selectDataByName(project.getV_real_equipment_name());
				if(v==null){
					log.info(project.getV_equipment_name()+"当前无数据。");
					continue;
				}
				v.setV_equipment_name(project.getV_equipment_name());
				String info = "["+QhdUtil.toJsonObject(v).toString()+"]";
				log.info(info);
				Map<String, String> map =  QhdUtil.getHeaderMap();
				CommonUtil.doHttpPost2(project.getV_url(), info, map, log);
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void sendEquipmentData(EquipmentData v) {
		
	}

	public static void main(String[] args) throws JSONException, ClientProtocolException, IOException {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				try {
					JSONObject object = null;
					EquipmentData v = CommonUtil.getEquipmentDataInstance();
					v.setV_equipment_name("AZ00000938");
					object = QhdUtil.toJsonObject(v);
					System.out.println(object);
					CommonUtil.doHttpPost2("http://ljxq-api.z023.cn/api/open/v1/env/runtime", "["+object.toString()+"]", QhdUtil.getHeaderMap(),log);
				} catch (ClientProtocolException e) {
					e.printStackTrace();
				} catch (JSONException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}, 0, 4*60*1000);
	}
}
