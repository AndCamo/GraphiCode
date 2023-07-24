<%@ page import="java.util.List" %>
<%@ page import="model.ProductBean" %>
<%@ page import="model.OrderBean" %>
<%@ page import="org.json.JSONArray" %>
<%@ page import="org.json.JSONObject" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Ordini</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/main-style.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/style-menu-show.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/form-style.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/dashboard-style.css">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/show-page-style.css">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/cart-style.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/script/navbar-script.js" defer></script>
    <script src="https://kit.fontawesome.com/2f266e86a2.js" crossorigin="anonymous"></script>
    <script src="${pageContext.request.contextPath}/script/validateUserForm.js" defer></script>
</head>
<body>
<%@ include file="/navbar.jsp"%>
<div class="main-container">
    <div class="title-container" style="display: block">
        <h1 class="title">Gestione Ordini</h1>
        <p class="subtitle">Visualizza o e gestisci gli ordini nel sito.</p>
    </div>
    <div class="list-container">
        <%
            @SuppressWarnings("unchecked")
            JSONArray orderList = (JSONArray) request.getAttribute("orderList");
            if (orderList != null && !orderList.isEmpty()){ %>
        <div class="list-header">
            <div class="list-col">CODICE<br>ORDINE</div>
            <div class="list-col">CODICE<br>CLIENTE</div>
            <div class="list-col">NOME</div>
            <div class="list-col">COGNOME</div>
            <div class="list-col"></div>
        </div>
        <%
            for (int i = 0; i < orderList.length(); i++){
                JSONObject tmpOrder = orderList.getJSONObject(i);
        %>
        <div class="list-item">
            <div class="list-row">
                <div class="list-col" id="col1"><span class="col-label">Numero ordine: </span> <span class="col-text"><%= tmpOrder.getInt("orderId")%></span></div>
                <div class="list-col" id="col2"><span class="col-label">Codice cliente: </span> <span class="col-text"><%= tmpOrder.getInt("userId")%></span></div>
                <div class="list-col" id="col3"><span class="col-label">Nome: </span> <span class="col-text"><%= tmpOrder.getString("userName")%></span></div>
                <div class="list-col" id="col4"><span class="col-label">Cognome: </span> <span class="col-text"><%= tmpOrder.getString("userSurname")%></span></div>
                <div class="list-col list-button-container">
                    <div class="list-col list-button"><a href="order-info?orderNumber=<%=tmpOrder.getInt("orderId")%>">Visualizza</a></div>
                </div>
            </div>
        </div>
        <%}}else {%>
        <div class="title-container" style="margin: 0">
            <h1 class="title">Nessun Ordine Presente</h1>
        </div>
        <%}%>

    </div>
</div>
</body>
</html>
