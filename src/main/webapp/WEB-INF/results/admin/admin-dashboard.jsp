<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Admin - Dashboard</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/main-style.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/style-menu-show.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/dashboard-style.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/script/navbar-script.js" defer></script>
</head>
<body>
<%@ include file="/navbar.jsp"%>
<div class="main-container">
    <div class="title-container">
        <h1 class="title">Dashboard</h1>
    </div>
    <div class="action-container">
        <div class="action-card" id="add-product-card" onclick="location.href='${pageContext.request.contextPath}/check-in?type=add-product'">
            <img class="image-card" src="${pageContext.request.contextPath}/assets/user.png">
            <p class="text-card">Gestione Utenti</p>
        </div>
        <div class="action-card">
            <img class="image-card" src="${pageContext.request.contextPath}/assets/box.png">
            <p class="text-card">Gestione Prodotti</p>
        </div>
        <div class="action-card">
            <img class="image-card" src="${pageContext.request.contextPath}/assets/purchase-order.png">
            <p class="text-card">Gestione Ordini</p>
        </div>

    </div>
</div>
</body>
</html>