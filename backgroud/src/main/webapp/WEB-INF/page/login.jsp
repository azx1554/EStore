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
<link href="${base}/css/elegant-icons-style.css" rel="stylesheet"/>
<link href="${base}/css/font-awesome.css" rel="stylesheet"/>
<!-- Custom styles -->
<link href="${base}/css/style.css" rel="stylesheet">
<link href="${base}/css/style-responsive.css" rel="stylesheet"/>
<script type="text/javascript" src="${base}/js/md/sha1.js"></script>
<script type="text/javascript">
    $(function(){
        if(window.parent.location.href!=self.location.href){
            window.parent.location=self.location;
        }

        $("#login").on("click",function(){
            if(!checkNull('username')){
                $("#username").focus().attr("placeholder","账号不能为空！");
                return ;
            }

            if(!checkNull('password')){
                $("#password").focus().attr("placeholder","密码不能为空！");
                return ;
            }

            var data = {};
            data.username = $("#username").val();
            data.password = $("#password").val();
            //data.password =hex_sha1($("#password").val());

            $.ajax({
                url : "${base}/sys/login.do",
                data:data,
                type:"post",
                dataType:"json",
                success:function(data){
                    if (data.code==10000) {
                        window.location.href="${base}/sys/main.do";
                    } else {
                        $("#login_msg").text(data.error);
                    }
                }
            });


        });
    });
</script>
</head>

<body class="login-img3-body">

<div class="container">
    <div class="login-form">
        <div class="login-wrap">
            <p class="login-img"><i class="icon_lock_alt"></i></p>
            <p style="text-align:center"><label id="login_msg"></label></p>
            <div class="input-group">
                <span class="input-group-addon"><i class="icon_profile"></i></span>
                <input type="text" id="username" class="form-control" placeholder="用户名" autofocus>
            </div>
            <div class="input-group">
                <span class="input-group-addon"><i class="icon_key_alt"></i></span>
                <input type="password" id="password" class="form-control" placeholder="密码">
            </div>
            <label class="checkbox">
                <input type="checkbox" value="remember-me"> 记住密码
                <span class="pull-right"> <a href="#"> 忘记密码?</a></span>
            </label>
            <button class="btn btn-primary btn-lg btn-block" id="login">登录</button>
            <button class="btn btn-info btn-lg btn-block" >注册</button>
        </div>
    </div>
</div>
</body>
</html>
