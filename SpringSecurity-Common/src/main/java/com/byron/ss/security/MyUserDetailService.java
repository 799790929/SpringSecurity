package com.byron.ss.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.byron.ss.dao.GroupsDao;
import com.byron.ss.dao.RolesDao;
import com.byron.ss.dao.UsersDao;
import com.byron.ss.model.Groups;
import com.byron.ss.model.Roles;
import com.byron.ss.model.Users;

public class MyUserDetailService implements UserDetailsService {
	private UsersDao usersDao;
	private GroupsDao groupsDao;
	private RolesDao rolesDao;
	public GroupsDao getGroupsDao() {
		return groupsDao;
	}
	public void setGroupsDao(GroupsDao groupsDao) {
		this.groupsDao = groupsDao;
	}
	public RolesDao getRolesDao() {
		return rolesDao;
	}
	public void setRolesDao(RolesDao rolesDao) {
		this.rolesDao = rolesDao;
	}
	public UsersDao getUsersDao() {
		return usersDao;
	}
	public void setUsersDao(UsersDao usersDao) {
		this.usersDao = usersDao;
	}

	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		Users users = this.usersDao.findByName(username);
		
		Collection<GrantedAuthority> grantedAuths = this.obtionGrantedAuthorities(users);
		
		boolean enables = true;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;
		//封装成spring security的user
		User userdetail = new User(users.getUsername(), users.getPassword(), enables, accountNonExpired, credentialsNonExpired, accountNonLocked, grantedAuths);
		return userdetail;
	}
	
	public Set<GrantedAuthority> obtionGrantedAuthorities(Users user)
	{
		Set<GrantedAuthority> authSet = new HashSet<GrantedAuthority>();
		String hql = "from com.byron.ss.model.Groups where id in (select groupId from com.byron.ss.model.UsersGroups where userId='" + user.getId() + "')";
		List<Groups> groups = groupsDao.executeFind(hql, null);
		
		for(Groups group : groups)
		{
			String hql1 = "from com.byron.ss.model.Roles where id in (select roleId from com.byron.ss.model.GroupsRoles where groupId='" + group.getId() + "')";
			List<Roles> roles = rolesDao.executeFind(hql1, null);
			for(Roles role : roles) {
				authSet.add(new GrantedAuthorityImpl(role.getName()));
			}
		}
		return authSet;
		
	}

}
