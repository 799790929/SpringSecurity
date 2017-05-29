package com.byron.ss.tag;
import java.io.IOException;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;
/**
* PermissionBodyTag 根据是否有权限显示body内容
 */
public class PermissionBodyTag extends BodyTagSupport {
	boolean permission;
	public boolean isPermission() {
		return permission;
	}
	public void setPermission(boolean permission) {
		this.permission = permission;
	}
	public int doStartTag() throws JspTagException {
		if (permission) {
			return EVAL_BODY_INCLUDE;
		} else {
			return SKIP_BODY;
		}
	}
	public int doEndTag() throws JspTagException {
		try {
			if (bodyContent != null) {
				bodyContent.writeOut(bodyContent.getEnclosingWriter());
			}else{
			}
		} catch (IOException e) {
			throw new JspTagException("IO ERROR:" + e.getMessage());
		}
		return EVAL_PAGE;
	}
	public void doInitBody() throws JspTagException {
	}
	public void setBodyContent(BodyContent bodyContent) {
		this.bodyContent = bodyContent;
	}
}