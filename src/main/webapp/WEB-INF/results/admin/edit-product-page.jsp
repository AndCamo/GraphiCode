<%@ page import="model.ProductBean" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Modifica Utente</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/form-style.css">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/main-style.css">
    <link rel="stylesheet" type="text/css" href="./style/style-menu-show.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
    <script src="https://kit.fontawesome.com/2f266e86a2.js" crossorigin="anonymous"></script>
    <script src="${pageContext.request.contextPath}/script/navbar-script.js" defer></script>
    <script src="${pageContext.request.contextPath}/script/validateUserForm.js" defer></script>
    <script src="${pageContext.request.contextPath}/script/validateProductForm.js" defer></script>
</head>
<body onload="loadCountry()">
<%@ include file="/navbar.jsp"%>
<div class="main-container">
    <div class="form-container">
        <div class="upper-container">
            <h3 class="form-title">MODIFICA PRODOTTO</h3>
            <p class="form-subtitle">Modifica i dati del prodotto:</p>
        </div>

        <%
            ProductBean product = (ProductBean) request.getAttribute("productToEdit");
        %>

        <form method="post" id="edit-product-form"  action="edit-product"  enctype="multipart/form-data" class="registration-form">
            <div class="label-container">
                <label for="productCode" class="label-field">Codice Prodotto:</label>
                <i class="fa-sharp fa-solid fa-circle-info icon" id="code-info-icon"></i>
            </div>
            <input type="text" id="productCode" name="productCode" placeholder="<%=product.getCode()%>" value="<%=product.getCode()%>" required>
            <p id="code-info" class="form-info">Il codice deve avere il seguente formato: <span style="font-weight: bold">123ABC</span></p>

            <label for="productName">Nome Prodotto:</label><br>
            <input type="text" id="productName" name="productName" placeholder="<%=product.getName()%>" value="<%=product.getName()%>" required>
            <p id="productName-info" class="form-info">Errore nel formato del nome</p>

            <label for="productPrice">Prezzo:</label><br>
            <input type="number" id="productPrice" name="productPrice" placeholder="<%=product.getPrice()%>" value="<%=product.getPrice()%>" required>
            <p id="price-info" class="form-info">Errore nel formato del prezzo</p>

            <div class="label-container">
                <label for="productSale" class="label-field" >Sconto iniziale:</label>
                <i class="fa-sharp fa-solid fa-circle-info icon" id="sale-info-icon"></i>
            </div>
            <input type="number" id="productSale" name="productSale"  placeholder="<%=product.getSale()%>" value="<%=product.getSale()%>" required>
            <p id="sale-info" class="form-info">Lo sconto deve essere una percentuale intera.</p>

            <label for="category">Categoria:</label><br>
            <input type="text" id="category" name="category" placeholder="<%=product.getCategory()%>" value="<%=product.getCategory()%>" required>
            <p id="category-info" class="form-info">Errore nel formato della categoria</p>

            <label for="description">Descrizione Prodotto:</label><br>
            <textarea style="resize: vertical;padding: 10px"  id="description" name="description" placeholder="<%=product.getDescription()%>" required><%=product.getDescription()%></textarea>
            <p id="description-info" class="form-info">Errore nel formato della descrizione</p>

            <div class="label-container">
                <label for="image" id="imageUpload" class="label-field">Immagine</label>
                <i class="fa-solid fa-file-circle-xmark icon"></i>
            </div>
            <input type="file" id="image" name="image" class="product" onchange="imageUpload(event)" accept="image/*" required>
            <div id="image-preview">
                <img src="<%=product.getImage()%>" alt="<%=product.getImage()%>" class="preview">
            </div>
        </form>
        <div class="button-container">
            <button class="button-35" type="button" onclick="validateProductEdits()"><i class="fa-solid fa-pen-to-square"></i> Modifica</button>
            <button class="button-35" onclick="$(':input','#edit-product-form').not(':button, :submit, :reset, :hidden').val('').prop('checked', false).prop('selected', false);">Cancella</button>
        </div>
    </div>

</div>
</body>
</html>