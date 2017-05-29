package cn.sf.ss.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

import cn.sf.ss.dao.ResourcesDao;
import cn.sf.ss.dao.RolesDao;
import cn.sf.ss.model.Roles;
import cn.sf.ss.security.tool.AntUrlPathMatcher;
import cn.sf.ss.security.tool.UrlMatcher;

/**
 * 
 * 此类在初始化时，应该取到所有资源及其对应角色的定义
 * 
 */
public class MyInvocationSecurityMetadataSource implements
		FilterInvocationSecurityMetadataSource {
	private UrlMatcher urlMatcher = new AntUrlPathMatcher();
	private static Map<String, Collection<ConfigAttribute>> resourceMap = null;
	
	private ResourcesDao resourcesDao;
	private RolesDao rolesDao;
	
	public RolesDao getRolesDao() {
		return rolesDao;
	}

	public void setRolesDao(RolesDao rolesDao) {
		this.rolesDao = rolesDao;
	}

	public ResourcesDao getResourcesDao() {
		return resourcesDao;
	}

	public void setResourcesDao(ResourcesDao resourcesDao) {
		this.resourcesDao = resourcesDao;
	}

	public MyInvocationSecurityMetadataSource(ResourcesDao resourcesDao, RolesDao rolesDao) {
		this.resourcesDao = resourcesDao;
		this.rolesDao = rolesDao;
		loadResourceDefine();
	}
	
	public void refreshResource() {
		loadResourceDefine();
	}
	
	private void loadResourceDefine() {
		resourceMap = null;
		if(null == resourceMap)
		{
			resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
			List<Roles> roles = this.rolesDao.findAll();
			for(Roles role : roles)
			{
				ConfigAttribute ca = new SecurityConfig(role.getName());
				List<String> list = this.resourcesDao.getResourcesByRole(role);
				
				for (String res : list) {
					String url = res;
					/**//*
						 * 判断资源文件和权限的对应关系，如果已经存在相关的资源url，则要通过该url为key提取出权限集合，
						 * 将权限增加到权限集合中。 sparta
						 */
					if (resourceMap.containsKey(url)) {
						Collection<ConfigAttribute> value = resourceMap.get(url);
						value.add(ca);
						resourceMap.put(url, value);
					} else {
						Collection<ConfigAttribute> atts = new ArrayList<ConfigAttribute>();
						atts.add(ca);
						resourceMap.put(url, atts);
					}
				}
			}
		}
		Set<Entry<String, Collection<ConfigAttribute>>> resourceSet = resourceMap.entrySet();
		Iterator<Entry<String, Collection<ConfigAttribute>>> iterator = resourceSet.iterator();
	}

	// According to a URL, Find out permission configuration of this URL.
	public Collection<ConfigAttribute> getAttributes(Object object)
			throws IllegalArgumentException {
		String url = ((FilterInvocation) object).getRequestUrl();
		int firstQuestionMarkIndex = url.indexOf("?");  
		if(firstQuestionMarkIndex != -1) {
			url = url.substring(0, firstQuestionMarkIndex);
		}
		Iterator<String> ite = resourceMap.keySet().iterator();
		while (ite.hasNext()) {
			String resURL = ite.next();
			if (urlMatcher.pathMatchesUrl(resURL, url)) {
				return resourceMap.get(resURL);
			}
		}
		return null;
	}
	
	public Collection<ConfigAttribute> getAttributesPage(String object)
			throws IllegalArgumentException {
		String url = object;
		int firstQuestionMarkIndex = url.indexOf("?");  
		if(firstQuestionMarkIndex != -1) {
			url = url.substring(0, firstQuestionMarkIndex);
		}
		Iterator<String> ite = resourceMap.keySet().iterator();
		while (ite.hasNext()) {
			String resURL = ite.next();
			if (urlMatcher.pathMatchesUrl(resURL, url)) {
				return resourceMap.get(resURL);
			}
		}
		return null;
	}

	public boolean supports(Class<?> clazz) {
		return true;
	}

	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}

}
