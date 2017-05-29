package javacommon.sf.ss.util;

import javax.persistence.Column;
import javax.persistence.Table;

import cn.sf.ss.model.Users;

public class HibernateToolsUtil {
	/**
     * 获得表名
     * 
     * @param clazz 映射到数据库的po类
     * @return String
     */
    @SuppressWarnings("unchecked")
    public static String getTableName(Class clazz) {
        Table annotation = (Table)clazz.getAnnotation(Table.class);
        if(annotation != null){
            return annotation.name();
        }
 
        return null;
    }
 
    /**
     * 获得列名
     * 
     * @param clazz 映射到数据库的po类
     * @param icol 第几列
     * @return String
     */
    @SuppressWarnings("unchecked")
    public static String getColumnName(Class clazz, String name) {
    	Column annotation = (Column)clazz.getAnnotation(Column.class);
    	if(null != annotation) {
    		return annotation.table();
    	}
        return null;
    }
    
    public static void main(String[] args) {
		String c = getColumnName(Users.class, "");
	}
}
