package com.byron.ss.security;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Enumeration;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.byron.ss.common.util.ConfigUtil;
import com.byron.ss.dao.UsersDao;
import com.byron.ss.model.Users;
import com.byron.ss.util.SSUtil;
import com.byron.ss.util.StringUtil;

public class MyUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	protected Logger logger = Logger.getLogger(getClass());
	public static final String VALIDATE_CODE = "validateCode";
	public static final String USERNAME = "username";
	public static final String PASSWORD = "password";
	
	private UsersDao usersDao;
	public UsersDao getUsersDao() {
		return usersDao;
	}
	public void setUsersDao(UsersDao usersDao) {
		this.usersDao = usersDao;
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
		/*if (!request.getMethod().equals("POST")) {
			throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
		}*/
		
		//checkValidateCode(request);
		//获取cookies信息
		String ivUserId = null;
		String isCookie = ConfigUtil.getProperty("isCookie");
		if ("1".equals(isCookie)) {
			String portal = ConfigUtil.getProperty("cookie");
			this.logger.info("\ncookie->[" + portal+ "]");
			Cookie[] cookies = request.getCookies();
			if (null != cookies) {
				for (Cookie cookie : cookies) {
					this.logger.info("\nname->[" + cookie.getName() + "],value->[" + cookie.getValue() + "]");
					if (cookie.getName().equals(portal)) {
						ivUserId = cookie.getValue();
						break;
					}
				}
			}
		} else if("0".equals(isCookie)) {
			String portal = ConfigUtil.getProperty("header");
			this.logger.info("\nheader->[" + portal + "]");
			Enumeration<String> e = request.getHeaderNames();
			while (e.hasMoreElements()) {
				String name = (String) e.nextElement();
				String value = request.getHeader(name);
				this.logger.info("\nname->[" + name + "],value->[" + value + "]");
				if (name.equals(portal)) {
					ivUserId = value;
				}
			}
		}
		
		//String ivUserId = request.getHeader(portal);
		this.logger.info("\nivUserId->[" + ivUserId + "]");
		if (null != ivUserId) {
			Users user = this.usersDao.findByProperty("ivUserId", ivUserId);
			if (null != user) {
				logger.info("\niv-user-id->[" + user + "]");
				
				//UsernamePasswordAuthenticationToken实现 Authentication
				UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
				// Place the last username attempted into HttpSession for views
				
				// 允许子类设置详细属性
				setDetails(request, authRequest);
				
				//处理相关系统session等处理(pws)
				HttpSession session = request.getSession();
				session.setAttribute("loginUser", user);
				
				// 运行UserDetailsService的loadUserByUsername 再次封装Authentication
				return this.getAuthenticationManager().authenticate(authRequest);
			} else {
				throw new AuthenticationServiceException("password or username is notEquals"); 
			}
			
		}
		
		String username = obtainUsername(request);
		String password = obtainPassword(request);
		
		//验证用户账号与密码是否对应
		username = username.trim();
		//logger.info("\nusername->[" + username + "]");
		//logger.info("\npassword->[" + password + "]");
		Users users = this.usersDao.findByName(username);
		logger.info("\nusers->[" + users + "]");
		logger.info("\npassword->[" + password + "]");
		if(users == null || !users.getPassword().equals(password)) {
			throw new AuthenticationServiceException("password or username is notEquals"); 
		}
		logger.info("\nkkkkkkkkkkkkkkkkkkkkkkkk");
		
		//UsernamePasswordAuthenticationToken实现 Authentication
		UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
		// Place the last username attempted into HttpSession for views
		
		// 允许子类设置详细属性
		setDetails(request, authRequest);
		
		//处理相关系统session等处理(pws)
		HttpSession session = request.getSession();
		session.setAttribute("loginUser", users);
		logger.info("\nkkkkkkkkkkkkkkkkkkkkkkkk2");
		// 运行UserDetailsService的loadUserByUsername 再次封装Authentication
		return this.getAuthenticationManager().authenticate(authRequest);
	}
	
	protected void checkValidateCode(HttpServletRequest request) { 
		HttpSession session = request.getSession();
		
	    String sessionValidateCode = obtainSessionValidateCode(session); 
	    //让上一次的验证码失效
	    session.setAttribute(VALIDATE_CODE, null);
	    String validateCodeParameter = obtainValidateCodeParameter(request);  
	    /*if (StringUtils.isEmpty(validateCodeParameter) || !sessionValidateCode.equalsIgnoreCase(validateCodeParameter)) {  
	        throw new AuthenticationServiceException("validateCode.notEquals");  
	    }*/  
	    if (null == validateCodeParameter || "".equals(validateCodeParameter) || !sessionValidateCode.equalsIgnoreCase(validateCodeParameter)) {  
	        throw new AuthenticationServiceException("validateCode.notEquals");  
	    }
	}

	private String obtainValidateCodeParameter(HttpServletRequest request) {
		Object obj = request.getParameter(VALIDATE_CODE);
		return null == obj ? "" : obj.toString();
	}

	protected String obtainSessionValidateCode(HttpSession session) {
		Object obj = session.getAttribute(VALIDATE_CODE);
		return null == obj ? "" : obj.toString();
	}
	
	@Override
	protected String obtainUsername(HttpServletRequest request)
	{
		Object obj = request.getParameter(USERNAME);
		return null == obj ? "" : obj.toString();
	}
	
	@Override
	protected String obtainPassword(HttpServletRequest request)
	{
		Object obj = request.getParameter(PASSWORD);
		try {
			return null == obj ? "" : SSUtil.encoderPwdByMd5(StringUtil.nullStringTrim(obj.toString()));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}
}
