$(() => {
    $('#simple_login').click(() => {
        $('#userForm').ajaxSubmit(function (data) {
            console.log(data)
        });
    });
    $("#get_home_work").click(() => {
        $.post(`${webctx}filterTest/getAllHomeWork`, function (data) {
            if (data) {
                $('#home_work_content').html(createContent(data));
            }
        })
    });

    function createContent(data) {
        let lineInfo = '';
        data.forEach(ele => {
            lineInfo += `<tr><td>${ele.id}</td><td>${ele.createTime}</td><td>${ele.updateTime}</td><td>${ele.content}</td></tr>`;
        })
        return lineInfo;
    }
});