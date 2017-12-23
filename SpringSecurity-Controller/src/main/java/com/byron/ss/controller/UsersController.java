/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2014
 */

package com.byron.ss.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.byron.ss.common.base.BaseController;
import com.byron.ss.model.Users;
import com.byron.ss.service.UsersGroupsManager;
import com.byron.ss.service.UsersManager;
import com.byron.ss.util.SSUtil;


/**
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
 */

@Controller
@RequestMapping("/ss/Users")
public class UsersController extends BaseController {
	protected Logger logger = Logger.getLogger(getClass());
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	//forward paths
	protected static final String QUERY_JSP = "/ss/Users/query.jsp";
	protected static final String LIST_JSP= "/ss/Users/list.jsp";
	protected static final String CREATE_JSP = "/ss/Users/create.jsp";
	protected static final String EDIT_JSP = "/ss/Users/edit.jsp";
	protected static final String SHOW_JSP = "/ss/Users/show.jsp";
	//redirect paths,startWith: !
	protected static final String LIST_ACTION = "!/ss/Users/list.do";
	
	private UsersManager usersManager;
	
	private UsersGroupsManager usersGroupsManager;
	
	public void setUsersGroupsManager(UsersGroupsManager usersGroupsManager) {
		this.usersGroupsManager = usersGroupsManager;
	}

	private Users users;
	java.lang.String id = null;
	private String[] items;

	public void prepare() throws Exception {
		if (isNullOrEmptyString(id)) {
			users = new Users();
		} else {
			users = (Users)usersManager.getById(id);
		}
	}
	
	/** 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,注意大小写 */
	public void setUsersManager(UsersManager manager) {
		this.usersManager = manager;
	}	
	
	public Object getModel() {
		return users;
	}
	
	public void setId(java.lang.String val) {
		this.id = val;
	}

	public void setItems(String[] items) {
		this.items = items;
	}
	
	
	/** 查看对象*/
	public String show() {
		String ssPage = "/ss/Users/show.jsp";
		this.setMainPage(ssPage);
		return indexPage;
//		return SHOW_JSP;
	}
	
	/** 进入新增页面*/
	public String create() {
		String ssPage = "/ss/Users/create.jsp";
		this.setMainPage(ssPage);
		return indexPage;
//		return CREATE_JSP;
	}
	
	
	/**进入更新页面*/
	public String edit() {
		String ssPage = "/ss/Users/edit.jsp";
		this.setMainPage(ssPage);
		return indexPage;
//		return EDIT_JSP;
	}
	
	
	public String doLogin(HttpServletRequest request, HttpServletResponse response) {
		// HttpServletRequest request = this.getRequest();
		return LIST_ACTION;
	}

	@RequestMapping("/doList.do")
	public ModelAndView doList(HttpServletRequest request, HttpServletResponse response) {
		// HttpServletRequest request = this.getRequest();
		
		HttpSession session = request.getSession();
		Users users = (Users)session.getAttribute("loginUser");
		List<Users> list = null;
		//超级管理员可以查看所有用户
		if ("super".equalsIgnoreCase(users.getUsername())) {
			list = this.usersManager.findByPage(request);
			request.setAttribute("isSuper", 1);
		} else {
			//其它用户只能查看自己的信息
			// list = this.usersManager.findUsersByProperty("username" , users.getUsername());
			list = this.usersManager.queryUsersByUserName(users.getUsername());
			request.setAttribute("isSuper", 0);
			request.setAttribute("requestPage", 1);
			request.setAttribute("pagesCount", 1);
		}
		request.setAttribute("userList", list);
		
		//List<Users> list = this.usersManager.findAll();
		
		request.setAttribute("context", "/ss/Users/userList.jsp");
		request.setAttribute("left", systemLeft);
		request.setAttribute("top", "/top.jsp");
		request.setAttribute("footer", "/footer.jsp");
		request.setAttribute("lMenuSelect", "user");
		doInitTMenu(request);
		
		/*request.setAttribute("boxPath", this.getBoxPath());
		request.setAttribute("footerPath", "/footer.jsp");*/
		
		//this.setBoxPath(this.getBoxPath());
		//this.setLeftPath(systemLeft);
		//this.setRightPath("/ss/Users/userList.jsp");
		request.setAttribute("boxPath", this.getBoxPath());
		request.setAttribute("leftPath", systemLeft);
		request.setAttribute("rightPath", "/ss/Users/userList.jsp");
		request.setAttribute("footerPath", "/footer.jsp");
		request.setAttribute("basePath", getBasePath(request));
		return new ModelAndView("/index");
//		return "/myIndex.jsp";
	}
	
