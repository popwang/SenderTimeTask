package com.http.sdrz;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.ServerInterface;
import com.mapper.CommonMapper;
import com.utils.CommonUtil;
import com.utils.SystemEnum;
import com.vo.EquipmentData;
import com.vo.EquipmentProjectVo;
/**
 * 1.山东日照设备数据上传服务类；
 * 2.数据上传使用http服务，每2分钟发送一次；
 * 3.后续增加设备，直接添加即可。
 * 4.http://222.133.182.247:56/Home/Index/index
 * ?pm25=100.0&pm10=120.0&tsp=0.0&tmp=27.9&rh=58.4&wd=5.0&ws=0.3&wp=0.0&pa=0.0&zs=47.6
 * &devid=00000114
 */
@Component("sdrzServer")
public class SdrzServer implements ServerInterface {
	
	public static Log log = LogFactory.getLog(SdrzServer.class);    //日志记录
	
	@Autowired
	private CommonMapper mapper;
	
	@Override
	public void handler() {
		List<EquipmentProjectVo> list = mapper.selectEquipmentListBySystemId(SystemEnum.SD_RZ_SYSTEM.getId());
		log.info("本轮待发送设备数为："+list.size());
		for(EquipmentProjectVo vo : list){
			String e = vo.getV_equipment_name();
			EquipmentData v = mapper.selectDataByName(e.substring(2));
			if(v==null){
				log.info(e+"当前无数据。");
				continue;
			}
			StringBuffer sb = new StringBuffer(vo.getV_url());
			sb.append("?pm25=");
			sb.append(v.getP002());
			sb.append("&pm10=");
			sb.append(v.getP003());
			sb.append("&tsp=");
			sb.append(v.getP009());
			sb.append("&tmp=");
			sb.append(v.getP006());
			sb.append("&rh=");
			sb.append(v.getP007());
			sb.append("&wd=");
			sb.append(v.getP005());
			sb.append("&ws=");
			sb.append(v.getP004());
			sb.append("&wp=");//风力
			sb.append(v.getP011());
			sb.append("&pa=");
			sb.append(v.getP010());
			sb.append("&zs=");
			sb.append(v.getP008());
			sb.append("&devid=");
			sb.append(e);
			log.info(sb.toString());
			CommonUtil.doHttpGet(sb.toString(),log);
		}
	}
}
