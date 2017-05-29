package com.byron.ss.tag;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.ServletContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.byron.ss.security.MyInvocationSecurityMetadataSource;

public class PermissionTag extends TagSupport {
	String permission;
	public String isPermission() {
		return permission;
	}
	public void setPermission(String permission) {
		this.permission = permission;
	}
	/**
	 * 当方法返回SKIP_BODY的时候，这个“删除”操作时不会显示在页面中的,然后调用doEndTag方法
	*	当方法返回EVAL_BODY_INCLUDE.时，会在页面中显示“删除”操作，然后执行doAfterTag操作
	 */
	@Override
    public int doStartTag() throws JspException {
		ServletContext sc = this.pageContext.getServletContext();
		WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(sc);
		MyInvocationSecurityMetadataSource mySecurityMetadataSource = (MyInvocationSecurityMetadataSource) wac.getBean("mySecurityMetadataSource");
        try {
        	//全部资源(url)-角色(role)
        	
        	Collection<ConfigAttribute> configAttributes = mySecurityMetadataSource.getAttributesPage(permission);
//        	Map<String, Collection<ConfigAttribute>> resourceMap = mySecurityMetadataSource.getResourceMap();
//        	Collection<ConfigAttribute> cas = resourceMap.get(permission);
        	//用户信息
//        	Object principal =  SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        	if(principal instanceof UserDetails) {
//        		Iterator it = ((UserDetails) principal).getAuthorities().iterator();
//        	}
        	
        	if (configAttributes == null) {         //与访问资源对应的权限与资源对应列表
    			return EVAL_BODY_INCLUDE;
    		}
        	Iterator<ConfigAttribute> ite = configAttributes.iterator();
    		while (ite.hasNext()) {
    			ConfigAttribute ca = ite.next();
    			String needRole = ((SecurityConfig) ca).getAttribute();
    			for (GrantedAuthority ga : authentication.getAuthorities()) {//循环用户能访问资源 resource.getName()
    				if (needRole.equals(ga.getAuthority())) { // ga is user's role.  ga.getAuthority()获取资源名
    					return EVAL_BODY_INCLUDE;
    				}
    			}
    		}
//            JspWriter out = this.pageContext.getOut();
//            if(permission) {
//                out.println("No UserInfo Found...");
//                return SKIP_BODY;
//            }
        } catch(Exception e) {
        	e.printStackTrace();
            throw new JspException(e.getMessage());
        }
        return SKIP_BODY;
    }
	
	/**SKIP_PAGE:标签后面的jsp代码会被忽略，中止了JSP页面的执行
	EVAL_PAGE:与SKIP_PAGE相反，会在标签体处理完后继续处理后面的JSP内容，这是是默认的返回值*/
    @Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }
    @Override
    public void release() {
        super.release();
    }
}
