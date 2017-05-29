/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2014
 */

package cn.sf.ss.model;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import javax.validation.constraints.*;
import org.hibernate.validator.constraints.*;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.util.*;

import javacommon.sf.ss.base.*;
import javacommon.sf.ss.util.*;

import cn.org.rapid_framework.util.*;
import cn.org.rapid_framework.web.util.*;
import cn.org.rapid_framework.page.*;
import cn.org.rapid_framework.page.impl.*;
import cn.sf.ss.dao.*;
import cn.sf.ss.model.*;
import cn.sf.ss.service.*;
import cn.sf.ss.vo.query.*;


/**
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
 */


@Entity
@Table(name = "TS_RESOURCE")
public class Resources extends BaseEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "Resources";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_ENABLE = "enable";
	public static final String ALIAS_MEMO = "memo";
	public static final String ALIAS_NAME = "name";
	public static final String ALIAS_PRIORITY = "priority";
	public static final String ALIAS_TYPE = "type";
	public static final String ALIAS_URL = "url";
	
	//table
	public static final String rs_pk_resource = "ID_RESOURCE";
	
	//date formats
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
    /**
     * id       db_column: ID 
     */ 	
	@Length(max=32)
	private java.lang.String id;
    /**
     * enable       db_column: enable 
     */ 	
	@Length(max=2)
	private java.lang.String enable;
    /**
     * memo       db_column: memo 
     */ 	
	@Length(max=50)
	private java.lang.String memo;
    /**
     * name       db_column: name 
     */ 	
	@Length(max=50)
	private java.lang.String name;
    /**
     * priority       db_column: priority 
     */ 	
	
	private java.lang.Integer priority;
    /**
     * type       db_column: type 
     */ 	
	
	private java.lang.Integer type;
    /**
     * url       db_column: url 
     */ 	
	@Length(max=100)
	private java.lang.String url;
	//columns END


	public Resources(){
	}

	public Resources(
		java.lang.String id
	){
		this.id = id;
	}

	

	public void setId(java.lang.String value) {
		this.id = value;
	}
	
	@Id @GeneratedValue(generator="custom-id")
	@GenericGenerator(name="custom-id", strategy = "uuid")
	@Column(name = "ID_RESOURCE", unique = true, nullable = false, insertable = true, updatable = true, length = 32)
	public java.lang.String getId() {
		return this.id;
	}
	
	@Column(name = "IS_ENABLE", unique = false, nullable = true, insertable = true, updatable = true, length = 2)
	public java.lang.String getEnable() {
		return this.enable;
	}
	
	public void setEnable(java.lang.String value) {
		this.enable = value;
	}
	
	@Column(name = "memo", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public java.lang.String getMemo() {
		return this.memo;
	}
	
	public void setMemo(java.lang.String value) {
		this.memo = value;
	}
	
	@Column(name = "RES_NAME", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public java.lang.String getName() {
		return this.name;
	}
	
	public void setName(java.lang.String value) {
		this.name = value;
	}
	
	@Column(name = "priority", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getPriority() {
		return this.priority;
	}
	
	public void setPriority(java.lang.Integer value) {
		this.priority = value;
	}
	
	@Column(name = "RES_TYPE", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getType() {
		return this.type;
	}
	
	public void setType(java.lang.Integer value) {
		this.type = value;
	}
	
	@Column(name = "url", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public java.lang.String getUrl() {
		return this.url;
	}
	
	public void setUrl(java.lang.String value) {
		this.url = value;
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
			.append("Enable",getEnable())
			.append("Memo",getMemo())
			.append("Name",getName())
			.append("Priority",getPriority())
			.append("Type",getType())
			.append("Url",getUrl())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Resources == false) return false;
		if(this == obj) return true;
		Resources other = (Resources)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

