package com.tcp.sxyc;

import org.springframework.stereotype.Component;

import com.common.service.AbstractBaseService;
import com.vo.EquipmentData;
/**
 * 山西运城数据对接服务
 * 重写数据发送接口即可
 * @author pactera
 */
@Component
public class SxycService extends AbstractBaseService {

	@Override
	public void sendEquipmentData(EquipmentData v) {
		System.out.println("发送完成");
	}
	
	public static void main(String[] args) {
		
	}
}
