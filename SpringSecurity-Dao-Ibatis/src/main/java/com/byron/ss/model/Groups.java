/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2014
 */

package com.byron.ss.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.byron.ss.common.base.BaseEntity;
import com.byron.ss.util.DateConvertUtils;


/**
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
 */


public class Groups extends BaseEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "Groups";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_ENABLE = "enable";
	public static final String ALIAS_NAME = "name";
	
	public static final String FORMAT_UPDATEDT = DATE_FORMAT;
	public static final String FORMAT_CREATEDT = DATE_FORMAT;
	
	//table
	public final static String g_pk_group = "ID_GROUP";
	
	//date formats
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
    /**
     * id       db_column: ID 
     */ 	
	private java.lang.String id;
    /**
     * enable       db_column: enable 
     */ 	
	private java.lang.String enable;
    /**
     * name       db_column: name 
     */ 	
	private java.lang.String name;
	
	private java.lang.String descri;
	//columns END

	public Groups(){
	}

	public Groups(
		java.lang.String id
	){
		this.id = id;
	}

	

	public void setId(java.lang.String value) {
		this.id = value;
	}
	
	public java.lang.String getId() {
		return this.id;
	}
	
	public java.lang.String getEnable() {
		return this.enable;
	}
	
	public void setEnable(java.lang.String value) {
		this.enable = value;
	}
	
	public java.lang.String getName() {
		return this.name;
	}
	
	public void setName(java.lang.String value) {
		this.name = value;
	}
	public java.lang.String getDescri() {
		return this.descri;
	}
	
	public void setDescri(java.lang.String value) {
		this.descri = value;
	}
	
	
	

	

	private java.util.Date updatedt;
    /**
     * createdt       db_column: CREATEDT 
     */ 	
	
	private java.util.Date createdt;
    /**
     * updateName       db_column: UPDATE_NAME 
     */ 	
	private java.lang.String updateName;
    /**
     * createName       db_column: CREATE_NAME 
     */ 	
	private java.lang.String createName;
	
	public String getCreatedtString() {
		return DateConvertUtils.format(getCreatedt(), FORMAT_CREATEDT);
	}
	public void setCreatedtString(String value) {
		setCreatedt(DateConvertUtils.parse(value, FORMAT_CREATEDT,java.util.Date.class));
	}
	
	public java.util.Date getCreatedt() {
		return this.createdt;
	}
	
	public void setCreatedt(java.util.Date value) {
		this.createdt = value;
	}
	
	public String getUpdatedtString() {
		return DateConvertUtils.format(getUpdatedt(), FORMAT_UPDATEDT);
	}
	public void setUpdatedtString(String value) {
		setUpdatedt(DateConvertUtils.parse(value, FORMAT_UPDATEDT,java.util.Date.class));
	}
	
	public java.util.Date getUpdatedt() {
		return this.updatedt;
	}
	
	public void setUpdatedt(java.util.Date value) {
		this.updatedt = value;
	}
	
	public java.lang.String getCreateName() {
		return this.createName;
	}
	
	public void setCreateName(java.lang.String value) {
		this.createName = value;
	}
	
	public java.lang.String getUpdateName() {
		return this.updateName;
	}
	
	public void setUpdateName(java.lang.String value) {
		this.updateName = value;
	}
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Enable",getEnable())
			.append("Name",getName())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Groups == false) return false;
		if(this == obj) return true;
		Groups other = (Groups)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

