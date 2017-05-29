package cn.sf.ss.action;

import java.util.List;

import javax.servlet.http.HttpSession;

import cn.sf.ss.model.Roles;
import cn.sf.ss.model.Users;

public class SSSystemMenuAction {
	/**
     * 
     * <p>系统管理中不同的用户跳转至不同的界面</p>
     * <ol>
     *  <li>大众员工跳转至车辆信息界面</li>
     *  <li>其它所有人员跳转至用户管理界面</li>
     * </ol>
     * @return 跳转的链接地址
     * @throws Exception 抛出异常
     */
    public String doSystemJump() throws Exception {
        String link = "!/ss/Users/doList.do?menu=system&indexMenu=user";
        
        return link;
    }
}
