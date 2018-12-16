function clearConsole() {
    $("#content").empty();
}

$(function () {
    const label_sql = '<span class="label label-primary">sql</span>';
    const label_param = '<span class="label label-info">param</span>';
    let page_index = 0;
    var stompClient = null;
    connect();
    loadPage();

    function connect() {
        if (stompClient == null) {
            var socket = new SockJS(webctx + 'websocket?token=kl');
            stompClient = Stomp.over(socket);
            stompClient.connect({token: "kl"}, function (frame) {
                stompClient.subscribe('/topic/pullLogger', function (greeting) {
                    var body = JSON.parse(greeting.body);
                    let msg = createConnsoleInfo(body);
                    $("#content").append(msg);
                    console.log(greeting.body)
                }, {
                    token: "kltoen"
                });
            });
        }
    }

    function loadPage(next) {
        let ctx = examplePages.map(o => webctx + o);
        $.post(ctx[page_index], function (page) {
            $("#form_context").html(page);
            if (ctx.length === (page_index + 1)) {
                page_index = 0;
            } else {
                if (next) {
                    page_index++;
                } else {
                    page_index--;
                    if (page_index < 0) {
                        page_index = ctx.length - 1;
                    }
                }
            }
        }, 'html')
    }

    function createConnsoleInfo(logMessage) {
        let msg = '';
        if (logMessage.type == 1) {
            msg += label_sql;
        } else {
            msg += label_param;
        }
        msg = '<p>' + msg + logMessage.body + '</p>';
        return msg;
    }

    $("#connect").click(function () {
        $.post(webctx + "testSubmit", function (data) {
            console.log(data);
        })

    });
    $("#sendMessage").click(function () {
        stompClient.send("/app/hello", {}, JSON.stringify({'name': $("#mesgInput").val()}));
    });

    $('#prevEx').click(() => {
        loadPage(false);
    });

    $("#nextEx").click(() => {
        loadPage(true);
    });
    $("#clear_console_button").click(() => {
        clearConsole();
    })
});


