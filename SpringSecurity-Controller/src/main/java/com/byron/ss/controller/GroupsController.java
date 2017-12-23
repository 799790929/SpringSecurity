

package com.byron.ss.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.byron.ss.common.base.BaseController;
import com.byron.ss.model.Groups;
import com.byron.ss.service.GroupsManager;
import com.byron.ss.service.GroupsRolesManager;
import com.byron.ss.service.UsersGroupsManager;


/**
 * @author byron
 * @version 1.0
 * @since 1.0
 */

@Controller
@RequestMapping("/ss/Groups")
public class GroupsController extends BaseController {
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

	@RequestMapping("/doList.do")
	public ModelAndView doList(HttpServletRequest request) {
		//HttpServletRequest request = this.getRequest();
		
		List<Groups> list = this.groupsManager.findAll();
		request.setAttribute("groupList", list);
		
		request.setAttribute("context", "/ss/Groups/groupList.jsp");
		request.setAttribute("left", systemLeft);
		request.setAttribute("top", "/top.jsp");
		request.setAttribute("footer", "/footer.jsp");
		request.setAttribute("lMenuSelect", "group");
		doInitTMenu(request);
		
		/*this.setBoxPath(this.getBoxPath());
		this.setLeftPath(systemLeft);
		this.setRightPath("/ss/Groups/groupList.jsp");*/
		request.setAttribute("boxPath", this.getBoxPath());
		request.setAttribute("leftPath", systemLeft);
		request.setAttribute("rightPath", "/ss/Groups/groupList.jsp");
		request.setAttribute("footerPath", "/footer.jsp");
		request.setAttribute("basePath", getBasePath(request));
		return new ModelAndView("/index");
		//return "/WEB-INF/myIndex.jsp";
	}
	
	
	@RequestMapping("/doSavePage.do")
	public ModelAndView doSavePage(HttpServletRequest request) {
		
		request.setAttribute("context", "/ss/Groups/groupSave.jsp");
		request.setAttribute("left", systemLeft);
		request.setAttribute("top", "/top.jsp");
		request.setAttribute("footer", "/footer.jsp");
		request.setAttribute("lMenuSelect", "user");
		//return "/WEB-INF/myIndex.jsp";
		return new ModelAndView("/ss/Groups/groupSaveOpenWin");
	}
	
	@RequestMapping("/doSave.do")
	public ModelAndView doSave(HttpServletRequest request) {
		String msg = "success";
		//HttpServletRequest request = this.getRequest();
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
		return new ModelAndView("/ss/Groups/saveResult");
	}
	
	@RequestMapping("/doSaveAjax.do")
	public void doSaveAjax(HttpServletRequest request, HttpServletResponse response) {
		String msg = "success";
		// HttpServletRequest request = this.getRequest();
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
				out(response, "{\"success\":false,\"message\":\"数据库中已存在该群组名\"}");
				return;
			}
			groupsManager.save(group);
		} catch(Exception e) {
			msg = "error";
			e.printStackTrace();
			out(response, "{\"success\":false,\"message\":\"保存失败\"}");
			return;
		}
		
		out(response, "{\"success\":true,\"message\":\"保存成功\"}");
	}
	
	@RequestMapping("/doDeleteAjax.do")
	public void doDeleteAjax(HttpServletRequest request, HttpServletResponse response) {
		String ret = "success";
		//HttpServletRequest request = this.getRequest();
		String id = request.getParameter("id");
		try {
			//groupsManager.removeById(id);
			if(this.usersGroupsManager.hasUserByGroup(this.groupsManager.getById(id))) {
				out(response, "该群组下有用户，请先移除用户!");
				return;
			}
			groupsManager.doDeleteGroup(id);
		} catch(Exception e) {
			e.printStackTrace();
			ret = "删除数据失败!";
		}
		out(response, ret);
	}
}
