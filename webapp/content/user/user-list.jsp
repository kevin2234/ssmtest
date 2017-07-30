<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>user-list</title>
</head>
<body>
    <h2>user-list.jsp</h2>
    <shiro:authenticated>
        谢谢，您已经登录了！
    </shiro:authenticated>
    <br/>
    Hello, <shiro:principal/>, how are you today?
    <br/>
    <shiro:hasPermission name="order:query">当前用户有查询订单权限</shiro:hasPermission>
    <shiro:lacksPermission name="order:add">当前用户没有下单权限</shiro:lacksPermission>
</body>
</html>
