$(document).ready (function(){
    $("#success-alert").hide();
    $("#myWish").click(function showAlert() {
        $("#success-alert").alert();
        $("#success-alert").fadeTo(2000, 500).slideUp(200, function(){
            $("#success-alert").slideUp(500);
        });
    });
});


