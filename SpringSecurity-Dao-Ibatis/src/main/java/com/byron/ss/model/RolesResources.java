
package com.byron.ss.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.byron.ss.common.base.BaseEntity;
import com.byron.ss.util.DateConvertUtils;


/**
 * @author byron
 * @version 1.0
 * @since 1.0
 */


public class RolesResources extends BaseEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "RolesResources";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_RESOURCE_ID = "resourceId";
	public static final String ALIAS_RESOURCE_NAME = "resourceName";
	public static final String ALIAS_ROLE_ID = "roleId";
	public static final String ALIAS_ROLE_NAME = "roleName";
	
	//date formats
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
    /**
     * id       db_column: ID 
     */ 	
	private java.lang.String id;
    /**
     * resourceId       db_column: resource_id 
     */ 	
	private java.lang.String resourceId;
    /**
     * resourceName       db_column: resource_name 
     */ 	
	private java.lang.String resourceName;
    /**
     * roleId       db_column: role_id 
     */ 	
	private java.lang.String roleId;
    /**
     * roleName       db_column: role_name 
     */ 	
	private java.lang.String roleName;
	//columns END


	public RolesResources(){
	}

	public RolesResources(
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
	
	public java.lang.String getResourceId() {
		return this.resourceId;
	}
	
	public void setResourceId(java.lang.String value) {
		this.resourceId = value;
	}
	
	public java.lang.String getResourceName() {
		return this.resourceName;
	}
	
	public void setResourceName(java.lang.String value) {
		this.resourceName = value;
	}
	
	public java.lang.String getRoleId() {
		return this.roleId;
	}
	
	public void setRoleId(java.lang.String value) {
		this.roleId = value;
	}
	
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
			.append("ResourceId",getResourceId())
			.append("ResourceName",getResourceName())
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
		if(obj instanceof RolesResources == false) return false;
		if(this == obj) return true;
		RolesResources other = (RolesResources)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