	@RequestMapping("/doSavePage.do")
	public ModelAndView doSavePage(HttpServletRequest request, HttpServletResponse response) {
		// HttpServletRequest request = this.getRequest();
		
		
		request.setAttribute("context", "/ss/Users/userSave.jsp");
		request.setAttribute("left", systemLeft);
		request.setAttribute("top", "/top.jsp");
		request.setAttribute("footer", "/footer.jsp");
		request.setAttribute("lMenuSelect", "user");
		//return "/myIndex.jsp";
		
		return new ModelAndView("/ss/Users/userSaveOpenWin");
	}
	
	@RequestMapping("/doSave.do")
	public ModelAndView doSave(HttpServletRequest request, HttpServletResponse response) {
		String msg = "success";
		// HttpServletRequest request = this.getRequest();
		String username = request.getParameter("username");
		String realname = request.getParameter("realname");
		String password = request.getParameter("password");
		String repassword = request.getParameter("repassword");
		String descri = request.getParameter("descri");
		
		Users user = new Users();
		user.setUsername(username);
		user.setRealname(realname);
		user.setDescri(descri);
		try {
			user.setPassword(SSUtil.encoderPwdByMd5(password));
		} catch (Exception e1) {
			e1.printStackTrace();
		} 
		user.setEnable("1");
		user.setCreatedt(new Date());
		user.setUpdatedt(new Date());
		try {
			usersManager.save(user);
		} catch(Exception e) {
			msg = "error";
			e.printStackTrace();
		}
		
		//return "!/ss/Users/doList.do";
		request.setAttribute("msg", msg);
		return new ModelAndView("/ss/Users/saveResult");
	}
	
