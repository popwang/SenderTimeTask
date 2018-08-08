package com.http.aysm;

import org.apache.commons.logging.Log;

import com.tcp.zz.SendRunnable;
import com.utils.CommonUtil;
import com.vo.EquipmentData;

public class AysmRunner extends SendRunnable{

	public AysmRunner(EquipmentData v, Log log) {
		super(v, log);
	}

	@Override
	public void run() {
		String info = AysmUtil.getInfoString(getV());
		CommonUtil.doHttpGet(info,getLog());
	}
}
