package com.test.random;

import java.util.Random;

public class TestRandom {
	/**
	 * 1����ò��ظ������룬��һ��n���ַ�����������ѭ��n�Σ����ȡ����һ���ַ����������ڽ������������У�
	 * Ȼ���ȡx���ַ���Ϊ���ظ�����
	 * 2��������ظ���û��Ҫ�������ÿ�δ����������ȡһ���ַ�������x���ַ�����������
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
	
	//����������ֺ���ĸ,  
    public static String getStringRandom(int length) {  

        String val = "";  
        Random random = new Random();        
        //lengthΪ��λ���� 
        for(int i = 0; i < length; i++) {          
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";  
            //�����ĸ��������  
            if( "char".equalsIgnoreCase(charOrNum) ) {  
                //����Ǵ�д��ĸ����Сд��ĸ  
                int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;  
                val += (char)(random.nextInt(26) + temp);  
            } else if( "num".equalsIgnoreCase(charOrNum) ) {  
                val += String.valueOf(random.nextInt(10));  
            }  
        }  
        return val;  
    }
    /**
     * ��ȡһ��������Сд��ĸ�����ֺ�������ŵ����룬����ÿ��Ԫ��������һ��
     * @param len
     * @return
     */
    public static String getThreeRandom(int len){
    	String code = "";
    	Random random = new Random();
    	//�ȷֱ�����õ�4���ַ�������
    	char[] lowchars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    	char[] upchars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    	char[] numchars = "0123456789".toCharArray();
    	char[] spchars = "!@#$%^&*()".toCharArray();
    	StringBuffer sb = new StringBuffer("");
    	if(len>4){
    		//�����ȡ�����ַ��ĸ���
    		int lowlen = random.nextInt(len-3)+1;
    		int uplen = random.nextInt(len-lowlen-2)+1;
    		int numlen = random.nextInt(len-lowlen-uplen-1)+1;
    		int splen = len-lowlen-uplen-numlen;
//    		System.out.println(lowlen+"-"+uplen+"-"+numlen+"-"+splen);
    		//�����ַ������������ȡ�����ַ�
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
    		//���ַ����д���
    		char[] codechars = sb.toString().toCharArray();
    		for(int i=len;i>1;i--){
    			int index = random.nextInt(i);
    			char codechar = codechars[index];
    			codechars[index] = codechars[i-1];
    			codechars[i-1] = codechar;
    		}
    		code = new String(codechars);
    	}else{
    		code = "λ��̫�٣�";
    	}
    	return code;
    }

	public static void main(String[] args) {
		System.out.println(getThreeRandom(14));
	}
}
