package com.webservice.kfyz;

import com.webservice.kfyz.ws.ObjectFactory;
import com.webservice.kfyz.ws.SaveDBHeader;
import com.webservice.kfyz.ws.YCSaveDB;
import com.webservice.kfyz.ws.YCSaveDBHttpGet;
import com.webservice.kfyz.ws.YCSaveDBHttpPost;
import com.webservice.kfyz.ws.YCSaveDBSoap;
import com.webservice.kfyz.ws.YCSaveDBSoapImpl;
import com.webservice.kfyz.ws.YCSaveDBSoap_YCSaveDBSoap_Server;

public class KfyzService {
	public static void main(String[] args) {
//		SaveDBService.YCSaveDBSoapClient saveDBClient = new SaveDBService.YCSaveDBSoapClient ("YCSaveDBSoap",http://47.100.108.91/SaveDB/YCSaveDB.asmx");
//			//实例化加密验证对象
//			SaveDBService.SaveDBHeader header = new SaveDBService.SaveDBHeader();   
//			header.UserName = "Txbk@wxb";
//			header.Password = "xa20180823";
//			var deviceAddr = "01031D0001";
//			var str = "88.825|102.53|23.45|34.36|42.28|213.152|27.65|87.26|东南|2018-9-25 10:21:36";
//			   //调用WebService接口存库
//			saveDBClient.UpdateYCRealtimeData(header, deviceAddr, str);

		YCSaveDB ss = new YCSaveDB();
//		YCSaveDBHttpGet port = ss.getYCSaveDBHttpGet();
		YCSaveDBSoap port = ss.getYCSaveDBSoap();
			
			String deviceAddr = "01031D0001";
			String str = "88.825|102.53|23.45|34.36|42.28|213.152|27.65|87.26|东南|2018-9-25 10:21:36";
			port.updateYCRealtimeData(deviceAddr, str);
			
	}
}
