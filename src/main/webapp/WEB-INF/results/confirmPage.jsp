<%--
  Created by IntelliJ IDEA.
  User: andrea
  Date: 04/07/23
  Time: 00:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
  <head>
    <title>Confirm Page</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/main-style.css">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/form-style.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/style-menu-show.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/script/navbar-script.js" defer></script>
  </head>
  <body>
  <%@ include file="/navbar.jsp"%>
    <script>
      let count = 11;
      function updateCount() {
        count = count - 1;
        document.getElementById("timer").innerHTML = count+"";
        setTimeout(updateCount, 1000);
      }
      //setTimeout("window.location.href='<%=(String)request.getAttribute("redirect")%>'", 10000)
    </script>

    <div class="main-container">
      <h1 class="form-title">Benvenuto, ${requestScope.newUser.getName()}!</h1>
      <h2><%= (String) request.getAttribute("msg") %></h2>
      <p><a href="<%= (String) request.getAttribute("redirect") %>" class="error">Procedi</a> <span id="timer"></span></p>
    </div>
  </body>
</html>
