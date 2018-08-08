package com.tcp.zz;

import org.apache.commons.logging.Log;

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
		SocketUtil.init(SystemEnum.ZZ_TBJ_SYSTEM.toString(), ConfigReader.getZZtbjIP(), ConfigReader.getZZtbjPORT());
		SocketUtil.sendDataBySocket(SystemEnum.ZZ_TBJ_SYSTEM.toString(), 1,info, log);
	}
}
