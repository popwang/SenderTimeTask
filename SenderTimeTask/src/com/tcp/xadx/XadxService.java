package com.tcp.xadx;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.ServerInterface;
import com.mapper.CommonMapper;
import com.utils.ConfigReader;
import com.utils.SocketUtil;
import com.utils.SystemEnum;
import com.vo.EquipmentData;
import com.vo.EquipmentProjectVo;
/**
 * 西安大兴区对接接口;莲湖区对接接口，与大兴区一致
 * 安正出货，294 SIM:18490437284
 * 对接人：齐工：13519194981  联系不上，无效
 * 设备添加：石工，刘元有微信，添加设备
 * 技术对接：樊工：131 9333 4306 邮箱： fca756@126.com，估计是后台厂家
 * @author Administrator
 */
@Component
public class XadxService implements ServerInterface {
	
	public static Log log = LogFactory.getLog(XadxService.class);
	@Autowired
	private CommonMapper mapper;
	@Override
	public void handler() {
		List<EquipmentProjectVo> list = mapper.selectEquipmentListBySystemId(SystemEnum.XA_DXQ_SYSTEM.getId());
		log.info(SystemEnum.XA_DXQ_SYSTEM.getName()+"本轮待发送设备数为：" + list.size());
		for (EquipmentProjectVo vo : list) {
			EquipmentData v = mapper.selectDataByName(vo.getV_equipment_name().substring(2));
			if(v==null){
				log.info(vo.getV_equipment_name()+"当前无数据。");
				continue;
			}
			v.setV_equipment_name(vo.getV_url());
			DxUtil dx = new DxUtil(v);
			SocketUtil.init(SystemEnum.XA_DXQ_SYSTEM.toString(), ConfigReader.getDaxingquIP(),
					ConfigReader.getDaxingquPORT());
			SocketUtil.sendByteDataBySocket(SystemEnum.XA_DXQ_SYSTEM.toString(), 1,dx.getSequenceBytes(), log);
			SocketUtil.sendByteDataBySocket(SystemEnum.XA_DXQ_SYSTEM.toString(), 1,dx.getNoiseBytes(), log);
			SocketUtil.sendByteDataBySocket(SystemEnum.XA_DXQ_SYSTEM.toString(), 1,dx.getPM25Bytes(), log);
			SocketUtil.sendByteDataBySocket(SystemEnum.XA_DXQ_SYSTEM.toString(), 1,dx.getPM10Bytes(), log);
//			SocketUtil.sendByteDataBySocket(SystemEnum.XA_DXQ_SYSTEM.toString(), 1,dx.getGPSBytes(), log);
//			SocketUtil.sendByteDataBySocket(SystemEnum.XA_DXQ_SYSTEM.toString(), 1,dx.getDropRateBytes(), log);
		}
	}
	
	//
	public static void main(String[] args){
		EquipmentData e = new EquipmentData();
		e.setV_equipment_name("18490437284");
		e.setP001(0);
		e.setP002(300);
		e.setP003(400);
		e.setP004(0.5);
		e.setP005(3);
		e.setP006(32.0);
		e.setP007(45.3);
		e.setP008(52.1);
		e.setP009(0);
		e.setP010(0);
		DxUtil dx = new DxUtil(e);
		SocketUtil.init(SystemEnum.XA_DXQ_SYSTEM.toString(), ConfigReader.getDaxingquIP(),
				ConfigReader.getDaxingquPORT());
		SocketUtil.sendByteDataBySocket(SystemEnum.XA_DXQ_SYSTEM.toString(), 3,dx.getSequenceBytes(), log);
		SocketUtil.sendByteDataBySocket(SystemEnum.XA_DXQ_SYSTEM.toString(), 3,dx.getNoiseBytes(), log);
		SocketUtil.sendByteDataBySocket(SystemEnum.XA_DXQ_SYSTEM.toString(), 3,dx.getPM25Bytes(), log);
		SocketUtil.sendByteDataBySocket(SystemEnum.XA_DXQ_SYSTEM.toString(), 3,dx.getPM10Bytes(), log);
//		SocketUtil.sendByteDataBySocket(SystemEnum.SX_XA_SYSTEM.toString(), 3,dx.getGPSBytes(), log);
//		SocketUtil.sendByteDataBySocket(SystemEnum.SX_XA_SYSTEM.toString(), 3,dx.getDropRateBytes(), log);
	}

}
