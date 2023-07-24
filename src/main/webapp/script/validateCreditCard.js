const number_regex = /^[0-9]{4}(?:[ ]{0,1}[0-9]{4}){3}$/
const exipire_regex = /^([ ]*0[1-9]|[ ]*1[0-2])[ ]*\/([ ]*[0-9]{2})$/
const cvc_regex = /^([0-9]{3})$/
const holder_regex = /^([a-zA-Z\xE0\xE8\xE9\xF9\xF2\xEC\x27]\s?){2,255}$/;


function loadMask() {
    // Set up formatting for Credit Card fields
    $('.cc-number').formatCardNumber();
    $('.cc-expires').formatCardExpiry();
    $('.cc-cvc').formatCardCVC();
}

function validateCardNumber(){
    let cardNumber = document.getElementById("cardNumber").value;
    if (number_regex.test(cardNumber)) {
        $("#cardNumber").css("border-color", "#141414");
        $("#card-number-info").slideUp();
        return true;
    }
    else {
        $("#cardNumber").css("border-color", "#e5383b");
        $("#card-number-info").slideDown();
        return false;
    }
}

function validateCardHolder(){
    let cardHolder = document.getElementById("cardHolder").value;
    if (holder_regex.test(cardHolder)) {
        $("#cardHolder").css("border-color", "#141414");
        $("#card-holder-info").slideUp();
        return true;
    }
    else {
        $("#cardHolder").css("border-color", "#e5383b");
        $("#card-holder-info").slideDown();
        return false;
    }
}

function validateExpireDate(){
    let date = document.getElementById("expireDate").value;
    if (exipire_regex.test(date)) {
        $("#expireDate").css("border-color", "#141414");
        $("#card-date-info").slideUp();
        return true;
    }
    else {
        $("#expireDate").css("border-color", "#e5383b");
        $("#card-date-info").slideDown();
        return false;
    }
}

function validateCardCode(){
    let cvc = document.getElementById("credit-cvc").value;
    if (cvc_regex.test(cvc)) {
        $("#credit-cvc").css("border-color", "#141414");
        $("#card-cvc-info").slideUp();
        return true;
    }
    else {
        $("#credit-cvc").css("border-color", "#e5383b");
        $("#card-cvc-info").slideDown();
        return false;
    }
}

function validatePayment(){
    if (validateCardNumber() & validateCardHolder()
        & validateExpireDate() & validateCardCode()){
        document.getElementById("payment-form").submit();
    }
}
