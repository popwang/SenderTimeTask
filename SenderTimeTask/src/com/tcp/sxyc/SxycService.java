package com.tcp.sxyc;

import java.util.Timer;
import java.util.TimerTask;

import org.springframework.stereotype.Component;

import com.common.service.AbstractBaseService;
import com.utils.CommonUtil;
import com.utils.ConfigReader;
import com.utils.SocketUtil;
import com.utils.SystemEnum;
import com.vo.EquipmentData;
/**
 * 山西运城数据对接服务，对接成功，尚未发送数据
 * 重写数据发送接口即可
 * @author pactera
 */
@Component
public class SxycService extends AbstractBaseService {

	@Override
	public void sendEquipmentData(EquipmentData v) {
		YcUtil yc = new YcUtil(v);
		SocketUtil.init(SystemEnum.SX_YC_SYSTEM.toString(), ConfigReader.getHost(SystemEnum.SX_YC_SYSTEM.toString()),
				ConfigReader.getPort(SystemEnum.SX_YC_SYSTEM.toString()));
		
		SocketUtil.sendByteDataBySocket(SystemEnum.SX_YC_SYSTEM.toString(), 10, yc.getDataInfo(), log);
	}
	
	public static void main(String[] args) {
	
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				EquipmentData e = CommonUtil.getEquipmentDataInstance();
				e.setV_equipment_name("0E5A424C00005138");
				YcUtil yc = new YcUtil(e);
				e.setP001(0);
				e.setP002(68);
				e.setP003(85);
				e.setP004(0.5);
				e.setP005(3);
				e.setP006(32.0);
				e.setP007(10.3);
				e.setP008(52.1);
				e.setP009(0);
				e.setP010(0);
				
				SocketUtil.init(SystemEnum.SX_YC_SYSTEM.toString(), ConfigReader.getHost(SystemEnum.SX_YC_SYSTEM.toString()),
						ConfigReader.getPort(SystemEnum.SX_YC_SYSTEM.toString()));
				
				SocketUtil.sendByteDataBySocket(SystemEnum.SX_YC_SYSTEM.toString(), 10, yc.getDataInfo(), log);
			}
		}, 1000, 1*30*1000);
		
	}
}
