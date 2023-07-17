<%--
  Created by IntelliJ IDEA.
  User: andrea
  Date: 09/07/23
  Time: 01:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title id="page-title">Prodotto</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/main-style.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/style-menu-show.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/catalog-style.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/dashboard-style.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/form-style.css">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/show-page-style.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/script/navbar-script.js" defer></script>
    <script src="https://kit.fontawesome.com/2f266e86a2.js" crossorigin="anonymous"></script>
    <script src="${pageContext.request.contextPath}/script/validateUserForm.js" defer></script>
</head>
<body>
<%@ include file="/navbar.jsp"%>
<div class="main-container">
    <div class="title-container">
        <h1 class="title">Mostra Prodotto</h1>
    </div>
    <div class="main-panel">
        <p class="info-text"><span class="info-header">Codice:</span> ${requestScope.product.getCode()}</p>
    </div>
</div>
</body>
</html>
