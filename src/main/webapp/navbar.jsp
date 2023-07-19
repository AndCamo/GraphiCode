<%@ page import="model.UserBean" %>
<div id="navbar">
    <nav>
        <input type="checkbox" id="nav" class="hidden" onchange="showMenu()"/>
        <label for="nav"><img id="navbar-menu-icon"  src="./assets/menu.png"></label>

        <div class="nav-container">
            <ul>
                <li><a href="index.jsp" style="color: #F16461; text-decoration: underline;">Home</a></li>
                <li><a href="show-catalog?filter=all">Catalogo</a></li>
                <li><a href="#">About</a></li>
                <li><a href="#">Contact</a></li>
                <%
                    UserBean profile = (UserBean) request.getSession().getAttribute("user");
                    if(profile == null) {
                %>
                    <li><a href="login-page.jsp">Log in</a><a href="registration-page.jsp">Sign up</a></li>
                <% } else {%>
                    <li><a href=""><%= profile.getName()%></a><a onclick="location.href='${pageContext.request.contextPath}/logout'">Log out</a></li>
                <%}%>
            </ul>
        </div>
    </nav>
    <img  style="cursor: pointer;" onclick="location.href= 'index.jsp'" id="navbar-logo" src="./assets/Pittogramma.png">
    <%
        if(profile != null && profile.isAdmin()){
    %>
        <img id="navbar-cart" onclick="location.href='${pageContext.request.contextPath}/check-in?type=dashboard'" src="./assets/setting.png">
    <%} else {%>
        <img id="navbar-cart" onclick="location.href='cart.jsp'" src="./assets/cart3.png">
    <%}%>
</div>
