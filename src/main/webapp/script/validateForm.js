const name_regex = /^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$/;
const nation_regex = /^[a-zA-ZÀ-ÿ\\s]+$/;
const password_regex = /^(?=.*[A-Z])(?=.*\\d)(?=.*[^\\w\\s]).+$/;
const date_regex = /^\d{4}\-(0?[1-9]|1[012])\-(0?[1-9]|[12][0-9]|3[01])$/;
const email_regex = /^[a-zA-Z\\d._%-]+@[a-zA-Z\\d.-]+\\.[a-zA-Z]{2,20}$/;
const phone_regex = /^\d{10}$/;

function validateName(){
    let name = document.getElementById("firstName").value;
    console.log(name);
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
    console.log(secondName);
    if (name_regex.test(secondName)) {
        $("#lastName").css("border-color", "#141414");
        return true;
    }
    else {
        $("#lastName").css("border-color", "#e5383b");
        return false;
    }
}

function validateNation(){
    let nation = document.getElementById("nation").value;
    console.log(nation);
    if (nation_regex.test(nation)) {
        $("#nation").css("border-color", "#141414");
        return true;
    }
    else {
        $("#nation").css("border-color", "#e5383b");
        return false;
    }
}

function validateBirthDate(){
    let birthDate = document.getElementById("birthDate").value;
    console.log(birthDate);
    if (date_regex.test(birthDate)) {
        $("#birthDate").css("border-color", "#141414");
        return true;
    }
    else {
        $("#birthDate").css("border-color", "#e5383b");
        return false;
    }
}
function validatePhoneNumber(){
    let phone = document.getElementById("phoneNumber").value;
    console.log(phone);
    if (phone_regex.test(phone)) {
        $("#phoneNumber").css("border-color", "#141414");
        return true;
    }
    else {
        $("#phoneNumber").css("border-color", "#e5383b");
        return false;
    }
}


function validatePersonalInf(){
    if (validateName() && validateSecondName() && validateNation()
        && validatePhoneNumber() && validateBirthDate()){
        //document.getElementById("registration-form").submit();
    }
}