<%--
  Created by IntelliJ IDEA.
  User: Yang
  Date: 2017/10/28
  Time: 22:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<c:set var="base" value="<%=basePath%>" />
<!-- Bootstrap CSS -->
<link href="${base}/css/bootstrap.min.css" rel="stylesheet">
<!-- bootstrap theme -->
<link href="${base}/css/bootstrap-theme.css" rel="stylesheet">

<script type="text/javascript" src="${base}/js/common.js"></script>
<!--jQuery-->
<script type="text/javascript" src="${base}/js/jquery.js"></script>
<!-- HTML5 shim and Respond.js IE8 support of HTML5 -->
<!--[if lt IE 9]-->
<script type="text/javascript" src="${base}/js/html5shiv.js"></script>
<!--[endif]-->
