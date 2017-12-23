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
import com.byron.ss.util.ConstantUtils;
import com.ibatis.sqlmap.client.SqlMapClient;

@Repository
public class GroupsRolesDao extends BaseIbatisDao<GroupsRoles,java.lang.String> {
	private SqlMapClient sqlMapClient;
	
	
    /**
     * 是否有组在该角色下
     * <p>Description: TODO</p>
     * @param role
     * @return
     * @throws Exception
     */
	public boolean hasGroupByRole(Roles role) throws Exception {
		boolean exists = false;
		
		/*String sql = "select g.group_name from ts_group g,tr_group_role gr,ts_role r where g.id_group=gr.grp_id and r.id_role=gr.role_id and r.id_role='" + role.getId() + "'";
		List<Object> list= this.sqlMapClient.queryForList(sql);*/
		List<Groups> list = getSqlMapClientTemplate().queryForList(ConstantUtils.GROUP_NAMESPACE + ".getGroupsByRole", role);
		
		if (null != list && list.size() > 0) {
			exists = true;
		}
		/*Session session = this.getSessionFactory().openSession();
		
		StringBuffer hql = new StringBuffer();
		hql.append("SELECT {g.*} ");
		hql.append("FROM " + HibernateToolsUtil.getTableName(Groups.class) + " g ");
		hql.append("," + HibernateToolsUtil.getTableName(GroupsRoles.class) + " gr ");
		hql.append("," + HibernateToolsUtil.getTableName(Roles.class) + " r ");
		hql.append(" where 1=1 and g."+ Groups.g_pk_group +"=gr."+ GroupsRoles.gr_group_id +" and r."+ Roles.r_pk_role +"=gr."+ GroupsRoles.gr_role_id +" ");
		if(null == role) {
			throw new Exception("角色不能为空!");
		}
		hql.append(" and r."+ Roles.r_pk_role +"='" + role.getId() + "' ");
		logger.info("\nsql->[" + hql.toString() + "]");
		SQLQuery query = session.createSQLQuery(hql.toString());
		query.addEntity("g", Groups.class);
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

	public List<GroupsRoles> queryByModel(GroupsRoles gr) {
		List<GroupsRoles> list = getSqlMapClientTemplate().queryForList(getIbatisSqlMapNamespace() + ".queryByModel", gr);
		return list;
	}

	@Override
	public String getIbatisSqlMapNamespace() {
		return ConstantUtils.GROUP_ROLE_NAMESPACE;
	}
	
	public void saveOrUpdate(GroupsRoles entity) {
		if(entity.getId() == null) 
			save(entity);
		else 
			update(entity);
	}

}
