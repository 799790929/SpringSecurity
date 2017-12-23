# SpringSecurity
1.应用说明

SpringSecurity是maven管理的应用,采用mvc模式,包括若干子项目。

SpringSecurity-Common封装了spring-security的配置,提供权限管理的service层,控制层才采用springmvc或struts2,持久层采用hibernate或ibatis;

SpringSecurity-Action封装了struts的控制层代码;

SpringSecurity-Controller封装了springmvc的控制层代码;

SpringSecurity-Dao-Hibernate持久层采用hibernate;

SpringSecurity-Dao-Ibatis持久层采用ibatis;

SpringSecurity-Web用于集成测试;

SpringSecurity-Web/pom.xml,SpringSecurity-Action与SpringSecurity-Controller选一个
SpringSecurity-Web/pom.xml,SpringSecurity-Dao-Hibernate与SpringSecurity-Dao-Ibatis选一个

SpringSecurity-Common封装了集成spring-security的配置,实现了管理UAL的一些基本逻辑

读者可根据实际情况扩展或修改jar包

sql初始化语句：

SpringSecurity-Common/sql.mysql.init

SpringSecurity-Common/sql.oracle.init

1.1 Action与Controller的选择取决于用户主框架用的是struts或者springmvc作控制层

SpringSecurity-Web/web.xml选择相应的过滤器

SpringSecurity-Web/pom.xml配置相应的依赖

2.在本地安装完成后,启动访问

http://localhost:8080/SpringSecurity-Web/login.jsp

super/super


3.使用说明
3.0 可以根据主体项目的jar包,选择springmvc或struts,ibatis或hibernate.修改pom.xml生成不同的依赖jar包

web.xml修改:
<!-- struts访问配置-集成SpringSecurity-Action -->
	<!-- <filter>
		<filter-name>struts2-cleanup</filter-name>
		<filter-class>org.apache.struts2.dispatcher.ActionContextCleanUp</filter-class>
	</filter>
	<filter>
	      <filter-name>struts2Filter</filter-name>
	      <filter-class>org.apache.struts2.dispatcher.ng.filter.StrutsPrepareAndExecuteFilter</filter-class>
	</filter>
    
    <filter-mapping>
        <filter-name>struts2-cleanup</filter-name>
        <url-pattern>*.do</url-pattern>
    </filter-mapping>		
    <filter-mapping>
        <filter-name>struts2Filter</filter-name>
        <url-pattern>*.do</url-pattern>
    </filter-mapping>    
    <filter-mapping>
	<filter-name>struts2Filter</filter-name>
	    <url-pattern>*.jsp</url-pattern>
	    <dispatcher>REQUEST</dispatcher>
	    <dispatcher>FORWARD</dispatcher> 
	 </filter-mapping>
	 <filter-mapping>
		 <filter-name>struts2Filter</filter-name>
	     <url-pattern>*.action</url-pattern>
	     <dispatcher>REQUEST</dispatcher>
	     <dispatcher>FORWARD</dispatcher> 
	 </filter-mapping> -->
	 
	 <!-- springmvc配置-集成SpringSecurity-Controller -->
	 <servlet>
        <servlet-name>springmvc</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <load-on-startup>1</load-on-startup>
        <init-param>
            <param-name>contextConfigLocation</param-name>
            <param-value>classpath*:spring/applicationContext-servlet.xml</param-value>
        </init-param>
    </servlet>

    <servlet-mapping>
        <servlet-name>springmvc</servlet-name>
        <url-pattern>/</url-pattern>
    </servlet-mapping>

3.1 登陆需集成SpringSecurity-Common的登陆方式

对于用户表等的字段,根据需要修改。可修改登陆页面样式,设置首页等

3.2 在页面上集成权限配置

在实际业务的页面上,加上标签控制权限:

<%@ taglib uri="/ss" prefix="ss"%>
...
<ss:permission permission="/url.do">
	...
</ss:permission>

如果登陆用户没用/url.do的权限,则看不到里面的内容(即没用权限)