	@RequestMapping("/doSaveAjax.do")
	public void doSaveAjax(HttpServletRequest request, HttpServletResponse response) {
		String msg = "success";
		// HttpServletRequest request = this.getRequest();
		String username = request.getParameter("username");
		String realname = request.getParameter("realname");
		String password = request.getParameter("password");
		String repassword = request.getParameter("repassword");
		String descri = request.getParameter("descri");
		
		/*String brand = request.getParameter("brand");
		String dealerCode = request.getParameter("dealerCode");
		String dealerName = request.getParameter("dealerName");
		String dealer = request.getParameter("dealer");
		String userType = request.getParameter("userType");
		
		String ivUserId = request.getParameter("ivUserId");
		
		String isRsscCode = request.getParameter("isRsscCode");*/
		
		Users user = new Users();
		user.setUsername(username);
		user.setRealname(realname);
		user.setDescri(descri);
		
		/*user.setBrand(brand);
		user.setDealerCode(dealerCode);
		user.setDealerName(dealerName);
		user.setDealer(dealer);
		user.setUserType(userType);*/
		
		// user.setIvUserId(ivUserId);
		
		/*user.setIsRsscCode(isRsscCode);*/
		
		try {
			user.setPassword(SSUtil.encoderPwdByMd5(password));
		} catch (Exception e1) {
			e1.printStackTrace();
		} 
		user.setEnable("1");
		user.setCreatedt(new Date());
		user.setUpdatedt(new Date());
		try {
			/*List<Users> list = this.usersManager.getEntityDao().findAllBy("username", username);*/
			List<Users> list = this.usersManager.queryUsersByUserName(username);
			if(null != list && list.size() > 0) {
				out(response, "{\"success\":false,\"message\":\"数据库中已存在该用户名\"}");
				return;
			}
			/*list = this.usersManager.getEntityDao().findAllBy("ivUserId", ivUserId);
			if(null != list && list.size() > 0) {
				out("{\"success\":false,\"message\":\"数据库中已存在该iv-user-id\"}");
				return;
			}*/
			usersManager.save(user);
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
		Users loginUser = (Users) request.getSession().getAttribute("loginUser");
		String id = request.getParameter("id");
		Users users = this.usersManager.getById(id);
		request.setAttribute("users", users);
		
		/*request.setAttribute("rsscCodeOptions", this.getRsscCodeOptions(users.getDealer()));
		request.setAttribute("userType", loginUser.getUserType());*/
		/*request.setAttribute("context", "/ss/Users/userSave.jsp");
		request.setAttribute("left", systemLeft);
		request.setAttribute("top", "/top.jsp");
		request.setAttribute("footer", "/footer.jsp");
		request.setAttribute("lMenuSelect", "user");*/
		//return "/myIndex.jsp";
		
		return new ModelAndView("/ss/Users/userEditOpenWin");
	}
	
	@RequestMapping("/doEditAjax.do")
	public void doEditAjax(HttpServletRequest request, HttpServletResponse response) {
		String msg = "success";
		logger.info("\nusers->[" + users + "]");
		// HttpServletRequest request = this.getRequest();
		Users loginUser = (Users) request.getSession().getAttribute("loginUser");
		//String loginUserType = loginUser.getUserType();
		
		String id = request.getParameter("id");
		Users user = this.usersManager.getById(id);
		if (null == user) {
			out(response, "{\"success\":false,\"message\":\"用户不存在\"}");
			return;
		}
		String isJudge = request.getParameter("isJudge");
		String username = request.getParameter("username");
		String realname = request.getParameter("realname");
		String oldpassword = request.getParameter("oldpassword");
		String password = request.getParameter("password");
		String repassword = request.getParameter("repassword");
		String descri = request.getParameter("descri");
		
		String brand = request.getParameter("brand");
		String dealerCode = request.getParameter("dealerCode");
		String dealerName = request.getParameter("dealerName");
		String dealer = request.getParameter("dealer");
		String userType = request.getParameter("userType");
		
		String updPwd = request.getParameter("updPwd");
		
		String ivUserId = request.getParameter("ivUserId");
		
		String isRsscCode = request.getParameter("isRsscCode");
		
		//user.setUsername(username);
		user.setRealname(realname);
		/*logger.info("\nuser->[" + loginUserType + "]");
		logger.info("\nisJudge->[" + isJudge + "]");
		if (null != updPwd && "1".equals(updPwd)) {
			if (!"0".equals(loginUserType) || "1".equals(isJudge)) {
				try {
					String old = SSUtil.encoderPwdByMd5(oldpassword);
					if (!user.getPassword().equals(old)) {
						out("{\"success\":false,\"message\":\"原密码与用户实际密码不一致\"}");
						return;
					}
				} catch (Exception e) {
					e.printStackTrace();
				} 
			}
		}*/
		user.setDescri(descri);
		
		/*user.setBrand(brand);
		user.setDealerCode(dealerCode);
		user.setDealerName(dealerName);
		user.setDealer(dealer);
		user.setUserType(userType);*/
		
		// user.setIvUserId(ivUserId);
		
		/*user.setIsRsscCode(isRsscCode);*/
		
		try {
			if (null != updPwd && "1".equals(updPwd)) {
				user.setPassword(SSUtil.encoderPwdByMd5(password));
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		} 
		//user.setEnable("1");
		//user.setCreatedt(new Date());
		
		user.setUpdatedt(new Date());
		
		try {
			if (null != updPwd && "1".equals(updPwd)) {
				if (!password.equals(repassword)) {
					out(response, "{\"success\":false,\"message\":\"新密码及确认密码不一致\"}");
					return;
				}
			}
			
			usersManager.update(user);
		} catch(Exception e) {
			msg = "error";
			out(response, "{\"success\":false,\"message\":\"修改失败\"}");
			e.printStackTrace();
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
			//usersManager.removeById(id);
			usersManager.doDeleteUser(id);
		} catch(Exception e) {
			e.printStackTrace();
			ret = "删除数据失败!";
		}
		out(response, ret);
	}
	
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		// HttpServletRequest request = this.getRequest();
		HttpSession session = request.getSession();
		session.removeAttribute("loginUser");
		
		return "/login.jsp";
	}
}
