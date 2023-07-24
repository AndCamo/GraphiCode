<%@ page import="model.ProductBean" %><%--
  Created by IntelliJ IDEA.
  User: andrea
  Date: 09/07/23
  Time: 01:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title id="page-title">Prodotto</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/main-style.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/style-menu-show.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/catalog-style.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/show-product-style.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/dashboard-style.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/form-style.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/script/navbar-script.js" defer></script>
    <script src="https://kit.fontawesome.com/2f266e86a2.js" crossorigin="anonymous"></script>
    <script src="${pageContext.request.contextPath}/script/validateBriefing.js" defer></script>

    <script>
        $("#palette1").change(function(){
            $("primary-color-label").append()
        });
    </script>
</head>
<body>
<%@ include file="/navbar.jsp"%>
<div class="main-container">
    <div class="title-container">
        <h1 class="title">${requestScope.product.getName()}</h1>
    </div>
    <div id="catalog-container">
        <div class="product-image-container">
            <img src="${requestScope.product.getImage()}" alt="Immagine">
        </div>
        <div class="product-details-container">
            <h4>${requestScope.product.getName()}</h4>
            <p class="product-details-price">${requestScope.product.getDiscountedPrice()}€</p>
            <p class="product-details-description" align="justify">${requestScope.product.getDescription()}</p>
        </div>

        <div class="product-briefing-container">
            <hr style="display: block;">
            <form id="briefing-form" method="post" action="add-to-cart">
                <input type="hidden" id="productId" name="productId" value="${requestScope.product.getCode()}">
        <%
            ProductBean product = (ProductBean) request.getAttribute("product");
            if (product != null && product.isPersonalized()) {
        %>
                    <p id="alert">Prima di aggiungere il prodotto al carrello, fornisci le <span style="color: #5761F1">indicazioni per la sua realizzazione </span> compilando il form di seguito!</p>
                        <div class="column">
                            <div class="label-container">
                                <label for="target" class="label-field">Target:</label>
                                <i class="fa-sharp fa-solid fa-circle-info icon" id="target-info-icon"></i>
                            </div>
                            <textarea id="target" name="target" placeholder="Target" required></textarea>
                                <div id="target-info" class="form-info">
                                    <ul>
                                        <li>Chi è il pubblico di destinazione per questa grafica?</li>
                                        <li>Quali sono le loro età, interessi e valori?</li>
                                    </ul>
                                </div>

                            <div class="label-container">
                                <label for="style" class="label-field">Stile:</label><br>
                                <i class="fa-sharp fa-solid fa-circle-info icon" id="style-info-icon"></i>
                            </div>
                            <textarea id="style" name="style" placeholder="Stile" required></textarea>
                            <p id="style-info" class="form-info">Quale stile grafico deve avere la realizzazione? (es: Classico, Moderno, Retrò, etc.)</p>
                            <div class="label-container">
                                <label for="goals" class="label-field">Obiettivi:</label><br>
                                <i class="fa-sharp fa-solid fa-circle-info icon" id="goals-info-icon"></i>
                            </div>
                            <textarea id="goals" name="goals" required placeholder="Obiettivi"></textarea>
                            <p id="goals-info" class="form-info">Descrivi brevemente gli obiettivi richiesti. (es: "Locandina per torneo di calcetto")</p>
                        </div>
                        <div class="column" style="float: right">
                            <div class="label-container">
                                <label id="primary-color-label" for="palette1" class="label-field">Colore Primario:</label>
                                <i class="fa-sharp fa-solid fa-circle-info icon" id="primary-color-icon"></i>
                            </div>
                            <input type="color" id="palette1" name="palette1" required>
                            <div class="label-container">
                                <label for="palette2" class="label-field">Colore Secondario:</label>
                                <i class="fa-sharp fa-solid fa-circle-info icon" id="secondary-color-icon"></i>
                            </div>
                            <input type="color" id="palette2" name="palette2" value="#FF0000" required>
                            <div class="label-container">
                                <label for="notes" class="label-field">Note:</label><br>
                                <i class="fa-sharp fa-solid fa-circle-info icon" id="notes-info-icon"></i>
                            </div>
                            <textarea id="notes" name="notes" required placeholder="Note"></textarea>
                            <p id="notes-info" class="form-info">Specifica qui tutte le informazioni che la grafica dovrà contenere e qualsiasi altra richiesta.<br> (Nome evento / descrizione / data e ora / slogan / etc.)</p>
                        </div>
                        <div class="label-container">
                            <label for="quantity" class="label-field">Numero proposte:</label><br>
                            <i class="fa-sharp fa-solid fa-circle-info icon" id="quantity-info-icon"></i>
                        </div>
                        <select id="quantity" name="quantity">
                            <option value="1" selected>1</option>
                            <option value="2">2</option>
                            <option value="3">3</option>
                        </select>
                        <p id="quantity-info" class="form-info">Il numero di proposte corrisponde alla quantità di grafiche diverse (basate sullo stesso briefing) che si desidera ricevere.</p>
                        <div class="button-container">
                            <button class="button-35" onclick="validateBriefing()">Aggiungi al carello <i class="fa-solid fa-cart-plus"></i></button>
                            <button class="button-35" onclick="location.href = 'show-catalog?filter=all'">Torna al catalogo <i class="fa-solid fa-backward"></i></button>
                        </div>
            <%} else {%>
                <input type="hidden" id="quantity" name="quantity" value="1">
                <p id="alert">Il prodotto è un <span style="color: #5761F1">contenuto digitale</span>. Dopo l'acquisto arriverà una mail con il link per il download</p>
                <div class="button-container">
                    <button class="button-35" onclick="document.getElementById('briefing-form').submit();">Aggiungi al carello <i class="fa-solid fa-cart-plus"></i></button>
                    <button class="button-35" onclick="location.href = 'show-catalog?filter=all'">Torna al catalogo <i class="fa-solid fa-backward"></i></button>
                </div>
            <%}%>
            </form>
        </div>
    </div>
</div>
</body>
</html>
