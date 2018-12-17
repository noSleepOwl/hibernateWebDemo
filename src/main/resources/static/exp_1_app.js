$(function () {

    $("#submitButton").click(() => {
        $("#studentForm").ajaxSubmit(function (res) {
            loadStudent();
        })
    });

    function loadStudent() {
        $.post(webctx + "exp_1/findAll", function (data) {
            let tr = '';
            if (data) {
                data.forEach((ele) => {
                    let tb = `<tr>
                            <td>${ele['id']}</td>
                            <td>${ele['name']}</td>
                            <td>${ele['age']}</td>
                           </tr>`;
                    tr += tb;
                })
                $('#studentListTable').html(tr);
            }
        }, 'json')
    }
});