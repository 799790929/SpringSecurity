

package com.byron.ss.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.byron.ss.common.base.BaseIbatisDao;
import com.byron.ss.model.Resources;
import com.byron.ss.model.Roles;
import com.byron.ss.model.RolesResources;
import com.byron.ss.util.ConstantUtils;
import com.ibatis.sqlmap.client.SqlMapClient;

@Repository
public class RolesResourcesDao extends BaseIbatisDao<RolesResources,java.lang.String> {
	private SqlMapClient sqlMapClient;

	public List<RolesResources> queryByModel(RolesResources rrs) {
		List<RolesResources> list = getSqlMapClientTemplate().queryForList(getIbatisSqlMapNamespace() + ".queryByModel", rrs);
		return list;
	}
    
	
	public boolean hasResourceByRole(Roles role) throws Exception {
		boolean exists = false;
		
		/*String sql = "select g.group_name from ts_role r,tr_role_resource rrs,ts_resource rs where r.id_role=rrs.role_id and rs.id_resource=rrs.resource_id and r.id_role='" + role.getId() + "'";
		List<Object> list= this.sqlMapClient.queryForList(sql);*/
		List<Resources> list = getSqlMapClientTemplate().queryForList(ConstantUtils.RESOURCE_NAMESPACE + ".getResourcesByRole", role);
		
		
		if (null != list && list.size() > 0) {
			exists = true;
		}
		/*Session session = this.getSessionFactory().openSession();
		
		StringBuffer hql = new StringBuffer();
		hql.append("SELECT {rs.*} ");
		hql.append("FROM " + HibernateToolsUtil.getTableName(Resources.class) + " rs ");
		hql.append("," + HibernateToolsUtil.getTableName(RolesResources.class) + " rrs ");
		hql.append("," + HibernateToolsUtil.getTableName(Roles.class) + " r ");
		hql.append(" where 1=1 and rs."+ Resources.rs_pk_resource +"=rrs.resource_id and r."+ Roles.r_pk_role +"=rrs.role_id ");
		if(null == role) {
			throw new Exception("角色不能为空!");
		}
		hql.append(" and r."+ Roles.r_pk_role +"='" + role.getId() + "' ");
		logger.info("\nsql->[" + hql.toString() + "]");
		SQLQuery query = session.createSQLQuery(hql.toString());
		query.addEntity("rs", Resources.class);
		List<Object> list = query.list();
		if(null != list && list.size() > 0) {
			exists = true;
		}
		if(null != session) {
			try {
				session.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}*/
		
		return exists;
	}

	@Override
	public String getIbatisSqlMapNamespace() {
		return ConstantUtils.ROLE_RESOURCE_NAMESPACE;
	}
	
	public void saveOrUpdate(RolesResources entity) {
		if(entity.getId() == null) 
			save(entity);
		else 
			update(entity);
	}

	

}
