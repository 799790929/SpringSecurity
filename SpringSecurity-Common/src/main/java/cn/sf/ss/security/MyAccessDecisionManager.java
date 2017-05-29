package cn.sf.ss.security;

import java.util.Collection;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

public class MyAccessDecisionManager implements AccessDecisionManager {

	protected Logger log = Logger.getLogger(this.getClass());
	// In this method, need to compare authentication with configAttributes.
	// 1, A object is a URL, a filter was find permission configuration by
	// this URL, and pass to here.
	// 2, Check authentication has attribute in permission configuration
	// (configAttributes)
	// 3, If not match corresponding authentication, throw a
	// AccessDeniedException.
	public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException,
			InsufficientAuthenticationException {
		if (configAttributes == null) {         //与访问资源对应的权限与资源对应列表
			return;
		}
		Iterator<ConfigAttribute> ite = configAttributes.iterator();
		while (ite.hasNext()) {
			ConfigAttribute ca = ite.next();
			String needRole = ((SecurityConfig) ca).getAttribute();
			//this.log.info("\nneedPermission is " + needRole);
			//this.log.info("\n登陆用户权限->[" + authentication.getAuthorities() + "]");
			for (GrantedAuthority ga : authentication.getAuthorities()) {//循环用户能访问资源 resource.getName()
				if (needRole.equals(ga.getAuthority())) { // ga is user's role.  ga.getAuthority()获取资源名
					return;
				}
			}
		}
		throw new AccessDeniedException("no right");
	}

	public boolean supports(ConfigAttribute attribute) {
		return true;
	}

	public boolean supports(Class<?> clazz) {
		return true;
	}

}
