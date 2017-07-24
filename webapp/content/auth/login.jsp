<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
</head>
<body>
    <form action="/auth/login">
        用户名：<input type="text" name="username"/>
        密码：<input type="password" name="password"/>
        <input type="submit" value="提交"/>
    </form>
</body>
</html>
