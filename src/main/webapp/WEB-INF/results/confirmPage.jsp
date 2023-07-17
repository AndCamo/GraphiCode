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
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/result-page-style.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/style-menu-show.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/script/navbar-script.js" defer></script>
  </head>
  <body>
  <%@ include file="/navbar.jsp"%>
    <script>
      //setTimeout("window.location.href='<%=(String)request.getAttribute("redirect")%>'", 10000)
      let timeleft = 10;
      let downloadTimer = setInterval(function(){
        if(timeleft <= 0){
          clearInterval(downloadTimer);
        } else {
          document.getElementById("timer").innerHTML = "Verrai reindirizzato automaticamente in: " + timeleft + " secondi";
        }
        timeleft -= 1;
      }, 1000);
    </script>

    <div class="main-container">
      <div class="result-container">
        <div id="result-title-containter">
          <h2 id="result-title">
            <%
              String type = (String) request.getAttribute("type");
              if(type.equals("success-insert") || type.equals("success-login")){
            %>
              Ciao, ${sessionScope.user.getName()}!
            <%} else if (type.equals("alert")){%>
              Attenzione!
            <%} else if (type.equals("success-add") || type.equals("success-edit")){%>
              Operazione confermata
            <%} else{%>
            Errore!
            <%}%>
          </h2>
        </div>
        <h3 id="result-message">${requestScope.msg}</h3>
        <div class="redirect-container">
          <ul>
            <li><p id="next" onclick="location.href='${pageContext.request.contextPath}' + '${requestScope.redirect}'">Procedi</p></li>
            <li><p id="timer">10</p></li>
          </ul>
        </div>

      </div>
    </div>
  </body>
</html>
