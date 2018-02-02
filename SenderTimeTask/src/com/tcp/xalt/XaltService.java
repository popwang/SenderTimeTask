package com.tcp.xalt;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.ServerInterface;
import com.mapper.CommonMapper;
import com.tcp.xadx.DxUtil;
import com.utils.ConfigReader;
import com.utils.SocketUtil;
import com.utils.SystemEnum;
import com.vo.EquipmentData;
import com.vo.EquipmentProjectVo;
/**
 * 西安临潼区对接接口
 * 与长安区联系人一样
 * 协议与大兴区一样
 * 秦御佳苑II期（二）标段的MN号AZ000217121101,448,18484756014
 * @author pactera
 *
 */
@Component
public class XaltService implements ServerInterface {
	
	public static Log log = LogFactory.getLog(XaltService.class);
	@Autowired
	private CommonMapper mapper;
	@Override
	public void handler() {
		List<EquipmentProjectVo> list = mapper.selectEquipmentListBySystemId(SystemEnum.XA_LT_SYSTEM.getId());
		log.info(SystemEnum.XA_LT_SYSTEM.getName()+"本轮待发送设备数为：" + list.size());
		for (EquipmentProjectVo vo : list) {
			EquipmentData v = mapper.selectDataByName(vo.getV_equipment_name().substring(2));
			if(v==null){
				log.info(vo.getV_equipment_name()+"当前无数据。");
				continue;
			}
			v.setV_equipment_name(vo.getV_url());
			DxUtil dx = new DxUtil(v);
			SocketUtil.init(SystemEnum.XA_LT_SYSTEM.toString(), ConfigReader.getXaLintongIP(),
					ConfigReader.getXaLintongPORT());
			SocketUtil.sendByteDataBySocket(SystemEnum.XA_LT_SYSTEM.toString(), 1,dx.getSequenceBytes(), log);
			SocketUtil.sendByteDataBySocket(SystemEnum.XA_LT_SYSTEM.toString(), 1,dx.getNoiseBytes(), log);
			SocketUtil.sendByteDataBySocket(SystemEnum.XA_LT_SYSTEM.toString(), 1,dx.getPM25Bytes(), log);
			SocketUtil.sendByteDataBySocket(SystemEnum.XA_LT_SYSTEM.toString(), 1,dx.getPM10Bytes(), log);
			SocketUtil.sendByteDataBySocket(SystemEnum.XA_LT_SYSTEM.toString(), 1,dx.getGPSBytes(), log);
			SocketUtil.sendByteDataBySocket(SystemEnum.XA_LT_SYSTEM.toString(), 1,dx.getDropRateBytes(), log);
		}
	}
	
	public static void main(String[] args){
		SocketUtil.init(SystemEnum.XA_LT_SYSTEM.toString(), "localhost",
				9999);
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				EquipmentData e = new EquipmentData();
				e.setV_equipment_name("18484756014");
				e.setP001(0);
				e.setP002(150);
				e.setP003(50);
				e.setP004(0.5);
				e.setP005(3);
				e.setP006(39.0);
				e.setP007(50.3);
				e.setP008(33.1);
				e.setP009(0);
				e.setP010(0);
				DxUtil dx = new DxUtil(e);
				
				SocketUtil.sendByteDataBySocket(SystemEnum.XA_LT_SYSTEM.toString(), 3,dx.getSequenceBytes(), log);
				SocketUtil.sendByteDataBySocket(SystemEnum.XA_LT_SYSTEM.toString(), 3,dx.getNoiseBytes(), log);
				SocketUtil.sendByteDataBySocket(SystemEnum.XA_LT_SYSTEM.toString(), 3,dx.getPM25Bytes(), log);
				SocketUtil.sendByteDataBySocket(SystemEnum.XA_LT_SYSTEM.toString(), 3,dx.getPM10Bytes(), log);
				SocketUtil.sendByteDataBySocket(SystemEnum.XA_LT_SYSTEM.toString(), 3,dx.getGPSBytes(), log);
				SocketUtil.sendByteDataBySocket(SystemEnum.XA_LT_SYSTEM.toString(), 3,dx.getDropRateBytes(), log);
			}
		}, 1000, 1*60*1000);
	}
}
