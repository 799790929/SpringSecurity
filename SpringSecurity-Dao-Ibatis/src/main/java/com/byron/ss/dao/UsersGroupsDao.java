

package com.byron.ss.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.byron.ss.common.base.BaseIbatisDao;
import com.byron.ss.model.Groups;
import com.byron.ss.model.Users;
import com.byron.ss.model.UsersGroups;
import com.byron.ss.util.ConstantUtils;
import com.ibatis.sqlmap.client.SqlMapClient;

@Repository
public class UsersGroupsDao extends BaseIbatisDao<UsersGroups,java.lang.String> {
	private SqlMapClient sqlMapClient;
	
	public List<UsersGroups> getUsersGroupsByUserId (String userId) {
		List<UsersGroups> list = getSqlMapClientTemplate().queryForList(getIbatisSqlMapNamespace() + ".getUsersGroupsByUserId", userId);
		
		return list;
	}
	
	public List<UsersGroups> queryByModel(UsersGroups ug) {
		List<UsersGroups> list = getSqlMapClientTemplate().queryForList(getIbatisSqlMapNamespace() + ".queryByModel", ug);
		
		return list;
	}
	
	public boolean hasUserByGroup(Groups group) throws Exception {
		boolean exists = false;
		
		/*String sql = "select u.username from ts_user u,tr_user_group ug,ts_group g where u.id_user=ug.user_id and g.id_group = ug.grp_id and g.id_group='" + group.getId() + "'";
		List<String> list= this.sqlMapClient.queryForList(sql);*/
		List<Users> list = getSqlMapClientTemplate().queryForList(ConstantUtils.USER_NAMESPACE + ".getUsersByGroup", group);
		
		
		if (null != list && list.size() > 0) {
			exists = true;
		}
		
		/*Session session = this.getSessionFactory().openSession();
		
		StringBuffer hql = new StringBuffer();
		hql.append("SELECT {u.*} ");
		hql.append("FROM " + HibernateToolsUtil.getTableName(Users.class) + " u ");
		hql.append("," + HibernateToolsUtil.getTableName(UsersGroups.class) + " ug ");
		hql.append("," + HibernateToolsUtil.getTableName(Groups.class) + " g ");
		hql.append(" where 1=1 and u."+ Users.u_pk_user +"=ug.user_id and g."+ Groups.g_pk_group +"=ug."+ UsersGroups.ug_group_id +" ");
		if(null == group) {
			throw new Exception("群组不能为空!");
		}
		hql.append(" and g."+ Groups.g_pk_group +"='" + group.getId() + "' ");
		logger.info("\nsql->[" + hql.toString() + "]");
		SQLQuery query = session.createSQLQuery(hql.toString());
		query.addEntity("u", Users.class);
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
		return ConstantUtils.USER_GROUP_NAMESPACE;
	}
	
	public void saveOrUpdate(UsersGroups entity) {
		if(entity.getId() == null) 
			save(entity);
		else 
			update(entity);
	}

}
