<%--
  Created by IntelliJ IDEA.
  User: Yang
  Date: 2017/10/28
  Time: 22:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="common/base.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="Creative - Bootstrap 3 Responsive Admin Template">
    <meta name="author" content="GeeksLabs">
    <meta name="keyword" content="Creative, Dashboard, Admin, Template, Theme, Bootstrap, Responsive, Retina, Minimal">

    <title>后台 | 登陆</title>

    <!--external css-->
    <!-- font icon -->
    <link href="<%=basePath%>/css/elegant-icons-style.css" rel="stylesheet"/>
    <link href="<%=basePath%>/css/font-awesome.css" rel="stylesheet"/>
    <!-- Custom styles -->
    <link href="<%=basePath%>/css/style.css" rel="stylesheet">
    <link href="<%=basePath%>/css/style-responsive.css" rel="stylesheet"/>

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 -->
    <!--[if lt IE 9]-->
    <script type="text/javascript" src="<%=basePath%>/js/html5shiv.js"></script>
    <!--[endif]-->

</head>

<body class="login-img3-body">

<div class="container">
    <form class="login-form" action="<%=basePath%>/sys/login.do" method="post">
        <div class="login-wrap">
            <p class="login-img"><i class="icon_lock_alt"></i></p>
            <div class="input-group">
                <span class="input-group-addon"><i class="icon_profile"></i></span>
                <input type="text" name="username" class="form-control" placeholder="用户名" autofocus>
            </div>
            <div class="input-group">
                <span class="input-group-addon"><i class="icon_key_alt"></i></span>
                <input type="password" name="password" class="form-control" placeholder="密码">
            </div>
            <label class="checkbox">
                <input type="checkbox" value="remember-me"> 记住密码
                <span class="pull-right"> <a href="#"> 忘记密码?</a></span>
            </label>
            <button class="btn btn-primary btn-lg btn-block" type="submit">登录</button>
            <button class="btn btn-info btn-lg btn-block" type="submit">注册</button>
        </div>
    </form>
</div>
</body>
</html>
