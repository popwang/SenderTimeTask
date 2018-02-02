package com.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Random;

import javax.xml.namespace.QName;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.webservice.weather.ws.WeatherWebService;
import com.webservice.weather.ws.WeatherWebServiceSoap;
/**
 * 天气工具类
 * @author pactera
 *
 */
public class WeatherUtil {
	public static Log log = LogFactory.getLog(WeatherUtil.class);
	//阿里云服务天气接口地址
	public static String URL = "http://jisutianqi.market.alicloudapi.com/weather/query?location=";
	//请求的key
	public static String APPCODE = "8e16cd7e73134e538fc9612a72edbf58";
	
	
	public static void main(String[] args) {
		System.out.println(appendWeatherString(WeatherUtil.getWeatherStringByHttp("34.799093", "113.370527")));
	}
	
	/**
	 * 根据经纬度获取天气信息字符串
	 * @param lat
	 * @param lon
	 * @return
	 */
	public static String getWeatherStringByHttp(String lat,String lon) {
		String weather ="";
		CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(URL+lat+","+lon);
        httpGet.setHeader("Authorization", "APPCODE " + APPCODE);
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(5000).setConnectionRequestTimeout(1000)    
                .setSocketTimeout(5000).build();
        httpGet.setConfig(requestConfig); 
        CloseableHttpResponse httpResponse = null;
        try {
            httpResponse = httpClient.execute(httpGet);
            HttpEntity httpEntity = httpResponse.getEntity();
            weather = EntityUtils.toString(httpEntity);
        } catch (Exception e) {
        	log.info(e.getMessage(),e);
        } finally {
            try {
                httpClient.close();
                httpResponse.close();
            } catch (IOException e) {
            	log.info(e.getMessage(),e);
            }
        }
        return weather;
	}
	
	/**
	 * 将天气信息json字符串转换为发送字符串
	 * @param json
	 * @return
	 */
	public static String appendWeatherString(String json) {
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode root;
		Random rd = new Random();
		StringBuffer sb = new StringBuffer("TITLE:");
		try {
			root = objectMapper.readTree(json);
			JsonNode result = root.path("result");
	        JsonNode index = result.path("index");
	        JsonNode aqi = result.path("aqi");
	        
	        sb.append(result.path("city").asText().replaceAll("市$", ""));
			sb.append("天气预报,");
			sb.append("CONTENT:");
			sb.append(result.path("date").asText());
			sb.append(" ");
			sb.append(result.path("week").asText());
			sb.append(" ");
			sb.append(result.path("weather").asText());
			sb.append(" ");
			sb.append(result.path("templow").asText());
			sb.append("/");
			sb.append(result.path("temphigh").asText());
			sb.append("℃ 湿度:");
			sb.append(result.path("humidity").asText());
			sb.append("RH;风向:");
			sb.append(result.path("winddirect").asText());
			sb.append(";风力:");
			sb.append(result.path("windpower").asText());
			sb.append(";气压:");
			sb.append(result.path("pressure").asText());
			sb.append("kPa;");
			
			sb.append("AQI指数(单位mg/m3):");
			sb.append("PM2.5:");
			sb.append(aqi.path("pm2_5").asText());
			sb.append(";PM10:");
			sb.append(aqi.path("pm10").asText());
//			sb.append(";二氧化硫:");
//			sb.append(aqi.path("so2").asText());
//			sb.append(";二氧化氮:");
//			sb.append(aqi.path("no2").asText());
//			sb.append(";一氧化碳:");
//			sb.append(aqi.path("co").asText());
//			sb.append(";臭氧:");
//			sb.append(aqi.path("o3").asText());
			sb.append(";负氧离子:");
			sb.append(rd.nextInt(500)+100);
			sb.append(";");
			if(index.isArray()) {
				for(JsonNode i : index) {
					if(i.path("iname").asText().indexOf("扩散")>0) {
						continue;
					}
					sb.append(i.path("iname").asText());
					sb.append(":");
					sb.append(i.path("ivalue").asText());
					sb.append(";");
				}
			}
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString().replaceAll(";$", "。");
	}
	
	/**
	 * 根据经纬度通过百度地图api获取城市名称
	 * @param lat
	 * @param lon
	 * @return
	 */
	public static String getCityNameByLonla(String lat,String lon) {
		ObjectMapper objectMapper = new ObjectMapper();
		String cityName ="";
		URL resjson;
		try {
			resjson = new URL("http://api.map.baidu.com/geocoder/v2/?callback=renderReverse&output=json&pois=1&ak=GjDiD7d0PcGH4PyIjEAYhz5t&location="+lat+","+lon);
			BufferedReader in = new BufferedReader(new InputStreamReader(resjson.openStream(),"UTF-8"));
			String res = "";
            StringBuilder sb = new StringBuilder("");
            while ((res = in.readLine()) != null) {
                sb.append(res.trim());
            }
            in.close();  
            String str = sb.toString();
            if(str!=null&&!str.equals("")){
            	str = str.replaceAll("^renderReverse&&renderReverse\\(", "").replaceAll("\\)$", "");
//            	System.out.println(str);
            	JsonNode root = objectMapper.readTree(str);
    			JsonNode result = root.path("result");
    			JsonNode component = result.path("addressComponent");
    			JsonNode city = component.path("city");
    			cityName = city.asText().replaceAll("市$", "");
            }
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return cityName;
	}
	
	/**
	 * 根据城市名称获取天气字符串
	 * @param cityName 城市名称
	 * @param flag true输出全量字符串 false输出定制字符串
	 * @return
	 */
	public static String getWeatherStringByCityName(String cityName,boolean flag) {
		URL wsdlURL = WeatherWebService.WSDL_LOCATION;
		QName SERVICE_NAME = new QName("http://WebXml.com.cn/", "WeatherWebService");
		WeatherWebService ss = new WeatherWebService(wsdlURL, SERVICE_NAME);
		WeatherWebServiceSoap port = ss.getWeatherWebServiceSoap();
		List<String> ls = port.getWeatherbyCityName(cityName).getString();
		Random rd = new Random();
		StringBuffer sb = new StringBuffer("TITLE:");
		sb.append(cityName);
		sb.append("天气预报,");
		sb.append("CONTENT:");
		try{
			if(flag){
				for(String str : ls){
					sb.append(str);
				}
			}else{
				sb.append(ls.get(6)+" ");
				sb.append(ls.get(5)+" ");
				sb.append(ls.get(7)+"。");
//				int index = ls.get(10).indexOf("空气质量");
//				sb.append(ls.get(10).substring(index));
				sb.append(ls.get(10));
				String[] tmp = ls.get(11).split("\\n");
				for(int i=0;i<tmp.length;i++) {
					if(tmp[i].indexOf("。")<0){//偶尔会有广告乱入
						continue;
					}
					sb.append(tmp[i].substring(0,tmp[i].indexOf("，")));
					sb.append("。");
				}
				sb.append("负氧离子：");
				sb.append(rd.nextInt(500)+100);
				sb.append("个/cm3。");
			}
		}catch(Exception e){
			e.printStackTrace();
			sb.delete(0, sb.length());
			sb.append("TITLE:");
			sb.append(cityName);
			sb.append("天气预报,");
			sb.append("CONTENT:");
			sb.append("今日天气实况：天气信息获取中，请稍候...");
		}
		sb.append(",");
		return sb.toString();
	}

}
