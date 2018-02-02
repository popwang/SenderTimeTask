package com.http.sdhz;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.ServerInterface;
import com.mapper.CommonMapper;
import com.utils.CRC;
import com.utils.CommonUtil;
import com.utils.SystemEnum;
import com.vo.EquipmentData;
import com.vo.EquipmentProjectVo;
/**
 * 南华城4号楼，415：371702102400001(设备编号):371702102401(站点名称)
 * @author pactera
 *
 */
@Component
public class SdhzService implements ServerInterface {
	
public static Log log = LogFactory.getLog(SdhzService.class);    //日志记录
	
	@Autowired
	private CommonMapper mapper;
	
	@Override
	public void handler() {
		List<EquipmentProjectVo> list = mapper.selectEquipmentListBySystemId(SystemEnum.SD_HZ_SYSTEM.getId());
		log.info(SystemEnum.SD_HZ_SYSTEM.toString()+"本轮待发送设备数为："+list.size());
		for(EquipmentProjectVo vo : list){
			String e = vo.getV_equipment_name();
			EquipmentData v = mapper.selectDataByName(e.substring(2));
			if(v==null){
				log.info(e+"当前无数据。");
				continue;
			}
			v.setV_equipment_name(vo.getV_project_name());//projectName里面存放工地分配的id
			String info = getDataString(v,vo.getV_url());
			log.info(info);
			CommonUtil.doHttpGet(info,log);
		}
	}
	
	public static void main(String[] args){
		EquipmentData v = CommonUtil.getEquipmentDataInstance();
		v.setV_equipment_name("371702102400001");
		SdhzService service = new SdhzService();
		String info = service.getDataString(v, "http://pm.inheze.cn:9053/ycjc/api/rdata/");
		log.info(info);
		CommonUtil.doHttpGet(info,log);
	}
	
	public String getDataString(EquipmentData v,String url){
		StringBuffer sb = new StringBuffer(url);
		sb.append("001,");
		sb.append(v.getV_equipment_name());
		sb.append(",");
		sb.append(CRC.currentTimeString3());
		sb.append(",");
		sb.append(v.getP002());
		sb.append(",");
		sb.append(v.getP003());
		sb.append(",");
		sb.append(v.getP008());
		sb.append(",");
		sb.append(v.getP006());
		sb.append(",");
		sb.append(v.getP007());
		sb.append(",");
		sb.append(v.getP004());
		sb.append(",");
		sb.append(CommonUtil.getWindString(v.getP005()));
		sb.append(",");
		sb.append((int)v.getP010());
		sb.append(",0,0,0");
		return sb.toString();
	}
}
