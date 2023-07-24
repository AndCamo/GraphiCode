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
            <div class="dim-text-container">
                <p class="dim-title">Brand Identity</p>
                <p class="dim-text"> <b>Una brand identity forte è l'essenza di ogni business di successo.</b></p>
                <p class="dim-text">Assieme al servizio di logo design, lo sviluppo della brand identity vi permetterà di avere un brand che si distingue dalla massa, si fa notare e si imprime nella mente dei vostri clienti.</p>
                <p class="dim-text">Dalla scelta delle palette cromatiche, alla selezione dei font e alla definizione dello stile visivo, ogni aspetto della vostra brand identity sarà attentamente studiato per rispecchiare l'unicità della vostra attività.</p>
                <p class="dim-text">GraphiCode è consapevole che ogni business ha le proprie sfide e obiettivi, ed è per questo che adotta un approccio personalizzato per ogni progetto. Che siate un'azienda consolidata o una start-up in crescita, l’obiettivo è farvi brillare nel mercato competitivo.</p>

                <p class="dim-button"><a href="show-catalog?filter=Brand-Identity">Scopri di più</a></p>
            </div>
            <div class="dim-image"><img src="assets/portfolio/home-branding.png"></div>
        </div>
        <div class="dim-container">
            <div class="dim-image"><img src="assets/portfolio/home-locandine.png"></div>
            <div class="dim-text-container">
                <p class="dim-title">LOCANDINE</p>
                <p class="dim-text">Le locandine pubblicitarie sono il mezzo perfetto per catturare l'attenzione e generare interesse intorno al vostro evento. GraphiCode è pronto a creare una locandina unica e coinvolgente, studiata appositamente per mettere in risalto i dettagli più importanti del vostro evento.</p>
                <p class="dim-text">Dalle feste esclusive alle conferenze aziendali, dai concerti emozionanti alle mostre d'arte uniche, ogni tipo di evento troverà la locandina perfetta nel nostro shop.</p>
                <p class="dim-text">Il design accattivante, i colori vibranti e la disposizione strategica dei contenuti faranno sì che la vostra locandina si distingua dalla massa, attirando l'attenzione e contribuendo al successo del vostro evento.</p>
                <p class="dim-button"><a href="show-catalog?filter=locandine">Scopri di più</a></p>
            </div>
        </div>
    </div>
</body>
</html>