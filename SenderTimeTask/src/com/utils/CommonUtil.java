package com.utils;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.sun.xml.internal.ws.util.ByteArrayBuffer;
import com.vo.EquipmentData;
/**
 * 通用方法工具类
 * @author pactera
 */
public class CommonUtil {
	/**
	 * 根据风向id转换为风向角度
	 * @param d
	 * @return
	 */
	public static String getWindString(double d) {
		String str = "";
		int i = (int) d;
		switch (i) {
		case 1:
			str = "0";
			break;
		case 2:
			str = "45";
			break;
		case 3:
			str = "90";
			break;
		case 4:
			str = "135";
			break;
		case 5:
			str = "180";
			break;
		case 6:
			str = "225";
			break;
		case 7:
			str = "270";
			break;
		case 8:
			str = "315";
			break;
		default:
			str = "360";
		}
		return str;
	}
	
	/**
	 * 风向编号转换成字母
	 * 
	 * @param d
	 * @return
	 */
	public static String getWindString2(double d) {
		String str = "";
		int i = (int) d;
		switch (i) {
		case 1:
			str = "N";
			break;
		case 2:
			str = "NE";
			break;
		case 3:
			str = "E";
			break;
		case 4:
			str = "SE";
			break;
		case 5:
			str = "S";
			break;
		case 6:
			str = "SW";
			break;
		case 7:
			str = "W";
			break;
		case 8:
			str = "NW";
			break;
		default:
			str = "ESWN";
		}
		return str;
	}
	
	public static void sendDataToRemote2(String key,String info,Log log) {
		String ip = ConfigReader.getHost(key);
		int port = ConfigReader.getPort(key);
		sendDataToRemote(ip, port, info, log);
	}
	
