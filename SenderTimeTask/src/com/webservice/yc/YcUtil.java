package com.webservice.yc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.utils.CommonUtil;
import com.vo.EquipmentData;

public class YcUtil {
	public static String switchEquipmentToData(EquipmentData v){
		ObjectMapper objectMapper = new ObjectMapper();
		YcVo vo = new YcVo();
		vo.setHUM(v.getP007()+"");
		vo.setNoise(v.getP008()+"");
		vo.setPM10(v.getP003()+"");
		vo.setPM25(v.getP002()+"");
		vo.setSBID(v.getV_equipment_name());
		vo.setTEMP2(v.getP006()+"");
		vo.setTSP(v.getWindSpeed());
		vo.setWD(CommonUtil.getWindString3(v.getP005())+"·ç");
		vo.setWS(v.getP004()+"");
		String json = "";
		try {
			json = objectMapper.writeValueAsString(vo);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return json;
	}
	public static void main(String[] args) {
		EquipmentData vo  = CommonUtil.getEquipmentDataInstance();
		vo.setV_equipment_name("ZB00000955");
		System.out.println(switchEquipmentToData(vo));
	}
}
