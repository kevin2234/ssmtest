<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>user-list</title>
</head>
<body>
    <h2>user-list.jsp</h2>
    <shiro:guest>您当前是游客，<a href="javascript:void(0);" class="dropdown-toggle qqlogin" >登录</a><br/></shiro:guest>
    <shiro:user>（已经登录，或者记住我登录）您好，<shiro:principal property="uname"/>，<a href="/auth/ldo-logout">退出</a><br/></shiro:user>
    <shiro:authenticated>（已经认证，排除记住我登录的）您好，<shiro:principal property="uname"/>，<a href="/auth/do-logout">退出</a><br/></shiro:authenticated>
    <shiro:authenticated>用户<shiro:principal property="uname"/>已身份验证通过 <br/></shiro:authenticated>
    <shiro:notAuthenticated>当前身份未认证（包括记住我登录的）<br/></shiro:notAuthenticated>
    <br/>
    你好，<shiro:principal property="uname"/>
    <br/>
    <shiro:hasPermission name="order:query">当前用户有查询订单权限<br/></shiro:hasPermission>
    <shiro:lacksPermission name="order:add">当前用户没有下单权限<br/></shiro:lacksPermission>

    <shiro:hasPermission name="User:add">有增加用户权限<br/></shiro:hasPermission>
    <shiro:hasPermission name="User">有用户管理权限<br/></shiro:hasPermission>
    <shiro:hasPermission name="Statistics">有统计报表权限<br/></shiro:hasPermission>
</body>
</html>
