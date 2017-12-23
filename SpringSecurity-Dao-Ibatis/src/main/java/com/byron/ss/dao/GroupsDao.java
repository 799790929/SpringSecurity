/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2014
 */

package com.byron.ss.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.byron.ss.common.base.BaseIbatisDao;
import com.byron.ss.model.Groups;
import com.byron.ss.model.GroupsRoles;
import com.byron.ss.model.Roles;
import com.byron.ss.model.Users;
import com.byron.ss.util.ConstantUtils;
import com.ibatis.sqlmap.client.SqlMapClient;

@Repository
public class GroupsDao extends BaseIbatisDao<Groups,java.lang.String> {
	private SqlMapClient sqlMapClient;
	
	/**增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,请注意大小写*/
	private GroupsRolesDao groupsRolesDao;
	
	public long getRowsNotInRoleId(String roleId) {
		GroupsRoles gr = new GroupsRoles();
		gr.setRoleId(roleId);
		List<Groups> list = getSqlMapClientTemplate().queryForList(getIbatisSqlMapNamespace() + ".getGroupsNotInRoleId", gr);
		return list.size();
	}
	
	public List<Groups> queryByPageNotInRoleId(int start, int pageSize, String roleId) {
		String sqlWhere = " where id_group not in (select grp_id from tr_group_role where role_id='" + roleId + "') ";
		
		return queryByPage(start, pageSize, sqlWhere);
	}
	
	public int doDeleteGroup(String id) throws Exception {
		// 删除组
		this.deleteById(id);
		
		// 删除组角色关系
		GroupsRoles gr = new GroupsRoles();
		gr.setGroupId(id);
		int rows = getSqlMapClientTemplate().delete(ConstantUtils.GROUP_ROLE_NAMESPACE + ".deleteByModel", gr);
		
		return rows;
	}
	
	public List<Roles> getRolesByGroupId(Groups group) {
		List<Roles> roles = getSqlMapClientTemplate().queryForList(getIbatisSqlMapNamespace() + ".getRolesByGroupId", group);
		return roles;
	}
	
	public List<Users> getUsersByGroupId(Groups group) {
		List<Users> users = getSqlMapClientTemplate().queryForList(getIbatisSqlMapNamespace() + ".getUsersByGroupId", group);
		return users;
	}

	public void setGroupsRolesDao(GroupsRolesDao groupsRolesDao) {
		this.groupsRolesDao = groupsRolesDao;
	}
	
	public List<Groups> getGroupsByRoleId(Roles role) {
		List<Groups> list = getSqlMapClientTemplate().queryForList(getIbatisSqlMapNamespace() + ".getGroupsByRoleId", role);
		return list;
	}

	public List<Groups> getGroupsByName(String name) {
		Groups group = new Groups();
		group.setName(name);
		List<Groups> list = getSqlMapClientTemplate().queryForList(getIbatisSqlMapNamespace() + ".queryGroups", group);
		return list;
	}
	
	public List<Groups> getGroupsNotInRoleId(String roleId) {
		GroupsRoles gr = new GroupsRoles();
		gr.setRoleId(roleId);
		List<Groups> list = getSqlMapClientTemplate().queryForList(getIbatisSqlMapNamespace() + ".getGroupsNotInRoleId", gr);
		return list;
	}

	@Override
	public String getIbatisSqlMapNamespace() {
		return ConstantUtils.GROUP_NAMESPACE;
	}
	
	public void saveOrUpdate(Groups entity) {
		if(entity.getId() == null) 
			save(entity);
		else 
			update(entity);
	}
	
	
}
