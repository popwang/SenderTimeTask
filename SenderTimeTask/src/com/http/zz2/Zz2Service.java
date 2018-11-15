package com.http.zz2;


import org.springframework.stereotype.Component;

import com.common.service.AbstractBaseService;
import com.utils.CRC;
import com.utils.CommonUtil;
import com.vo.EquipmentData;
/**
 * 中邦介绍的平台，联系人微信：中邦郑州平台2联系人
 * 有协议，但是错误百出，以最终程序代码为准。
 * @author pactera
 *
 */
@Component
public class Zz2Service extends AbstractBaseService {

	@Override
	public void sendEquipmentData(EquipmentData v) {
		String info = getInfoString(v);
		log.info("发送内容："+info);
		CommonUtil.doHttpGet(info,log);
	}
	
	public static void main(String[] args) {
		Zz2Service zz  = new Zz2Service();
		EquipmentData v = CommonUtil.getEquipmentDataInstance();
		v.setV_url("http://www.d2p9.com:9993/thirdparty/dust");
		v.setV_equipment_name("ZB00005069");
		zz.sendEquipmentData(v);
	}
	
	private String getInfoString(EquipmentData v){
		StringBuilder sb = new StringBuilder();
		sb.append(v.getV_url());
		sb.append("?ProjectToken=2advgbhg6j552kjl");
		sb.append("&Deviceid=");
		sb.append(v.getV_equipment_name());
		sb.append("&Pm25=");
		sb.append(v.getP002());
		sb.append("&Pm10=");
		sb.append(v.getP003());
		sb.append("&Noise=");
		sb.append(v.getP008());
		sb.append("&Temperature=");
		sb.append(v.getP006());
		sb.append("&Humidity=");
		sb.append(v.getP007());
		sb.append("&Windspeed=");
		sb.append(v.getP004());
		sb.append("&Winddirection=");
		sb.append(CommonUtil.getWindString(v.getP005()));
		sb.append("&Time=");
		sb.append(CRC.currentTimeString("yyyy-MM-dd%HH:mm:ss"));
		return sb.toString();
	}
}