	/**
	 * 调用socket发送数据
	 * @param info
	 * @param ip
	 * @param port
	 */
	public static void sendDataToRemote(String ip,int port,String info,Log log) {
		try {
			Socket socket = new Socket(ip,port);
			socket.setSoTimeout(3000);
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "utf-8"), true);
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			log.info("发送数据："+info);
			pw.write(info);
			pw.flush();
			socket.shutdownOutput();
			String tmp = null;
			try{
				while((tmp=br.readLine())!=null){
					log.info("返回内容:"+tmp);
				}
			}catch(Exception e){
				log.info("返回内容接收完毕！");
			}
			br.close();
			pw.close();
			socket.close();
		} catch (UnknownHostException e) {
			log.info(e.getMessage(),e);
		} catch (IOException e) {
			log.info(e.getMessage(),e);
		} 
	}
	
	/**
	 * 包装sendByteDataToRemote，减少参数列表，方便调用
	 * @param key
	 * @param bytes
	 * @param log
	 */
	public static void sendByteDataToRemote2(String key,byte[] bytes,Log log){
		String ip = ConfigReader.getHost(key);
		int port = ConfigReader.getPort(key);
		sendByteDataToRemote(ip,port,bytes,log);
	}
	/**
	 * 调用socket发送字节数据
	 * @param ip
	 * @param port
	 * @param bytes
	 * @param log
	 */
	public static void sendByteDataToRemote(String ip,int port,byte[] bytes,Log log) {
		try {
			Socket socket = new Socket(ip,port);
			socket.setSoTimeout(5000);
			log.info("connect success");
			OutputStream os = socket.getOutputStream();
			InputStream is = new DataInputStream(socket.getInputStream());
			os.write(bytes);
			os.flush();
			log.info("send over");
			
//			byte[] data = new byte[22];
//			int totalBytesRcvd = 0;
//            int bytesRcvd;
//            while (totalBytesRcvd < data.length) {
//                bytesRcvd = is.read(data, totalBytesRcvd, data.length - totalBytesRcvd);
//                totalBytesRcvd += bytesRcvd;
//            }
//            log.info(new String(data,"UTF-8"));
//            log.info("Received-hex:" + ByteUtil.bytesToHexString(data));
//            socket.shutdownOutput();
//			is.close();
//			os.close();
//			socket.close();
			
			DataInputStream dis = new DataInputStream(is);
            String result =  "无返回结果";
            int resultLen = dis.readInt();
            if(0 != resultLen) {
                ByteArrayBuffer bb = new ByteArrayBuffer();
                while(true){
                    bb.write(dis.readByte());
                    if(bb.size() >= resultLen){
                        break;
                    }
                }
                result = new String(bb.getRawData(),"utf-8");
                log.info("返回字节：" + ByteUtil.bytesToHexString(bb.getRawData()));
                log.info("返回字符串： " + result);
                bb.close();
            }
            is.close();
			os.close();
            socket.close();
		} catch (UnknownHostException e) {
			log.info(e.getMessage(),e);
		} catch (IOException e) {
			log.info(e.getMessage(),e);
		} finally {
			
		}
	}
	
	/**
	 * 调用socket发送字节数据
	 * @param ip
	 * @param port
	 * @param bytes
	 * @param log
	 */
	public static void sendByteDataToUDP(String ip,int port,byte[] data,Log log) {
		try {
			InetAddress address = InetAddress.getByName(ip);
	        DatagramPacket packet = new DatagramPacket(data, data.length, address, port);
	        DatagramSocket client = new DatagramSocket();
	        client.setSoTimeout(3000);
	        client.send(packet);
	        // 1.创建数据报，用于接受服务端响应的数据
	        byte[] bytes = new byte[1024];
	        packet = new DatagramPacket(bytes, bytes.length);
	        // 2.接收服务器响应的数据
	        client.receive(packet);
	        // 3.读取数据
	        String reply = new String(bytes, 0, packet.getLength(),"gbk");
	        System.out.println("server say：" + reply);
	        client.close();
		} catch (UnknownHostException e) {
			log.info(e.getMessage(),e);
		} catch (IOException e) {
			log.info(e.getMessage(),e);
		} finally {
			
		}
	}
	
	/**
	 * 发送http get 请求
	 * @param url
	 */
    public static void doHttpGet(String url,Log logger) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        RequestConfig requestConfig = RequestConfig.custom()    
                .setConnectTimeout(5000).setConnectionRequestTimeout(1000)    
                .setSocketTimeout(5000).build();    
        httpGet.setConfig(requestConfig);
        CloseableHttpResponse httpResponse = null;
        try {
            httpResponse = httpClient.execute(httpGet);
            HttpEntity httpEntity = httpResponse.getEntity();
            logger.info(EntityUtils.toString(httpEntity));
        } catch (Exception e) {
        	logger.info(e.getMessage(),e);
        } finally {
            try {
            	if(httpClient!=null){
            		httpClient.close();
            	}
                if(httpResponse!=null){
                	httpResponse.close();
                }
            } catch (Exception e) {
            	logger.info(e.getMessage(),e);
            }
        }
    }
    
    public static void doHttpGet2(String url,Map<String,String> map,Log log) {
		CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        for(Map.Entry<String, String> entry : map.entrySet()){
        	httpGet.setHeader(entry.getKey(), entry.getValue());
        }
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(5000).setConnectionRequestTimeout(1000)    
                .setSocketTimeout(5000).build();
        httpGet.setConfig(requestConfig); 
        CloseableHttpResponse httpResponse = null;
        try {
            httpResponse = httpClient.execute(httpGet);
            HttpEntity httpEntity = httpResponse.getEntity();
            log.info(EntityUtils.toString(httpEntity));
        } catch (Exception e) {
        	log.info(e.getMessage(),e);
        } finally {
            try {
            	if(httpClient!=null){
            		httpClient.close();
            	}
                if(httpResponse!=null){
                	httpResponse.close();
                }
            } catch (Exception e) {
            	log.info(e.getMessage(),e);
            }
        }
	}
    
    public static EquipmentData getEquipmentDataInstance(){
    	EquipmentData e = new EquipmentData();
		e.setV_equipment_name("811170374");
		e.setP001(0);
		e.setP002(103);
		e.setP003(83);
		e.setP004(0.5);
		e.setP005(3);
		e.setP006(32.0);
		e.setP007(45.3);
		e.setP008(52.1);
		e.setP009(0);
		e.setP010(0);
		e.setP014(34.23424);
		e.setP015(113.43594);
		return e;
    }
    
    /**
     * 在原始数据基础上上浮10%以内的一个增量
     * @param e
     * @return
     */
    public static EquipmentData getRandomEquipmentData(EquipmentData e){
		e.setP002(e.getP002()+Math.round(e.getP002()*0.1*Math.random()));
		e.setP003(e.getP003()+Math.round(e.getP003()*0.1*Math.random()));
		e.setP004(new BigDecimal(e.getP004()+Math.round(e.getP004()*Math.random())*0.1).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
		e.setP005(e.getP005());
		e.setP006(new BigDecimal(e.getP006()+Math.round(e.getP006()*Math.random())*0.1).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
		e.setP007(new BigDecimal(e.getP007()+Math.round(e.getP007()*Math.random())*0.1).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
		e.setP008(new BigDecimal(e.getP008()+Math.round(e.getP008()*Math.random())*0.1).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
		e.setP009(e.getP009()+Math.round(e.getP009()*0.1*Math.random()));
		e.setP010(e.getP010()+Math.round(e.getP010()*0.1*Math.random()));
    	return e;
    }
    
    public static void main(String[] args){
    	System.out.println(CommonUtil.getEquipmentDataInstance().toString());
    	System.out.println(CommonUtil.getRandomEquipmentData(CommonUtil.getEquipmentDataInstance()).toString());
    	System.out.println(CommonUtil.getRandomEquipmentData(CommonUtil.getEquipmentDataInstance()).toString());
    	System.out.println(CommonUtil.getRandomEquipmentData(CommonUtil.getEquipmentDataInstance()).toString());
    	System.out.println(CommonUtil.getRandomEquipmentData(CommonUtil.getEquipmentDataInstance()).toString());
    	System.out.println(CommonUtil.getRandomEquipmentData(CommonUtil.getEquipmentDataInstance()).toString());
    }
}
