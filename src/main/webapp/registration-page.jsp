<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Registration</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link type="text/css" rel="stylesheet" href="style/form-style.css">
    <link type="text/css" rel="stylesheet" href="style/main-style.css">
    <link rel="stylesheet" type="text/css" href="./style/style-menu-show.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
    <script src="script/navbar-script.js" defer></script>
    <script src="${pageContext.request.contextPath}/script/validateForm.js" defer></script>
</head>
<body>
<%@ include file="./navbar.jsp"%>
<div class="main-container">
    <div class="form-container">
        <form method="post" id="#registration-form"  action="add-user" class="registration-form">
            <label for="firstName">Nome:</label><br>
            <input type="text" id="firstName" name="firstName" required>
            <p id="name-info" class="form-info">Il nome può contenere solo lettere!</p>
            <label for="lastName">Cognome:</label><br>
            <input type="text" id="lastName" name="lastName" required>
            <label for="birthDate">Data di Nascita:</label><br>
            <input type="date" id="birthDate" name="birthDate" required>
            <label for="nation">Nazione:</label><br>
            <select id="nation" name="nation">
                <option>Italia</option>
                <option>Francia</option>
            </select>
            <label for="phoneNumber">Numero di telefono:</label><br>
            <input type="number" id="phoneNumber" name="phoneNumber" required>
            <label for="email">eMail:</label><br>
            <input type="email" id="email" name="email" required>
            <label for="password">Password</label><br>
            <input type="password" id="password" name="password" required>
        </form>
        <div class="button-container">
            <button class="button-35" onclick="validatePersonalInf()">Submit</button>
            <button class="button-35">Cancel</button>
        </div>
    </div>

</div>
</body>
</html>