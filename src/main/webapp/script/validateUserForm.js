const name_regex = /^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$/;
const nation_regex = /^[a-zA-ZÀ-ÿ ]*$/;
const password_regex = /^(?=.*[A-Z])(?=.*\d)(?=.*[^\w\s]).+$/;
const date_regex = /^\d{4}\-(0?[1-9]|1[012])\-(0?[1-9]|[12][0-9]|3[01])$/;
const email_regex = /^[a-zA-Z\d._%-]+@[a-zA-Z\d.-]+\.[a-zA-Z]{2,20}$/;
const phone_regex = /^\d{1,10}$/;


window.addEventListener("keydown", function (event){
    let url = window.location.pathname;
    let page = url.split("/").pop();
    if (event.key==="Enter"){
        if (page === "login-page.jsp" || page === "login")
            validateLogin();
        else if (page === "registration-page.jsp")
            validateRegistration();
    }
});


function validateName(){
    let name = document.getElementById("firstName").value;
    if (name_regex.test(name)) {
        $("#firstName").css("border-color", "#141414");
        $("#name-info").slideUp();
        return true;
    }
    else {
        $("#firstName").css("border-color", "#e5383b");
        $("#name-info").slideDown();
        return false;
    }
}

function validateSecondName(){
    let secondName = document.getElementById("lastName").value;
    if (name_regex.test(secondName)) {
        $("#lastName").css("border-color", "#141414");
        $("#secondName-info").slideUp();
        return true;
    }
    else {
        $("#lastName").css("border-color", "#e5383b");
        $("#secondName-info").slideDown();
        return false;
    }
}

function validateNation(){
    let nation = document.getElementById("nation").value;
    if (nation_regex.test(nation) && document.getElementById("nation").selectedIndex !== 0) {
        $("#nation").css("border-color", "#141414");
        $("#nation-info").slideUp();
        return true;
    }
    else {
        $("#nation").css("border-color", "#e5383b");
        $("#nation-info").slideDown();
        return false;
    }
}

function validateBirthDate(){
    let birthDate = document.getElementById("birthDate").value;
    if (date_regex.test(birthDate)) {
        $("#birthDate").css("border-color", "#141414");
        $("#date-info").slideUp();
        return true;
    }
    else {
        $("#birthDate").css("border-color", "#e5383b");
        $("#date-info").slideDown();
        return false;
    }
}
function validatePhoneNumber(){
    let phone = document.getElementById("phoneNumber").value;
    if (phone_regex.test(phone)) {
        $("#phoneNumber").css("border-color", "#141414");
        $("#phone-info").slideUp();
        return true;
    }
    else {
        $("#phoneNumber").css("border-color", "#e5383b");
        $("#phone-info").slideDown();
        return false;
    }
}

$("#phone-info-icon").click( function() {
    $("#phone-info").slideToggle();
});


function validateMail(){
    let email = document.getElementById("email").value;
    if (email_regex.test(email)) {
        $("#email").css("border-color", "#141414");
        $("#mail-info").slideUp();
        return true;
    }
    else {
        $("#email").css("border-color", "#e5383b");
        $("#mail-info").slideDown();
        return false;
    }
}

function validatePassword(){
    let password = document.getElementById("password").value;
    if (password_regex.test(password)) {
        $("#password").css("border-color", "#141414");
        $("#password-info").slideUp();
        return true;
    }
    else {
        $("#password").css("border-color", "#e5383b");
        $("#password-info").slideDown();
        return false;
    }
}

$("#password-info-icon").click( function() {
    $("#password-info").slideToggle();
});


function loadCountry(){
    function onTextReady(text) {
            const countryArray = JSON.parse(text);
            for (let i = 0; i < countryArray.length; i++){
                $("#nation").append("<option>" + countryArray[i].nome + "</option>")
            }
        }
    function onResponse(response) {
        return response.text();
    }
fetch('./assets/json/countries.json')
        .then(onResponse)
        .then(onTextReady);
}


function validatePersonalData(){
    return (validateName() & validateSecondName() & validateNation()
        & validatePhoneNumber() & validateBirthDate())
}

function validateLoginData(){
    return validateMail() & validatePassword();
}

function validateRegistration(){
    if (validateLoginData() & validatePersonalData()){
        document.getElementById("registration-form").submit();
    }
}

function validateUserEdits(){
    if (validateLoginData() & validatePersonalData()){
        document.getElementById("edit-form").submit();
    }
}

function validateLogin(){
    if (validateLoginData()){
        document.getElementById("login-form").submit();
    }
}