package com.test.filter;

public class Main {

	public static void main(String[] args) {
		String content = "<scrpit> ���׹�һ��Ҫ���������ģ�����ġ��й��������̫���ˣ��Ǻǣ��Ǻ�";
        Request request = new Request();
        request.setRequestStr(content);
        Response response = new Response();
        response.setResponseStr(content);
        //�½�һ��������������
        FilterChain filterChain = new FilterChain();
        //�ڹ�����������ӹ��˹���
        filterChain.addFilter(new FuckFilter())
                .addFilter(new SensitiveFilter());

        //���˺������
        filterChain.doFilter(request, response, filterChain);
        //�������
        System.out.println(request.getRequestStr());
        System.out.println(response.getResponseStr());
	}

}
