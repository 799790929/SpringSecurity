

package com.byron.ss.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.byron.ss.common.base.BaseIbatisDao;
import com.byron.ss.model.Groups;
import com.byron.ss.model.Resources;
import com.byron.ss.model.Roles;
import com.byron.ss.model.RolesResources;
import com.byron.ss.util.ConstantUtils;
import com.ibatis.sqlmap.client.SqlMapClient;

@Repository
public class ResourcesDao extends BaseIbatisDao<Resources,java.lang.String> {
	private SqlMapClient sqlMapClient;
	
	private RolesResourcesDao rolesResourcesDao;
	
	public List<Resources> getResourcesByName(String name) {
		Resources r = new Resources();
		r.setName(name);
		List<Resources> list= getSqlMapClientTemplate().queryForList(getIbatisSqlMapNamespace() + ".queryByModel", r);
		
		return list;
	}
	
	public long getRowsNotInRoleId(String roleId) {
		List<Resources> list = getResourcesNotInRowId(roleId);
		return list.size();
	}
	
	public List<Resources> queryByPageNotInRoleId(int start, int pageSize, String roleId) {
		String sqlWhere = " where id_resource not in (select resource_id from tr_role_resource where role_id='" + roleId + "')";
		return queryByPage(start, pageSize, sqlWhere);
	}
	
	public int doDeleteResource(String id) throws Exception {
		// 删除资源
		this.deleteById(id);
		
		// 删除角色资源关系
		RolesResources rrs = new RolesResources();
		rrs.setResourceId(id);
		int rows = getSqlMapClientTemplate().delete(ConstantUtils.ROLE_RESOURCE_NAMESPACE + ".deleteByModel", rrs);
        return rows;
		
	}
	
	public List<Resources> getResourcesNotInRowId(String roleId) {
		Roles r = new Roles();
		r.setId(roleId);
		List<Resources> list= getSqlMapClientTemplate().queryForList(getIbatisSqlMapNamespace() + ".getResourcesNotInRoleId", r);
		
		return list;
	}
	
	
	final static String rss_role_id = "";//资源表
	final static String rss_pk_resource = "ID_RESOURCE";//资源表id
	final static String r_pk_role = "ID_ROLE";//角色表id
	final static String r_role_name = "ROLE_NAME";//角色表name
	public List<String> getResourcesByRole(Roles role) {
		String sql = "select rs.url from ts_role r,tr_role_resource rss, ts_resource rs " +
				" where rss.role_id=r."+ r_pk_role +" and rss.resource_id=rs."+ rss_pk_resource +" and " +
				" r."+ r_role_name +"='" + role.getName() + "'";
		List<String> list= getSqlMapClientTemplate().queryForList(getIbatisSqlMapNamespace() + ".getResourcesForRole", role);
		
		return list;
		
		/*Session session = this.getSessionFactory().openSession();
		String sql = "select rs.url from ts_role r,tr_role_resource rss, ts_resource rs " +
				" where rss.role_id=r."+ r_pk_role +" and rss.resource_id=rs."+ rss_pk_resource +" and " +
				" r."+ r_role_name +"='" + role.getName() + "'";
		List<String> list = session.createSQLQuery(sql).list();
		try {
			if(null != session) {
				session.close();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return list;*/
	}
	
	public void setRolesResourcesDao(RolesResourcesDao rolesResourcesDao) {
		this.rolesResourcesDao = rolesResourcesDao;
	}
	
	public List<Resources> getResourcesByRoleId(Roles role) {
		List<Resources> list = getSqlMapClientTemplate().queryForList(getIbatisSqlMapNamespace() + ".getResourcesByRoleId", role);
		return list;
	}

	

	@Override
	public String getIbatisSqlMapNamespace() {
		return ConstantUtils.RESOURCE_NAMESPACE;
	}
	
	public void saveOrUpdate(Resources entity) {
		if(entity.getId() == null) 
			save(entity);
		else 
			update(entity);
	}

	
	
}
