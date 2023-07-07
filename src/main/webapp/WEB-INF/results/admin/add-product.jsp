<%--
  Created by IntelliJ IDEA.
  User: andrea
  Date: 07/07/23
  Time: 15:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Admin - Dashboard</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/main-style.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/style-menu-show.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/dashboard-style.css">
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/form-style.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
        <script src="${pageContext.request.contextPath}/script/navbar-script.js" defer></script>
        <script src="https://kit.fontawesome.com/2f266e86a2.js" crossorigin="anonymous"></script>
        <script src="${pageContext.request.contextPath}/script/validateForm.js" defer></script>
    </head>
<body>
<%@ include file="/navbar.jsp"%>
    <div class="main-container">
        <div class="form-container">
            <div class="upper-container">
                <h3 class="form-title">Nuovo Prodotto</h3>
                <p class="form-subtitle">Inserisci i dettagli del prodotto.</p>
            </div>
            <form method="post" id="add-product-form"  action="add-product" class="registration-form">
                <div class="label-container">
                    <label for="productCode" class="label-field">Codice Prodotto:</label>
                    <i class="fa-sharp fa-solid fa-circle-info icon" id="password-info-icon"></i>
                </div>
                <input type="text" id="productCode" name="productCode" required>

                <label for="productName">Nome Prodotto:</label><br>
                <input type="text" id="productName" name="productName" required>

                <label for="productPrice">Prezzo:</label><br>
                <input type="number" id="productPrice" name="productPrice" required>

                <label for="productSale">Sconto iniziale:</label><br>
                <input type="number" id="productSale" name="productSale" required>

                <label for="category">Categoria:</label><br>
                <input type="text" id="category" name="category" required>

                <label for="description">Descrizione Prodotto:</label><br>
                <textarea style="resize: vertical;padding: 10px"  id="description" name="description" required></textarea>

                <label for="image">Immagine:</label><br>
                <input type="text" id="image" name="image" required>

                <div class="button-container">
                    <button class="button-35" type="button" onclick="document.getElementById('add-product-form').submit()">Aggiungi</button>
                </div>
            </form>
        </div>
    </div>
</body>
</html>
