<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册</title>
</head>
<body>
    <form action="/auth/do-register">
        <p>用户注册</p>
        姓名：<input type="text" name="uname"/>
        用户名：<input type="text" name="username"/>
        密码：<input type="password" name="password"/>
        <input type="submit" value="提交"/>
    </form>
</body>
</html>
