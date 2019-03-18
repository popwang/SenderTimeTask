package com.tcp.zztbj2;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.springframework.stereotype.Component;

import com.common.service.AbstractBaseService;
import com.tcp.hncs.HnUtil;
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
public class Zztbj2Service extends AbstractBaseService {
	
	public static void main(String[] args){
		Timer timer = new Timer();
		Zztbj2Service zz = new Zztbj2Service();
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				EquipmentData e = CommonUtil.getEquipmentDataInstance();
				zz.sendEquipmentData(e);
			}
		}, 1000, 2*60*1000);
	}

	@Override
	public void sendEquipmentData(EquipmentData v) {
		String info = TBJUtil.getDataString(v);
		SocketUtil.init2(SystemEnum.ZZ_TBJ2_SYSTEM.toString());
		SocketUtil.sendDataBySocket(SystemEnum.ZZ_TBJ2_SYSTEM.toString(), 1,info, log);
	}
}

