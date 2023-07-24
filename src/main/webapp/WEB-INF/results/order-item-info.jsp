<%@ page import="model.ProductBean" %>
<%@ page import="model.BriefingBean" %><%--
  Created by IntelliJ IDEA.
  User: andrea
  Date: 09/07/23
  Time: 01:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title id="page-title">Dettagli Ordine</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/main-style.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/style-menu-show.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/catalog-style.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/show-product-style.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/dashboard-style.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/info-page-style.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/script/navbar-script.js" defer></script>
    <script src="https://kit.fontawesome.com/2f266e86a2.js" crossorigin="anonymous"></script>
    <script src="${pageContext.request.contextPath}/script/validateBriefing.js" defer></script>

</head>
<body>
<%@ include file="/navbar.jsp"%>
<div class="main-container">
    <div class="title-container">
        <h1 class="title">Dettagli Ordine Prodotto</h1>
    </div>
    <div id="catalog-container">
        <div class="product-image-container">
            <img src="${requestScope.product.getImage()}" alt="Immagine">
        </div>
        <div class="product-details-container">
            <p class="section-title">Info Prodotto Ordinato:</p>
            <p class="info-text"><span class="info-header">Codice:</span> ${requestScope.product.getCode()}</p>
            <p class="info-text"><span class="info-header">Nome:</span> ${requestScope.product.getName()}</p>
            <p class="info-text"><span class="info-header">Prezzo di Acquisto:</span> ${requestScope.orderItem.getPrice()}€</p>
            <hr style="width:100%;height: 2px;color: #1b1b1b;background-color: #1b1b1b;text-align:center;margin: 1vh 0">
            <%
                ProductBean product = (ProductBean) request.getAttribute("product");
                if (product != null && product.isPersonalized()){%>

            <h4 class="section-title">Briefing:</h4>
            <p class="info-text"><span class="info-header">Target:</span> ${requestScope.briefing.getTarget()}</p>
            <p class="info-text"><span class="info-header">Stile:</span> ${requestScope.briefing.getStyle()}</p>
            <p class="info-text"><span class="info-header">Obiettivi:</span> <span class="info-text-long">${requestScope.briefing.getGoals()}</span></p>
            <p class="info-text"><span class="info-header">Palette colori:</span> ${requestScope.briefing.getColorPalette()}</p>
            <%
                BriefingBean briefing = (BriefingBean) request.getAttribute("briefing");
                String palette = briefing.getColorPalette();
                palette = palette.substring(1, palette.length() - 1);
                String[] colors = palette.split(",");
            %>
            <span class="color" style="background-color: <%=colors[0]%>"></span>
            <span class="color" style="background-color: <%=colors[1]%>"></span>
            <p class="info-text"><span class="info-header">Note:</span> <span class="info-text-long">${requestScope.briefing.getNote()}</span></p>
            <%}%>
        </div>
        <div class="button-info-container">
            <button class="back-info-button" onclick="history.back()">Indietro <i class="fa-solid fa-backward"></i></button>
        </div>
    </div>

</div>
</body>
</html>
