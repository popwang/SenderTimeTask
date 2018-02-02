package com.test;

public class TestIndex {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str = "SD,INFO,00000400,TITLE:郑州天气预报,CONTENT:10月24日 晴转多云 11℃/19℃ 东南风转南风微风。今日天气实况：空气质量：中。紫外线指数：中等。感冒指数：较易发。运动指数：适宜。空气污染指数：中。,END";
		System.out.println(str.getBytes().length);
		
		StringBuffer sb = new StringBuffer();
		System.out.println(sb.toString().equals(""));
		
		System.out.println("612000235".substring(3));
		
		
	}

}
