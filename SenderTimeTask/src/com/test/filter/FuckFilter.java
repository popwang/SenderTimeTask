package com.test.filter;

public class FuckFilter implements Filter {

	@Override
	public void doFilter(Request request, Response response, Filter chainFilter) {
		String requestFilterStr = request.getRequestStr()
                .replace("ÄáÂê", "xx")
                .replace("ÄãÂè", "xx");
        request.setRequestStr(requestFilterStr+ "|FuckFilter");

        chainFilter.doFilter(request, response, chainFilter);
        
        String responseFilterStr = response.getResponseStr()
                .replace("ÄáÂê", "++")
                .replace("ÄãÂè", "++");
        response.setResponseStr(responseFilterStr + "|FuckFilter");
    }
}
