

package com.byron.ss.action;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.byron.ss.common.base.BaseStruts2Action;
import com.byron.ss.model.Groups;
import com.byron.ss.service.GroupsManager;
import com.byron.ss.service.GroupsRolesManager;
import com.byron.ss.service.UsersGroupsManager;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;


/**
 * @author byron
 * @version 1.0
 * @since 1.0
 */


public class GroupsAction extends BaseStruts2Action implements Preparable,ModelDriven{
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	//forward paths
	protected static final String QUERY_JSP = "/ss/Groups/query.jsp";
	protected static final String LIST_JSP= "/ss/Groups/list.jsp";
	protected static final String CREATE_JSP = "/ss/Groups/create.jsp";
	protected static final String EDIT_JSP = "/ss/Groups/edit.jsp";
	protected static final String SHOW_JSP = "/ss/Groups/show.jsp";
	//redirect paths,startWith: !
	protected static final String LIST_ACTION = "!/ss/Groups/list.do";
	
	private GroupsManager groupsManager;
	
	private UsersGroupsManager usersGroupsManager;
	private GroupsRolesManager groupsRolesManager;
	public void setUsersGroupsManager(UsersGroupsManager usersGroupsManager) {
		this.usersGroupsManager = usersGroupsManager;
	}

	public void setGroupsRolesManager(GroupsRolesManager groupsRolesManager) {
		this.groupsRolesManager = groupsRolesManager;
	}

	private Groups groups;
	java.lang.String id = null;
	private String[] items;

	public void prepare() throws Exception {
		if (isNullOrEmptyString(id)) {
			groups = new Groups();
		} else {
			groups = (Groups)groupsManager.getById(id);
		}
	}
	
	/** 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,注意大小写 */
	public void setGroupsManager(GroupsManager manager) {
		this.groupsManager = manager;
	}	
	
	public Object getModel() {
		return groups;
	}
	
	public void setId(java.lang.String val) {
		this.id = val;
	}

	public void setItems(String[] items) {
		this.items = items;
	}
	
	/** 执行搜索 */
	/*public String list() {
		GroupsQuery query = newQuery(GroupsQuery.class,DEFAULT_SORT_COLUMNS);
		
		Page page = groupsManager.findPage(query);
		savePage(page,query);
		
		this.setSfSelectIndex("groups");
		String ssPage = "/ss/Groups/list.jsp";
		this.setMainPage(ssPage);
		return indexPage;
	}*/
	
	/** 查看对象*/
	public String show() {
		String ssPage = "/ss/Groups/show.jsp";
		this.setMainPage(ssPage);
		return indexPage;
//		return SHOW_JSP;
	}
	
	/** 进入新增页面*/
	public String create() {
		String ssPage = "/ss/Groups/create.jsp";
		this.setMainPage(ssPage);
		return indexPage;
//		return CREATE_JSP;
	}
	
	/** 保存新增对象 */
	/*public String save() {
		groupsManager.save(groups);
		Flash.current().success(CREATED_SUCCESS); //存放在Flash中的数据,在下一次http请求中仍然可以读取数据,error()用于显示错误消息
		return list();
	}*/
	
	/**进入更新页面*/
	public String edit() {
		String ssPage = "/ss/Groups/edit.jsp";
		this.setMainPage(ssPage);
		return indexPage;
	}
	
	/**保存更新对象*/
	/*public String update() {
		groupsManager.update(this.groups);
		Flash.current().success(UPDATE_SUCCESS);
		return list();
	}*/
	
	/**删除对象*/
	/*public String delete() {
		for(int i = 0; i < items.length; i++) {
			Hashtable params = HttpUtils.parseQueryString(items[i]);
			java.lang.String id = new java.lang.String((String)params.get("id"));
			groupsManager.removeById(id);
			usersGroupsManager.doDeleteByGroups(id);
			groupsRolesManager.doDeleteByGroups(id);
		}
		Flash.current().success(DELETE_SUCCESS);
		return list();
	}*/

	public String doList() {
		HttpServletRequest request = this.getRequest();
		
		List<Groups> list = this.groupsManager.findAll();
		request.setAttribute("groupList", list);
		
		request.setAttribute("context", "/ss/Groups/groupList.jsp");
		request.setAttribute("left", systemLeft);
		request.setAttribute("top", "/top.jsp");
		request.setAttribute("footer", "/footer.jsp");
		request.setAttribute("lMenuSelect", "group");
		doInitTMenu();
		
		this.setBoxPath(this.getBoxPath());
		this.setLeftPath(systemLeft);
		this.setRightPath("/ss/Groups/groupList.jsp");
		return "/index.jsp";
		//return "/WEB-INF/myIndex.jsp";
	}
	
	
	
	public String doSavePage() {
		HttpServletRequest request = this.getRequest();
		
		request.setAttribute("context", "/ss/Groups/groupSave.jsp");
		request.setAttribute("left", systemLeft);
		request.setAttribute("top", "/top.jsp");
		request.setAttribute("footer", "/footer.jsp");
		request.setAttribute("lMenuSelect", "user");
		//return "/WEB-INF/myIndex.jsp";
		return "/ss/Groups/groupSaveOpenWin.jsp";
	}
	
	public String doSave() {
		String msg = "success";
		HttpServletRequest request = this.getRequest();
		String name = request.getParameter("name");
		String descri = request.getParameter("descri");
		
		Groups group = new Groups();
		group.setName(name);
		group.setDescri(descri);
		group.setEnable("1");
		group.setCreatedt(new Date());
		group.setUpdatedt(new Date());
		try {
			groupsManager.save(group);
		} catch(Exception e) {
			msg = "error";
			e.printStackTrace();
		}
		
		//return "!/ss/Groups/doList.do";
		request.setAttribute("msg", msg);
		return "/ss/Groups/saveResult.jsp";
	}
	
	public void doSaveAjax() {
		String msg = "success";
		HttpServletRequest request = this.getRequest();
		String name = request.getParameter("name");
		String descri = request.getParameter("descri");
		
		Groups group = new Groups();
		group.setName(name);
		group.setDescri(descri);
		group.setEnable("1");
		group.setCreatedt(new Date());
		group.setUpdatedt(new Date());
		try {
			/*List<Groups> list = this.groupsManager.getEntityDao().findAllBy("name", name);*/
			List<Groups> list = this.groupsManager.getGroupsByName(name);
			if(null != list && list.size() > 0) {
				out("{\"success\":false,\"message\":\"数据库中已存在该群组名\"}");
				return;
			}
			groupsManager.save(group);
		} catch(Exception e) {
			msg = "error";
			e.printStackTrace();
			out("{\"success\":false,\"message\":\"保存失败\"}");
			return;
		}
		
		out("{\"success\":true,\"message\":\"保存成功\"}");
	}
	
	public void doDeleteAjax() {
		String ret = "success";
		HttpServletRequest request = this.getRequest();
		String id = request.getParameter("id");
		try {
			//groupsManager.removeById(id);
			if(this.usersGroupsManager.hasUserByGroup(this.groupsManager.getById(id))) {
				out("该群组下有用户，请先移除用户!");
				return;
			}
			groupsManager.doDeleteGroup(id);
		} catch(Exception e) {
			e.printStackTrace();
			ret = "删除数据失败!";
		}
		out(ret);
	}
}
