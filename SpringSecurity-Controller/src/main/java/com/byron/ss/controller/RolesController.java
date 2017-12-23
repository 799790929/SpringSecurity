

package com.byron.ss.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.byron.ss.common.base.BaseController;
import com.byron.ss.model.Roles;
import com.byron.ss.service.GroupsRolesManager;
import com.byron.ss.service.RolesManager;
import com.byron.ss.service.RolesResourcesManager;


/**
 * @author byron
 * @version 1.0
 * @since 1.0
 */

@Controller
@RequestMapping("/ss/Roles")
public class RolesController extends BaseController {
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	//forward paths
	protected static final String QUERY_JSP = "/ss/Roles/query.jsp";
	protected static final String LIST_JSP= "/ss/Roles/list.jsp";
	protected static final String CREATE_JSP = "/ss/Roles/create.jsp";
	protected static final String EDIT_JSP = "/ss/Roles/edit.jsp";
	protected static final String SHOW_JSP = "/ss/Roles/show.jsp";
	//redirect paths,startWith: !
	protected static final String LIST_ACTION = "!/ss/Roles/list.do";
	
	private RolesManager rolesManager;
	private GroupsRolesManager groupsRolesManager;
	private RolesResourcesManager rolesResourcesManager;
	
	public void setGroupsRolesManager(GroupsRolesManager groupsRolesManager) {
		this.groupsRolesManager = groupsRolesManager;
	}

	public void setRolesResourcesManager(RolesResourcesManager rolesResourcesManager) {
		this.rolesResourcesManager = rolesResourcesManager;
	}

	private Roles roles;
	java.lang.String id = null;
	private String[] items;

	public void prepare() throws Exception {
		if (isNullOrEmptyString(id)) {
			roles = new Roles();
		} else {
			roles = (Roles)rolesManager.getById(id);
		}
	}
	
	/** 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,注意大小写 */
	public void setRolesManager(RolesManager manager) {
		this.rolesManager = manager;
	}	
	
	public Object getModel() {
		return roles;
	}
	
	public void setId(java.lang.String val) {
		this.id = val;
	}

	public void setItems(String[] items) {
		this.items = items;
	}
	
	/** 执行搜索 */
	/*public String list() {
		RolesQuery query = newQuery(RolesQuery.class,DEFAULT_SORT_COLUMNS);
		
		Page page = rolesManager.findPage(query);
		savePage(page,query);
		
		this.setSfSelectIndex("roles");
		String ssPage = "/ss/Roles/list.jsp";
		this.setMainPage(ssPage);
		return indexPage;
//		return LIST_JSP;
	}*/
	
	/** 查看对象*/
	public String show() {
		String ssPage = "/ss/Roles/show.jsp";
		this.setMainPage(ssPage);
		return indexPage;
//		return SHOW_JSP;
	}
	
	/** 进入新增页面*/
	public String create() {
		String ssPage = "/ss/Roles/create.jsp";
		this.setMainPage(ssPage);
		return indexPage;
//		return CREATE_JSP;
	}
	
	/** 保存新增对象 */
	/*public String save() {
		rolesManager.save(roles);
		Flash.current().success(CREATED_SUCCESS); //存放在Flash中的数据,在下一次http请求中仍然可以读取数据,error()用于显示错误消息
		return list();
//		return LIST_ACTION;
	}*/
	
	/**进入更新页面*/
	public String edit() {
		String ssPage = "/ss/Roles/edit.jsp";
		this.setMainPage(ssPage);
		return indexPage;
//		return EDIT_JSP;
	}
	
	/**保存更新对象*/
	/*public String update() {
		rolesManager.update(this.roles);
		Flash.current().success(UPDATE_SUCCESS);
		return list();
//		return LIST_ACTION;
	}
	
	*//**删除对象*//*
	public String delete() {
		for(int i = 0; i < items.length; i++) {
			Hashtable params = HttpUtils.parseQueryString(items[i]);
			java.lang.String id = new java.lang.String((String)params.get("id"));
			rolesManager.removeById(id);
			groupsRolesManager.doDeleteByRoles(id);
			rolesResourcesManager.doDeleteByRoles(id);
		}
		Flash.current().success(DELETE_SUCCESS);
		return list();
//		return LIST_ACTION;
	}*/

