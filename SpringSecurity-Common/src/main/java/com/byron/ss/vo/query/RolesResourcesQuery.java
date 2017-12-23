

package com.byron.ss.vo.query;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.byron.ss.common.base.BaseQuery;


/**
 * @author byron
 * @version 1.0
 * @since 1.0
 */


public class RolesResourcesQuery extends BaseQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    

	/** id */
	private java.lang.String id;
	/** resourceId */
	private java.lang.String resourceId;
	/** resourceName */
	private java.lang.String resourceName;
	/** roleId */
	private java.lang.String roleId;
	/** roleName */
	private java.lang.String roleName;

	public java.lang.String getId() {
		return this.id;
	}
	
	public void setId(java.lang.String value) {
		this.id = value;
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
	

	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
	
}

