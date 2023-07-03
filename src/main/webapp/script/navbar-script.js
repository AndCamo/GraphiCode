var prevScrollpos = window.pageYOffset;
let myElement = document.getElementById("navbar");
    if (myElement == null)
        console.log("CAZZO");
    else
        console.log("ENNO")
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
            var heightToSet = ("-" + originalHeight);
            document.getElementById("navbar").style.top = heightToSet;
        }
    }
    prevScrollpos = currentScrollPos;
}

function blockScrolling() {
    if(document.getElementById("nav").checked) {
        document.getElementsByTagName("body")[0].style.overflow = "hidden";
        document.getElementById("navbar-menu-icon").src="./assets/close.png";
    } else {
        document.getElementsByTagName("body")[0].style.overflow = "auto";
        document.getElementById("navbar-menu-icon").src="./assets/menu.png";
    }
}