package com.test.random;

import java.util.Random;

public class TestRandom {
	/**
	 * 1）获得不重复的密码，把一个n个字符的密码序列循环n次，随机取其中一个字符进行序列内交换（打乱序列）
	 * 然后截取x个字符作为不重复密码
	 * 2）如果对重复性没有要求，则可以每次从序列中随机取一个字符，构成x个字符的密码序列
	 * @return
	 */
	public static int card() {
		int[] array = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		Random rand = new Random();
		for (int i = 10; i > 1; i--) {
			int index = rand.nextInt(i);
			int tmp = array[index];
			array[index] = array[i - 1];
			array[i - 1] = tmp;
		}
		int result = 0;
		for (int i = 0; i < 6; i++) {
			result = result * 10 + array[i];
		}
		return result;
	}
	
	//生成随机数字和字母,  
    public static String getStringRandom(int length) {  

        String val = "";  
        Random random = new Random();        
        //length为几位密码 
        for(int i = 0; i < length; i++) {          
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";  
            //输出字母还是数字  
            if( "char".equalsIgnoreCase(charOrNum) ) {  
                //输出是大写字母还是小写字母  
                int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;  
                val += (char)(random.nextInt(26) + temp);  
            } else if( "num".equalsIgnoreCase(charOrNum) ) {  
                val += String.valueOf(random.nextInt(10));  
            }  
        }  
        return val;  
    }
    /**
     * 获取一个包含大小写字母，数字和特殊符号的密码，并且每种元素至少有一个
     * @param len
     * @return
     */
    public static String getThreeRandom(int len){
    	String code = "";
    	Random random = new Random();
    	//先分别随机得到4种字符的序列
    	char[] lowchars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    	char[] upchars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    	char[] numchars = "0123456789".toCharArray();
    	char[] spchars = "!@#$%^&*()".toCharArray();
    	StringBuffer sb = new StringBuffer("");
    	if(len>4){
    		//随机获取各种字符的个数
    		int lowlen = random.nextInt(len-3)+1;
    		int uplen = random.nextInt(len-lowlen-2)+1;
    		int numlen = random.nextInt(len-lowlen-uplen-1)+1;
    		int splen = len-lowlen-uplen-numlen;
//    		System.out.println(lowlen+"-"+uplen+"-"+numlen+"-"+splen);
    		//根据字符个数，随机获取各种字符
    		for(int i=0;i<lowlen;i++){
    			sb.append(lowchars[random.nextInt(25)]);
    		}
    		for(int i=0;i<uplen;i++){
    			sb.append(upchars[random.nextInt(25)]);
    		}
    		for(int i=0;i<numlen;i++){
    			sb.append(numchars[random.nextInt(9)]);
    		}
    		for(int i=0;i<splen;i++){
    			sb.append(spchars[random.nextInt(9)]);
    		}
    		System.out.println(sb.toString());
    		//将字符序列打乱
    		char[] codechars = sb.toString().toCharArray();
    		for(int i=len;i>1;i--){
    			int index = random.nextInt(i);
    			char codechar = codechars[index];
    			codechars[index] = codechars[i-1];
    			codechars[i-1] = codechar;
    		}
    		code = new String(codechars);
    	}else{
    		code = "位数太少！";
    	}
    	return code;
    }

	public static void main(String[] args) {
		System.out.println(getThreeRandom(14));
	}
}
