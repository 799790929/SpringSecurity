/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2014
 */

package com.byron.ss.action;

import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import cn.org.rapid_framework.page.Page;
import cn.org.rapid_framework.web.scope.Flash;
import cn.org.rapid_framework.web.util.HttpUtils;

import com.byron.ss.common.base.BaseStruts2Action;
import com.byron.ss.model.Resources;
import com.byron.ss.model.Roles;
import com.byron.ss.model.RolesResources;
import com.byron.ss.service.ResourcesManager;
import com.byron.ss.service.RolesManager;
import com.byron.ss.service.RolesResourcesManager;
import com.byron.ss.vo.query.RolesResourcesQuery;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

/**
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
 */


public class RolesResourcesAction extends BaseStruts2Action implements Preparable,ModelDriven{
	protected Logger log = Logger.getLogger(this.getClass());
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	//forward paths
	protected static final String QUERY_JSP = "/ss/RolesResources/query.jsp";
	protected static final String LIST_JSP= "/ss/RolesResources/list.jsp";
	protected static final String CREATE_JSP = "/ss/RolesResources/create.jsp";
	protected static final String EDIT_JSP = "/ss/RolesResources/edit.jsp";
	protected static final String SHOW_JSP = "/ss/RolesResources/show.jsp";
	//redirect paths,startWith: !
	protected static final String LIST_ACTION = "!/ss/RolesResources/list.do";
	
	private RolesResourcesManager rolesResourcesManager;
	
	private RolesManager rolesManager;
	private ResourcesManager resourcesManager;
	
	final static String RESOURCEID = "ID_RESOURCE";
	
	public void setRolesManager(RolesManager rolesManager) {
		this.rolesManager = rolesManager;
	}

	public void setResourcesManager(ResourcesManager resourcesManager) {
		this.resourcesManager = resourcesManager;
	}
	
	private RolesResources rolesResources;
	java.lang.String id = null;
	private String[] items;

	public void prepare() throws Exception {
		if (isNullOrEmptyString(id)) {
			rolesResources = new RolesResources();
		} else {
			rolesResources = (RolesResources)rolesResourcesManager.getById(id);
		}
	}
	
	/** 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,注意大小写 */
	public void setRolesResourcesManager(RolesResourcesManager manager) {
		this.rolesResourcesManager = manager;
	}	
	
	public Object getModel() {
		return rolesResources;
	}
	
	public void setId(java.lang.String val) {
		this.id = val;
	}

	public void setItems(String[] items) {
		this.items = items;
	}
	
	/** 执行搜索 */
	public String list() {
		RolesResourcesQuery query = newQuery(RolesResourcesQuery.class,DEFAULT_SORT_COLUMNS);
		
		Page page = rolesResourcesManager.findPage(query);
		savePage(page,query);
		return LIST_JSP;
	}
	
	/** 查看对象*/
	public String show() {
		return SHOW_JSP;
	}
	
	/** 进入新增页面*/
	public String create() {
		return CREATE_JSP;
	}
	
	/** 保存新增对象 */
	public String save() {
		rolesResourcesManager.save(rolesResources);
		Flash.current().success(CREATED_SUCCESS); //存放在Flash中的数据,在下一次http请求中仍然可以读取数据,error()用于显示错误消息
		return LIST_ACTION;
	}
	
	/**进入更新页面*/
	public String edit() {
		return EDIT_JSP;
	}
	
	/**保存更新对象*/
	public String update() {
		rolesResourcesManager.update(this.rolesResources);
		Flash.current().success(UPDATE_SUCCESS);
		return LIST_ACTION;
	}
	
	/**删除对象*/
	public String delete() {
		for(int i = 0; i < items.length; i++) {
			Hashtable params = HttpUtils.parseQueryString(items[i]);
			java.lang.String id = new java.lang.String((String)params.get("id"));
			rolesResourcesManager.removeById(id);
		}
		Flash.current().success(DELETE_SUCCESS);
		return LIST_ACTION;
	}
	

