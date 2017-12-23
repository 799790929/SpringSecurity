
package com.byron.ss.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.byron.ss.common.base.BaseController;
import com.byron.ss.model.Groups;
import com.byron.ss.model.Users;
import com.byron.ss.model.UsersGroups;
import com.byron.ss.service.GroupsManager;
import com.byron.ss.service.UsersGroupsManager;
import com.byron.ss.service.UsersManager;

/**
 * @author byron
 * @version 1.0
 * @since 1.0
 */

@Controller
@RequestMapping("/ss/UsersGroups")
public class UsersGroupsController extends BaseController {
	protected Logger log = Logger.getLogger(this.getClass());
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	//forward paths
	protected static final String QUERY_JSP = "/ss/UsersGroups/query.jsp";
	protected static final String LIST_JSP= "/ss/UsersGroups/list.jsp";
	protected static final String CREATE_JSP = "/ss/UsersGroups/create.jsp";
	protected static final String EDIT_JSP = "/ss/UsersGroups/edit.jsp";
	protected static final String SHOW_JSP = "/ss/UsersGroups/show.jsp";
	//redirect paths,startWith: !
	protected static final String LIST_ACTION = "!/ss/UsersGroups/list.do";
	
	private UsersGroupsManager usersGroupsManager;
	
	private UsersManager usersManager;
	private GroupsManager groupsManager;
	
	public void setUsersManager(UsersManager usersManager) {
		this.usersManager = usersManager;
	}

	public void setGroupsManager(GroupsManager groupsManager) {
		this.groupsManager = groupsManager;
	}
	
	private UsersGroups usersGroups;
	java.lang.String id = null;
	private String[] items;

	public void prepare() throws Exception {
		if (isNullOrEmptyString(id)) {
			usersGroups = new UsersGroups();
		} else {
			usersGroups = (UsersGroups)usersGroupsManager.getById(id);
		}
	}
	
	/** 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,注意大小写 */
	public void setUsersGroupsManager(UsersGroupsManager manager) {
		this.usersGroupsManager = manager;
	}	
	
	public Object getModel() {
		return usersGroups;
	}
	
	public void setId(java.lang.String val) {
		this.id = val;
	}

	public void setItems(String[] items) {
		this.items = items;
	}
	
	/** 执行搜索 */
	/*public String list() {
		UsersGroupsQuery query = newQuery(UsersGroupsQuery.class,DEFAULT_SORT_COLUMNS);
		
		Page page = usersGroupsManager.findPage(query);
		savePage(page,query);
		return LIST_JSP;
	}*/
	
	/** 查看对象*/
	public String show() {
		return SHOW_JSP;
	}
	
	/** 进入新增页面*/
	public String create() {
		return CREATE_JSP;
	}
	
	/** 保存新增对象 */
	/*public String save() {
		usersGroupsManager.save(usersGroups);
		Flash.current().success(CREATED_SUCCESS); //存放在Flash中的数据,在下一次http请求中仍然可以读取数据,error()用于显示错误消息
		return LIST_ACTION;
	}*/
	
	/**进入更新页面*/
	public String edit() {
		return EDIT_JSP;
	}
	
	/**保存更新对象*/
	/*public String update() {
		usersGroupsManager.update(this.usersGroups);
		Flash.current().success(UPDATE_SUCCESS);
		return LIST_ACTION;
	}
	
	*//**删除对象*//*
	public String delete() {
		for(int i = 0; i < items.length; i++) {
			Hashtable params = HttpUtils.parseQueryString(items[i]);
			java.lang.String id = new java.lang.String((String)params.get("id"));
			usersGroupsManager.removeById(id);
		}
		Flash.current().success(DELETE_SUCCESS);
		return LIST_ACTION;
	}*/


