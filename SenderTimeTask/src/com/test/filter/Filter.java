package com.test.filter;

public interface Filter {
	void doFilter(Request request, Response response, Filter chainFilter);
}
