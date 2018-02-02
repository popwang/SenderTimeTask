package com.tcp.traffic;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mapper.CommonMapper;
import com.tcp.sd.SdService;
import com.utils.CRC;
import com.utils.CommonUtil;
import com.utils.ConfigReader;
import com.utils.SystemEnum;
import com.vo.EquipmentData;
import com.vo.EquipmentProjectVo;

/**
 * 1.郑州交委数据接入平台，公司为深圳公司；估计与山东济宁为同一公司；
 * 2.目前已接入联调成功，后续如果工地要接入设备，需要到有关部门进行备案；填写比较完备的信息；
 * 3.当前需要接入设备为144；由于公司未备案，数据不被接收；该协议对设备编号有特殊要求； 
 * 4.技术电话：13826513167，QQ:120014831
 * 5.Socket socket = new Socket("203.110.178.83", 9005);//测试环境
 *	 Socket socket = new Socket("139.224.57.175", 9005);// 正式环境
 * @author Administrator
 */
@Component
public class TrafficService {
	public static Log log = LogFactory.getLog(SdService.class);
	@Autowired
	private CommonMapper mapper;

	public void handler() {
		List<EquipmentProjectVo> list = mapper
				.selectEquipmentListBySystemId(SystemEnum.ZZ_TRA_SYSTEM.getId());
		log.info("本轮待发送设备数为："+list.size());
		for (EquipmentProjectVo vo : list) {
			EquipmentData e = mapper.selectDataByName(vo.getV_equipment_name());
			if(e==null){
				log.info(vo.getV_equipment_name()+"当前无数据。");
				continue;
			}
			String info = CRC.getDataString2(e);
			log.info("发送内容:" + info);
			CommonUtil.sendDataToRemote(ConfigReader.getZZtraIP(),
					ConfigReader.getZZtraPORT(),info,log);
		}
	}
}
