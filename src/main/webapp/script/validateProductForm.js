const code_regex = /^[A-Z]{3}[0-9]{3}$/;
const productName_regex = /^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$/;
const price_regex = /^[0-9]+([.,][0-9]+)?$/;
const sales_regex = /^[0-9]{1,3}$/;

function validateProductCode(){
    let productCode = document.getElementById("productCode").value;
    if (code_regex.test(productCode)) {
        $("#productCode").css("border-color", "#141414");
        $("#code-info").slideUp();
        return true;
    }
    else {
        $("#productCode").css("border-color", "#e5383b");
        $("#code-info").slideDown();
        return false;
    }
}

$("#code-info-icon").click( function() {
    $("#code-info").slideToggle();
});


function validateProductName(){
    let productName = document.getElementById("productName").value;
    if (productName !== '') {
        $("#productName").css("border-color", "#141414");
        $("#productName-info").slideUp();
        return true;
    }
    else {
        $("#productName").css("border-color", "#e5383b");
        $("#productName-info").slideDown();
        return false;
    }
}

function validatePrice(){
    let price = document.getElementById("productPrice").value;
    if (price_regex.test(price)) {
        $("#productPrice").css("border-color", "#141414");
        $("#price-info").slideUp();
        return true;
    }
    else {
        $("#productPrice").css("border-color", "#e5383b");
        $("#price-info").slideDown();
        return false;
    }
}

function validateSale(){
    let sale = document.getElementById("productSale").value;
    if (price_regex.test(sale)) {
        $("#productSale").css("border-color", "#141414");
        $("#sale-info").slideUp();
        return true;
    }
    else {
        $("#productSale").css("border-color", "#e5383b");
        $("#sale-info").slideDown();
        return false;
    }
}
$("#sale-info-icon").click( function() {
    $("#sale-info").slideToggle();
});

function validateCategory(){
    let category = document.getElementById("category").value;
    if (productName_regex.test(category)) {
        $("#category").css("border-color", "#141414");
        $("#category-info").slideUp();
        return true;
    }
    else {
        $("#category").css("border-color", "#e5383b");
        $("#category-info").slideDown();
        return false;
    }
}

function validateDescription(){
    let description = document.getElementById("description").value;
    if (description !== '') {
        $("#description").css("border-color", "#141414");
        $("#description-info").slideUp();
        return true;
    }
    else {
        $("#description").css("border-color", "#e5383b");
        $("#description-info").slideDown();
        return false;
    }
}


function validateProductData(){
    if (validateProductCode() & validateProductName() & validatePrice()
        & validateSale() & validateCategory() & validateDescription()){
        document.getElementById("add-product-form").submit();
    }
}

function validateProductEdits(){
    if (validateProductCode() & validateProductName() & validatePrice()
        & validateSale() & validateCategory() & validateDescription()){
        document.getElementById("edit-product-form").submit();
    }
}

