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
<c:set var="base" value="${pageContext.request.contextPath}" />
<!-- Bootstrap CSS -->
<link href="<%=basePath%>/css/bootstrap.min.css" rel="stylesheet">
<!-- bootstrap theme -->
<link href="<%=basePath%>/css/bootstrap-theme.css" rel="stylesheet">
