package com.tcp.lyyc;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.ServerInterface;
import com.mapper.CommonMapper;
import com.tcp.kf.KFUtil;
import com.utils.ByteUtil;
import com.utils.CommonUtil;
import com.utils.ConfigReader;
import com.utils.SocketUtil;
import com.utils.SystemEnum;
import com.vo.EquipmentData;
import com.vo.EquipmentProjectVo;
@Component
public class LyycService implements ServerInterface {
	public static Log log = LogFactory.getLog(LyycService.class);
	@Autowired
	private CommonMapper mapper;
	@Override
	public void handler() {
		List<EquipmentProjectVo> list = mapper.selectEquipmentListBySystemId(SystemEnum.LY_YC_SYSTEM.getId());
		log.info("本轮待发送设备数为：" + list.size());
		for (EquipmentProjectVo vo : list) {
			EquipmentData v = mapper.selectDataByName(vo.getV_equipment_name());
			if(v==null){
				log.info(vo.getV_equipment_name()+"当前无数据。");
				continue;
			}
			v.setV_equipment_name(vo.getV_url());
			
			String info = KFUtil.getRegisterInfo(v);
			log.info("发送注册信息:"+info);
			sendKfDataToRemote(ByteUtil.getSendDataBytes(info, ByteUtil.CMD_R));
			
			String vinfo = KFUtil.getVoiceInfo(v);
			log.info("发送噪声数据:"+vinfo);
			sendKfDataToRemote(ByteUtil.getSendDataBytes(vinfo,ByteUtil.CMD_V));
			
			String pinfo = KFUtil.getPmInfo(v);
			log.info("发送扬尘数据:"+pinfo);
			sendKfDataToRemote(ByteUtil.getSendDataBytes(pinfo,ByteUtil.CMD_P));
		}
	}
	
	public static void main(String[] args){
		EquipmentData e = CommonUtil.getEquipmentDataInstance();
		e.setV_equipment_name("20170000449");
		sendKfDataToRemote(ByteUtil.getSendDataBytes(KFUtil.getRegisterInfo(e), ByteUtil.CMD_R));
		sendKfDataToRemote(ByteUtil.getSendDataBytes(KFUtil.getVoiceInfo(e),ByteUtil.CMD_V));
		sendKfDataToRemote(ByteUtil.getSendDataBytes(KFUtil.getPmInfo(e), ByteUtil.CMD_P));
	}
	
	public static void sendKfDataToRemote(byte[] info){
		SocketUtil.init(SystemEnum.LY_YC_SYSTEM.toString(), ConfigReader.getLyYcIP(),
				ConfigReader.getLyYcPORT());
		SocketUtil.sendByteDataBySocket(SystemEnum.LY_YC_SYSTEM.toString(), 1,info, log);
	}
}
