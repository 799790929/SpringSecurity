/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2014
 */

package cn.sf.ss.action;

import java.util.Hashtable;
import java.util.List;

import javacommon.sf.ss.base.BaseStruts2Action;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import cn.org.rapid_framework.page.Page;
import cn.org.rapid_framework.web.scope.Flash;
import cn.org.rapid_framework.web.util.HttpUtils;
import cn.sf.ss.model.Groups;
import cn.sf.ss.model.GroupsRoles;
import cn.sf.ss.model.Roles;
import cn.sf.ss.service.GroupsManager;
import cn.sf.ss.service.GroupsRolesManager;
import cn.sf.ss.service.RolesManager;
import cn.sf.ss.vo.query.GroupsRolesQuery;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

/**
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
 */


public class GroupsRolesAction extends BaseStruts2Action implements Preparable,ModelDriven{
	protected Logger log = Logger.getLogger(this.getClass());
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	//forward paths
	protected static final String QUERY_JSP = "/ss/GroupsRoles/query.jsp";
	protected static final String LIST_JSP= "/ss/GroupsRoles/list.jsp";
	protected static final String CREATE_JSP = "/ss/GroupsRoles/create.jsp";
	protected static final String EDIT_JSP = "/ss/GroupsRoles/edit.jsp";
	protected static final String SHOW_JSP = "/ss/GroupsRoles/show.jsp";
	//redirect paths,startWith: !
	protected static final String LIST_ACTION = "!/ss/GroupsRoles/list.do";
	
	private GroupsRolesManager groupsRolesManager;
	
	private RolesManager rolesManager;
	private GroupsManager groupsManager;
	
	public void setRolesManager(RolesManager rolesManager) {
		this.rolesManager = rolesManager;
	}

	public void setGroupsManager(GroupsManager groupsManager) {
		this.groupsManager = groupsManager;
	}
	
	private GroupsRoles groupsRoles;
	java.lang.String id = null;
	private String[] items;

	public void prepare() throws Exception {
		if (isNullOrEmptyString(id)) {
			groupsRoles = new GroupsRoles();
		} else {
			groupsRoles = (GroupsRoles)groupsRolesManager.getById(id);
		}
	}
	
	/** 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,注意大小写 */
	public void setGroupsRolesManager(GroupsRolesManager manager) {
		this.groupsRolesManager = manager;
	}	
	
	public Object getModel() {
		return groupsRoles;
	}
	
	public void setId(java.lang.String val) {
		this.id = val;
	}

	public void setItems(String[] items) {
		this.items = items;
	}
	
	/** 执行搜索 */
	public String list() {
		GroupsRolesQuery query = newQuery(GroupsRolesQuery.class,DEFAULT_SORT_COLUMNS);
		
		Page page = groupsRolesManager.findPage(query);
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
		groupsRolesManager.save(groupsRoles);
		Flash.current().success(CREATED_SUCCESS); //存放在Flash中的数据,在下一次http请求中仍然可以读取数据,error()用于显示错误消息
		return LIST_ACTION;
	}
	
	/**进入更新页面*/
	public String edit() {
		return EDIT_JSP;
	}
	
	/**保存更新对象*/
	public String update() {
		groupsRolesManager.update(this.groupsRoles);
		Flash.current().success(UPDATE_SUCCESS);
		return LIST_ACTION;
	}
	
