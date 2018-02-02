package com.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ExecuteBat {

	public static void main(String[] args) {
		
		try {
			String file = "c:\\install.bat";
			BufferedReader br = new BufferedReader(new FileReader(new File(file)));
			String tmp = "";
			int i = 0;
			while((tmp=br.readLine())!=null){
				i++;
				System.out.println("当前执行命令："+tmp);
				Runtime.getRuntime().exec(tmp);
			}
			System.out.println("所有命令执行完成，共执行："+i+"条！");
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
