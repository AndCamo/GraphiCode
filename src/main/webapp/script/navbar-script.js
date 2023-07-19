var prevScrollpos = window.pageYOffset;
let myElement = document.getElementById("navbar");
let navbarStyle = window.getComputedStyle(myElement);
var originalHeight = navbarStyle.height;
console.log(originalHeight);

window.onscroll = function() {
    var currentScrollPos = window.pageYOffset;
    if(currentScrollPos < 110){
        document.getElementById("navbar").style.top = "0";
    }
    else
    {
        if (prevScrollpos > currentScrollPos) {
            document.getElementById("navbar").style.top = "0";
        } else {
            document.getElementById("navbar").style.top = ("-" + originalHeight);
        }
    }
    prevScrollpos = currentScrollPos;
}



function showMenu() {
    if(document.getElementById("nav").checked) {
        document.getElementsByTagName("body")[0].style.overflow = "hidden";
        $(".nav-container").fadeIn("fast");
        document.getElementById("navbar-menu-icon").src="./assets/close.png";
        $(".card-image-container").style("overflow", "hidden");
    } else {
        document.getElementsByTagName("body")[0].style.overflow = "auto";
        $(".nav-container").fadeOut("fast");
        document.getElementById("navbar-menu-icon").src="./assets/menu.png";
        $(".card-image-container").style("overflow", "visible");
    }
}