	@RequestMapping("/doList.do")
	public ModelAndView doList(HttpServletRequest request, HttpServletResponse response) {
		// HttpServletRequest request = this.getRequest();
		
		List<Roles> list = this.rolesManager.findAll();
		request.setAttribute("roleList", list);
		
		request.setAttribute("context", "/ss/Roles/roleList.jsp");
		request.setAttribute("left", systemLeft);
		request.setAttribute("top", "/top.jsp");
		request.setAttribute("footer", "/footer.jsp");
		request.setAttribute("lMenuSelect", "role");
		doInitTMenu(request);
		
		/*this.setBoxPath(this.getBoxPath());
		this.setLeftPath(systemLeft);
		this.setRightPath("/ss/Roles/roleList.jsp");
		return "/index.jsp";*/
		request.setAttribute("boxPath", this.getBoxPath());
		request.setAttribute("leftPath", systemLeft);
		request.setAttribute("rightPath", "/ss/Roles/roleList.jsp");
		request.setAttribute("footerPath", "/footer.jsp");
		request.setAttribute("basePath", getBasePath(request));
		return new ModelAndView("/index");
		//return "/myIndex.jsp";
	}
	
	@RequestMapping("/doSavePage.do")
	public ModelAndView doSavePage(HttpServletRequest request, HttpServletResponse response) {
		// HttpServletRequest request = this.getRequest();
		
		request.setAttribute("context", "/ss/Roles/roleSave.jsp");
		request.setAttribute("left", systemLeft);
		request.setAttribute("top", "/top.jsp");
		request.setAttribute("footer", "/footer.jsp");
		request.setAttribute("lMenuSelect", "role");
		//return "/myIndex.jsp";
		return new ModelAndView("/ss/Roles/roleSaveOpenWin");
	}
	
	@RequestMapping("/doSave.do")
	public ModelAndView doSave(HttpServletRequest request, HttpServletResponse response) {
		String msg = "success";
		// HttpServletRequest request = this.getRequest();
		String name = request.getParameter("name");
		String descri = request.getParameter("descri");
		String scope = request.getParameter("scope");
		
		Roles role = new Roles();
		role.setName(name);
		role.setDescri(descri);
		//role.setScope(scope);
		role.setEnable("1");
		role.setCreatedt(new Date());
		role.setUpdatedt(new Date());
		try {
			rolesManager.save(role);
		} catch(Exception e) {
			msg = "error";
			e.printStackTrace();
		}
		
		//return "!/ss/Roles/doList.do";
		request.setAttribute("msg", msg);
		return new ModelAndView("/ss/Roles/saveResult");
	}
	
	@RequestMapping("/doSaveAjax.do")
	public void doSaveAjax(HttpServletRequest request, HttpServletResponse response) {
		String msg = "success";
		// HttpServletRequest request = this.getRequest();
		String name = request.getParameter("name");
		String descri = request.getParameter("descri");
		String brand = request.getParameter("brand");
		String dealerCode = request.getParameter("dealerCode");
		String dealerName = request.getParameter("dealerName");
		String dealer = request.getParameter("dealer");
		String scope = request.getParameter("scope");
		String systemFirstPage = request.getParameter("systemFirstPage");
		String searchFirstPage = request.getParameter("searchFirstPage");
		
		Roles role = new Roles();
		role.setName(name);
		role.setDescri(descri);
		/*role.setBrand(brand);
		role.setDealerCode(dealerCode);
		role.setDealerName(dealerName);
		role.setDealer(dealer);
		role.setScope(scope);
		role.setSystemFirstPage(systemFirstPage);
		role.setSearchFirstPage(searchFirstPage);*/
		role.setEnable("1");
		role.setCreatedt(new Date());
		role.setUpdatedt(new Date());
		try {
			/*List<Roles> list = this.rolesManager.getEntityDao().findAllBy("name", name);*/
			List<Roles> list = this.rolesManager.getRolesByName(name);
			if(null != list && list.size() > 0) {
				out(response, "{\"success\":false,\"message\":\"数据库中已存在该角色\"}");
				return;
			}
			rolesManager.save(role);
		} catch(Exception e) {
			msg = "error";
			e.printStackTrace();
			out(response, "{\"success\":false,\"message\":\"保存失败\"}");
			return;
		}
		
		out(response, "{\"success\":true,\"message\":\"保存成功\"}");
	}
	
