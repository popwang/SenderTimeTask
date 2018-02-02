package com.business.update;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.QuartzInterface;

/**
 * 定时将data2种的经纬度更新到info表，并转换为百度坐标
 * @author pactera
 */
@Component
public class UpdateGPSQuartz implements QuartzInterface {
	public static Log log = LogFactory.getLog(UpdateGPSQuartz.class);
	@Autowired
	private UpdateEquipmentInfoService updateService;
	@Override
	public void startTimeTask() {
		log.info("开始更新最新的经纬度到设备信息表...");
		updateService.handler2();
		log.info("更新最新的经纬度到设备信息表完成！");
	}

}
