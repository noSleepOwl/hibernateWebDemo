$(function () {
    $("#submitButton").click(() => {
        $("#studentForm").ajaxSubmit(function (res) {
            alert(res);
        })
    });
});