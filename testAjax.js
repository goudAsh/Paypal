$(document).ready(function() {
        $('#userName').blur(function(event) {
                var name = $('#userName').val();
                $.get("http://localhost:8080/TestAjaxProject/getUserServlet", {
                        userName : name
                }, function(responseText) {
                        $('#ajaxGetUserServletResponse').text(responseText);
                });
        });
});