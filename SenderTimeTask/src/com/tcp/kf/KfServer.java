package com.tcp.kf;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.ServerInterface;
import com.mapper.CommonMapper;
import com.utils.ByteUtil;
import com.utils.CommonUtil;
import com.utils.ConfigReader;
import com.utils.SystemEnum;
import com.vo.EquipmentData;
import com.vo.EquipmentProjectVo;

/**
 * �����������ﳾ����ƽ̨�Խ�
 * 1.��ϵ�ˣ��ι�-18530950471��
 * 2.ʹ�ö����������������ݣ�
 * 3.���Ե�ַ��121.42.182.8
	  ��ʽ��ַ��61.53.71.3
	  �˿ڣ�9445
   4.�и����������ϵ;299-4102010021101
 * @author pactera
 */
@Component("kfServer")
public class KfServer implements ServerInterface {
	public static Log log = LogFactory.getLog(KfServer.class);
	
	@Autowired
	private CommonMapper mapper;
	
	@Override
	public void handler() {
		List<EquipmentProjectVo> list = mapper.selectEquipmentListBySystemId(SystemEnum.HA_KF_SYSTEM.getId());
		log.info("���ִ������豸��Ϊ��"+list.size());
		for(EquipmentProjectVo vo : list){
			EquipmentData e = mapper.selectDataByName(vo.getV_equipment_name().substring(2));
			if(e==null){
				log.info(vo.getV_equipment_name()+"��ǰ�����ݡ�");
				continue;
			}
			e.setV_equipment_name(vo.getV_url());
			
			String info = KFUtil.getRegisterInfo(e);
			log.info("����ע����Ϣ:"+info);
			sendKfDataToRemote(ByteUtil.getSendDataBytes(info, ByteUtil.CMD_R));
			
			String vinfo = KFUtil.getVoiceInfo(e);
			log.info("������������:"+vinfo);
			sendKfDataToRemote(ByteUtil.getSendDataBytes(vinfo,ByteUtil.CMD_V));
			
			String pinfo = KFUtil.getPmInfo(e);
			log.info("�����ﳾ����:"+pinfo);
			sendKfDataToRemote(ByteUtil.getSendDataBytes(pinfo,ByteUtil.CMD_P));
		}
		log.info("���ִ������豸��Ϊ��"+list.size());
	}
	
	public static void main(String[] args){
		EquipmentData e = CommonUtil.getEquipmentDataInstance();
		sendKfDataToRemote(ByteUtil.getSendDataBytes(KFUtil.getRegisterInfo(e), ByteUtil.CMD_R));
		sendKfDataToRemote(ByteUtil.getSendDataBytes(KFUtil.getVoiceInfo(e),ByteUtil.CMD_V));
		sendKfDataToRemote(ByteUtil.getSendDataBytes(KFUtil.getPmInfo(e), ByteUtil.CMD_P));
	}
	
	public static void sendKfDataToRemote(byte[] info){
		CommonUtil.sendByteDataToRemote(ConfigReader.getKfIP(),
				ConfigReader.getKfPORT(),info,log);
	}
}
