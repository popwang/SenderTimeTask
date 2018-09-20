package com.tcp.zz;

import java.util.List;

import org.springframework.stereotype.Component;

import com.common.service.AbstractBaseService;
import com.utils.CommonUtil;
import com.utils.SocketUtil;
import com.utils.SystemEnum;
import com.utils.TBJUtil;
import com.utils.ThreadPoolUtil;
import com.vo.EquipmentData;

/**
 * 1.郑州特必佳公司接口；目前接入5台设备； 
 * 2.设备列表直接添加即可； 
 * 3.技术电话：宋-177 3718 3234
 * 4.20180808,修改数据查询方式，一次性关联查询全部数据；增加线程池并发发送数据；
 * @author Administrator
 */
@Component
public class ZztbjService extends AbstractBaseService {
	@Override
	public void handler(SystemEnum systemEnum) {
		List<EquipmentData> list = mapper.selectEquipmentDataListBySystemId(systemEnum.getId());
		log.info("本轮待发送设备数为：" + list.size());
		ThreadPoolUtil.printExecutorStatus();
		for (EquipmentData v : list) {
			if(v.getV_real_equipment_name()==null){
				log.info(v.getV_equipment_name()+"当前无数据。");
				continue;
			}
			SendRunnable runner = new SendRunnable(v,log);
			ThreadPoolUtil.getExecutorService().execute(runner);
		}
	}
	
	public static void main(String[] args){
		EquipmentData e = CommonUtil.getEquipmentDataInstance();
		e.setV_equipment_name("ZB00005081");
		String info = TBJUtil.getDataString(e);
		SocketUtil.init(SystemEnum.ZZ_TBJ_SYSTEM.toString(), "123.15.58.210", 9123);
		SocketUtil.sendDataBySocket(SystemEnum.ZZ_TBJ_SYSTEM.toString(), 1,info, log);	
	}

	@Override
	public void sendEquipmentData(EquipmentData v) {
		
	}
}

