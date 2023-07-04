<%@ page import="model.UserBean" %>
<div id="navbar">
    <nav>
        <input type="checkbox" id="nav" class="hidden" onchange="showMenu()"/>
        <label for="nav"><img id="navbar-menu-icon"  src="./assets/menu.png"></label>

        <div class="nav-container">
            <ul>
                <li><a href="index.jsp" style="color: #F16461; text-decoration: underline;">Home</a></li>
                <li><a href="#">Works</a></li>
                <li><a href="#">About</a></li>
                <li><a href="#">Contact</a></li>
                <%
                    UserBean profile = (UserBean) request.getSession().getAttribute("user");
                    if(profile == null) {
                %>
                    <li><a href="login-page.jsp">Log in</a><a href="registration-page.jsp">Sign up</a></li>
                <% } else {%>
                    <li><a href="login-page.jsp">Profilo</a><a href="registration-page.jsp">Log out</a></li>
                <%}%>
            </ul>
        </div>
    </nav>
    <img id="navbar-logo" src="./assets/Pittogramma.png">
    <img id="navbar-user" src="./assets/cart3.png">
</div>