	/**
	 * 初始化页面
	 * @return
	 */
	public String initPage(HttpServletRequest request, HttpServletResponse response) {
		// HttpServletRequest request = this.getRequest();
		
		String groupid = request.getParameter("groupid");
		Groups group = null;
		if(null != groupid)
			group = groupsManager.getById(groupid);
		List<Users> users = null;
		
		List<Groups> groups = groupsManager.findAll();
		if(null != groups && groups.size() > 0) {
			if(null == group)
				group = groups.get(0);
			/*String hql = "from com.byron.ss.model.Users where id in (select userId from com.byron.ss.model.UsersGroups where groupId='" + group.getId() + "')";
			users = usersManager.getEntityDao().executeFind(hql, null);*/
			users = usersManager.getUsersByGroupId(group.getId());
		}
		
		request.setAttribute("users", users);
		request.setAttribute("groups", groups);
		request.setAttribute("group", group);
		
		this.setSfSelectIndex("groupusers");
		String page = "/ss/UsersGroups/groupusers.jsp";
		this.setMainPage(page);
		return indexPage;
	}
	
	/**
	 * 分配用户页面
	 * @return
	 */
	public String doSavePagddde(HttpServletRequest request, HttpServletResponse response) {
		// HttpServletRequest request = this.getRequest();
		
		String groupid = request.getParameter("groupid");
		Groups group = null;
		if(null != groupid)
			group = groupsManager.getById(groupid);
		List<Users> users = null;
		List<Groups> groups = groupsManager.findAll();
		if(null != groups && groups.size() > 0) {
			if(null == group) 
				group = groups.get(0);
			/*String hql = "from com.byron.ss.model.Users where id not in (select userId from com.byron.ss.model.UsersGroups where groupId='" + group.getId() + "')";
			users = usersManager.getEntityDao().executeFind(hql, null);*/
			users = usersManager.getUsersNotInGroupId(group.getId());
		}
		
		request.setAttribute("users", users);
		request.setAttribute("groups", groups);
		request.setAttribute("group", group);
		this.setSfSelectIndex("groupusers");
		String page = "/ss/UsersGroups/doSavePage.jsp";
		this.setMainPage(page);
		return indexPage;
	}
	
	/**
	 * 异步解除关系(unused)
	 */
	public void doDeleteSyn(HttpServletRequest request, HttpServletResponse response) {
		// HttpServletRequest request = this.getRequest();
		String msg = "删除成功";
		String userid = request.getParameter("userid");
		String groupid = request.getParameter("groupid");
		log.info("\nuserid:" + userid);
		log.info("\ngroupid:" + groupid);
		/*String[] fieldNames = {"userId", "groupId"};
		Object[] fieldValues = {userid, groupid};
		List<UsersGroups> usersGroups = usersGroupsManager.getEntityDao().findBy(fieldNames, fieldValues, "");*/
		UsersGroups ug = new UsersGroups();
		ug.setUserId(userid);
		ug.setGroupId(groupid);
		List<UsersGroups> usersGroups = usersGroupsManager.queryByModel(ug);
		if(null != usersGroups && usersGroups.size() > 0) {
			UsersGroups po = usersGroups.get(0);
			if(null != po) {
				try {
					usersGroupsManager.removeById(po.getId());
				} catch(Exception e) {
					msg = e.getMessage();
				}
			}
		}
		out(response, msg);
	}
	
	/**
	 * 解除关系(used)
	 */
	public String doDelete(HttpServletRequest request, HttpServletResponse response) {
		// HttpServletRequest request = this.getRequest();
		String msg = "删除成功";
		String userid = request.getParameter("userid");
		String groupid = request.getParameter("groupid");
		log.info("\nuserid:" + userid);
		log.info("\ngroupid:" + groupid);
		/*String[] fieldNames = {"userId", "groupId"};
		Object[] fieldValues = {userid, groupid};
		List<UsersGroups> usersGroups = usersGroupsManager.getEntityDao().findBy(fieldNames, fieldValues, "");*/
		UsersGroups ug = new UsersGroups();
		ug.setUserId(userid);
		ug.setGroupId(groupid);
		List<UsersGroups> usersGroups = usersGroupsManager.queryByModel(ug);
		if(null != usersGroups && usersGroups.size() > 0) {
			UsersGroups po = usersGroups.get(0);
			if(null != po) {
				try {
					usersGroupsManager.removeById(po.getId());
				} catch(Exception e) {
					msg = e.getMessage();
				}
			}
		}
		return initPage(request, response);
	}
	
