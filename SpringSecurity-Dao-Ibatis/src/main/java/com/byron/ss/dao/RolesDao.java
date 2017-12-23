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
import com.byron.ss.model.Roles;
import com.byron.ss.util.ConstantUtils;
import com.ibatis.sqlmap.client.SqlMapClient;

@Repository
public class RolesDao extends BaseIbatisDao<Roles,java.lang.String> {
	private SqlMapClient sqlMapClient;
	
	public List<Roles> getRolesByName(String name) {
		Roles role = new Roles();
		role.setName(name);
		List<Roles> list = getSqlMapClientTemplate().queryForList(getIbatisSqlMapNamespace() + ".queryByModel", role);
		return list;
	}

    @Override
	public String getIbatisSqlMapNamespace() {
		return ConstantUtils.ROLE_NAMESPACE;
	}
	
	public void saveOrUpdate(Roles entity) {
		if(entity.getId() == null) 
			save(entity);
		else 
			update(entity);
	}


}