	@RequestMapping("/doEditPage.do")
	public ModelAndView doEditPage(HttpServletRequest request, HttpServletResponse response) {
		// HttpServletRequest request = this.getRequest();
		String id = request.getParameter("id");
		Roles roles = this.rolesManager.getById(id);
		request.setAttribute("roles", roles);
		
		/*request.setAttribute("context", "/ss/Roles/roleSave.jsp");
		request.setAttribute("left", systemLeft);
		request.setAttribute("top", "/top.jsp");
		request.setAttribute("footer", "/footer.jsp");
		request.setAttribute("lMenuSelect", "role");*/
		//return "/myIndex.jsp";
		return new ModelAndView("/ss/Roles/roleEdit");
	}
	
	@RequestMapping("/doEditAjax.do")
	public void doEditAjax(HttpServletRequest request, HttpServletResponse response) {
		String msg = "success";
		// HttpServletRequest request = this.getRequest();
		String name = request.getParameter("name");
		String descri = request.getParameter("descri");
		String brand = request.getParameter("brand");
		String dealerCode = request.getParameter("dealerCode");
		String dealerName = request.getParameter("dealerName");
		String dealer = request.getParameter("dealer");
		String scope = request.getParameter("scope");
		String systemFirstPage = request.getParameter("systemFirstPage");
		String searchFirstPage = request.getParameter("searchFirstPage");
		
		String id = request.getParameter("id");
		Roles role = this.rolesManager.getById(id);
		if (null == role) {
			out(response, "{\"success\":false,\"message\":\"角色不存在\"}");
			return;
		}
		role.setName(name);
		role.setDescri(descri);
		/*role.setBrand(brand);
		role.setDealerCode(dealerCode);
		role.setDealerName(dealerName);
		role.setDealer(dealer);
		role.setScope(scope);
		role.setSystemFirstPage(systemFirstPage);
		role.setSearchFirstPage(searchFirstPage);*/
		role.setEnable("1");
		//role.setCreatedt(new Date());
		role.setUpdatedt(new Date());
		try {
			/*List<Roles> list = this.rolesManager.getEntityDao().findAllBy("name", name);
			if(null != list && list.size() > 0) {
				out("{\"success\":false,\"message\":\"数据库中已存在该角色\"}");
				return;
			}*/
			rolesManager.update(role);
		} catch(Exception e) {
			msg = "error";
			e.printStackTrace();
			out(response, "{\"success\":false,\"message\":\"修改失败\"}");
			return;
		}
		
		out(response, "{\"success\":true,\"message\":\"修改成功\"}");
	}
	
	@RequestMapping("/doDeleteAjax.do")
	public void doDeleteAjax(HttpServletRequest request, HttpServletResponse response) {
		String ret = "success";
		// HttpServletRequest request = this.getRequest();
		String id = request.getParameter("id");
		try {
			Roles po = this.rolesManager.getById(id);
			if(this.groupsRolesManager.hasGroupByRole(po)) {
				out(response, "该角色下有群组，请先移除群组!");
				return;
			}
			if(this.rolesResourcesManager.hasResourceByRole(po)) {
				out(response, "该角色下有资源，请先移除资源!");
				return;
			}
			rolesManager.removeById(id);
		} catch(Exception e) {
			e.printStackTrace();
			ret = "删除数据失败!";
		}
		out(response, ret);
	}
}
