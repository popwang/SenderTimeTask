package com.http.weather;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.ServerInterface;
import com.mapper.CommonMapper;
import com.utils.SystemEnum;
import com.utils.WeatherUtil;
import com.vo.EquipmentData;
import com.vo.EquipmentProjectVo;
import com.vo.OrderBufferVo;

/**
 * 阿里云天气服务
 * @author Administrator
 */
@Component
public class WeatherService2 implements ServerInterface {
	public static Log log = LogFactory.getLog(WeatherService2.class);    //日志记录
	@Autowired
	private CommonMapper mapper;
	@Override
	public void handler() {
		List<EquipmentProjectVo> list = mapper.selectEquipmentListBySystemId(SystemEnum.WEATHER_SYSTEM.getId());
		log.info(SystemEnum.WEATHER_SYSTEM.getName()+"本轮待处理设备数为："+list.size());
		StringBuffer sb = new StringBuffer();
		for(EquipmentProjectVo vo : list){
			String e = vo.getV_equipment_name();
			EquipmentData v = mapper.selectDataByName(e);
			if(v==null){
				log.info(vo.getV_equipment_name()+"当前无数据。");
				continue;
			}
			//获取天气预报内容
			sb.append("SD,INFO,");
			sb.append(e);
			sb.append(",");
			String v_order_content = WeatherUtil.appendWeatherString(WeatherUtil.getWeatherStringByHttp(v.getP014()+"", v.getP015()+""));
			sb.append(v_order_content);
			sb.append(",");
			sb.append("END");
			//获取到内容才往发送表插入
			if(v_order_content!=null && !"".equals(v_order_content)){
				OrderBufferVo order = new OrderBufferVo();
				order.setI_send_flag(0);
				order.setV_equipment_name(e);
				order.setV_order_content(sb.toString());
				mapper.insertWeatherInfoIntoBuffer(order);
			}
			//最后清空stringbuffer便于下一次使用
			sb.delete(0, sb.length());
		}
		log.info(SystemEnum.WEATHER_SYSTEM.getName()+"本轮数据处理完成。");
	}
	
	
	public static void main(String[] args) {

	}

}