	public String initPage() {
		HttpServletRequest request = this.getRequest();
		
		String roleid = request.getParameter("roleid");
		Roles role = null;
		if(null != roleid)
			role = rolesManager.getById(roleid);
		List<Roles> roles = rolesManager.findAll();
		List<Resources> resources = null;
		if(null != roles && roles.size() > 0) {
			if(null == role)
				role = roles.get(0);
			String hql = "from com.byron.ss.model.Resources where id in (select resourceId from com.byron.ss.model.RolesResources where roleId='" + role.getId() + "')";
			resources = resourcesManager.getEntityDao().executeFind(hql, null);
		}
		
		request.setAttribute("roles", roles);
		request.setAttribute("resources", resources);
		request.setAttribute("role", role);
		this.setSfSelectIndex("roleresources");
		String page = "/ss/RolesResources/roleresources.jsp";
		this.setMainPage(page);
		return indexPage;
	}
	
	/**
	 * 分配资源页面
	 * @return
	 */
	public String doSavePddage() {
		HttpServletRequest request = this.getRequest();
		
		String roleid = request.getParameter("roleid");
		Roles role = null;
		if(null != roleid)
			role = rolesManager.getById(roleid);
		List<Roles> roles = rolesManager.findAll();
		List<Resources> resources = null;
		if(null != roles && roles.size() > 0) {
			if(null == role)
				role = roles.get(0);
			String hql = "from com.byron.ss.model.Resources where id not in (select resourceId from com.byron.ss.model.RolesResources where roleId='" + role.getId() + "')";
			resources = resourcesManager.getEntityDao().executeFind(hql, null);
		}
		
		request.setAttribute("roles", roles);
		request.setAttribute("resources", resources);
		request.setAttribute("role", role);
		this.setSfSelectIndex("roleresources");
		String page = "/ss/RolesResources/doSavePage.jsp";
		this.setMainPage(page);
		return indexPage;
	}
	
	/**
	 * 异步解除关系
	 */
	public void doDeleteSyn() {
		HttpServletRequest request = this.getRequest();
		String msg = "删除成功";
		String roleid = request.getParameter("roleid");
		String resourceid = request.getParameter("resourceid");
		log.info("\nroleid:" + roleid);
		log.info("\nresourceid:" + resourceid);
		String[] fieldNames = {"roleId", "resourceId"};
		Object[] fieldValues = {roleid, resourceid};
		List<RolesResources> rolesResources = rolesResourcesManager.getEntityDao().findBy(fieldNames, fieldValues, "");
		if(null != rolesResources && rolesResources.size() > 0) {
			RolesResources po = rolesResources.get(0);
			if(null != po) {
				try {
					rolesResourcesManager.removeById(po.getId());
				} catch(Exception e) {
					msg = e.getMessage();
				}
			}
		}
		out(msg);
	}
	
	/**
	 * 解除关系
	 */
	public String doDelete() {
		HttpServletRequest request = this.getRequest();
		String msg = "删除成功";
		String roleid = request.getParameter("roleid");
		String resourceid = request.getParameter("resourceid");
		log.info("\nroleid:" + roleid);
		log.info("\nresourceid:" + resourceid);
		String[] fieldNames = {"roleId", "resourceId"};
		Object[] fieldValues = {roleid, resourceid};
		List<RolesResources> rolesResources = rolesResourcesManager.getEntityDao().findBy(fieldNames, fieldValues, "");
		if(null != rolesResources && rolesResources.size() > 0) {
			RolesResources po = rolesResources.get(0);
			if(null != po) {
				try {
					rolesResourcesManager.removeById(po.getId());
				} catch(Exception e) {
					msg = e.getMessage();
				}
			}
		}
		return initPage();
		//out(msg);
	}
	
