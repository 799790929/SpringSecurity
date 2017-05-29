package cn.sf.ss.security.tool;

/*
 * ����ӿ�����ǰspring�汾�еģ����ڵ�spring�汾�в����ڣ�
 * ������Ŀ��Ҫ��ʹ�÷��㣬�ʼ��뵽��Ŀ���С�
 */
public abstract interface UrlMatcher {
	public abstract Object compile(String paramString);
	
	public abstract boolean pathMatchesUrl(Object paramObject, String paramString);
	
	public abstract String getUniversalMatchPattern();
	
	public abstract boolean requiresLowerCaseUrl();
}