	@RequestMapping("/doGroupUsersSave.do")
	public ModelAndView doGroupUsersSave(HttpServletRequest request, HttpServletResponse response) {
		// HttpServletRequest request = this.getRequest();
		String[] userids = request.getParameterValues("userid");
		String groupid = request.getParameter("groupid");
		log.info("\n" + groupid);
		Groups group = groupsManager.getById(groupid);
		Users user = null;
		if(null != group) {
			for(String userid : userids) {
				log.info("\n" + userid);
				user = usersManager.getById(userid);
				if(null != user) {
					UsersGroups groupUsers = new UsersGroups();
					groupUsers.setUserId(userid);
					groupUsers.setUserName(user.getUsername());
					groupUsers.setGroupId(groupid);
					groupUsers.setGroupName(group.getName());
					usersGroupsManager.save(groupUsers);
				}
			}
		}
		
		String page = "/ss/UsersGroups/groupusers.jsp";
		this.setMainPage(page);
		return doSavePage(request, response);
//		return indexPage;
	}
	//static final String u_pk_user = "ID_USER";
	@RequestMapping("/doList.do")
	public ModelAndView doList(HttpServletRequest request, HttpServletResponse response) {
		// HttpServletRequest request = this.getRequest();
		
		/*List<UsersGroups> list = this.usersGroupsManager.findAll();
		request.setAttribute("userGroupList", list);*/
		String groupid = request.getParameter("groupid");
		Groups group = null;
		if(null != groupid) {
			group = groupsManager.getById(groupid);
		}
		List<Users> users = null;
		
		List<Groups> groups = groupsManager.findAll();
		if(null != groups && groups.size() > 0) {
			if(null == group) {
				group = groups.get(0);
			}
			/*String hql = "from com.byron.ss.model.Users where "+Users.u_pk_user+" in (select userId from com.byron.ss.model.UsersGroups where groupId='" + group.getId() + "')";
			users = usersManager.getEntityDao().executeFind(hql, null);*/
			users = groupsManager.getUsersByGroupId(group);
		}
		
		request.setAttribute("users", users);
		request.setAttribute("groups", groups);
		request.setAttribute("group", group);
		
		request.setAttribute("context", "/ss/UsersGroups/user_group_list.jsp");
		request.setAttribute("left", systemLeft);
		request.setAttribute("top", "/top.jsp");
		request.setAttribute("footer", "/footer.jsp");
		request.setAttribute("lMenuSelect", "usergroup");
		doInitTMenu(request);
		
		/*this.setBoxPath(this.getBoxPath());
		this.setLeftPath(systemLeft);
		this.setRightPath("/ss/UsersGroups/user_group_list.jsp");
		return "/index.jsp";*/
		request.setAttribute("boxPath", this.getBoxPath());
		request.setAttribute("leftPath", systemLeft);
		request.setAttribute("rightPath", "/ss/UsersGroups/user_group_list.jsp");
		request.setAttribute("footerPath", "/footer.jsp");
		request.setAttribute("basePath", getBasePath(request));
		return new ModelAndView("/index");
//		return "/myIndex.jsp";
	}
	
	/**
	 * 分配用户页面
	 * @return
	 */
	@RequestMapping("/doSavePage.do")
	public ModelAndView doSavePage(HttpServletRequest request, HttpServletResponse response) {
		// HttpServletRequest request = this.getRequest();
		
		List<Users> list = usersGroupsManager.findByPage(request);
		
		request.setAttribute("users", list);
		
		/*request.setAttribute("context", "/ss/UsersGroups/save_page.jsp");
		request.setAttribute("left", "/ss/left.jsp");
		request.setAttribute("top", "/top.jsp");
		request.setAttribute("footer", "/footer.jsp");
		request.setAttribute("lMenuSelect", "usergroup");*/
		
		return new ModelAndView("/ss/UsersGroups/savePageOpenWin");
		//return "/myIndex.jsp";
	}
	
