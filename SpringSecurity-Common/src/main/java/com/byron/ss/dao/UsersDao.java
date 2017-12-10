/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2014
 */

package com.byron.ss.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.byron.ss.common.base.BaseHibernateDao;
import com.byron.ss.model.Users;

@Repository
public class UsersDao extends BaseHibernateDao<Users,java.lang.String>{

	public Class getEntityClass() {
		return Users.class;
	}
	
	/*public Page findPage(UsersQuery query) {
        //XsqlBuilder syntax,please see http://code.google.com/p/rapid-xsqlbuilder
        // [column]为字符串拼接, {column}为使用占位符. [column]为使用字符串拼接,如username='[username]',偷懒时可以使用字符串拼接 
        // [column] 为PageRequest的属性
		String sql = "select t from Users t where 1=1 "
			  	+ "/~ and t.enable = {enable} ~/"
			  	+ "/~ and t.password = {password} ~/"
			  	+ "/~ and t.username = {username} ~/"
				+ "/~ order by [sortColumns] ~/";

        //生成sql2的原因是为了不喜欢使用xsqlbuilder的同学，请修改生成器模板，删除本段的生成
        StringBuilder sql2 = new StringBuilder("select t from Users t where 1=1 ");
        if(isNotEmpty(query.getId())) {
            sql2.append(" and  t.id = :id ");
        }
        if(isNotEmpty(query.getEnable())) {
            sql2.append(" and  t.enable = :enable ");
        }
        if(isNotEmpty(query.getPassword())) {
            sql2.append(" and  t.password = :password ");
        }
        if(isNotEmpty(query.getUsername())) {
            sql2.append(" and  t.username = :username ");
        }
        if(isNotEmpty(query.getSortColumns())) {
            sql2.append(" order by :sortColumns ");
        }	
        
		return pageQuery(sql,query);
	}*/
	
	public Users findByName(String name) {
		List<Users> objs = this.findBy("username", name, "");
//		List<Users> objs = this.findByCriteria(Restrictions.eq("account", name));
		return (objs.size() == 0 ? null : objs.get(0)); 
	}
}
