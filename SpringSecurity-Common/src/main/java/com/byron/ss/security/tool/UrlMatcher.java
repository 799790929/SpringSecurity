package com.byron.ss.security.tool;

/**
 * 
 * <p>ClassName: UrlMatcher</p>
 * <p>Description: TODO</p>
 * <p>Author: hadoop</p>
 * <p>Date: 2017-5-29</p>
 */
public abstract interface UrlMatcher {
	public abstract Object compile(String paramString);
	
	public abstract boolean pathMatchesUrl(Object paramObject, String paramString);
	
	public abstract String getUniversalMatchPattern();
	
	public abstract boolean requiresLowerCaseUrl();
}
