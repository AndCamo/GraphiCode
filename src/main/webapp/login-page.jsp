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
    <script src="script/navbar-script.js" defer></script>
</head>
<body>
<%@ include file="./navbar.jsp"%>
<div class="main-container">
    <div class="form-container">
        <form method="post"  action="add-user" class="registration-form">
            <label for="email">eMail:</label><br>
            <input type="email" id="email" name="email" required><br>
            <label for="password">Password</label><br>
            <input type="password" id="password" name="password" required><br>
            <input type="submit" value="Insert">
            <input type="reset" value="Reset">
        </form>
    </div>
</div>
</body>
</html>