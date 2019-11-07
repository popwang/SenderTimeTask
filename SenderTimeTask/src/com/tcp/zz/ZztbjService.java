package com.tcp.zz;

import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;

import org.springframework.stereotype.Component;

import com.common.service.AbstractBaseService;
import com.utils.CommonUtil;
import com.utils.SocketUtil;
import com.utils.SystemEnum;
import com.utils.TBJUtil;
import com.vo.EquipmentData;
import com.vo.EquipmentProjectVo;

/**
 * 1.郑州特必佳公司接口；目前接入5台设备； 
 * 2.设备列表直接添加即可； 
 * 3.技术电话：宋-177 3718 3234
 * 4.20180808,修改数据查询方式，一次性关联查询全部数据；增加线程池并发发送数据；
 * 5.限流思维，将每10分钟集中发送，改为一分钟发一部分，每次只发送设备尾号与当前分钟相同的设备数据。
 * @author Administrator
 */
@Component
public class ZztbjService extends AbstractBaseService {
	
	public static void main(String[] args){
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				EquipmentData e = CommonUtil.getEquipmentDataInstance();
				e.setV_equipment_name("AZ00005348");
				String info = TBJUtil.getDataString(e);
				SocketUtil.init(SystemEnum.ZZ_TBJ_SYSTEM.toString(), "123.15.58.210", 9123);
				SocketUtil.sendDataBySocket(SystemEnum.ZZ_TBJ_SYSTEM.toString(), 1,info, log);
			}
		}, 1000, 2*60*1000);
//		String no = "AZ01000947";
//		System.out.println(no.substring(no.length()-1));
	}

	@Override
	public void handler(SystemEnum systemEnum) {
		List<EquipmentProjectVo> list = mapper.selectEquipmentListBySystemId(systemEnum.getId());
		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));
		int minute = calendar.get(Calendar.MINUTE)%10;
		for (EquipmentProjectVo vo : list) {
			if(Integer.parseInt(vo.getV_equipment_name().substring(vo.getV_equipment_name().length()-1))==minute) {
				EquipmentData v = mapper.selectDataByName(vo.getV_real_equipment_name());
				if(v==null){
					log.info(vo.getV_equipment_name()+"当前无数据。");
					continue;
				}
				v.setV_equipment_name(vo.getV_equipment_name());
				log.info("当前发送设备号："+v.getV_equipment_name());
				String info = TBJUtil.getDataString(v);
//				SocketUtil.init2(SystemEnum.ZZ_TBJ_SYSTEM.toString());
//				SocketUtil.sendDataBySocket(SystemEnum.ZZ_TBJ_SYSTEM.toString(), 1,info, log);
				CommonUtil.sendDataToRemote2(SystemEnum.ZZ_TBJ_SYSTEM.toString(), info, log);
			}
		}
		log.info(systemEnum.getName()+"本轮发送完成。");
	}
	
	@Override
	public void sendEquipmentData(EquipmentData v) {
		
	}
}

