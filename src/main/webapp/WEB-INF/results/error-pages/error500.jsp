
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>ERROR 500</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/main-style.css">
  <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/error-page-style.css">
  <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/result-page-style.css">
  <script src="https://kit.fontawesome.com/2f266e86a2.js" crossorigin="anonymous"></script>
</head>
<body>
<div id="error-page-container">
  <div id="logo-container">
    <img  style="cursor: pointer;" onclick="location.href= 'index.jsp'" id="navbar-logo" src="./assets/main-logo.png">
  </div>
  <div id="error-title-container">
    <h1>ERROR 500 <i class="fa-solid fa-bug"></i></h1>
    <p>Oops! Qualcosa è andato storto.</p>
    <p class="button-container"><a href="${pageContext.request.contextPath}/index.jsp">TORNA ALLA HOME</a></p>
  </div>
  <div id="error-content-container">
    <p>Prova a riaggiornare la pagina altrimenti contattaci se il problema persiste.
      <br>
    andreacamoia10@gmail.com
    </p>
  </div>
</div>
</body>
</html>
