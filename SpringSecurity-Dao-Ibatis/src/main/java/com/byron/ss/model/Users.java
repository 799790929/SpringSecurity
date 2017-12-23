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


public class Users extends BaseEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "Users";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_ENABLE = "enable";
	public static final String ALIAS_PASSWORD = "password";
	public static final String ALIAS_USERNAME = "username";
	
	//table
	public static final String u_pk_user = "ID_USER";
	
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
     * password       db_column: password 
     */ 	
	private java.lang.String password;
    /**
     * username       db_column: username 
     */ 	
	private java.lang.String username;
	
	private java.lang.String realname;
	
	private java.lang.String descri;
	
	/*@Length(max=100)
	private java.lang.String brand;
	
	@Length(max=100)
	private java.lang.String dealerCode;
	
	@Length(max=100)
	private java.lang.String dealerName;
	
	@Length(max=100)
	private java.lang.String dealer;
	
	@Length(max=3)
	private java.lang.String userType;
	
	@Length(max=50)
	private java.lang.String ivUserId;
	
	private java.lang.String isRsscCode;*/
	//columns END


	public Users(){
	}

	public Users(
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
	
	public java.lang.String getPassword() {
		return this.password;
	}
	
	public void setPassword(java.lang.String value) {
		this.password = value;
	}
	
	public java.lang.String getUsername() {
		return this.username;
	}
	
	public void setUsername(java.lang.String value) {
		this.username = value;
	}
	
	public java.lang.String getRealname() {
		return this.realname;
	}
	
	public void setRealname(java.lang.String value) {
		this.realname = value;
	}
	public java.lang.String getDescri() {
		return this.descri;
	}
	
	public void setDescri(java.lang.String value) {
		this.descri = value;
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
	
	
	
	
	/*@Column(name = "brand", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public java.lang.String getBrand() {
		return this.brand;
	}
	
	public void setBrand(java.lang.String value) {
		this.brand = value;
	}
	
	@Column(name = "dealer_code", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public java.lang.String getDealerCode() {
		return this.dealerCode;
	}
	
	public void setDealerCode(java.lang.String value) {
		this.dealerCode = value;
	}
	
	@Column(name = "dealer_name", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public java.lang.String getDealerName() {
		return this.dealerName;
	}
	
	public void setDealerName(java.lang.String value) {
		this.dealerName = value;
	}
	
	@Column(name = "RSSC_CODE", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public java.lang.String getDealer() {
		return this.dealer;
	}
	
	public void setDealer(java.lang.String value) {
		this.dealer = value;
	}
	
	@Column(name = "USERTYPE", unique = false, nullable = true, insertable = true, updatable = true, length = 3)
	public java.lang.String getUserType() {
		return this.userType;
	}
	
	public void setUserType(java.lang.String value) {
		this.userType = value;
	}
	
	@Column(name = "IV_USER_ID", unique = true, nullable = true, insertable = true, updatable = true, length = 60)
	public java.lang.String getIvUserId() {
		return ivUserId;
	}

	public void setIvUserId(java.lang.String ivUserId) {
		this.ivUserId = ivUserId;
	}
	
	@Column(name = "is_rssc_code", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.String getIsRsscCode() {
		return isRsscCode;
	}

	public void setIsRsscCode(java.lang.String isRsscCode) {
		this.isRsscCode = isRsscCode;
	}*/

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Enable",getEnable())
			.append("Password",getPassword())
			.append("Username",getUsername())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Users == false) return false;
		if(this == obj) return true;
		Users other = (Users)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

