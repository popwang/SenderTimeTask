package com.test.filter;

public class Main {

	public static void main(String[] args) {
		String content = "<scrpit> 法伦功一定要灭掉，尼玛的，你妈的。中国政府真的太好了，呵呵，呵呵";
        Request request = new Request();
        request.setRequestStr(content);
        Response response = new Response();
        response.setResponseStr(content);
        //新建一个『过滤链条』
        FilterChain filterChain = new FilterChain();
        //在过滤链条中添加过滤规则
        filterChain.addFilter(new FuckFilter())
                .addFilter(new SensitiveFilter());

        //过滤后的内容
        filterChain.doFilter(request, response, filterChain);
        //输出内容
        System.out.println(request.getRequestStr());
        System.out.println(response.getResponseStr());
	}
	/**
	 * 过滤器链的使用：
	 * 1.先定义一个过滤器类的接口，包含一个doFilter(request,response,filterChain);
	 * 2.定义一个filterChain类，实现filter接口，filterChain类内部维护一个filter的集合成员变量filterList；
	 * filterChain的dofilter方法则是依次取出filterList中的filter，做dofilter调用；
	 * 3.filter接口的实现类在实现doFilter方法时先使用参数中request做操作；在调用参数中的filterChain的dofilter方法；
	 * 然后使用参数中的response做操作；
	 */
}
