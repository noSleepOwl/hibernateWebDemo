$(function () {

    findAllStudents();
    $("#submitButton").click(() => {
        $("#studentForm").ajaxSubmit(function (res) {
            alert("保存成功~!");
            findAllStudents()
        })
    });

    function findAllStudents() {
        $.post(`${webctx}sgw/getAllStudent`, function (data) {
            if (data) {
                let html = createStudentList(data).join("");
                $('#studentListContent').html(html);
                $('#studentListContent [data-student]').each(function () {
                    let self = $(this);
                    self.click(() => {
                        $.post(`${webctx}sgw/deleteStudent`, {studentId: self.attr('data-student')});
                        self.parent().remove();
                    });
                })
            }
        }, 'json');
    }

    function createStudentList(students) {
        let ele = [];
        students.forEach(student => {
            let info = student['userInfo'];
            let name = info['name'];
            let id = student['id'];
            let listEle = `<li class="list-group-item">${name} 
                            <button class="btn btn-sm btn-danger pull-right" data-student="${id}">删除</button>
                        </li>`;
            ele.push(listEle);
        });
        return ele;
    }


});