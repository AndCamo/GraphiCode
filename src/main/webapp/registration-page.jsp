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
    <script src="https://kit.fontawesome.com/2f266e86a2.js" crossorigin="anonymous"></script>
    <script src="script/navbar-script.js" defer></script>
    <script src="${pageContext.request.contextPath}/script/validateForm.js" defer></script>
</head>
<body onload="loadCountry()">
<%@ include file="./navbar.jsp"%>
<div class="main-container">
    <div class="form-container">
        <form method="post" id="registration-form"  action="add-user" class="registration-form">
            <label for="firstName">Nome:</label>
            <input type="text" id="firstName" name="firstName" placeholder="Nome" required>
            <p id="name-info" class="form-info">Il nome può contenere solo lettere!</p>
            <label for="lastName">Cognome:</label><br>
            <input type="text" id="lastName" name="lastName" required>
            <p id="secondName-info" class="form-info">Il cognome può contenere solo lettere!</p>
            <label for="birthDate">Data di Nascita:</label><br>
            <input type="date" id="birthDate" name="birthDate" required>
            <p id="date-info" class="form-info">Errore formato data!</p>
            <label for="nation">Nazione:</label><br>
            <select id="nation" name="nation">
                <option value="" disabled selected>Seleziona nazione</option>
            </select>
            <p id="nation-info" class="form-info">Errore formato nazione!</p>
            <div class="label-container">
                <label for="phoneNumber" class="label-field">Numero di telefono:</label>
                <i class="fa-sharp fa-solid fa-circle-info icon" id="phone-info-icon"></i>
            </div>
            <input type="tel" id="phoneNumber" name="phoneNumber" required>
            <p id="phone-info" class="form-info">Il campo deve contenere 10 difre decimali!</p>
            <label for="email">eMail:</label><br>
            <input type="email" id="email" name="email" required>
            <p id="mail-info" class="form-info"> Formato eMail non valido!</p>
            <div class="label-container">
                <label for="password" class="label-field"> Password</label>
                <i class="fa-sharp fa-solid fa-circle-info icon" id="password-info-icon"></i>
            </div>
            <input type="password" id="password" name="password" required>
            <div id="password-info" class="form-info"> <p>La password deve contenere:</p>
                <ul>
                    <li>Minimo 8 caratteri.</li>
                    <li>Almeno una lettera maiuscola.</li>
                    <li>Almeno un numero.</li>
                    <li>Almeno un carattere speciale.</li>
                </ul>
            </div>
        </form>
        <div class="button-container">
            <button class="button-35" onclick="validateRegistration()">Submit</button>
            <button class="button-35">Cancel</button>
        </div>
    </div>

</div>
</body>
</html>