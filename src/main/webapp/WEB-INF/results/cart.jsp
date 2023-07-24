<%@ page import="model.CartBean" %>
<%@ page import="model.CartItemBean" %>
<%@ page import="java.util.List" %>
<%@ page import="model.ProductBean" %>
<%@ page import="org.json.JSONArray" %>
<%@ page import="org.json.JSONObject" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Carrello</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/main-style.css">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/cart-style.css">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/form-style.css">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/catalog-style.css">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/dashboard-style.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/style-menu-show.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/script/navbar-script.js" defer></script>
</head>
<body>
<%@ include file="/navbar.jsp"%>
<div class="main-container">
    <div class="title-container" style="display: block">
        <h1 class="title">Il Tuo Carrello</h1>
    </div>
    <div id="cart-container">
        <div id="cart-item-container">
            <%
                CartBean cart = (CartBean) request.getSession().getAttribute("cart");

                if (cart == null || (cart.getProductCount() == 0)){%>
            <p>Il carrello è vuoto!</p>
            <%} else {
                JSONArray cartItemList = (JSONArray) request.getAttribute("products");

                for (int i = 0; i < cartItemList.length(); i++) {
                    JSONObject cartItem = cartItemList.getJSONObject(i);

            %>
                    <div class="cart-item">
                        <div class="cart-item-image">
                            <img src="<%=cartItem.getString("productImage")%>" alt="image">
                        </div>
                        <div class="cart-item-info">
                            <p class="info-tile"><%=cartItem.getString("productName")%></p>
                            <p class="info-text"><b>Categoria: </b><%=cartItem.getString("category")%></p>
                            <p class="info-text"><b>Prezzo: </b><%=cartItem.getDouble("price")%></p>
                            <p class="info-text"><b>Quantità: </b><%=cartItem.getInt("quantity")%></p>
                            <p class="info-button"><a href="show-cartitem-info?cartItemId=<%=cartItem.getInt("cartItemId")%>">Info</a></p>
                            <p class="info-button"><a href="remove-cart-product?productId=<%=cartItem.getInt("cartItemId")%>">Rimuovi</a></p>
                        </div>
                    </div>
                    <hr style="width:100%;height: 2px;color: #1b1b1b;background-color: #1b1b1b;text-align:center;margin: 1vh 0">
            <%}}%>

        </div>
        <div id="cart-details-container">
            <p class="info-tile">Totale:</p>
            <%if (cart == null || (cart.getProductCount() == 0)){%>
            <p class="cart-price">0.00€</p>
            <%} else {%>
            <p class="cart-price">${requestScope.total}€</p>
                <div class="order-button-container">
                    <button class="order-button" onclick="location.href = '${pageContext.request.contextPath}/show-payment'">Procedi con l'ordine</button>
                </div>
            <%}%>

        </div>
    </div>
</div>
</body>
</html>