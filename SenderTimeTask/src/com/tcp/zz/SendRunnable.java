package com.tcp.zz;

import java.util.Random;

import org.apache.commons.logging.Log;

import com.utils.CommonUtil;
import com.utils.ConfigReader;
import com.utils.SocketUtil;
import com.utils.SystemEnum;
import com.utils.TBJUtil;
import com.vo.EquipmentData;

public class SendRunnable implements Runnable {
	private EquipmentData v;
	private Log log;
	
	public EquipmentData getV() {
		return v;
	}

	public void setV(EquipmentData v) {
		this.v = v;
	}

	public Log getLog() {
		return log;
	}

	public void setLog(Log log) {
		this.log = log;
	}

	public SendRunnable(EquipmentData v, Log log){
		this.v = v;
		this.log = log;
	}
	
	@Override
	public void run() {
		String info = TBJUtil.getDataString(v);
		int id = new Random().nextInt(10);
		log.info("当前使用socketId:"+SystemEnum.ZZ_TBJ_SYSTEM.toString()+"-"+id);
		SocketUtil.init(SystemEnum.ZZ_TBJ_SYSTEM.toString()+"-"+id,ConfigReader.getHost(SystemEnum.ZZ_TBJ_SYSTEM.toString()),
				ConfigReader.getPort(SystemEnum.ZZ_TBJ_SYSTEM.toString()));
		SocketUtil.sendDataBySocket(SystemEnum.ZZ_TBJ_SYSTEM.toString()+"-"+id, 1,info, log);
	}
}
