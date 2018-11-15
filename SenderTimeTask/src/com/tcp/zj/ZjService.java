package com.tcp.zj;

import org.springframework.stereotype.Component;

import com.common.service.AbstractBaseService;
import com.utils.CRC;
import com.utils.CommonUtil;
import com.utils.SocketUtil;
import com.utils.SystemEnum;
import com.vo.EquipmentData;

/**
 * 中建四局内部平台，中邦介绍
 * 212通用协议
 * @author pactera
 */
@Component
public class ZjService extends AbstractBaseService {

	@Override
	public void sendEquipmentData(EquipmentData e) {
		String info = getAirString(e);
		SocketUtil.init2(SystemEnum.ZZ_ZJ_SYSTEM.toString());
		SocketUtil.sendDataBySocket(SystemEnum.ZZ_ZJ_SYSTEM.toString(), 3, info, log);
	}
	
	public static void main(String[] args) {
		ZjService service = new ZjService();
		EquipmentData e = CommonUtil.getEquipmentDataInstance();
		e.setV_equipment_name("01476440f000001100000001");
		service.sendEquipmentData(e);
	}
	
	public static String getAirString(EquipmentData e) {
		StringBuffer sb = new StringBuffer("");
		sb.append("QN=");
		sb.append(CRC.currentTimeStampString());//yyyyMMddHHmmssSSS
		sb.append(";ST=22;CN=2011;PW=GDItG0;MN=");
		sb.append(e.getV_equipment_name());
		sb.append(";Flag=5;CP=&&DataTime=");
		sb.append(CRC.currentTimeString());//yyyyMMddHHmmss
		sb.append(";");
		//PM2.5
		sb.append("a34004-Rtd=");
		sb.append(e.getP002()*1000);
		sb.append(",a34004-Flag=N;");
		//PM10
		sb.append("a34002-Rtd=");
		sb.append(e.getP003()*1000);
		sb.append(",a34002-Flag=N;");
		//PM100
		sb.append("a34001-Rtd=");
		sb.append(e.getP009());
		sb.append(",a34001-Flag=N;");
		//噪音
		sb.append("LA-Rtd=");
		sb.append(e.getP008());
		sb.append(",LA-Flag=N;");
		//气压
		sb.append("a01006-Rtd=");
		sb.append(e.getP010());
		sb.append(",a01006-Flag=N;");
		//风速
		sb.append("a01007-Rtd=");
		sb.append(e.getP004());
		sb.append(",a01007-Flag=N;");
		//风向
		sb.append("a01008-Rtd=");
		sb.append(CommonUtil.getWindString(e.getP005()));//转换为度数
		sb.append(",a01008-Flag=N;");
		//温度
		sb.append("a01001-Rtd=");
		sb.append(e.getP006());
		sb.append(",a01001-Flag=N;");
		//湿度
		sb.append("a01002-Rtd=");
		sb.append(e.getP007());
		sb.append(",a01002-Flag=N&&");
		
		String crc = sb.toString();
		return CRC.pre+CRC.patternNumber(crc.length())+crc+CRC.GetCRC2(crc)+"\r\n";
	}

}
