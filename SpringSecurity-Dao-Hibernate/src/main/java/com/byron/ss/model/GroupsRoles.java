/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2014
 */

package com.byron.ss.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;

import com.byron.ss.common.base.BaseEntity;
import com.byron.ss.util.DateConvertUtils;


/**
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
 */


@Entity
@Table(name = "TR_GROUP_ROLE")
public class GroupsRoles extends BaseEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "GroupsRoles";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_GROUP_ID = "groupId";
	public static final String ALIAS_GROUP_NAME = "groupName";
	public static final String ALIAS_ROLE_ID = "roleId";
	public static final String ALIAS_ROLE_NAME = "roleName";
	
	//date formats
	
	//table
	public static final String gr_group_id = "GRP_ID";//群组角色   群组id
	public static final String gr_role_id = "ROLE_ID";//群组角色   群组id
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
    /**
     * id       db_column: ID 
     */ 	
	@Length(max=32)
	private java.lang.String id;
    /**
     * groupId       db_column: group_id 
     */ 	
	@Length(max=32)
	private java.lang.String groupId;
    /**
     * groupName       db_column: group_name 
     */ 	
	@Length(max=50)
	private java.lang.String groupName;
    /**
     * roleId       db_column: role_id 
     */ 	
	@Length(max=32)
	private java.lang.String roleId;
    /**
     * roleName       db_column: role_name 
     */ 	
	@Length(max=50)
	private java.lang.String roleName;
	//columns END


	public GroupsRoles(){
	}

	public GroupsRoles(
		java.lang.String id
	){
		this.id = id;
	}

	

	public void setId(java.lang.String value) {
		this.id = value;
	}
	
	@Id @GeneratedValue(generator="custom-id")
	@GenericGenerator(name="custom-id", strategy = "uuid")
	@Column(name = "ID_GROUP_ROLE", unique = true, nullable = false, insertable = true, updatable = true, length = 32)
	public java.lang.String getId() {
		return this.id;
	}
	
	@Column(name = "GRP_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public java.lang.String getGroupId() {
		return this.groupId;
	}
	
	public void setGroupId(java.lang.String value) {
		this.groupId = value;
	}
	
	@Column(name = "group_name", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public java.lang.String getGroupName() {
		return this.groupName;
	}
	
	public void setGroupName(java.lang.String value) {
		this.groupName = value;
	}
	
	@Column(name = "role_id", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public java.lang.String getRoleId() {
		return this.roleId;
	}
	
	public void setRoleId(java.lang.String value) {
		this.roleId = value;
	}
	
	@Column(name = "role_name", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public java.lang.String getRoleName() {
		return this.roleName;
	}
	
	public void setRoleName(java.lang.String value) {
		this.roleName = value;
	}
	
	
	
	
	//date formats
	public static final String FORMAT_CREATEDT = DATE_FORMAT;
	public static final String FORMAT_UPDATEDT = DATE_FORMAT;
	
	private java.util.Date updatedt;
    /**
     * createdt       db_column: CREATEDT 
     */ 	
	
	private java.util.Date createdt;
    /**
     * updateName       db_column: UPDATE_NAME 
     */ 	
	@Length(max=255)
	private java.lang.String updateName;
    /**
     * createName       db_column: CREATE_NAME 
     */ 	
	@Length(max=255)
	private java.lang.String createName;
	
	@Transient
	public String getCreatedtString() {
		return DateConvertUtils.format(getCreatedt(), FORMAT_CREATEDT);
	}
	public void setCreatedtString(String value) {
		setCreatedt(DateConvertUtils.parse(value, FORMAT_CREATEDT,java.util.Date.class));
	}
	
	@Column(name = "CREATEDT", unique = false, nullable = true, insertable = true, updatable = true, length = 23)
	public java.util.Date getCreatedt() {
		return this.createdt;
	}
	
	public void setCreatedt(java.util.Date value) {
		this.createdt = value;
	}
	
	@Transient
	public String getUpdatedtString() {
		return DateConvertUtils.format(getUpdatedt(), FORMAT_UPDATEDT);
	}
	public void setUpdatedtString(String value) {
		setUpdatedt(DateConvertUtils.parse(value, FORMAT_UPDATEDT,java.util.Date.class));
	}
	
	@Column(name = "UPDATEDT", unique = false, nullable = true, insertable = true, updatable = true, length = 23)
	public java.util.Date getUpdatedt() {
		return this.updatedt;
	}
	
	public void setUpdatedt(java.util.Date value) {
		this.updatedt = value;
	}
	
	@Column(name = "CREATE_NAME", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public java.lang.String getCreateName() {
		return this.createName;
	}
	
	public void setCreateName(java.lang.String value) {
		this.createName = value;
	}
	
	@Column(name = "UPDATE_NAME", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public java.lang.String getUpdateName() {
		return this.updateName;
	}
	
	public void setUpdateName(java.lang.String value) {
		this.updateName = value;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("GroupId",getGroupId())
			.append("GroupName",getGroupName())
			.append("RoleId",getRoleId())
			.append("RoleName",getRoleName())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof GroupsRoles == false) return false;
		if(this == obj) return true;
		GroupsRoles other = (GroupsRoles)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