	public String doRoleResourcesSave() {
		HttpServletRequest request = this.getRequest();
		String[] resourceids = request.getParameterValues("resourceid");
		String roleid = request.getParameter("roleid");
		log.info("\n" + roleid);
		Roles roles = rolesManager.getById(roleid);
		Resources resources = null;
		if(null != roles) {
			for(String resourceid : resourceids) {
				log.info("\n" + resourceid);
				resources = resourcesManager.getById(resourceid);
				if(null != resources) {
					RolesResources roleResources = new RolesResources();
					roleResources.setRoleId(roleid);
					roleResources.setRoleName(roles.getName());
					roleResources.setResourceId(resourceid);
					roleResources.setResourceName(resources.getName());
					rolesResourcesManager.save(roleResources);
				}
			}
		}
		
		String page = "/ss/RolesResources/roleresources.jsp";
		return doSavePage();
		//return page;
	}
	
	private com.byron.ss.security.MyInvocationSecurityMetadataSource mySecurityMetadataSource;
	
	public com.byron.ss.security.MyInvocationSecurityMetadataSource getMySecurityMetadataSource() {
		return mySecurityMetadataSource;
	}

	public void setMySecurityMetadataSource(
			com.byron.ss.security.MyInvocationSecurityMetadataSource mySecurityMetadataSource) {
		this.mySecurityMetadataSource = mySecurityMetadataSource;
	}

	public void ajaxRefreshMetaSource() {
		mySecurityMetadataSource.refreshResource();
		out("刷新成功");
	}
	//final static String rs_pk_resource = "ID_RESOURCE";
	public String doList() {
		/*HttpServletRequest request = this.getRequest();
		
		List<RolesResources> list = this.rolesResourcesManager.findAll();
		request.setAttribute("roleResourceList", list);*/
		HttpServletRequest request = this.getRequest();
		
		String roleid = request.getParameter("roleid");
		Roles role = null;
		if(null != roleid)
			role = rolesManager.getById(roleid);
		List<Roles> roles = rolesManager.findAll();
		List<Resources> resources = null;
		if(null != roles && roles.size() > 0) {
			if(null == role)
				role = roles.get(0);
			String hql = "from com.byron.ss.model.Resources where "+ Resources.rs_pk_resource +" in (select resourceId from com.byron.ss.model.RolesResources where roleId='" + role.getId() + "')";
			resources = resourcesManager.getEntityDao().executeFind(hql, null);
		}
		
		request.setAttribute("roles", roles);
		request.setAttribute("resources", resources);
		request.setAttribute("role", role);
		
		request.setAttribute("context", "/ss/RolesResources/role_resource_list.jsp");
		request.setAttribute("left", systemLeft);
		request.setAttribute("top", "/top.jsp");
		request.setAttribute("footer", "/footer.jsp");
		request.setAttribute("lMenuSelect", "roleresource");
		doInitTMenu();
		
		this.setBoxPath(this.getBoxPath());
		this.setLeftPath(systemLeft);
		this.setRightPath("/ss/RolesResources/role_resource_list.jsp");
		return "/index.jsp";
//		return "/myIndex.jsp";
	}
	
	/**
	 * 分配资源页面
	 * @return
	 */
	public String doSavePage() {
		HttpServletRequest request = this.getRequest();
		
		/*String roleid = request.getParameter("roleid");
		Roles role = null;
		if(null != roleid)
			role = rolesManager.getById(roleid);
		List<Roles> roles = rolesManager.findAll();
		List<Resources> resources = null;
		if(null != roles && roles.size() > 0) {
			if(null == role)
				role = roles.get(0);
			String hql = "from com.byron.ss.model.Resources where PK_RESOURCE not in (select resourceId from com.byron.ss.model.RolesResources where roleId='" + role.getId() + "')";
			resources = resourcesManager.getEntityDao().executeFind(hql, null);
		}*/
		
		List<Resources> list = this.rolesResourcesManager.findByPage(request);
		request.setAttribute("resources", list);
		
		/*request.setAttribute("roles", roles);
		request.setAttribute("resources", resources);
		request.setAttribute("role", role);
		
		request.setAttribute("context", "/ss/RolesResources/save_page.jsp");
		request.setAttribute("left", systemLeft);
		request.setAttribute("top", "/top.jsp");
		request.setAttribute("footer", "/footer.jsp");
		request.setAttribute("lMenuSelect", "roleresource");*/
		//return "/myIndex.jsp";
		return "/ss/RolesResources/savePageOpenWin.jsp";
	}
	
