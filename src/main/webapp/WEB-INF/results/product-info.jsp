<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Info prodotto</title>
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
        <h1 class="title">Info Prodotto</h1>
    </div>
    <div id="info-container">
        <p class="info-text"><span class="info-header">Codice:</span> ${requestScope.product.getCode()}</p>
        <p class="info-text"><span class="info-header">Nome:</span> ${requestScope.product.getName()}</p>
        <p class="info-text"><span class="info-header">Prezzo:</span> ${requestScope.product.getPrice()}€</p>
        <p class="info-text"><span class="info-header">Sconto:</span> ${requestScope.product.getSale()}</p>
        <p class="info-text"><span class="info-header">Categoria:</span> ${requestScope.product.getCategory()}</p>
        <p class="info-text"><span class="info-header">Descrizione:</span> <span style="display: block">${requestScope.product.getDescription()}</span></p>
        <p class="info-text"><span class="info-header">Immagine Prodotto:</span></p>
        <div id="image-preview">
            <img src="${requestScope.product.getImage()}" alt="${requestScope.product.getImage()}" class="preview">
        </div>

        <div class="button-container">
            <button class="button-35" type="button" onclick="location.href='${pageContext.request.contextPath}/get-products'"><i class="fa-sharp fa-solid fa-backward" style="color: #1c1c1c;"></i>Indietro</button>
        </div>
    </div>
</div>
</body>
</html>



