<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Admin - Dashboard</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/main-style.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/style-menu-show.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/dashboard-style.css">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/show-page-style.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/script/navbar-script.js" defer></script>
    <script src="https://kit.fontawesome.com/2f266e86a2.js" crossorigin="anonymous"></script>
    <script src="${pageContext.request.contextPath}/script/validateUserForm.js" defer></script>

    <script>
        function changeAdminStatus(button){
            let idToSend = button.id;
            const link = "set-admin?userId=" + idToSend;
            $.get(link, function (data, status){
                if (status === "success"){
                    let response = JSON.parse(data);
                    if (response.esit === "set-success"){
                        if (response.isAdmin)
                            button.style.color = "#adff2f";
                        else
                            button.style.color = "#e5383b";
                    }
                }
                else {
                    alert("Errore durante l'operazione!");
                }
            }
            )
        }
    </script>

</head>
<body>
<%@ include file="/navbar.jsp"%>
<div class="main-container">
    <div class="title-container" style="display: block">
        <h1 class="title">Gestione Utenti</h1>
        <p class="subtitle">Visualizza o modifica le informazioni degli utenti registrati.</p>
    </div>
    <div class="list-container">
        <div class="list-header">
            <div class="list-col">ID</div>
            <div class="list-col">NOME</div>
            <div class="list-col">COGNOME</div>
            <div class="list-col"></div>
        </div>
        <%
            List<UserBean> userList = (List<UserBean>) request.getAttribute("userList");
            if (userList != null && !userList.isEmpty()){
                for (UserBean tmpUser : userList){%>
                    <div class="list-item">
                        <div class="list-row">
                            <div class="list-col"><%= tmpUser.getId()%></div>
                            <div class="list-col"><%= tmpUser.getName()%></div>
                            <div class="list-col"><%= tmpUser.getSurname()%></div>
                            <div class="list-col list-button-container">
                                <%
                                    String textColor = "";
                                    if(tmpUser.isAdmin()) {
                                        textColor = "#adff2f";
                                    } else {
                                        textColor = "#e5383b";
                                    }
                                %>
                                <div class="list-col list-button"><a onclick="changeAdminStatus(this)" id="<%=tmpUser.getId()%>" style="color: <%=textColor%>">Admin</a></div>
                                <div class="list-col list-button"><a href="user-info?userId=<%=tmpUser.getId()%>">Info</a></div>
                            </div>
                        </div>
                    </div>
        <%}}else {%>
        <div class="title-container">
            <h1 class="title">Nessun Utente Registrato</h1>
        </div>
        <%}%>

    </div>
</div>
</body>
</html>
