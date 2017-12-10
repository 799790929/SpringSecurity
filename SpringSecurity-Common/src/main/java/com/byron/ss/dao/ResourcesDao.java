/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2014
 */

package com.byron.ss.dao;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.byron.ss.common.base.BaseHibernateDao;
import com.byron.ss.model.Resources;
import com.byron.ss.model.Roles;

@Repository
public class ResourcesDao extends BaseHibernateDao<Resources,java.lang.String>{
	
	public Class getEntityClass() {
		return Resources.class;
	}
	
	/*public Page findPage(ResourcesQuery query) {
        //XsqlBuilder syntax,please see http://code.google.com/p/rapid-xsqlbuilder
        // [column]为字符串拼接, {column}为使用占位符. [column]为使用字符串拼接,如username='[username]',偷懒时可以使用字符串拼接 
        // [column] 为PageRequest的属性
		String sql = "select t from Resources t where 1=1 "
			  	+ "/~ and t.enable = {enable} ~/"
			  	+ "/~ and t.memo = {memo} ~/"
			  	+ "/~ and t.name = {name} ~/"
			  	+ "/~ and t.priority = {priority} ~/"
			  	+ "/~ and t.type = {type} ~/"
			  	+ "/~ and t.url = {url} ~/"
				+ "/~ order by [sortColumns] ~/";

        //生成sql2的原因是为了不喜欢使用xsqlbuilder的同学，请修改生成器模板，删除本段的生成
        StringBuilder sql2 = new StringBuilder("select t from Resources t where 1=1 ");
        if(isNotEmpty(query.getId())) {
            sql2.append(" and  t.id = :id ");
        }
        if(isNotEmpty(query.getEnable())) {
            sql2.append(" and  t.enable = :enable ");
        }
        if(isNotEmpty(query.getMemo())) {
            sql2.append(" and  t.memo = :memo ");
        }
        if(isNotEmpty(query.getName())) {
            sql2.append(" and  t.name = :name ");
        }
        if(isNotEmpty(query.getPriority())) {
            sql2.append(" and  t.priority = :priority ");
        }
        if(isNotEmpty(query.getType())) {
            sql2.append(" and  t.type = :type ");
        }
        if(isNotEmpty(query.getUrl())) {
            sql2.append(" and  t.url = :url ");
        }
        if(isNotEmpty(query.getSortColumns())) {
            sql2.append(" order by :sortColumns ");
        }	
        
		return pageQuery(sql,query);
	}*/
	
	final static String rss_role_id = "";//资源表
	final static String rss_pk_resource = "ID_RESOURCE";//资源表id
	final static String r_pk_role = "ID_ROLE";//角色表id
	final static String r_role_name = "ROLE_NAME";//角色表name
	public List<String> getResourcesByRole(Roles role) {
		Session session = this.getSessionFactory().openSession();
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
		return list;
	}
}
