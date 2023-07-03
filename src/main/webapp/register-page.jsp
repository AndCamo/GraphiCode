<%--
  Created by IntelliJ IDEA.
  User: andrea
  Date: 03/07/23
  Time: 01:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Registration</title>
    <link type="text/css" rel="stylesheet" href="style/main-style.css">
</head>
<body>
<%@ include file="./navbar.jsp"%>
<div class="container">
    <h1 style="color: #E5383B; text-align:center;"> REGISTRAZIONE </h1>
    <div style="padding: 25px; display: flex; justify-content: center;color: white">
        <form method="post" style="font-family: monospace; font-size: 20px">
            <label for="firstName">Name:</label><br>
            <input type="text" id="firstName" name="firstName" required> <br>
            <label for="lastName">Surname:</label><br>
            <input type="text" id="lastName" name="lastName" required> <br>
            <label for="balance">Balance:</label><br>
            <input type="number" id="balance" name="balance" required> <br><br>
            <input type="submit" value="Insert">
        </form>
    </div>
</div>
</body>
</html>