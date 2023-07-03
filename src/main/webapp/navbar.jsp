<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="./style/style-menu-show.css">
    <link rel="stylesheet" type="text/css" href="./style/main-style.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
    <script src="script/navbar-script.js" defer></script>
</head>
<body>

<div id="navbar">
    <nav>
        <input type="checkbox" id="nav" class="hidden" onchange="blockScrolling()"/>
        <label for="nav"><img id="navbar-menu-icon"  src="./assets/menu.png"></label>

        <div class="nav-container">
            <ul>
                <li><a href="index.jsp" style="color: #F16461; text-decoration: underline;">Home</a></li>
                <li><a href="#">Works</a></li>
                <li><a href="#">About</a></li>
                <li><a href="#">Contact</a></li>
                <li><a href="#">Log in</a><a href="register-page.jsp">Sign up</a></li>
            </ul>
        </div>
    </nav>
    <img id="navbar-logo" src="./assets/Pittogramma.png">
    <img id="navbar-user" src="./assets/cart3.png">
</div>



</body>
</html>
