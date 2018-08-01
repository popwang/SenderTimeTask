package com.http.zxline;

import java.io.IOException;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.common.service.AbstractBaseService;
import com.utils.CommonUtil;
import com.utils.SystemEnum;
import com.vo.EquipmentData;
import com.vo.EquipmentProjectVo;
/**
 * 郑许线（估计是许昌的平台）环保数据接口
 * 使用http post请求发送数据
 * 联系方式：微信群
 * @author pactera
 */
@Component
public class ZxlineService extends AbstractBaseService {
	
	@Override
	public void handler(SystemEnum systemEnum) {
		try {
			List<EquipmentProjectVo> list = mapper.selectEquipmentListBySystemId(systemEnum.getId());
			log.info(systemEnum.getName()+"本轮待发送设备数为：" + list.size());
			JSONArray array = new JSONArray();
			JSONObject object = null;
			for (EquipmentProjectVo project : list) {
				EquipmentData v = mapper.selectDataByName(project.getV_real_equipment_name());
				if(v==null){
					log.info(project.getV_equipment_name()+"当前无数据。");
					continue;
				}
				v.setV_equipment_name(project.getV_equipment_name());
				v.setV_project_name(project.getV_project_name());
				object = JsonUtil.toJsonObject(v);
				array.put(object);
			}
			String json = array.toString();
			log.info(json);
			CommonUtil.doHttpPost(list.get(0).getV_url(), json, log);
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
		JSONArray array = new JSONArray();
		JSONObject object = null;
		for(int i=0;i<5;i++){
			EquipmentData v = CommonUtil.getEquipmentDataInstance();
			v.setV_project_name("测试站点"+i);
			object = JsonUtil.toJsonObject(v);
			array.put(object);
		}
		System.out.println(array.toString());
		CommonUtil.doHttpPost("http://wechat-api.huaguisystems.com/homePage/environment/create", array.toString(), log);
	}
}
