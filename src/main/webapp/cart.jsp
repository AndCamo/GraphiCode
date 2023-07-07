<%@ page import="model.CartBean" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Carrello</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link type="text/css" rel="stylesheet" href="style/main-style.css">
    <link type="text/css" rel="stylesheet" href="style/cart-style.css">
    <link rel="stylesheet" type="text/css" href="./style/style-menu-show.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
    <script src="script/navbar-script.js" defer></script>
</head>
<body>
<%@ include file="./navbar.jsp"%>
<div class="main-container">
    <div class="cart-container">
        <h1 class="title" style="color: var(--highlight)">
            <%
                CartBean cart = (CartBean) request.getSession().getAttribute("cart");
                if (cart != null){ %>
                    Carrello di ${sessionScope.user.getName()}
                <%} else {%>
                    Nuovo Carrello
                <%}%>
        </h1>
    </div>
</div>
</body>
</html>