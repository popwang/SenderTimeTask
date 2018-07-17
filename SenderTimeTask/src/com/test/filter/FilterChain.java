package com.test.filter;

import java.util.ArrayList;
import java.util.List;

public class FilterChain implements Filter {

	public List<Filter> mFilters = new ArrayList<>();
	
	public int index = 0;
	
	public FilterChain addFilter(Filter filter){
		this.mFilters.add(filter);
		return this;
	}
	
	@Override
	public void doFilter(Request request, Response response, Filter chain) {
		if(index==this.mFilters.size()) return;
		Filter filter = this.mFilters.get(index);
		index++;
		filter.doFilter(request, response, this);
	}
}
