



function validateTarget(){
    let target = document.getElementById("target").value;
    if (target !== '') {
        $("#target").css("border-color", "#141414");
        $("#target-info").slideUp();
        return true;
    }
    else {
        $("#target").css("border-color", "#e5383b");
        $("#target-info").slideDown();
        return false;
    }
}
$("#target-info-icon").click( function() {
    $("#target-info").slideToggle();
});

function validateStyle(){
    let style = document.getElementById("style").value;
    if (style !== '') {
        $("#style").css("border-color", "#141414");
        $("#style-info").slideUp();
        return true;
    }
    else {
        $("#style").css("border-color", "#e5383b");
        $("#style-info").slideDown();
        return false;
    }
}
$("#style-info-icon").click( function() {
    $("#style-info").slideToggle();
});


function validateGoals(){
    let goals = document.getElementById("goals").value;
    if (goals !== '') {
        $("#goals").css("border-color", "#141414");
        $("#goals-info").slideUp();
        return true;
    }
    else {
        $("#goals").css("border-color", "#e5383b");
        $("#goals-info").slideDown();
        return false;
    }
}
$("#goals-info-icon").click( function() {
    $("#goals-info").slideToggle();
});


function validateNote(){
    let notes = document.getElementById("notes").value;
    if (notes !== '') {
        $("#notes").css("border-color", "#141414");
        $("#notes-info").slideUp();
        return true;
    }
    else {
        $("#notes").css("border-color", "#e5383b");
        $("#notes-info").slideDown();
        return false;
    }
}
$("#notes-info-icon").click( function() {
    $("#notes-info").slideToggle();
});


function validateBriefing(){
    if (validateTarget() & validateStyle() &
        validateGoals() & validateNote()){
        return true;
    }
}