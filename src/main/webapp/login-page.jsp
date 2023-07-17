<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link type="text/css" rel="stylesheet" href="style/form-style.css">
    <link type="text/css" rel="stylesheet" href="style/main-style.css">
    <link rel="stylesheet" type="text/css" href="./style/style-menu-show.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
    <script src="https://kit.fontawesome.com/2f266e86a2.js" crossorigin="anonymous"></script>
    <script src="script/navbar-script.js" defer></script>
    <script src="${pageContext.request.contextPath}/script/validateUserForm.js" defer></script>
</head>
<body>
<%
    if (request.getSession().getAttribute("user") != null) {
        response.sendRedirect("index.jsp");
    }
%>
<%@ include file="./navbar.jsp"%>
<div class="main-container">
    <div class="form-container">
        <div class="upper-container">
            <h3 class="form-title">LOGIN</h3>
            <p class="form-subtitle">Inserisci le tue credenziali.</p>
        </div>
        <form method="post" id="login-form"  action="login" class="registration-form">
            <label for="email">eMail:</label><br>
            <input type="email" id="email" name="email" value="andreacamoia10@gmail.com" required>
            <p id="mail-info" class="form-info"> Formato eMail non valido!</p>
            <div class="label-container">
                <label for="password" class="label-field"> Password</label>
                <i class="fa-sharp fa-solid fa-circle-info icon" id="password-info-icon"></i>
            </div>
            <input type="password" id="password" name="password" value="Andcamo-10" required>
            <div id="password-info" class="form-info"> <p>La password deve contenere:</p>
                <ul>
                    <li>Minimo 8 caratteri.</li>
                    <li>Almeno una lettera maiuscola.</li>
                    <li>Almeno un numero.</li>
                    <li>Almeno un carattere speciale.</li>
                </ul>
            </div>
            <div class="button-container">
                <button class="button-35" type="button" onclick="validateLogin()">Log in</button>
            </div>
        </form>

        <%
            String result = (String) request.getAttribute("type");
            if (result != null) {
                if (result.equals("login-error")) {
        %>
                    <div class="login-info" id="login-info">
                        Credenziali Sbagliate!
                    </div>
                <%}%>
        <%}%>
        <div class="bottom-container">
            <p>Non sei registrato?<br> <a href="registration-page.jsp"> Sign up.</a></p>
        </div>
    </div>
</div>
</body>
</html>