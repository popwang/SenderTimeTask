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
    	List<EquipmentProjectVo> list = mapper.selectEquipmentListBySystemId(SystemEnum.AY_SM_SYSTEM.getId());
		log.info("本轮待发送设备数为："+list.size());
		for(EquipmentProjectVo vo : list){
			//由于新增三门峡设备未按最初规则编号，修改编号截取方案
			String e = "0000"+vo.getV_equipment_name().substring(vo.getV_equipment_name().length()-4);
			EquipmentData v = mapper.selectDataByName(e);
			if(v==null){
				log.info(vo.getV_equipment_name()+"当前无数据。");
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
     * 获取当前时间字符串yyyyMMddHHmm
     * @return
     */
    public static String getDateString(){
    	return new SimpleDateFormat("yyyyMMddHHmm").format(new Date());
    }
    
    /**
     * 根据数据对象组装参数字符串
     * @param v
     * @return
     */
    public static String getParamsString(EquipmentData v){
		StringBuffer sb = new StringBuffer();
		sb.append((int)v.getP002());//PM2.5
		sb.append(",");
		sb.append((int)v.getP003());//PM10
		sb.append(",");
		sb.append(v.getP006());//温度
		sb.append(",");
		sb.append((int)v.getP007());//湿度
		sb.append(",");
		sb.append(v.getP008());//噪声
		sb.append(",");
		sb.append(v.getP004());//风速
		sb.append(",");
		sb.append(CommonUtil.getWindString(v.getP005()));//风向
		sb.append(",");
		sb.append("0");//气压
		return sb.toString();
	}
}
