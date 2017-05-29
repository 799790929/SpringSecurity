package com.byron.ss.common.base;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.springframework.util.Assert;

import cn.org.rapid_framework.beanutils.BeanUtils;
import cn.org.rapid_framework.page.Page;
import cn.org.rapid_framework.page.PageRequest;
import cn.org.rapid_framework.util.ObjectUtils;

import com.byron.ss.common.util.ConvertRegisterHelper;
import com.byron.ss.common.util.PageRequestFactory;
import com.opensymphony.xwork2.ActionSupport;

public abstract class BaseStruts2Action extends ActionSupport implements RequestAware {
	protected final static String CREATED_SUCCESS = "创建成功";
	protected final static String UPDATE_SUCCESS = "更新成功";
	protected final static String DELETE_SUCCESS = "删除成功";
	
	protected String basePath ;//获取URL
	protected String topPath;//头部页面
	protected String footerPath;//底部页面
	protected String leftPath;//右侧菜单
	protected String boxPath;//中间内容页面
	protected String rightPath;//右侧也页面
	protected String menu;//头部大菜单标志
	protected String indexMenu;//左部小菜单标志

	
	protected static final String systemLeft = "/pages/leftSystemManagement.jsp";
	
	protected Map requestMap = null;
	static {
		//注册converters
		ConvertRegisterHelper.registerConverters();
	}
	
	public void copyProperties(Object target,Object source) {
		BeanUtils.copyProperties(target, source);
	}

	public <T> T copyProperties(Class<T> destClass,Object orig) {
		return BeanUtils.copyProperties(destClass, orig);
	}
	
	public void setRequest(Map request) {
		this.requestMap = request;
	}

	public void savePage(Page page,PageRequest pageRequest){
		savePage("",page,pageRequest);
	}
	
	/**
	 * 用于一个页面有多个extremeTable是使用
	 * @param tableId 等于extremeTable的tableId属性
	 */
	public void savePage(String tableId,Page page,PageRequest pageRequest){
		Assert.notNull(tableId,"tableId must be not null");
		Assert.notNull(page,"page must be not null");
		
		getRequest().setAttribute(tableId+"page", page);
		getRequest().setAttribute(tableId+"totalRows", new Integer(page.getTotalCount()));
		getRequest().setAttribute(tableId+"pageRequest", pageRequest);
		getRequest().setAttribute(tableId+"query", pageRequest);
	}
	
	public <T extends PageRequest> T newQuery(Class<T> queryClazz,String defaultSortColumns){
		PageRequest query = PageRequestFactory.bindPageRequest(org.springframework.beans.BeanUtils.instantiateClass(queryClazz),ServletActionContext.getRequest(),defaultSortColumns);
		return (T)query;
    }
	
	public boolean isNullOrEmptyString(Object o) {
		return ObjectUtils.isNullOrEmptyString(o);
	}
	
	public HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}
	
	public HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}
	public static String indexPage = "/WEB-INF/ss/index.jsp";
	private String sfSelectIndex;
	private String menuSelectIndex;
	private String mainPage;
	private String contentPage;
	
	public String getMainPage() {
		return mainPage;
	}

	public void setMainPage(String mainPage) {
		this.mainPage = mainPage;
	}

	public String getContentPage() {
		return contentPage;
	}

	public void setContentPage(String contentPage) {
		this.contentPage = contentPage;
	}

	public String getSfSelectIndex() {
		return sfSelectIndex;
	}

	public void setSfSelectIndex(String sfSelectIndex) {
		this.sfSelectIndex = sfSelectIndex;
	}

	public String getMenuSelectIndex() {
		return menuSelectIndex;
	}
	public void setMenuSelectIndex(String menuSelectIndex) {
		this.menuSelectIndex = menuSelectIndex;
	}
	
	/**
	 * 直接输入到页面
	 * @param s
	 */
	public void out(String s){
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.print(s);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(null != out)
				out.close();
		}
	}
	
	protected void doInitTMenu() {
		getRequest().setAttribute("menu", "system");
	}
	
	
	
	
	
	
	
	
	public String getRightPath() {
		return rightPath;
	}

	public void setRightPath(String rightPath) {
		this.rightPath = rightPath;
	}

	public String getBoxPath() {
		if(boxPath==null || boxPath.length()==0){
			boxPath="/box.jsp";
		}
		return boxPath;
	}

	public void setBoxPath(String boxPath) {
		this.boxPath = boxPath;
	}

	public String getTopPath() {
		if(topPath==null || topPath.length()==0){
			topPath="/top.jsp";
		}
		return topPath;
	}

	public void setTopPath(String topPath) {
		this.topPath = topPath;
	}
	public String getMenu() {
		return menu;
	}
	public void setMenu(String menu) {
		this.menu = menu;
	}
	public String getIndexMenu() {
		return indexMenu;
	}
	public void setIndexMenu(String indexMenu) {
		this.indexMenu = indexMenu;
	}

	public String getFooterPath() {
		if(footerPath==null || footerPath.length()==0){
			footerPath="/footer.jsp";
		}
		return footerPath;
	}

	public void setFooterPath(String footerPath) {
		this.footerPath = footerPath;
	}

	public String getLeftPath() {
		if(leftPath==null || leftPath.length()==0){
			leftPath="/left.jsp";
		}
		return leftPath;
	}

	public void setLeftPath(String leftPath) {
		this.leftPath = leftPath;
	}

	public String getBasePath() {
		if(basePath==null || basePath.length()==0){ 
		String path = getRequest().getContextPath();
		basePath =this.getRequest().getScheme() + "://"
				+ getRequest().getServerName() + ":" + getRequest().getServerPort()
				+ path + "/";
		}
		return basePath;
	}
}

