package com.byron.ss.common.base;

import cn.org.rapid_framework.page.PageRequest;

public class BaseQuery extends PageRequest implements java.io.Serializable {
	private static final long serialVersionUID = -360860474471966681L;
	public static final int DEFAULT_PAGE_SIZE = 10;
	
    static {
    }
    
	public BaseQuery() {
		setPageSize(DEFAULT_PAGE_SIZE);
	}
	  
}