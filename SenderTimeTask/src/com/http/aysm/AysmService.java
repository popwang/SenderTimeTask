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
 * 1.����������Ͽ�����豸�����ϴ������ࣻ
 * 2.�����ϴ�ʹ��http����ÿ10���ӷ���һ�Σ�
 * 3.�����������Ľ�����Ϊͬһ�ң��ӿڹ淶��ȫһ����ֻ���ϴ��ĵ�ַ��ͬ��
 * 4.���������豸��ֱ����Ӽ��ɡ�
 * 5.������ϵ�绰��0372-3810966
 * @author Administrator
 */
@Component
public class AysmService {
	public static Log log = LogFactory.getLog(AysmService.class);    //��־��¼
	
	@Autowired
	private CommonMapper mapper;
	
    public void service(){
    	List<EquipmentData> list = mapper.selectEquipmentDataListBySystemId(SystemEnum.AY_SM_SYSTEM.getId());
		log.info("���ִ������豸��Ϊ��"+list.size());
		
		ThreadPoolUtil.printExecutorStatus();
		for(EquipmentData vo : list){
			if(vo.getV_real_equipment_name()==null){
				log.info(vo.getV_equipment_name()+"��ǰ�����ݡ�");
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
