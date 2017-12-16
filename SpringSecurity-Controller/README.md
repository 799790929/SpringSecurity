# SpringSecurity-Controller
1.SpringMVC没有Struts2一样的模型驱动
一些常量不能通过类变量设置,需通过request.setAttribute设置

SpringMVC是单例执行的

2.<jsp:include page="${rightPath }"></jsp:include>

rightPath为空,会导致死循环