	public String doSave() {
		String msg = "success";
		HttpServletRequest request = this.getRequest();
		String[] resourceids = request.getParameterValues("resourceid");
		String roleid = request.getParameter("roleid");
		log.info("\n" + roleid);
		Roles roles = rolesManager.getById(roleid);
		Resources resources = null;
		if(null != roles) {
			if(null != resourceids && resourceids.length > 0) {
				
				for(String resourceid : resourceids) {
					log.info("\n" + resourceid);
					resources = resourcesManager.getById(resourceid);
					if(null != resources) {
						RolesResources roleResources = new RolesResources();
						roleResources.setRoleId(roleid);
						roleResources.setRoleName(roles.getName());
						roleResources.setResourceId(resourceid);
						roleResources.setResourceName(resources.getName());
						rolesResourcesManager.save(roleResources);
					} 
				}
			} else {
				msg = "请选择资源!";
			}
		} else {
			msg = "角色不存在!";
		}
		
		//String page = "/ss/RolesResources/roleresources.jsp";
		//return doSavePage();
		request.setAttribute("msg", msg);
		return "/ss/RolesResources/saveResult.jsp";
	}
	
	public void doSaveAjax() {
		String msg = "success";
		HttpServletRequest request = this.getRequest();
		String[] resourceids = request.getParameterValues("resourceid");
		String roleid = request.getParameter("roleid");
		log.info("\n" + roleid);
		Roles roles = rolesManager.getById(roleid);
		Resources resources = null;
		if(null != roles) {
			if(null != resourceids && resourceids.length > 0) {
				
				for(String resourceid : resourceids) {
					log.info("\n" + resourceid);
					resources = resourcesManager.getById(resourceid);
					if(null != resources) {
						RolesResources roleResources = new RolesResources();
						roleResources.setRoleId(roleid);
						roleResources.setRoleName(roles.getName());
						roleResources.setResourceId(resourceid);
						roleResources.setResourceName(resources.getName());
						rolesResourcesManager.save(roleResources);
					} 
				}
			} else {
				msg = "请选择资源!";
				out("{\"success\":false,\"message\":\"请选择资源\"}");
				return;
			}
		} else {
			msg = "角色不存在!";
			out("{\"success\":false,\"message\":\"角色不存在!\"}");
			return;
		}
		
		out("{\"success\":true,\"message\":\"保存成功\"}");
	}
	
	/**
	 * 解除关系
	 */
	public void doDeleteAjax() {
		String msg = "success";
		HttpServletRequest request = this.getRequest();
		
		String roleid = request.getParameter("roleid");
		String resourceid = request.getParameter("resourceid");
		log.info("\nroleid:" + roleid);
		log.info("\nresourceid:" + resourceid);
		String[] fieldNames = {"roleId", "resourceId"};
		Object[] fieldValues = {roleid, resourceid};
		List<RolesResources> rolesResources = rolesResourcesManager.getEntityDao().findBy(fieldNames, fieldValues, "");
		if(null != rolesResources && rolesResources.size() > 0) {
			RolesResources po = rolesResources.get(0);
			if(null != po) {
				try {
					rolesResourcesManager.removeById(po.getId());
				} catch(Exception e) {
					msg = e.getMessage();
				}
			}
		}
		//return doList();
		out(msg);
	}
}
