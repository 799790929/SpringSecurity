/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2014
 */

package com.byron.ss.dao;

import static cn.org.rapid_framework.util.ObjectUtils.isNotEmpty;

import org.springframework.stereotype.Repository;

import cn.org.rapid_framework.page.Page;

import com.byron.ss.common.base.BaseHibernateDao;
import com.byron.ss.model.Groups;
import com.byron.ss.vo.query.GroupsQuery;

@Repository
public class GroupsDao extends BaseHibernateDao<Groups,java.lang.String>{

	public Class getEntityClass() {
		return Groups.class;
	}
	
	public Page findPage(GroupsQuery query) {
        //XsqlBuilder syntax,please see http://code.google.com/p/rapid-xsqlbuilder
        // [column]为字符串拼接, {column}为使用占位符. [column]为使用字符串拼接,如username='[username]',偷懒时可以使用字符串拼接 
        // [column] 为PageRequest的属性
		String sql = "select t from Groups t where 1=1 "
			  	+ "/~ and t.enable = {enable} ~/"
			  	+ "/~ and t.name = {name} ~/"
				+ "/~ order by [sortColumns] ~/";

        //生成sql2的原因是为了不喜欢使用xsqlbuilder的同学，请修改生成器模板，删除本段的生成
        StringBuilder sql2 = new StringBuilder("select t from Groups t where 1=1 ");
        if(isNotEmpty(query.getId())) {
            sql2.append(" and  t.id = :id ");
        }
        if(isNotEmpty(query.getEnable())) {
            sql2.append(" and  t.enable = :enable ");
        }
        if(isNotEmpty(query.getName())) {
            sql2.append(" and  t.name = :name ");
        }
        if(isNotEmpty(query.getSortColumns())) {
            sql2.append(" order by :sortColumns ");
        }	
        
		return pageQuery(sql,query);
	}
	

}
