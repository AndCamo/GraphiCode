<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Payment</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/form-style.css">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/main-style.css">
    <link rel="stylesheet" type="text/css" href="./style/style-menu-show.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
    <script src="https://kit.fontawesome.com/2f266e86a2.js" crossorigin="anonymous"></script>
    <script src="${pageContext.request.contextPath}/script/navbar-script.js" defer></script>
    <script src="${pageContext.request.contextPath}/script/validateCreditCard.js" defer></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/prefixfree/1.0.7/prefixfree.min.js"></script>
    <script src='https://cdnjs.cloudflare.com/ajax/libs/jquery.payment/1.0.1/jquery.payment.min.js'></script>
</head>
<body onload="loadMask()">
<%@ include file="/navbar.jsp"%>
<div class="main-container">
    <div class="form-container">
        <div class="upper-container">
            <h3 class="form-title">PAGAMENTO</h3>
            <p class="form-subtitle">Inserisci i dati di pagamento.</p>
        </div>
        <form method="post" id="payment-form"  action="do-order" class="registration-form">
            <label for="cardNumber">Numero di Carta:</label>
            <input class="cc-number" id="cardNumber" name="cardNumber" type="tel" inputmode="numeric" pattern="[0-9\s]{13,19}"  maxlength="19" value="1234 1233 1231 1232" placeholder="xxxx xxxx xxxx xxxx">
            <p id="card-number-info" class="form-info">Formato numero spbagliato!</p>
            <label for="cardHolder">Titolare Carta:</label>
            <input id="cardHolder" type="text" name="cardHolder" maxlength="19" placeholder="Nome Cognome" value="Andrea Camoia">
            <p id="card-holder-info" class="form-info">Il nome può contenere solo lettere!</p>
            <label for="expireDate">Data di Scadenza:</label>
            <input class="cc-expires" maxlength="7" name="expireDate"  id="expireDate" pattern="\d*" placeholder="MM / YY" type="tel" value="06/25" />
            <p id="card-date-info" class="form-info">Formato Data spbagliato!</p>
            <label for="credit-cvc">Codice di sicurezza:</label>
            <input class="cc-cvc"  id="credit-cvc" maxlength="3" name="credit-cvc" pattern="\d*" placeholder="CVC" type="tel" value="765" />
            <p id="card-cvc-info" class="form-info">Formato CVC spbagliato!</p>
        </form>
        <div class="button-container">
            <button class="button-35" onclick="validatePayment()">Submit</button>
            <button class="button-35" onclick="document.getElementById('payment-form').reset()">Cancella</button>
        </div>
    </div>

</div>
</body>
</html>