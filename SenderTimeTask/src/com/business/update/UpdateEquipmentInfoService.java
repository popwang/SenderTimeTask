package com.business.update;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mapper.BusinessMapper;
import com.mapper.CommonMapper;
import com.vo.EquipmentData;
import com.vo.EquipmentInfo;

@Component
public class UpdateEquipmentInfoService {
	public static Log log = LogFactory.getLog(UpdateEquipmentInfoService.class);
	public static final String API_KEY = "3QOt9hb2XPDc00yMdVdmRmxp";
	@Autowired
	private BusinessMapper business;
	
	@Autowired
	private CommonMapper common;
	/**
	 * 用于将utf8编码的设备信息更新称中文编码
	 */
	public void hanlder() {
		List<EquipmentInfo> list = business.selectEquipmentInfo();
		log.info("本轮待更新设备数为："+list.size());
		for(EquipmentInfo e : list){
			if(e.getV_address()!=null && !"".equals(e.getV_address())){
				try {
					e.setV_company(java.net.URLDecoder.decode(e.getV_company(), "UTF-8"));
					e.setV_address(java.net.URLDecoder.decode(e.getV_address(), "UTF-8"));
					business.updateEquipmentInfo(e);
				} catch (UnsupportedEncodingException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
	/**
	 * 用于将设备的经纬度更新进info表，同时转换成百度经纬度
	 */
	public void handler2() {
		List<EquipmentInfo> list = business.selectEquipmentInfo();
		EquipmentData data = null;
		log.info("本轮待更新设备数为："+list.size());
		int count = 0;
		for(EquipmentInfo e : list){
			String equipmentName = e.getV_equipment_name();
//			log.info("当前处理设备为："+equipmentName);
			data = common.selectDataByName(equipmentName);
			if(data!=null) {
				count++;
				e.setN_latitude(data.getP014());
				e.setN_longitude(data.getP015());
				switchList2Baidu(e);
				business.updateEquipmentInfo(e);
			}
//			else {
//				log.info(equipmentName+"当前设备没有数据。");
//			}
		}
		log.info("本轮设备处理完成。更新设备数为："+count);
	}
	
	public static void main(String[] args) {
		EquipmentInfo e = new EquipmentInfo();
		e.setN_latitude(34.725957);
		e.setN_longitude(113.641365);
		switchList2Baidu(e);
		System.out.println(e.getN_latitude_bd());
		System.out.println(e.getN_longitude_bd());
	}
	
	public static EquipmentInfo switchList2Baidu(EquipmentInfo e){
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			URL resjson = new URL("http://api.map.baidu.com/geoconv/v1/?coords="+ e.getN_longitude()+","+e.getN_latitude()
					+ "&from=1&to=5&ak=" + API_KEY);
			BufferedReader in = new BufferedReader(new InputStreamReader(
                    resjson.openStream(),"UTF-8"));
			String res;
            StringBuilder sb = new StringBuilder("");  
            while ((res = in.readLine()) != null) {
                sb.append(res.trim());
            }
            in.close();
            String str = sb.toString();
            if(str!=null&&!str.equals("")){
            	log.info(str);
            	JsonNode root = objectMapper.readTree(str);
    			JsonNode result = root.path("result");
    			JsonNode cell = result.get(0);
    			JsonNode lng = cell.path("x");
    			JsonNode lat = cell.path("y");
    			e.setN_longitude_bd(lng.asDouble());
    			e.setN_latitude_bd(lat.asDouble());
            }
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return e;
	}
}
