<%@ page import="java.util.List" %>
<%@ page import="model.ProductBean" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Prodotti</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/main-style.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/style-menu-show.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/form-style.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/dashboard-style.css">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/show-page-style.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/script/navbar-script.js" defer></script>
    <script src="https://kit.fontawesome.com/2f266e86a2.js" crossorigin="anonymous"></script>
    <script src="${pageContext.request.contextPath}/script/validateUserForm.js" defer></script>
</head>
<body>
<%@ include file="/navbar.jsp"%>
<div class="main-container">
    <div class="title-container" style="display: block">
        <h1 class="title">Gestione Prodotti</h1>
        <p class="subtitle">Visualizza o modifica i prodotti nel catalogo.</p>
    </div>
    <div class="button-container" style="margin: 0 auto; width: 35%">
        <button class="button-35" type="button" onclick="location.href='${pageContext.request.contextPath}/check-in?type=add-product'"><i class="fa-sharp fa-solid fa-plus"></i> Nuovo Prodotto</button>
    </div>
    <div class="list-container">
        <%
            List<ProductBean> productList = (List<ProductBean>) request.getAttribute("productList");
            if (productList != null && !productList.isEmpty()){ %>
            <div class="list-header">
                <div class="list-col">CODICE</div>
                <div class="list-col">NOME</div>
                <div class="list-col">CATEGORIA</div>
                <div class="list-col"></div>
            </div>
        <%
            for (ProductBean tmpProduct : productList){%>
            <div class="list-item">
                <div class="list-row">
                    <div class="list-col"><%= tmpProduct.getCode()%></div>
                    <div class="list-col"><%= tmpProduct.getName()%></div>
                    <div class="list-col"><%= tmpProduct.getCategory()%></div>
                    <div class="list-col list-button-container">
                        <div class="list-col list-button"><a href="${pageContext.request.contextPath}/check-in?type=edit-product&product-code=<%=tmpProduct.getCode()%>">Modifica</a></div>
                        <div class="list-col list-button"><a href="product-info?productCode=<%=tmpProduct.getCode()%>">Info</a></div>
                    </div>
                </div>
            </div>
        <%}}else {%>
            <div class="title-container" style="margin: 0">
                <h1 class="title">Nessun Prodotto Presente</h1>
            </div>
        <%}%>

    </div>
</div>
</body>
</html>
