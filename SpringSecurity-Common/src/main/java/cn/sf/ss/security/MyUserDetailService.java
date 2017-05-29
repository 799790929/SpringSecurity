package cn.sf.ss.security;

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

import cn.sf.ss.dao.GroupsDao;
import cn.sf.ss.dao.RolesDao;
import cn.sf.ss.dao.UsersDao;
import cn.sf.ss.model.Groups;
import cn.sf.ss.model.Roles;
import cn.sf.ss.model.Users;

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
		String hql = "from cn.sf.ss.model.Groups where id in (select groupId from cn.sf.ss.model.UsersGroups where userId='" + user.getId() + "')";
		List<Groups> groups = groupsDao.executeFind(hql, null);
		
		for(Groups group : groups)
		{
			String hql1 = "from cn.sf.ss.model.Roles where id in (select roleId from cn.sf.ss.model.GroupsRoles where groupId='" + group.getId() + "')";
			List<Roles> roles = rolesDao.executeFind(hql1, null);
			for(Roles role : roles) {
				authSet.add(new GrantedAuthorityImpl(role.getName()));
			}
		}
		return authSet;
		
	}

}
