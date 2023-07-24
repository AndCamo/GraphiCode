<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>GraphiCode - Contatti</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link type="text/css" rel="stylesheet" href="style/main-style.css">
  <link type="text/css" rel="stylesheet" href="style/homepage-style.css">
  <link rel="stylesheet" type="text/css" href="./style/style-menu-show.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
  <script src="https://kit.fontawesome.com/2f266e86a2.js" crossorigin="anonymous"></script>
  <script src="script/navbar-script.js" defer></script>
</head>
<body>
<%@ include file="./navbar.jsp"%>
<div class="main-container">
  <div class="main-title-container">
    <h1>Contatti</h1>
  </div>
  <div class="main-panel">
    <p class="info-text">E-Mail: <i class="fa-solid fa-envelope"></i></p>
    <p class="content-text" style="font-weight: 900; margin-bottom: 1vh">andreacamoia10@gmail.com</p>
    <p class="content-text">Utilizza questa mail per richidere assistenza, informazioni oppure eventuali seganalizioni!</p>
    <p class="info-text">Social: <i class="fa-solid fa-square-share-nodes"></i></p>
      <ul>
        <li><p class="content-text">Twitter <i class="fa-brands fa-square-twitter"></i>: <a style="color:#1b1b1b; font-weight: 900" href="https://twitter.com/And_Camo10" target="_blank">And_Camo10</a></p></li>
        <li><p class="content-text">Instagram <i class="fa-brands fa-square-instagram"></i>: <a style="color:#1b1b1b; font-weight: 900" href="https://www.instagram.com/1am_andrea/" target="_blank">1am_andrea</a></p></li>
      </ul>
  </div>
</div>
</body>
</html>