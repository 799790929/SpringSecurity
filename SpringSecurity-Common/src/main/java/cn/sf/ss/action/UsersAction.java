/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2014
 */

package cn.sf.ss.action;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import javacommon.sf.ss.base.BaseStruts2Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import cn.org.rapid_framework.page.Page;
import cn.org.rapid_framework.web.scope.Flash;
import cn.org.rapid_framework.web.util.HttpUtils;
import cn.sf.ss.model.Users;
import cn.sf.ss.service.UsersGroupsManager;
import cn.sf.ss.service.UsersManager;
import cn.sf.ss.util.SSUtil;
import cn.sf.ss.vo.query.UsersQuery;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;


/**
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
 */


public class UsersAction extends BaseStruts2Action implements Preparable,ModelDriven{
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
	
	/** 执行搜索 */
	public String list() {
		UsersQuery query = newQuery(UsersQuery.class,DEFAULT_SORT_COLUMNS);
		
		Page page = usersManager.findPage(query);
		savePage(page,query);
		
		this.setSfSelectIndex("users");
		String ssPage = "/ss/Users/list.jsp";
		this.setMainPage(ssPage);
		return indexPage;
//		return LIST_JSP;
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
	
	/** 保存新增对象 */
	public String save() {
		try {
			String pwd = SSUtil.encoderPwdByMd5(users.getPassword());
			users.setPassword(pwd);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		usersManager.save(users);
		Flash.current().success(CREATED_SUCCESS); //存放在Flash中的数据,在下一次http请求中仍然可以读取数据,error()用于显示错误消息
		return list();
//		return LIST_ACTION;
	}
	
	/**进入更新页面*/
	public String edit() {
		String ssPage = "/ss/Users/edit.jsp";
		this.setMainPage(ssPage);
		return indexPage;
//		return EDIT_JSP;
	}
	
	/**保存更新对象*/
	public String update() {
		try {
			String pwd = SSUtil.encoderPwdByMd5(users.getPassword());
			users.setPassword(pwd);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		usersManager.update(this.users);
		Flash.current().success(UPDATE_SUCCESS);
		return list();
//		return LIST_ACTION;
	}
	
	/**删除对象*/
	public String delete() {
		for(int i = 0; i < items.length; i++) {
			Hashtable params = HttpUtils.parseQueryString(items[i]);
			java.lang.String id = new java.lang.String((String)params.get("id"));
			usersManager.removeById(id);
			usersGroupsManager.doDeleteByUsers(id);
		}
		Flash.current().success(DELETE_SUCCESS);
		return list();
//		return LIST_ACTION;
	}
	
	public String doLogin() {
		HttpServletRequest request = this.getRequest();
		return LIST_ACTION;
	}

	public String doList() {
		HttpServletRequest request = this.getRequest();
		
		HttpSession session = request.getSession();
		Users users = (Users)session.getAttribute("loginUser");
		List<Users> list = null;
		//超级管理员可以查看所有用户
		if ("super".equalsIgnoreCase(users.getUsername())) {
			list = this.usersManager.findByPage(request);
			request.setAttribute("isSuper", 1);
		} else {
			//其它用户只能查看自己的信息
			list = this.usersManager.findUsersByProperty("username" , users.getUsername());
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
		doInitTMenu();
		
		this.setBoxPath(this.getBoxPath());
		this.setLeftPath(systemLeft);
		this.setRightPath("/ss/Users/userList.jsp");
		return "/index.jsp";
//		return "/myIndex.jsp";
	}
	
	private String getRsscCodeOptions(String rsscCode) {
		StringBuilder sb = new StringBuilder();
        String sql = "select distinct rssc_code,rssc_code as test from ts_point";
        List<Object[]> list = this.usersManager.getEntityDao().getFieldsBySql(sql);
        if (null != list) {
            for (Object[] item : list) {
            	if (null != rsscCode && !rsscCode.equals(item[0])) {
            		sb.append("<option value='"+ item[0] +"'>").append(item[0]).append("</option>");
            	} else {
            		sb.append("<option value='"+ item[0] +"' selected>").append(item[0]).append("</option>");
            	}
            }
        }
        
        return sb.toString();
	}
	
	public String doSavePage() {
		HttpServletRequest request = this.getRequest();
		
		request.setAttribute("rsscCodeOptions", this.getRsscCodeOptions(""));
		
		request.setAttribute("context", "/ss/Users/userSave.jsp");
		request.setAttribute("left", systemLeft);
		request.setAttribute("top", "/top.jsp");
		request.setAttribute("footer", "/footer.jsp");
		request.setAttribute("lMenuSelect", "user");
		//return "/myIndex.jsp";
		
		return "/ss/Users/userSaveOpenWin.jsp";
	}
	
	public String doSave() {
		String msg = "success";
		HttpServletRequest request = this.getRequest();
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
		return "/ss/Users/saveResult.jsp";
	}
	
	public void doSaveAjax() {
		String msg = "success";
		HttpServletRequest request = this.getRequest();
		String username = request.getParameter("username");
		String realname = request.getParameter("realname");
		String password = request.getParameter("password");
		String repassword = request.getParameter("repassword");
		String descri = request.getParameter("descri");
		
		String brand = request.getParameter("brand");
		String dealerCode = request.getParameter("dealerCode");
		String dealerName = request.getParameter("dealerName");
		String dealer = request.getParameter("dealer");
		String userType = request.getParameter("userType");
		
		String ivUserId = request.getParameter("ivUserId");
		
		String isRsscCode = request.getParameter("isRsscCode");
		
		Users user = new Users();
		user.setUsername(username);
		user.setRealname(realname);
		user.setDescri(descri);
		
		/*user.setBrand(brand);
		user.setDealerCode(dealerCode);
		user.setDealerName(dealerName);
		user.setDealer(dealer);
		user.setUserType(userType);*/
		
		user.setIvUserId(ivUserId);
		
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
			List<Users> list = this.usersManager.getEntityDao().findAllBy("username", username);
			if(null != list && list.size() > 0) {
				out("{\"success\":false,\"message\":\"数据库中已存在该用户名\"}");
				return;
			}
			list = this.usersManager.getEntityDao().findAllBy("ivUserId", ivUserId);
			if(null != list && list.size() > 0) {
				out("{\"success\":false,\"message\":\"数据库中已存在该iv-user-id\"}");
				return;
			}
			usersManager.save(user);
		} catch(Exception e) {
			msg = "error";
			e.printStackTrace();
			out("{\"success\":false,\"message\":\"保存失败\"}");
			return;
		}
		
		out("{\"success\":true,\"message\":\"保存成功\"}");
	}
	
	public String doEditPage() {
		HttpServletRequest request = this.getRequest();
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
		
		return "/ss/Users/userEditOpenWin.jsp";
	}
	
	public void doEditAjax() {
		String msg = "success";
		logger.info("\nusers->[" + users + "]");
		HttpServletRequest request = this.getRequest();
		Users loginUser = (Users) request.getSession().getAttribute("loginUser");
		//String loginUserType = loginUser.getUserType();
		
		String id = request.getParameter("id");
		Users user = this.usersManager.getById(id);
		if (null == user) {
			out("{\"success\":false,\"message\":\"用户不存在\"}");
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
		
		user.setIvUserId(ivUserId);
		
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
					out("{\"success\":false,\"message\":\"新密码及确认密码不一致\"}");
					return;
				}
			}
			
			usersManager.update(user);
		} catch(Exception e) {
			msg = "error";
			out("{\"success\":false,\"message\":\"修改失败\"}");
			e.printStackTrace();
			return;
		}
		
		out("{\"success\":true,\"message\":\"修改成功\"}");
	}
	
	public void doDeleteAjax() {
		String ret = "success";
		HttpServletRequest request = this.getRequest();
		String id = request.getParameter("id");
		try {
			//usersManager.removeById(id);
			usersManager.doDeleteUser(id);
		} catch(Exception e) {
			e.printStackTrace();
			ret = "删除数据失败!";
		}
		out(ret);
	}
	
	public String logout() {
		HttpServletRequest request = this.getRequest();
		HttpSession session = request.getSession();
		session.removeAttribute("loginUser");
		
		return "/login.jsp";
	}
}
