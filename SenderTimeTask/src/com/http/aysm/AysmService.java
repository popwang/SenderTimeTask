package com.http.aysm;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mapper.CommonMapper;
import com.utils.CommonUtil;
import com.utils.SystemEnum;
import com.utils.ThreadPoolUtil;
import com.vo.EquipmentData;
import com.vo.EquipmentProjectVo;

/**
 * 1.安阳，三门峡部分设备数据上传服务类；
 * 2.数据上传使用http服务，每10分钟发送一次；
 * 3.这两个地区的接入商为同一家，接口规范完全一样，只是上传的地址不同；
 * 4.后续增加设备，直接添加即可。
 * 5.技术联系电话：0372-3810966
 * @author Administrator
 */
@Component
public class AysmService {
	public static Log log = LogFactory.getLog(AysmService.class);    //日志记录
	
	@Autowired
	private CommonMapper mapper;
	
    public void service(){
    	List<EquipmentData> list = mapper.selectEquipmentDataListBySystemId(SystemEnum.AY_SM_SYSTEM.getId());
		log.info("本轮待发送设备数为："+list.size());
		
		ThreadPoolUtil.printExecutorStatus();
		for(EquipmentData vo : list){
			if(vo.getV_real_equipment_name()==null){
				log.info(vo.getV_equipment_name()+"当前无数据。");
				continue;
			}
			AysmRunner runner = new AysmRunner(vo,log);
			ThreadPoolUtil.getExecutorService().execute(runner);
		}
    }
    
    public static void main(String[] args){
    	for(int i=0;i<5;i++){
    		String s = "http://183.203.89.2:81/pub?userkey=ZBLW00000436&data=124,157,9.0,44,41.5,0.0,180,0&date=201711271810";
    		String s1 = "http://pub.171hb.com/pub?userkey=AZDZ00000057&data=124,157,9.0,44,41.5,0.0,180,0&date=201904031415";
	          //http://123.56.159.82:802/pub?userkey=ZBLW00000436&data=124,157,9.0,44,41.5,0.0,180,0&date=201711271810
    		CommonUtil.doHttpGet(s1,log);
    	}
    }
}
