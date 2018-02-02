package com.tcp.xy;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.ServerInterface;
import com.mapper.CommonMapper;
/**
 * 信阳数据对接，系统ID:10
 * 暂停开发
 * @author pactera
 */
@Component("xyServer")
public class XyServer implements ServerInterface {
	public static Log log = LogFactory.getLog(XyServer.class);
	@Autowired
	private CommonMapper mapper;
	@Override
	public void handler() {
		mapper.selectEquipmentListBySystemId(10);
	}
		
	public static void main(String[] args) {
		
	}
}
