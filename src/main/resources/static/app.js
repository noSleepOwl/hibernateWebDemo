$(function () {
    var stompClient = null;
    connect();
    museum.nextPage();

    function getLabel_sql(date) {
        return `<span class="label label-primary">sql${date}</span>`;
    }

    function connect() {
        if (stompClient == null) {
            var socket = new SockJS(webctx + 'websocket?token=kl');
            stompClient = Stomp.over(socket);
            stompClient.connect({token: "kl"}, function (frame) {
                stompClient.subscribe('/topic/pullLogger', function (greeting) {
                    var body = JSON.parse(greeting.body);
                    let msg = createConnsoleInfo(body);
                    $("#content").append(msg);
                    // hljs.configure({useBR: true});
                    $('pre code').each(function (i, block) {
                        hljs.highlightBlock(block);
                    });
                    var scrollHeight = $('#content').prop("scrollHeight");
                    $('#content').animate({scrollTop: scrollHeight}, 0);

                }, {
                    token: "kltoen"
                });
            });
        }
    }

    function createConnsoleInfo(logMessage) {
        if (logMessage.type == 1) {
            let msg = `<code class="sql">${logMessage.body}</code>`;
            return `<p>${ getLabel_sql(logMessage.timestamp)}<pre >${msg}</pre></p>`;
        }
        return "";
    }

    function consoleStyleRest() {
        $('#hql_demo_console').attr("style", "margin: 0 1% 1% 1%;");
    }

    $('#prevEx').click(museum.prevPage);

    $("#nextEx").click(museum.nextPage);
    $("#clear_console_button").click(code_format.clearConsole);
    $("#up_console_button").click(() => {
        $('#content').animate({scrollTop: 0}, 200);
    });
    $("#down_console_button").click(() => {
        var scrollHeight = $('#content').prop("scrollHeight");
        $('#content').animate({scrollTop: scrollHeight}, 200);
    });
    $("#right_console_button").click(() => {
        consoleStyleRest();
        $('#hql_demo_console').animate({"margin-left": "59%"}, 200);
    });
    $("#left_console_button").click(() => {
        consoleStyleRest();
        $('#hql_demo_console').animate({"margin-right": "59%"}, 200);
    });
    $("#rest_console_button").click(() => {
        consoleStyleRest();
    });

});


