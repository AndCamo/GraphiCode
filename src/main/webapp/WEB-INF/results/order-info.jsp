<%@ page import="java.util.List" %>
<%@ page import="model.OrderItemBean" %>
<%@ page import="model.OrderItemDAO" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Info Ordine</title>
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
        <h1 class="title">Info Ordine</h1>
    </div>
    <div id="info-container">
        <div class="text-container">
            <p class="info-text"><span class="info-header">Numero Ordine:</span> ${requestScope.order.getOrderNumber()}</p>
            <p class="info-text"><span class="info-header">Codice Cliente:</span> ${requestScope.order.getUserId()}</p>
            <p class="info-text"><span class="info-header">Cliente:</span> ${requestScope.client}</p>
            <p class="info-text"><span class="info-header">Data Ordine:</span> ${requestScope.order.getOrderDate()}</p>
            <p class="info-text" style="background-color: #e5383b; color: white"><span class="info-header">Prodotti(${requestScope.orderItems.size()}):</span></p>
            <div class="order-item-list">
                <%
                    UserBean currentUser = (UserBean) request.getSession().getAttribute("user");
                    List<OrderItemBean> orderItems = (List<OrderItemBean>) request.getAttribute("orderItems");
                    for (OrderItemBean tmpItem : orderItems) {
                %>
                    <div class="order-item">
                        <p class="info-text"><span class="info-header">Prodotto:</span> <%=tmpItem.getProductCode()%></p>
                        <p class="info-text"><span class="info-header">Quantità:</span> <%=tmpItem.getQuantity()%></p>
                        <p class="info-text"><span class="info-header">Costo:</span> <%=tmpItem.getPrice()%></p>
                        <%if (currentUser != null && currentUser.isAdmin()) {%>
                            <p class="info-text info-button"><span class="info-header"><a href="show-orderitem-info?orderItemId=<%=tmpItem.getId()%>">Info</a></span></p>
                        <%}%>
                    </div>
                <%}%>
            </div>
            <p class="info-text" style="background-color: #e5383b; color: white"><span class="info-header">TOTALE: </span> ${requestScope.order.getTotalAmount()}</p>
        </div>
        <div class="button-container">
            <button class="button-35" type="button" onclick="location.href='${pageContext.request.contextPath}/get-order'"><i class="fa-sharp fa-solid fa-backward" style="color: #1c1c1c;"></i> Indietro</button>
        </div>
    </div>
</div>
</body>
</html>



