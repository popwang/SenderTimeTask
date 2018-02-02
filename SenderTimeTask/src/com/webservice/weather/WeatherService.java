package com.webservice.weather;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.common.ServerInterface;
import com.mapper.CommonMapper;
import com.utils.SystemEnum;
import com.utils.WeatherUtil;
import com.vo.EquipmentData;
import com.vo.EquipmentProjectVo;
import com.vo.OrderBufferVo;

/**
 * ���ڴ˷�����Ϣ��ȫ�Ҳ��ȶ����˷����ѷ�������WeatherService2���
 * �����豸�ò�ѯ��γ��
 * ���ݾ�γ�ȶ�λ���ڳ���
 * ���ݳ��в�ѯ���������ƴ��100�����ڵ��ַ���
 * @author Administrator
 */
public class WeatherService implements ServerInterface {
	
	public static Log log = LogFactory.getLog(WeatherService.class);    //��־��¼
	@Autowired
	private CommonMapper mapper;
	
	@Override
	public void handler() {
		List<EquipmentProjectVo> list = mapper.selectEquipmentListBySystemId(SystemEnum.WEATHER_SYSTEM.getId());
		log.info(SystemEnum.WEATHER_SYSTEM.getName()+"���ִ������豸��Ϊ��"+list.size());
		StringBuffer sb = new StringBuffer();
		for(EquipmentProjectVo vo : list){
			String e = vo.getV_equipment_name();
			EquipmentData v = mapper.selectDataByName(e);
			if(v==null){
				log.info(vo.getV_equipment_name()+"��ǰ�����ݡ�");
				continue;
			}
			//��ȡ��������
			String cityName = WeatherUtil.getCityNameByLonla(v.getP014()+"", v.getP015()+"");
			//��ȡ����Ԥ������
			sb.append("SD,INFO,");
			sb.append(e);
			sb.append(",");
			String v_order_content = WeatherUtil.getWeatherStringByCityName(cityName,false);
			sb.append(v_order_content);
			sb.append("END");
			//��ȡ�����ݲ������ͱ����
			if(v_order_content!=null && !"".equals(v_order_content)){
				OrderBufferVo order = new OrderBufferVo();
				order.setI_send_flag(0);
				order.setV_equipment_name(e);
				order.setV_order_content(sb.toString());
				mapper.insertWeatherInfoIntoBuffer(order);
			}
			//������stringbuffer������һ��ʹ��
			sb.delete(0, sb.length());
		}
		log.info(SystemEnum.WEATHER_SYSTEM.getName()+"�������ݴ�����ɡ�");
	}
	
	public static void main(String[] args){
		String e = "00000400";
		StringBuffer sb = new StringBuffer();
		String cityName = WeatherUtil.getCityNameByLonla("34.725957","113.641365");
		//��ȡ����Ԥ������
		sb.append("SD,INFO,");
		sb.append(e);
		sb.append(",");
		String v_order_content = WeatherUtil.getWeatherStringByCityName(cityName,false);
		sb.append(v_order_content);
		sb.append("END");
		System.out.println(sb.toString());
	}
}
