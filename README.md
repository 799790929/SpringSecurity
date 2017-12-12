# SpringSecurity
1.应用说明
SpringSecurity是maven管理的应用,采用ssh框架,包括2个子项目。
一个是SpringSecurity-Common封装了SpringSecurity的配置；
一个是SpringSecurity-Web用于集成测试SpringSecurity-Common包的,以便生成jar包后,
可以根据实际应用修改部分配置即可使用SpringSecurity-Common的jar包

SpringSecurity-Common主要有2个作用:
一是封装了SpringSecurity的配置；
二是添加了用户组角色资源的管理界面,实际应用中，只需关注业务逻辑，用户权限的管理交予SpringSecurity-Common处理

读者可根据实际情况扩展或修改jar包(SpringSecurity-Common)

sql初始化语句：
SpringSecurity-Common/sql.mysql.init
SpringSecurity-Common/sql.oracle.init

2.在本地安装完成后,启动访问
http://localhost:8080/SpringSecurity-Web/login.jsp
super/super


3.使用说明
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


