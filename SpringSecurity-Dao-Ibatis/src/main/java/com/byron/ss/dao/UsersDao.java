
package com.byron.ss.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.byron.ss.common.base.BaseIbatisDao;
import com.byron.ss.model.Groups;
import com.byron.ss.model.Resources;
import com.byron.ss.model.Roles;
import com.byron.ss.model.Users;
import com.byron.ss.util.ConstantUtils;
import com.ibatis.sqlmap.client.SqlMapClient;

@Repository
public class UsersDao extends BaseIbatisDao<Users,java.lang.String> {
	private SqlMapClient sqlMapClient;
	
	public long getRowsNotInGroupId(String groupId) {
		Groups g = new Groups();
		g.setId(groupId);
		List<Users> list = getSqlMapClientTemplate().queryForList(getIbatisSqlMapNamespace() + ".getUsersNotInGroupId", g);
		return list.size();
	}
	
	public List<Users> queryByPageNotInGroupId(int start, int pageSize, String groupId) {
		String sqlWhere = " where id_user not in (select user_id from tr_user_group where grp_id='" + groupId + "') ";
		
		return queryByPage(start, pageSize, sqlWhere);
	}

	public List<Groups> getGroupsByUser(Users user) {
		List<Groups> groups = getSqlMapClientTemplate().queryForList(getIbatisSqlMapNamespace() + ".getGroupsByUser", user);
		return groups;
	}
	
	public List<Roles> getRolesByUser(Users user) {
		List<Roles> listRoles = getSqlMapClientTemplate().queryForList(getIbatisSqlMapNamespace() + ".getRolesByUser", user);
		return listRoles;
		
	}
	
	public List<Resources> getResourcesByUser(Users user) throws Exception {
		List<Resources> listRes = getSqlMapClientTemplate().queryForList(getIbatisSqlMapNamespace() + ".getResourcesByUser", user);
		return listRes;
		
	}
	
	public List<Users> getUsersByGroupId(String groupId) {
		Groups g = new Groups();
		g.setId(groupId);
		List<Users> list = getSqlMapClientTemplate().queryForList(getIbatisSqlMapNamespace() + ".getUsersByGroupId", g);
		return list;
	}
	
	public List<Users> getUsersNotInGroupId(String groupId) {
		Groups g = new Groups();
		g.setId(groupId);
		List<Users> list = getSqlMapClientTemplate().queryForList(getIbatisSqlMapNamespace() + ".getUsersNotInGroupId", g);
		return list;
	}
	
	public void delete(Users user) {
		int affectCount = getSqlMapClientTemplate().delete(getDeleteStatement(), user.getId());
	}
	
	public List<Groups> getGroupsByUserId(Users user) {
		List<Groups> groups = getSqlMapClientTemplate().queryForList(getIbatisSqlMapNamespace() + ".getGroupsByUserId", user);
		return groups;
	}
	
	public Users findByName(String name) {
		/*List<Users> objs = this.findBy("username", name, "");*/
		Users user = new Users();
		user.setUsername(name);
		List<Users> objs = getSqlMapClientTemplate().queryForList(getIbatisSqlMapNamespace() + ".queryUsers", user);
		return (objs.size() == 0 ? null : objs.get(0)); 
	}
	
	public List<Users> queryUsersByName(String name) {
		/*List<Users> objs = this.findBy("username", name, "");*/
		Users user = new Users();
		user.setUsername(name);
		List<Users> objs = getSqlMapClientTemplate().queryForList(getIbatisSqlMapNamespace() + ".queryUsers", user);
		return objs;
	}
	
	public long getRowsBySql(String sqlWhere) {
		
		Long count = (Long) getSqlMapClientTemplate().queryForObject(getIbatisSqlMapNamespace() + ".countBySqlWhere", sqlWhere);
	
		return count;
	}
	
	public Users findByProperty(String propertyName, Object propertyValue) {
		return null;
	}

	@Override
	public String getIbatisSqlMapNamespace() {
		return ConstantUtils.USER_NAMESPACE;
	}
	
	public void saveOrUpdate(Users entity) {
		if(entity.getId() == null) 
			save(entity);
		else 
			update(entity);
	}
	
}
