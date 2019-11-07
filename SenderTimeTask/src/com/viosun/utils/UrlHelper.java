package com.viosun.utils;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.*;

public class UrlHelper {
    /**
     * 解析出url请求的路径，包括页面
     *
     * @param strURL url地址
     * @return url路径
     */
    public static String getPage(String strURL) {
        String strPage = null;
        strURL = strURL.trim().toLowerCase();
        String[] arrSplit = strURL.split("[?]");
        if (strURL.length() > 0) {
            strPage = arrSplit[0];
        }
        return strPage;
    }

    /**
     * 去掉url中的路径，留下请求参数部分
     *
     * @param strURL url地址
     * @return url请求参数部分
     */
    private static String TruncateUrlPage(String strURL) {
        String strAllParam = null;
        String[] arrSplit = null;

        strURL = strURL.trim();

        arrSplit = strURL.split("[?]");
        if (strURL.length() > 1) {
            if (arrSplit.length > 1) {
                if (arrSplit[1] != null) {
                    strAllParam = arrSplit[1];
                }
            }
        }

        return strAllParam;
    }

    /**
     * 解析出url参数中的键值对
     * 如 "index.jsp?Action=del&id=123"，解析出Action:del,id:123存入map中
     *
     * @param URL url地址
     * @return url请求参数部分
     */
    public static Map<String, String> getParameter(String URL) {
        Map<String, String> mapRequest = new HashMap<String, String>();

        String strUrlParam = TruncateUrlPage(URL);
        if (strUrlParam == null) {
            return mapRequest;
        }

        //每个键值为一组 www.2cto.com
        String[] arrSplit = strUrlParam.split("[&]");
        for (String strSplit : arrSplit) {
            int index = strSplit.indexOf("=");
            if (index <= 0) continue;

            String key = strSplit.substring(0, index);
            String value = strSplit.substring(index + 1);
            try {
                value = URLDecoder.decode(value, "UTF-8");
            } catch (Exception err) {
            }

            if (value != "") {
                mapRequest.put(key, value);
            }
        }
        return mapRequest;
    }

    private static String toParameterString(Map<String, String> params) {

        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);

        String prestr = "";

        try {
            for (int i = 0; i < keys.size(); i++) {
                String key = keys.get(i);
                String value = params.get(key);

                if (i == keys.size() - 1) {//拼接时，不包括最后一个&字符
                    prestr = prestr + key + "=" + URLEncoder.encode(value, "UTF-8");
                } else {
                    prestr = prestr + key + "=" + URLEncoder.encode(value, "UTF-8") + "&";
                }
            }
        } catch (Exception err) {
        }

        return prestr;
    }

    public static String toUrlString(String url, Map<String, String> params) {
        String paraString = toParameterString(params);
        if (paraString.equals("")) {
            return url;
        } else {
            return url + "?" + paraString;
        }
    }
}
