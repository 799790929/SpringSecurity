package com.byron.ss.common.util;


import org.apache.commons.beanutils.BeanUtilsBean;
/**
 * 用于分页组件覆盖的类,新的分页组件覆盖此类的bindPageRequest()方法以适合不同的分页创建
 * @author byron
 */
public class PageRequestFactory {
    public static final int MAX_PAGE_SIZE = 1000;
    
    static BeanUtilsBean beanUtils = new BeanUtilsBean();
    static {
    	//用于注册日期类型的转换
    	String[] datePatterns = new String[] {"yyyy-MM-dd","yyyy-MM-dd HH:mm:ss","yyyy-MM-dd HH:mm:ss.SSS","HH:mm:ss"};
    	ConvertRegisterHelper.registerConverters(beanUtils.getConvertUtils(),datePatterns);
    	
    }

    /*public static PageRequest bindPageRequest(PageRequest pageRequest,HttpServletRequest request){
        return bindPageRequest(pageRequest, request, null);
    }
    
    public static PageRequest bindPageRequest(PageRequest pageRequest,HttpServletRequest request,String defaultSortColumns){
        return bindPageRequest(pageRequest, request, defaultSortColumns, BaseQuery.DEFAULT_PAGE_SIZE);
    }
    
    *//**
     * 绑定PageRequest的属性值
     *//*
    public static PageRequest bindPageRequest(PageRequest pageRequest, HttpServletRequest request,String defaultSortColumns, int defaultPageSize) {
	    	try {
	    		Map params = WebUtils.getParametersStartingWith(request, "");
	    		beanUtils.copyProperties(pageRequest, params);
		    } catch (IllegalAccessException e) {
		    	throw new IllegalArgumentException("beanUtils.copyProperties() error",e);
			} catch (InvocationTargetException e) {
				throw new IllegalArgumentException("beanUtils.copyProperties() error",e.getTargetException());
			}
	        
	        pageRequest.setPageNumber(ServletRequestUtils.getIntParameter(request, "pageNumber", 1));
	        pageRequest.setPageSize(ServletRequestUtils.getIntParameter(request, "pageSize", defaultPageSize));
	        pageRequest.setSortColumns(ServletRequestUtils.getStringParameter(request, "sortColumns",defaultSortColumns));
	        
	        if(pageRequest.getPageSize() > MAX_PAGE_SIZE) {
	            pageRequest.setPageSize(MAX_PAGE_SIZE);
	        }
	        return pageRequest;
    }*/

}
