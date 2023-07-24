<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>GraphiCode</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link type="text/css" rel="stylesheet" href="style/main-style.css">
    <link type="text/css" rel="stylesheet" href="style/homepage-style.css">
    <link rel="stylesheet" type="text/css" href="./style/style-menu-show.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
    <script src="script/navbar-script.js" defer></script>
</head>
<body>
    <%@ include file="./navbar.jsp"%>
    <div class="main-container">
        <div id="main-banner">
            <div id="banner-image">
                <img src="assets/main-logo.png" alt="Logo">
            </div>
            <div id="banner-text">
                <p>Benvenuto in GraphiCode! l'ecommerce di <span class="highlight">prodotti grafici digitali.</span><br>Wallpaper, Loghi, Locandine personalizzabili e poster: tutti concentrati nello stesso spazio.</p>
            </div>
        </div>
        <div class="dim-container">
            <div class="dim-image"><img src="assets/portfolio/Incontro_2_Final.png"></div>
            <div class="dim-text-container">
                <p class="dim-title">LOCANDINE</p>
                <p class="dim-text" align="justify">Le locandine pubblicitarie sono il mezzo perfetto per catturare l'attenzione e generare interesse intorno al vostro evento. <br>
                    GraphiCode è pronto a creare una locandina unica e coinvolgente, studiata appositamente per mettere in risalto i dettagli più importanti del vostro evento.
                    Dalle feste esclusive alle conferenze aziendali, dai concerti emozionanti alle mostre d'arte uniche, ogni tipo di evento troverà la locandina perfetta nel nostro shop.</p>
                <p class="dim-button"><a href="show-catalog?filter=poster">Scopri di più</a></p>
            </div>
        </div>
        <div class="dim-container">
            <div class="dim-text-container">
                <p class="dim-title">WALLPAPER</p>
                <p class="dim-text">Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>
                <p class="dim-button"><a href="show-catalog?filter=wallpaper">Scopri di più</a></p>
            </div>
            <div class="dim-image"><img src="assets/portfolio/home-mockup1.png"></div>
        </div>
    </div>
</body>
</html>