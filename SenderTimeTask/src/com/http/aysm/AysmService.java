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
    	List<EquipmentProjectVo> list = mapper.selectEquipmentListBySystemId(SystemEnum.AY_SM_SYSTEM.getId());
		log.info("���ִ������豸��Ϊ��"+list.size());
		for(EquipmentProjectVo vo : list){
			//������������Ͽ�豸δ����������ţ��޸ı�Ž�ȡ����
			String e = "0000"+vo.getV_equipment_name().substring(vo.getV_equipment_name().length()-4);
			EquipmentData v = mapper.selectDataByName(e);
			if(v==null){
				log.info(vo.getV_equipment_name()+"��ǰ�����ݡ�");
				continue;
			}
			StringBuffer sb = new StringBuffer(vo.getV_url());
			sb.append("?userkey=");
			sb.append(vo.getV_equipment_name());
			sb.append("&data=");
			sb.append(getParamsString(v));
			sb.append("&date=");
			sb.append(getDateString());
			log.info(sb.toString());
			CommonUtil.doHttpGet(sb.toString(),log);
		}
    }
    
    public static void main(String[] args){
    	for(int i=0;i<5;i++){
    		String s = "http://47.92.98.194:802/pub?userkey=ZBLW00000436&data=124,157,9.0,44,41.5,0.0,180,0&date=201711271810";
	          //http://123.56.159.82:802/pub?userkey=ZBLW00000436&data=124,157,9.0,44,41.5,0.0,180,0&date=201711271810
    		CommonUtil.doHttpGet(s,log);
    	}
    }
    
    /**
     * ��ȡ��ǰʱ���ַ���yyyyMMddHHmm
     * @return
     */
    public static String getDateString(){
    	return new SimpleDateFormat("yyyyMMddHHmm").format(new Date());
    }
    
    /**
     * �������ݶ�����װ�����ַ���
     * @param v
     * @return
     */
    public static String getParamsString(EquipmentData v){
		StringBuffer sb = new StringBuffer();
		sb.append((int)v.getP002());//PM2.5
		sb.append(",");
		sb.append((int)v.getP003());//PM10
		sb.append(",");
		sb.append(v.getP006());//�¶�
		sb.append(",");
		sb.append((int)v.getP007());//ʪ��
		sb.append(",");
		sb.append(v.getP008());//����
		sb.append(",");
		sb.append(v.getP004());//����
		sb.append(",");
		sb.append(CommonUtil.getWindString(v.getP005()));//����
		sb.append(",");
		sb.append("0");//��ѹ
		return sb.toString();
	}
}
