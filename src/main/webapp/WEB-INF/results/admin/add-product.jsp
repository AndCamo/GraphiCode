<%@ page import="java.util.List" %><%--
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
        <script src="${pageContext.request.contextPath}/script/validateUserForm.js" defer></script>
        <script src="${pageContext.request.contextPath}/script/validateProductForm.js" defer></script>
    </head>

    <script>
        function imageUpload(event) {
            $(".fa-file-circle-xmark").addClass("fa-solid fa-file-circle-check").removeClass("fa-file-circle-xmark");
            let src = URL.createObjectURL(event.target.files[0]);
            $(".preview").attr("src", src);
        }

        function loadCategories(){
            $.get("get-categories", function (data, status){
                if (status === "success"){
                    let response = JSON.parse(data);
                    for (let i = 0; i < response.length; i++){
                        $("#category-list").append("<option value='"+ response[i]+"'>" + response[i] + "</option>")
                    }
                }
                else {
                    alert("Errore durante l'operazione!");
                }
            })
        }

    </script>
<body onload="loadCategories()">
<%@ include file="/navbar.jsp"%>
    <div class="main-container">
        <div class="form-container">
            <div class="upper-container">
                <h3 class="form-title">Nuovo Prodotto</h3>
                <p class="form-subtitle">Inserisci i dettagli del prodotto.</p>
            </div>
            <form method="post" id="add-product-form"  action="add-product"  enctype="multipart/form-data" class="registration-form">
                <div class="label-container">
                    <label for="productCode" class="label-field">Codice Prodotto:</label>
                    <i class="fa-sharp fa-solid fa-circle-info icon" id="code-info-icon"></i>
                </div>
                <input type="text" id="productCode" name="productCode" required>
                <p id="code-info" class="form-info">Il codice deve avere il seguente formato: <span style="font-weight: bold">123ABC</span></p>

                <label for="productName">Nome Prodotto:</label><br>
                <input type="text" id="productName" name="productName" required>
                <p id="productName-info" class="form-info">Errore nel formato del nome</p>

                <label for="productPrice">Prezzo:</label><br>
                <input type="number" id="productPrice" name="productPrice" required>
                <p id="price-info" class="form-info">Errore nel formato del prezzo</p>

                <div class="label-container">
                    <label for="productSale" class="label-field">Sconto iniziale:</label>
                    <i class="fa-sharp fa-solid fa-circle-info icon" id="sale-info-icon"></i>
                </div>
                <input type="number" id="productSale" name="productSale" required>
                <p id="sale-info" class="form-info">Lo sconto deve essere una percentuale intera.</p>

                <label for="category">Categoria:</label><br>
                <input type="text" list="category-list" id="category" name="category" required>
                <datalist id="category-list">

                </datalist>
                <p id="category-info" class="form-info">Errore nel formato della categoria</p>

                <label for="description">Descrizione Prodotto:</label><br>
                <textarea style="resize: vertical;padding: 10px"  id="description" name="description" required></textarea>
                <p id="description-info" class="form-info">Errore nel formato della descrizione</p>

                <label for="personalized">È un prodotto da personalizzare?:</label><br>
                <input type="checkbox" value="true" id="personalized" name="personalized">

                <div class="label-container">
                    <label for="image" id="imageUpload" class="label-field">Immagine</label>
                    <i class="fa-solid fa-file-circle-xmark icon"></i>
                </div>
                <input type="file" id="image" name="image" class="product" onchange="imageUpload(event)" accept="image/*" required>
                <div id="image-preview">
                    <img src="" alt="" class="preview">
                </div>


                <div class="button-container">
                    <button class="button-35" type="button" onclick="validateProductData()"><i class="fa-sharp fa-solid fa-plus"></i> Aggiungi</button>
                </div>
            </form>
        </div>
    </div>
</body>
</html>
