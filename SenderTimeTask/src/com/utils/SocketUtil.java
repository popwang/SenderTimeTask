package com.utils;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sun.xml.internal.ws.util.ByteArrayBuffer;
import com.vo.EquipmentData;

/**
 * 创建单例模式socket，实现socket长连接
 * @author pactera
 *
 */
public class SocketUtil {
	public static Log log = LogFactory.getLog(SocketUtil.class);
	
	private static Map<String, Socket> cacheMap = new ConcurrentHashMap<String, Socket>();
	private static Map<String,PrintWriter> cacheWriter = new ConcurrentHashMap<String,PrintWriter>();
	private static Map<String,BufferedReader> cacheReader = new ConcurrentHashMap<String,BufferedReader>();
	private static Map<String,InputStream> cacheInput = new ConcurrentHashMap<String,InputStream>();
	private static Map<String,OutputStream> cacheOutput = new ConcurrentHashMap<String,OutputStream>();
	
	/**
	 * 初始化连接
	 * @param key
	 * @param ip
	 * @param port
	 */
	public static void init(String key,String ip,int port){
		getSocket(key,ip,port);
	}
	
	/**
	 * 参数简化版初始化连接
	 * @param key
	 */
	public static void init2(String key){
		String ip = ConfigReader.getHost(key);
		int port = ConfigReader.getPort(key);
		System.out.println(ip);
		System.out.println(port);
		getSocket(key,ip,port);
	}
	
	/**
	 * 根据key获取socket连接
	 * 如果存在则复用，如果不存在则重新创建
	 * @param key
	 * @param ip
	 * @param port
	 * @return
	 */
	public static Socket getSocket(String key,String ip,int port){
		Socket socket = cacheMap.get(key);
		if(socket==null){
			socket = createSocket(ip,port);
			if(socket!=null){
				cacheMap.put(key, socket);
				try {
					PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "utf-8"), true);
					BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					InputStream is = socket.getInputStream();
					OutputStream out = socket.getOutputStream();
					cacheWriter.put(key, pw);
					cacheReader.put(key, br);
					cacheInput.put(key, is);
					cacheOutput.put(key, out);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}else{
			if(!isSocketAvailble(socket)){
				deleteCacheByKey(key);
				socket = getSocket(key,ip,port);
			}
		}
		return socket;
	}
	
	/**
	 * 清理缓存内容
	 * @param key
	 */
	public static void deleteCacheByKey(String key){
		try {
			Socket socket = cacheMap.get(key);
			if(socket!=null){
				socket.close();
			}
		} catch (IOException e) {
			log.info("关闭连接异常");
			e.printStackTrace();
		}finally{
			cacheMap.remove(key);
			cacheWriter.remove(key);
			cacheReader.remove(key);
			cacheInput.remove(key);
			cacheOutput.remove(key);
		}
	}
	
	/**
	 * 发送心跳包，测试连接是否可用
	 * @param socket
	 * @return
	 */
	private static boolean isSocketAvailble(Socket socket){
		try {
			socket.sendUrgentData(0xFF);
		} catch (Exception e) {
			log.info("连接已不可用！");
			return false;
		}
		return true;
	}
	
	/**
	 * 创建socket连接
	 * @param ip
	 * @param port
	 * @return
	 */
	private static Socket createSocket(String ip,int port){
		Socket socket = null;
		try {
			socket = new Socket(ip,port);
		} catch (Exception e) {
			log.info("创建连接出错：");
			e.printStackTrace();
		}
		return socket;
	}
	
	/**
	 * 带超时的数据发送
	 * @param socket socket连接
	 * @param timeout 超时时间 秒
	 * @param info 发送内容
	 * @param log log
	 */
	public static void sendDataBySocket(String key,int timeout,String info,Log log){
		try {
			if(cacheMap.containsKey(key)){
				Socket socket = cacheMap.get(key);
				socket.setSoTimeout(timeout*1000);
				PrintWriter pw = cacheWriter.get(key);
				InputStream is = cacheInput.get(key);
				log.info("发送内容："+info);
				pw.write(info);
				pw.flush();
				byte[] buf = new byte[100];
				int len = 0;
				try{
					while((len = is.read(buf))!=-1){
						log.info(new String(buf,0,len));
					}
				}catch(Exception e){
					log.info("返回内容接收完毕。");
				}
				log.info("发送完成。");
			}else{
				log.info("连接不存在，请先初始化连接。");
			}
		} catch (IOException e) {
			log.info(e.getMessage(),e);
		} 
	}
	
	public static void sendByteDataBySocket(String key,int timeout,byte[] info,Log log){
		try {
			if(cacheMap.containsKey(key)){
				Socket socket = cacheMap.get(key);
				socket.setSoTimeout(timeout*1000);
				OutputStream out = cacheOutput.get(key);
				InputStream is = cacheInput.get(key);
				log.info("发送内容："+ByteUtil.bytesToHexString(info));
				out.write(info);
				out.flush();
				byte[] buf = new byte[22];
				try{
					while(is.read(buf)!=-1){
						log.info("接收内容："+ByteUtil.bytesToHexString(buf));
					}
				}catch(Exception e){
					log.info("返回内容接收完毕。");
				}
				log.info("发送完成。");
			}else{
				log.info("连接不存在，请先初始化连接。");
			}
		} catch (IOException e) {
			log.info(e.getMessage(),e);
		} 
	}
	
	/**
	 * 用于安阳滑县2平台数据接收
	 * 特殊之处在于返回的字节需要转成字符
	 * @param key
	 * @param timeout
	 * @param info
	 * @param log
	 */
	public static void sendByteDataBySocket2(String key,int timeout,byte[] info,Log log){
		try {
			if(cacheMap.containsKey(key)){
				Socket socket = cacheMap.get(key);
				socket.setSoTimeout(timeout*1000);
				OutputStream out = cacheOutput.get(key);
				InputStream is = cacheInput.get(key);
				log.info("发送内容："+ByteUtil.bytesToHexString(info));
				out.write(info);
				out.flush();
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
				log.info("发送完成。");
			}else{
				log.info("连接不存在，请先初始化连接。");
			}
		} catch (IOException e) {
			log.info(e.getMessage(),e);
		} 
	}
	
public static void main(String[] args){
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				EquipmentData e = CommonUtil.getEquipmentDataInstance();
				e.setV_equipment_name("AZ000117110901");
				
				String info = CRC.getDataString4(e)+",END";
//				CommonUtil.sendDataToRemote("127.0.0.1",
//						8888, info, log);
				SocketUtil.getSocket("SX_XA_SYSTEM", "127.0.0.1", 9999);
				SocketUtil.sendDataBySocket("SX_XA_SYSTEM", 3,info, log);
			}
		}, 1000, 1*30*1000);
	}

}