	@RequestMapping("/doSave.do")
	public ModelAndView doSave(HttpServletRequest request, HttpServletResponse response) {
		String msg = "success";
		
		// HttpServletRequest request = this.getRequest();
		String[] userids = request.getParameterValues("userid");
		String groupid = request.getParameter("groupid");
		log.info("\n" + groupid);
		Groups group = groupsManager.getById(groupid);
		Users user = null;
		if(null != group) {
			if(null != userids && userids.length > 0) {
				for(String userid : userids) {
					log.info("\n" + userid);
					user = usersManager.getById(userid);
					if(null != user) {
						UsersGroups groupUsers = new UsersGroups();
						groupUsers.setUserId(userid);
						groupUsers.setUserName(user.getUsername());
						groupUsers.setGroupId(groupid);
						groupUsers.setGroupName(group.getName());
						usersGroupsManager.save(groupUsers);
					}
				}
			} else {
				msg = "请选择用户!";
			}
		}
		
		/*String page = "/ss/UsersGroups/groupusers.jsp";
		this.setMainPage(page);*/
		//return doSavePage();
		request.setAttribute("msg", msg);
		return new ModelAndView("/ss/UsersGroups/saveResult");
	}
	
	@RequestMapping("/doSaveAjax.do")
	public void doSaveAjax(HttpServletRequest request, HttpServletResponse response) {
		String msg = "success";
		
		/// HttpServletRequest request = this.getRequest();
		String[] userids = request.getParameterValues("userid");
		String groupid = request.getParameter("groupid");
		log.info("\n" + groupid);
		Groups group = groupsManager.getById(groupid);
		Users user = null;
		if(null != group) {
			if(null != userids && userids.length > 0) {
				for(String userid : userids) {
					log.info("\n" + userid);
					user = usersManager.getById(userid);
					if(null != user) {
						UsersGroups groupUsers = new UsersGroups();
						groupUsers.setUserId(userid);
						groupUsers.setUserName(user.getUsername());
						groupUsers.setGroupId(groupid);
						groupUsers.setGroupName(group.getName());
						usersGroupsManager.save(groupUsers);
					}
				}
			} else {
				msg = "请选择用户!";
				out(response, "{\"success\":false,\"message\":\"请选择用户\"}");
				return;
			}
		} else {
			out(response, "{\"success\":false,\"message\":\"该组不存在\"}");
			return;
		}
		
		out(response, "{\"success\":true,\"message\":\"保存成功\"}");
	}
	
	/**
	 * 解除关系(used)
	 */
	@RequestMapping("/doDeleteAjax.do")
	public void doDeleteAjax(HttpServletRequest request, HttpServletResponse response) {
		String msg = "success";
		// HttpServletRequest request = this.getRequest();
		String userid = request.getParameter("userid");
		String groupid = request.getParameter("groupid");
		log.info("\nuserid:" + userid);
		log.info("\ngroupid:" + groupid);
		/*String[] fieldNames = {"userId", "groupId"};
		Object[] fieldValues = {userid, groupid};
		List<UsersGroups> usersGroups = usersGroupsManager.getEntityDao().findBy(fieldNames, fieldValues, "");*/
		UsersGroups ug = new UsersGroups();
		ug.setUserId(userid);
		ug.setGroupId(groupid);
		List<UsersGroups> usersGroups = usersGroupsManager.queryByModel(ug);
		if(null != usersGroups && usersGroups.size() > 0) {
			UsersGroups po = usersGroups.get(0);
			if(null != po) {
				try {
					usersGroupsManager.removeById(po.getId());
				} catch(Exception e) {
					msg = e.getMessage();
				}
			}
		}
		out(response, msg);
		//return doList();
	}
}
