package com.tcp.xabq2;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mapper.CommonMapper;
import com.tcp.xabq.XabqService;
import com.tcp.xabq.XabqUtil;
import com.utils.CommonUtil;
import com.utils.ConfigReader;
import com.utils.SocketUtil;
import com.utils.SystemEnum;
import com.utils.TBJUtil;
import com.vo.EquipmentData;
import com.vo.EquipmentProjectVo;
/**
 * 西安灞桥区，下属平台；协议需要自己提供，暂定使用灞桥区协议
 * 电话微信：18161830955
 * 将编号发给厂家，厂家找平台进行登记，暂定仍然使用灞桥分配的设备规则
 * SDYKAZ00001000
 * 由于间隔调为80-100秒之间，即90秒，cron的定时设置无法完成，固使用schedule定时功能，放在main类中启动
 * @author pactera
 *
 */
@Component
public class Xabq2Service implements Runnable {
	public static Log log = LogFactory.getLog(Xabq2Service.class);
	@Autowired
	private CommonMapper mapper;
	@Override
	public void run() {
		List<EquipmentProjectVo> list = mapper.selectEquipmentListBySystemId(SystemEnum.XA_BQ2_SYSTEM.getId());
		log.info(SystemEnum.XA_BQ2_SYSTEM.getName()+"本轮待发送设备数为："+list.size());
		for(EquipmentProjectVo vo : list){
			EquipmentData e = mapper.selectDataByName(vo.getV_equipment_name().substring(6));
			if(e==null){
				log.info(vo.getV_equipment_name()+"当前无数据。");
				continue;
			}
			e.setV_equipment_name(vo.getV_equipment_name());
			String info = XabqUtil.getAirString(e);
			SocketUtil.init2(SystemEnum.XA_BQ2_SYSTEM.toString());
			SocketUtil.sendDataBySocket(SystemEnum.XA_BQ2_SYSTEM.toString(), 1,info, log);
		}
		log.info(SystemEnum.XA_BQ2_SYSTEM.getName()+"本轮待数据发送完成！");
	}
		
	public static void main(String[] args) {
		EquipmentData e = CommonUtil.getEquipmentDataInstance();
		e.setV_equipment_name("SDYKAZ00001000");
		String info = TBJUtil.getDataString2(e);
		log.info("发送内容:" + info);
		CommonUtil.sendDataToRemote(ConfigReader.getHost(SystemEnum.XA_BQ2_SYSTEM.toString()),
				ConfigReader.getPort(SystemEnum.XA_BQ2_SYSTEM.toString()),info,log);
	}
}
