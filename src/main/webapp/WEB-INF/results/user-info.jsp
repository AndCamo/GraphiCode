<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Profilo di ${requestScope.userInfo.getName()}</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/main-style.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/style-menu-show.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/dashboard-style.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/info-page-style.css">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/show-page-style.css">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/form-style.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/script/navbar-script.js" defer></script>
    <script src="https://kit.fontawesome.com/2f266e86a2.js" crossorigin="anonymous"></script>
    <script src="${pageContext.request.contextPath}/script/validateUserForm.js" defer></script>
</head>
<body>
<%@ include file="/navbar.jsp"%>
<div class="main-container">
    <div class="title-container">
        <h1 class="title">Info Utente</h1>
    </div>
    <div id="info-container">
        <div class="text-container">
            <p class="info-text"><span class="info-header">ID:</span> ${requestScope.userInfo.getId()}</p>
            <p class="info-text"><span class="info-header">Nome:</span> ${requestScope.userInfo.getName()}</p>
            <p class="info-text"><span class="info-header">Cognome:</span> ${requestScope.userInfo.getSurname()}</p>
            <p class="info-text"><span class="info-header">E-Mail:</span> ${requestScope.userInfo.geteMail()}</p>
            <p class="info-text"><span class="info-header">Telefono:</span> ${requestScope.userInfo.getPhoneNumber()}</p>
            <p class="info-text"><span class="info-header">Nazione:</span> ${requestScope.userInfo.getNation()}</p>
            <p class="info-text"><span class="info-header">Data di Nascita:</span> ${requestScope.userInfo.getBirthDate()}</p>
            <p class="info-text"><span class="info-header">Admin:</span> ${requestScope.userInfo.isAdmin()}</p>
        </div>
        <div class="button-container">
            <button class="button-35" type="button" onclick="location.href='${pageContext.request.contextPath}/get-users'"><i class="fa-sharp fa-solid fa-backward" style="color: #1c1c1c;"></i> Indietro</button>
        </div>
    </div>
</div>
</body>
</html>