	/**删除对象*/
	public String delete() {
		for(int i = 0; i < items.length; i++) {
			Hashtable params = HttpUtils.parseQueryString(items[i]);
			java.lang.String id = new java.lang.String((String)params.get("id"));
			groupsRolesManager.removeById(id);
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
		List<Groups> groups = null;
		if(null != roles && roles.size() > 0) {
			if(null == role)
				role = roles.get(0);
			String hql = "from cn.sf.ss.model.Groups where id in (select groupId from cn.sf.ss.model.GroupsRoles where roleId='" + role.getId() + "')";
			groups = groupsManager.getEntityDao().executeFind(hql, null);
		}
		
		request.setAttribute("roles", roles);
		request.setAttribute("groups", groups);
		request.setAttribute("role", role);
		this.setSfSelectIndex("rolegroups");
		String page = "/ss/GroupsRoles/rolegroups.jsp";
		this.setMainPage(page);
		return indexPage;
	}
	
	/**
	 * 分配群组页面
	 * @return
	 */
	public String doSavePdddage() {
		HttpServletRequest request = this.getRequest();
		
		String roleid = request.getParameter("roleid");
		Roles role = null;
		if(null != roleid)
			role = rolesManager.getById(roleid);
		List<Roles> roles = rolesManager.findAll();
		List<Groups> groups = null;
		if(null != roles && roles.size() > 0) {
			if(null == role)
				role = roles.get(0);
			String hql = "from cn.sf.ss.model.Groups where not id in (select groupId from cn.sf.ss.model.GroupsRoles where roleId='" + role.getId() + "')";
			groups = groupsManager.getEntityDao().executeFind(hql, null);
		}
		
		request.setAttribute("roles", roles);
		request.setAttribute("groups", groups);
		request.setAttribute("role", role);
		this.setSfSelectIndex("rolegroups");
		String page = "/ss/GroupsRoles/doSavePage.jsp";
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
		String groupid = request.getParameter("groupid");
		log.info("\nroleid:" + roleid);
		log.info("\ngroupid:" + groupid);
		String[] fieldNames = {"roleId", "groupId"};
		Object[] fieldValues = {roleid, groupid};
		List<GroupsRoles> groupsRoles = groupsRolesManager.getEntityDao().findBy(fieldNames, fieldValues, "");
		if(null != groupsRoles && groupsRoles.size() > 0) {
			GroupsRoles po = groupsRoles.get(0);
			if(null != po) {
				try {
					groupsRolesManager.removeById(po.getId());
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
		String groupid = request.getParameter("groupid");
		log.info("\nroleid:" + roleid);
		log.info("\ngroupid:" + groupid);
		String[] fieldNames = {"roleId", "groupId"};
		Object[] fieldValues = {roleid, groupid};
		List<GroupsRoles> groupsRoles = groupsRolesManager.getEntityDao().findBy(fieldNames, fieldValues, "");
		if(null != groupsRoles && groupsRoles.size() > 0) {
			GroupsRoles po = groupsRoles.get(0);
			if(null != po) {
				try {
					groupsRolesManager.removeById(po.getId());
				} catch(Exception e) {
					msg = e.getMessage();
				}
			}
		}
		return initPage();
		//out(msg);
	}
	
	public String doRoleGroupsSave() {
		HttpServletRequest request = this.getRequest();
		String[] groupids = request.getParameterValues("groupid");
		String roleid = request.getParameter("roleid");
		log.info("\n" + roleid);
		Roles roles = rolesManager.getById(roleid);
		Groups groups = null;
		if(null != roles) {
			for(String groupid : groupids) {
				log.info("\n" + groupid);
				groups = groupsManager.getById(groupid);
				if(null != groups) {
					GroupsRoles roleGroups = new GroupsRoles();
					roleGroups.setRoleId(roleid);
					roleGroups.setRoleName(roles.getName());
					roleGroups.setGroupId(groupid);
					roleGroups.setGroupName(groups.getName());
					groupsRolesManager.save(roleGroups);
				}
			}
		}
		
		String page = "/ss/GroupsRoles/rolegroups.jsp";
		return doSavePage();
//		return page;
	}

	//final static String g_pk_group = "ID_GROUP";
	
	public String doList() {
		HttpServletRequest request = this.getRequest();
		
		String roleid = request.getParameter("roleid");
		Roles role = null;
		if(null != roleid)
			role = rolesManager.getById(roleid);
		List<Roles> roles = rolesManager.findAll();
		List<Groups> groups = null;
		if(null != roles && roles.size() > 0) {
			if(null == role)
				role = roles.get(0);
			String hql = "from cn.sf.ss.model.Groups where "+ Groups.g_pk_group +" in (select groupId from cn.sf.ss.model.GroupsRoles where roleId='" + role.getId() + "')";
			groups = groupsManager.getEntityDao().executeFind(hql, null);
		}
		
		request.setAttribute("roles", roles);
		request.setAttribute("groups", groups);
		request.setAttribute("role", role);
		this.setSfSelectIndex("rolegroups");
		
		request.setAttribute("context", "/ss/GroupsRoles/group_role_list.jsp");
		request.setAttribute("left", systemLeft);
		request.setAttribute("top", "/top.jsp");
		request.setAttribute("footer", "/footer.jsp");
		request.setAttribute("lMenuSelect", "grouprole");
		doInitTMenu();
		
		this.setBoxPath(this.getBoxPath());
		this.setLeftPath(systemLeft);
		this.setRightPath("/ss/GroupsRoles/group_role_list.jsp");
		return "/index.jsp";
//		return "/WEB-INF/myIndex.jsp";
	}
	
	/**
	 * 分配群组页面
	 * @return
	 */
	public String doSavePage() {
		HttpServletRequest request = this.getRequest();
		
		/*String roleid = request.getParameter("roleid");
		Roles role = null;
		if(null != roleid)
			role = rolesManager.getById(roleid);
		List<Roles> roles = rolesManager.findAll();
		List<Groups> groups = null;
		if(null != roles && roles.size() > 0) {
			if(null == role)
				role = roles.get(0);
			String hql = "from cn.sf.ss.model.Groups where not PK_GROUP in (select groupId from cn.sf.ss.model.GroupsRoles where roleId='" + role.getId() + "')";
			groups = groupsManager.getEntityDao().executeFind(hql, null);
		}*/
		
		List<Groups> list = this.groupsRolesManager.findByPage(request);
		request.setAttribute("groups", list);
		
		/*request.setAttribute("roles", roles);
		request.setAttribute("groups", groups);
		request.setAttribute("role", role);*/
		/*this.setSfSelectIndex("rolegroups");
		String page = "/WEB-INF/ss/GroupsRoles/doSavePage.jsp";
		this.setMainPage(page);
		return indexPage;*/
		/*request.setAttribute("context", "/WEB-INF/ss/GroupsRoles/save_page.jsp");
		request.setAttribute("left", systemLeft);
		request.setAttribute("top", "/WEB-INF/top.jsp");
		request.setAttribute("footer", "/WEB-INF/footer.jsp");
		request.setAttribute("lMenuSelect", "grouprole");*/
		//return "/WEB-INF/myIndex.jsp";
		return "/ss/GroupsRoles/savePageOpenWin.jsp";
	}
	
	public String doSave() {
		String msg = "success";
		HttpServletRequest request = this.getRequest();
		String[] groupids = request.getParameterValues("groupid");
		String roleid = request.getParameter("roleid");
		log.info("\n" + roleid);
		Roles roles = rolesManager.getById(roleid);
		Groups groups = null;
		if(null != roles) {
			if(null != groupids && groupids.length > 0) {
				for(String groupid : groupids) {
					log.info("\n" + groupid);
					groups = groupsManager.getById(groupid);
					if(null != groups) {
						GroupsRoles roleGroups = new GroupsRoles();
						roleGroups.setRoleId(roleid);
						roleGroups.setRoleName(roles.getName());
						roleGroups.setGroupId(groupid);
						roleGroups.setGroupName(groups.getName());
						groupsRolesManager.save(roleGroups);
					}
				}
			} else {
				msg = "请选择群组!";
			}
		}
		
		//String page = "/WEB-INF/ss/GroupsRoles/rolegroups.jsp";
		//return doSavePage();
		request.setAttribute("msg", msg);
		return "/ss/GroupsRoles/saveResult.jsp";
	}
	
	public void doSaveAjax() {
		String msg = "success";
		HttpServletRequest request = this.getRequest();
		String[] groupids = request.getParameterValues("groupid");
		String roleid = request.getParameter("roleid");
		log.info("\n" + roleid);
		Roles roles = rolesManager.getById(roleid);
		Groups groups = null;
		if(null != roles) {
			if(null != groupids && groupids.length > 0) {
				for(String groupid : groupids) {
					log.info("\n" + groupid);
					groups = groupsManager.getById(groupid);
					if(null != groups) {
						GroupsRoles roleGroups = new GroupsRoles();
						roleGroups.setRoleId(roleid);
						roleGroups.setRoleName(roles.getName());
						roleGroups.setGroupId(groupid);
						roleGroups.setGroupName(groups.getName());
						groupsRolesManager.save(roleGroups);
					}
				}
			} else {
				msg = "请选择群组!";
				out("{\"success\":false,\"message\":\"请选择群组\"}");
				return;
			}
		} else {
			out("{\"success\":false,\"message\":\"该角色不存在\"}");
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
		String groupid = request.getParameter("groupid");
		log.info("\nroleid:" + roleid);
		log.info("\ngroupid:" + groupid);
		String[] fieldNames = {"roleId", "groupId"};
		Object[] fieldValues = {roleid, groupid};
		List<GroupsRoles> groupsRoles = groupsRolesManager.getEntityDao().findBy(fieldNames, fieldValues, "");
		if(null != groupsRoles && groupsRoles.size() > 0) {
			GroupsRoles po = groupsRoles.get(0);
			if(null != po) {
				try {
					groupsRolesManager.removeById(po.getId());
				} catch(Exception e) {
					msg = e.getMessage();
				}
			}
		}
		//return doList();
		//out(msg);
		out(msg);
	}
}
