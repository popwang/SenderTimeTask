package com.tcp.sxyc;

import org.springframework.stereotype.Component;

import com.common.service.AbstractBaseService;
import com.vo.EquipmentData;
/**
 * ɽ���˳����ݶԽӷ���
 * ��д���ݷ��ͽӿڼ���
 * @author pactera
 */
@Component
public class SxycService extends AbstractBaseService {

	@Override
	public void sendEquipmentData(EquipmentData v) {
		System.out.println("�������");
	}
	
	public static void main(String[] args) {
		
	}
}
