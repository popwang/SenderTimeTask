package com.test.filter;

public class SensitiveFilter implements Filter {

	@Override
	public void doFilter(Request request, Response response, Filter chainFilter) {
		String requestFilterStr = request.getRequestStr()
                .replace("���׹�", "flg")
                .replace("����", "zf");
        request.setRequestStr(requestFilterStr+"|SensitiveFilter");

        chainFilter.doFilter(request, response, chainFilter);

        String responseFilterStr = response.getResponseStr()
                .replace("���׹�", "---")
                .replace("����", "--");
        response.setResponseStr(responseFilterStr + "|SensitiveFilter");
	}

}
