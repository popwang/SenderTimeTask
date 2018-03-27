package com.tcp.xabq;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.ServerInterface;
import com.mapper.CommonMapper;
import com.utils.CommonUtil;
import com.utils.ConfigReader;
import com.utils.SocketUtil;
import com.utils.SystemEnum;
import com.vo.EquipmentData;
import com.vo.EquipmentProjectVo;
/**
 * 西安灞桥区，来工：QQ:380787270
 * 发送自定义编号，后台添加
 * @author pactera
 *
 */
@Component
public class XabqService implements ServerInterface {

	public static Log log = LogFactory.getLog(XabqService.class);
	@Autowired
	private CommonMapper mapper;
	@Override
	public void handler() {
		List<EquipmentProjectVo> list = mapper.selectEquipmentListBySystemId(SystemEnum.XA_BQ_SYSTEM.getId());
		log.info("本轮待发送设备数为："+list.size());
		for(EquipmentProjectVo vo : list){
			EquipmentData e = mapper.selectDataByName(vo.getV_equipment_name().substring(6));
			if(e==null){
				log.info(vo.getV_equipment_name()+"当前无数据。");
				continue;
			}
			e.setV_equipment_name(vo.getV_equipment_name());
			String info = XabqUtil.getAirString(e);
//			log.info("发送内容:" + info);
//			CommonUtil.sendDataToRemote(ConfigReader.getXaBaQiaoIP(),
//					ConfigReader.getXaBaQiaoPORT(),info,log);
			SocketUtil.init(SystemEnum.XA_BQ_SYSTEM.toString(), ConfigReader.getXaBaQiaoIP(),
			ConfigReader.getXaBaQiaoPORT());
			SocketUtil.sendDataBySocket(SystemEnum.XA_BQ_SYSTEM.toString(), 1,info, log);
		}
		log.info("本轮待数据发送完成！");
	}
		
	public static void main(String[] args) {
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				EquipmentData e = new EquipmentData();
				e.setV_equipment_name("SDYKAZ00000087");
				e.setP001(0);
				e.setP002(68);
				e.setP003(85);
				e.setP004(0.5);
				e.setP005(3);
				e.setP006(32.0);
				e.setP007(45.3);
				e.setP008(52.1);
				e.setP009(0);
				e.setP010(0);
				String info = XabqUtil.getAirString(e);
				log.info("发送内容:" + info);
				CommonUtil.sendDataToRemote(ConfigReader.getXaBaQiaoIP(),
						ConfigReader.getXaBaQiaoPORT(),info,log);
				
//				SocketUtil.init(SystemEnum.XA_BQ_SYSTEM.toString(), ConfigReader.getXaBaQiaoIP(),
//						ConfigReader.getXaBaQiaoPORT());
//				SocketUtil.sendDataBySocket(SystemEnum.XA_BQ_SYSTEM.toString(), 4,info, log);
			}
		}, 1000, 1*30*1000);
	}

}
